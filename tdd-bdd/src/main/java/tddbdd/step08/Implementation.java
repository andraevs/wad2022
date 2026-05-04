package tddbdd.step08;

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
        boolean sameRoom = roomId.equals(request.roomId());
        boolean timeRangesOverlap = startsAt.isBefore(request.endsAt())
                && request.startsAt().isBefore(endsAt);
        return sameRoom && timeRangesOverlap;
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

final class RoomBookingService {
    private final RoomSchedule schedule = new RoomSchedule();
    private final BookingPolicy policy = new BookingPolicy();

    void addExistingBooking(Booking booking) {
        schedule.add(booking);
    }

    BookingResult book(BookingRequest request) {
        if (!policy.allows(request)) {
            return BookingResult.rejected();
        }
        if (!schedule.isAvailableFor(request)) {
            return BookingResult.rejected();
        }

        return BookingResult.confirmed(Booking.from(request));
    }
}
