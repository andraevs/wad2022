package tddbdd.step01;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class RoomBookingServiceTest {
    @Test
    void confirmsBookingWhenRoomIsFree() {
        RoomBookingService service = new RoomBookingService();
        BookingRequest request = new BookingRequest(
                "student-17",
                "B-201",
                LocalDateTime.of(2026, 5, 4, 10, 0),
                LocalDateTime.of(2026, 5, 4, 11, 0));

        BookingResult result = service.book(request);

        assertTrue(result.isConfirmed());
    }
}
