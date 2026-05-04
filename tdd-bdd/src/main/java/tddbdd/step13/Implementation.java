package tddbdd.step13;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

record BookingRequest(String roomId, LocalDateTime startsAt, LocalDateTime endsAt) {
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

    boolean allows(BookingRequest request) {
        return !request.startsAt().toLocalTime().isBefore(OPENING_TIME);
    }
}

final class RoomSchedule {
    private final List<Booking> bookings = new ArrayList<>();

    void add(Booking booking) {
        bookings.add(booking);
    }

    boolean isAvailableFor(BookingRequest request) {
        return bookings.stream().noneMatch(booking -> booking.overlaps(request));
    }
}

final class BookingRules {
    private final RoomSchedule schedule;
    private final BookingPolicy policy;

    BookingRules(RoomSchedule schedule, BookingPolicy policy) {
        this.schedule = schedule;
        this.policy = policy;
    }

    boolean canConfirm(BookingRequest request) {
        return policy.allows(request) && schedule.isAvailableFor(request);
    }
}

final class RoomBookingService {
    private final RoomSchedule schedule;
    private final BookingRules rules;
    private final AccessControlGateway gateway;

    RoomBookingService(AccessControlGateway gateway) {
        this.schedule = new RoomSchedule();
        this.rules = new BookingRules(schedule, new BookingPolicy());
        this.gateway = gateway;
    }

    void addExistingBooking(Booking booking) {
        schedule.add(booking);
    }

    BookingResult book(BookingRequest request) {
        if (!rules.canConfirm(request)) {
            return BookingResult.rejected();
        }

        return confirm(Booking.from(request));
    }

    private BookingResult confirm(Booking booking) {
        gateway.authorize(booking);
        return BookingResult.confirmed(booking);
    }
}
