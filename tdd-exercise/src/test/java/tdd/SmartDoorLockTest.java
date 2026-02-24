package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private static final int CORRECT_PIN = 1111;
    private static final int PIN_SMALLER_THAN_ALLOWED = 999;
    private static final int PIN_HIGHER_THAN_ALLOWED = 10_000;
    private static final int INITIAL_FAILED_ATTEMPTS = 0;
    public static final int WRONG_PIN = 1112;
    public static final int EXPECTED_FAILED_ATTEMPTS = 1;

    private SmartDoorLock doorLock;

    @BeforeEach
    public void beforeEach() {
        doorLock = new SmartDoorLockImpl();
    }

    @Test
    public void testLockIsInitiallyOpen() {
        assertFalse(doorLock.isLocked());
    }

    @Test
    public void testLockIsNotInitiallyBlocked() {
        assertFalse(doorLock.isBlocked());
    }

    @Test
    public void testLockNotAllowedBeforeSettingPin() {
        assertThrows(IllegalStateException.class, () -> doorLock.lock());
    }

    @Test
    public void testUnlockNotAllowedBeforeSettingPin() {
        assertThrows(IllegalStateException.class, () -> doorLock.unlock(CORRECT_PIN));
    }

    @Test
    public void testInitialFailedAttempts() {
        assertEquals(INITIAL_FAILED_ATTEMPTS, doorLock.getFailedAttempts());
    }

    @Test
    public void testSetWithPinSmallerThanAllowed() {
        assertThrows(IllegalArgumentException.class, () -> doorLock.setPin(PIN_SMALLER_THAN_ALLOWED));
    }

    @Test
    public void testSetWithPinBiggerThanAllowed() {
        assertThrows(IllegalArgumentException.class, () -> doorLock.setPin(PIN_HIGHER_THAN_ALLOWED));
    }

    private void setCorrectPinAndLock() {
        doorLock.setPin(CORRECT_PIN);
        doorLock.lock();
    }

    @Test
    public void testSetPinAndLock() {
        setCorrectPinAndLock();
        assertTrue(doorLock.isLocked());
    }

    @Test
    public void testSetPinAndUnlock() {
        setCorrectPinAndLock();
        doorLock.unlock(CORRECT_PIN);
        assertFalse(doorLock.isLocked());
    }

    @Test
    public void testLockIsStillLockedWithWrongPin() {
        setCorrectPinAndLock();
        doorLock.unlock(WRONG_PIN);
        assertTrue(doorLock.isLocked());
    }

    @Test
    public void testUnlockWhileBeingAlreadyUnlocked() {
        setCorrectPinAndLock();
        doorLock.unlock(CORRECT_PIN);
        doorLock.unlock(WRONG_PIN);
        assertFalse(doorLock.isLocked());
    }

    /* TODO: TEST FAILED ATTEMPTS, BLOCK AND RESET
    @Test
    public void testFailedAttempts() {
        doorLock.setPin(CORRECT_PIN);
        doorLock.lock();
        doorLock.unlock(WRONG_PIN);
        assertEquals(EXPECTED_FAILED_ATTEMPTS, doorLock.getFailedAttempts());
    }
    */
}
