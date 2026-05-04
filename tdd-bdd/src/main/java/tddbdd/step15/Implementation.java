package tddbdd.step15;

import java.time.LocalDateTime;

enum RequesterType {
    STUDENT
}

record BookingRequest(String roomId, RequesterType requesterType, LocalDateTime startsAt, LocalDateTime endsAt) {
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

final class RoomBookingService {
    private final AccessControlGateway gateway;

    RoomBookingService(AccessControlGateway gateway) {
        this.gateway = gateway;
    }

    BookingResult book(BookingRequest request) {
        Booking booking = Booking.from(request);
        gateway.authorize(booking);
        return BookingResult.confirmed(booking);
    }
}
