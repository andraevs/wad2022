package tddbdd.step01;

import java.time.LocalDateTime;

record BookingRequest(String requesterId, String roomId, LocalDateTime startsAt, LocalDateTime endsAt) {
}

final class BookingResult {
    private final boolean confirmed;

    private BookingResult(boolean confirmed) {
        this.confirmed = confirmed;
    }

    static BookingResult confirmed() {
        return new BookingResult(true);
    }

    boolean isConfirmed() {
        return confirmed;
    }
}

final class RoomBookingService {
    BookingResult book(BookingRequest request) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
