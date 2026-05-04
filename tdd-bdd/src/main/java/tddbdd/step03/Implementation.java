package tddbdd.step03;

import java.time.LocalDateTime;

record BookingRequest(String requesterId, String roomId, LocalDateTime startsAt, LocalDateTime endsAt) {
}

record Booking(String requesterId, String roomId, LocalDateTime startsAt, LocalDateTime endsAt) {
    static Booking from(BookingRequest request) {
        return new Booking(request.requesterId(), request.roomId(), request.startsAt(), request.endsAt());
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

    boolean isConfirmed() {
        return booking != null;
    }
}

final class RoomBookingService {
    BookingResult book(BookingRequest request) {
        return BookingResult.confirmed(Booking.from(request));
    }
}
