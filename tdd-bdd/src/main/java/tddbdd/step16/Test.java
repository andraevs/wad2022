package tddbdd.step16;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
        BookingRequest request = BddTestData.requestByStudent("B-201", 10, 0, 11, 0);

        // When
        BookingResult result = service.book(request);

        // Then
        assertTrue(result.isConfirmed());
        assertTrue(gateway.wasAuthorizedFor(result.booking()));
    }
}

class BookingPolicyTest {
    @Test
    void policyAllowsStudentDuringOpeningHours() {
        BookingPolicy policy = new BookingPolicy();

        assertTrue(policy.allows(BddTestData.requestByStudent("B-201", 10, 0, 11, 0)));
    }

    @Test
    void policyRejectsStudentOutsideAllowedHours() {
        BookingPolicy policy = new BookingPolicy();

        assertFalse(policy.allows(BddTestData.requestByStudent("B-201", 7, 30, 8, 30)));
    }
}

class RoomScheduleTest {
    @Test
    void scheduleRejectsOverlappingBookingForSameRoom() {
        RoomSchedule schedule = new RoomSchedule();
        schedule.reserve(new Booking(
                "B-201",
                LocalDateTime.of(2026, 5, 4, 10, 0),
                LocalDateTime.of(2026, 5, 4, 11, 0)));

        assertFalse(schedule.canReserve(BddTestData.requestByStudent("B-201", 10, 30, 11, 30)));
    }
}

final class BddTestData {
    static BookingRequest requestByStudent(
            String roomId,
            int startHour,
            int startMinute,
            int endHour,
            int endMinute) {
        return new BookingRequest(
                roomId,
                RequesterType.STUDENT,
                LocalDateTime.of(2026, 5, 4, startHour, startMinute),
                LocalDateTime.of(2026, 5, 4, endHour, endMinute));
    }
}

final class RecordingAccessControlGateway implements AccessControlGateway {
    private final List<Booking> authorizedBookings = new ArrayList<>();

    @Override
    public void authorize(Booking booking) {
        authorizedBookings.add(booking);
    }

    boolean wasAuthorizedFor(Booking booking) {
        return authorizedBookings.contains(booking);
    }
}
