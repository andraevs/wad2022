package tddbdd.step12;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class RoomBookingServiceTest {
    @Test
    void authorizesAccessForConfirmedBookingAtGatewayBoundary() {
        MockAccessControlGateway gateway = new MockAccessControlGateway();
        RoomBookingService service = new RoomBookingService(gateway);
        BookingRequest request = request("B-201", 10, 0, 11, 0);

        BookingResult result = service.book(request);

        assertTrue(result.isConfirmed());
        gateway.verifyAuthorizedOnceFor(result.booking());
    }

    @Test
    void doesNotAuthorizeAccessForRejectedBooking() {
        MockAccessControlGateway gateway = new MockAccessControlGateway();
        RoomBookingService service = new RoomBookingService(gateway);
        BookingRequest request = request("B-201", 7, 30, 8, 30);

        BookingResult result = service.book(request);

        assertFalse(result.isConfirmed());
        gateway.verifyNoAuthorization();
    }

    private static BookingRequest request(String roomId, int startHour, int startMinute, int endHour, int endMinute) {
        return new BookingRequest(
                roomId,
                LocalDateTime.of(2026, 5, 4, startHour, startMinute),
                LocalDateTime.of(2026, 5, 4, endHour, endMinute));
    }

    private static final class MockAccessControlGateway implements AccessControlGateway {
        private int authorizationCount;
        private Booking authorizedBooking;

        @Override
        public void authorize(Booking booking) {
            authorizationCount++;
            authorizedBooking = booking;
        }

        void verifyAuthorizedOnceFor(Booking booking) {
            assertEquals(1, authorizationCount);
            assertEquals(booking, authorizedBooking);
        }

        void verifyNoAuthorization() {
            assertEquals(0, authorizationCount);
        }
    }
}
