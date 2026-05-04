package tddbdd.step06;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class RoomBookingServiceTest {
    @Test
    void confirmsBookingWhenRoomIsFree() {
        RoomBookingService service = new RoomBookingService();
        BookingRequest request = new BookingRequest(
                "B-201",
                LocalDateTime.of(2026, 5, 4, 10, 0),
                LocalDateTime.of(2026, 5, 4, 11, 0));

        BookingResult result = service.book(request);

        assertTrue(result.isConfirmed());
    }

    @Test
    void rejectsBookingWhenSameRoomOverlapsExistingBooking() {
        RoomBookingService service = new RoomBookingService();
        service.addExistingBooking(new Booking(
                "B-201",
                LocalDateTime.of(2026, 5, 4, 10, 0),
                LocalDateTime.of(2026, 5, 4, 11, 0)));
        BookingRequest request = new BookingRequest(
                "B-201",
                LocalDateTime.of(2026, 5, 4, 10, 30),
                LocalDateTime.of(2026, 5, 4, 11, 30));

        BookingResult result = service.book(request);

        assertFalse(result.isConfirmed());
    }

    @Test
    void confirmsBookingWhenRequestStartsAsExistingBookingEnds() {
        RoomBookingService service = new RoomBookingService();
        service.addExistingBooking(new Booking(
                "B-201",
                LocalDateTime.of(2026, 5, 4, 9, 0),
                LocalDateTime.of(2026, 5, 4, 10, 0)));
        BookingRequest request = new BookingRequest(
                "B-201",
                LocalDateTime.of(2026, 5, 4, 10, 0),
                LocalDateTime.of(2026, 5, 4, 11, 0));

        BookingResult result = service.book(request);

        assertTrue(result.isConfirmed());
    }
}
