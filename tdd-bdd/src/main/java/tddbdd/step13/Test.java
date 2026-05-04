package tddbdd.step13;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class RoomBookingServiceTest {
    @Test
    void confirmsBookingWhenRoomIsFree() {
        RoomBookingService service = new RoomBookingService(new FakeAccessControlGateway());

        BookingResult result = service.book(request("B-201", 10, 0, 11, 0));

        assertTrue(result.isConfirmed());
    }

    @Test
    void rejectsBookingWhenSameRoomOverlapsExistingBooking() {
        RoomBookingService service = new RoomBookingService(new FakeAccessControlGateway());
        service.addExistingBooking(new Booking(
                "B-201",
                LocalDateTime.of(2026, 5, 4, 10, 0),
                LocalDateTime.of(2026, 5, 4, 11, 0)));

        BookingResult result = service.book(request("B-201", 10, 30, 11, 30));

        assertFalse(result.isConfirmed());
    }

    @Test
    void rejectsBookingBeforeAllowedHours() {
        RoomBookingService service = new RoomBookingService(new FakeAccessControlGateway());

        BookingResult result = service.book(request("B-201", 7, 30, 8, 30));

        assertFalse(result.isConfirmed());
    }

    @Test
    void authorizesAccessWhenBookingIsConfirmed() {
        FakeAccessControlGateway gateway = new FakeAccessControlGateway();
        RoomBookingService service = new RoomBookingService(gateway);

        BookingResult result = service.book(request("B-201", 10, 0, 11, 0));

        assertTrue(result.isConfirmed());
        assertTrue(gateway.wasAuthorizedFor(result.booking()));
    }

    private static BookingRequest request(String roomId, int startHour, int startMinute, int endHour, int endMinute) {
        return new BookingRequest(
                roomId,
                LocalDateTime.of(2026, 5, 4, startHour, startMinute),
                LocalDateTime.of(2026, 5, 4, endHour, endMinute));
    }

    private static final class FakeAccessControlGateway implements AccessControlGateway {
        private final List<Booking> authorizedBookings = new ArrayList<>();

        @Override
        public void authorize(Booking booking) {
            authorizedBookings.add(booking);
        }

        boolean wasAuthorizedFor(Booking booking) {
            return authorizedBookings.contains(booking);
        }
    }
}
