package tddbdd.step15;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class RoomBookingScenarioTest {
    @Test
    void studentBooksAFreeRoomAndReceivesAccess() {
        // Given
        RecordingAccessControlGateway gateway = new RecordingAccessControlGateway();
        RoomBookingService service = new RoomBookingService(gateway);
        BookingRequest request = new BookingRequest(
                "B-201",
                RequesterType.STUDENT,
                LocalDateTime.of(2026, 5, 4, 10, 0),
                LocalDateTime.of(2026, 5, 4, 11, 0));

        // When
        BookingResult result = service.book(request);

        // Then
        assertTrue(result.isConfirmed());
        assertTrue(gateway.wasAuthorizedFor(result.booking()));
    }

    private static final class RecordingAccessControlGateway implements AccessControlGateway {
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
