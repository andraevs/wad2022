package tddbdd.step11;

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
        BookingRequest request = new BookingRequest(
                "B-201",
                LocalDateTime.of(2026, 5, 4, 10, 0),
                LocalDateTime.of(2026, 5, 4, 11, 0));

        BookingResult result = service.book(request);

        assertTrue(result.isConfirmed());
    }

    @Test
    void rejectsBookingWhenSameRoomOverlapsExistingBooking() {
        RoomBookingService service = new RoomBookingService(new FakeAccessControlGateway());
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
    void rejectsBookingBeforeAllowedHours() {
        RoomBookingService service = new RoomBookingService(new FakeAccessControlGateway());
        BookingRequest request = new BookingRequest(
                "B-201",
                LocalDateTime.of(2026, 5, 4, 7, 30),
                LocalDateTime.of(2026, 5, 4, 8, 30));

        BookingResult result = service.book(request);

        assertFalse(result.isConfirmed());
    }

    @Test
    void authorizesAccessWhenBookingIsConfirmed() {
        FakeAccessControlGateway gateway = new FakeAccessControlGateway();
        RoomBookingService service = new RoomBookingService(gateway);
        BookingRequest request = new BookingRequest(
                "B-201",
                LocalDateTime.of(2026, 5, 4, 10, 0),
                LocalDateTime.of(2026, 5, 4, 11, 0));

        BookingResult result = service.book(request);

        assertTrue(result.isConfirmed());
        assertTrue(gateway.wasAuthorizedFor(result.booking()));
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
