package tddbdd.step04;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

record BookingRequest(String roomId, LocalDateTime startsAt, LocalDateTime endsAt) {
}

record Booking(String roomId, LocalDateTime startsAt, LocalDateTime endsAt) {
    static Booking from(BookingRequest request) {
        return new Booking(request.roomId(), request.startsAt(), request.endsAt());
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
    private final List<Booking> existingBookings = new ArrayList<>();

    void addExistingBooking(Booking booking) {
        existingBookings.add(booking);
    }

    BookingResult book(BookingRequest request) {
        return BookingResult.confirmed(Booking.from(request));
    }
}
