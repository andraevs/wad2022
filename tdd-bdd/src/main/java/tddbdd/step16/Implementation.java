package tddbdd.step16;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

enum RequesterType {
    STUDENT
}

record BookingRequest(String roomId, RequesterType requesterType, LocalDateTime startsAt, LocalDateTime endsAt) {
}

record Booking(String roomId, LocalDateTime startsAt, LocalDateTime endsAt) {
    static Booking from(BookingRequest request) {
        return new Booking(request.roomId(), request.startsAt(), request.endsAt());
    }

    boolean overlaps(BookingRequest request) {
        return roomId.equals(request.roomId())
                && startsAt.isBefore(request.endsAt())
                && request.startsAt().isBefore(endsAt);
    }
}

final class BookingResult {
    private final Booking booking;

    private BookingResult(Booking booking) {
        this.booking = booking;
    }

    static BookingResult confirmed(Booking booking) {
        return new BookingResult(booking);
    }

    static BookingResult rejected() {
        return new BookingResult(null);
    }

    boolean isConfirmed() {
        return booking != null;
    }

    Booking booking() {
        return booking;
    }
}

interface AccessControlGateway {
    void authorize(Booking booking);
}

final class BookingPolicy {
    private static final LocalTime OPENING_TIME = LocalTime.of(8, 0);
    private static final LocalTime CLOSING_TIME = LocalTime.of(18, 0);

    boolean allows(BookingRequest request) {
        LocalTime start = request.startsAt().toLocalTime();
        LocalTime end = request.endsAt().toLocalTime();
        return !start.isBefore(OPENING_TIME) && !end.isAfter(CLOSING_TIME);
    }
}

final class RoomSchedule {
    private final List<Booking> bookings = new ArrayList<>();

    void reserve(Booking booking) {
        bookings.add(booking);
    }

    boolean canReserve(BookingRequest request) {
        return bookings.stream().noneMatch(booking -> booking.overlaps(request));
    }
}

final class RoomBookingService {
    private final RoomSchedule schedule = new RoomSchedule();
    private final BookingPolicy policy = new BookingPolicy();
    private final AccessControlGateway gateway;

    RoomBookingService(AccessControlGateway gateway) {
        this.gateway = gateway;
    }

    BookingResult book(BookingRequest request) {
        if (!policy.allows(request) || !schedule.canReserve(request)) {
            return BookingResult.rejected();
        }

        Booking booking = Booking.from(request);
        schedule.reserve(booking);
        gateway.authorize(booking);
        return BookingResult.confirmed(booking);
    }
}
