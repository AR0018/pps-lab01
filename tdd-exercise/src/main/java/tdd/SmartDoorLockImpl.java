package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {

    private static final int UNSET_PIN = -1;
    private static final int MINIMUM_PIN = 1111;
    private static final int MAXIMUM_PIN = 9999;

    private int correctPin = UNSET_PIN;
    private boolean isLocked;

    /*
    private final int maxFailedAttempts;

    public SmartDoorLockImpl(final int maxFailedAttempts) {
        this.maxFailedAttempts = maxFailedAttempts;
    }
    */

    @Override
    public void setPin(final int pin) {
        if(pin >= MINIMUM_PIN && pin <= MAXIMUM_PIN) {
            correctPin = pin;
        } else {
            throw new IllegalArgumentException("The pin must be 4 digits long.");
        }
    }

    @Override
    public void unlock(final int pin) {
        if (isPinSet()) {
            isLocked = !(pin == correctPin) && isLocked;
        } else {
            throw new IllegalStateException("The lock cannot be unlocked when the pin is not set.");
        }
    }

    @Override
    public void lock() {
        if(isPinSet()) {
            isLocked = true;
        } else {
            throw new IllegalStateException("The lock cannot be locked when the pin is not set.");
        }
    }

    @Override
    public boolean isLocked() {
        return isLocked;
    }

    @Override
    public boolean isBlocked() {
        return false;
    }

    @Override
    public int getMaxAttempts() {
        return 0;
    }

    @Override
    public int getFailedAttempts() {
        return 0;
    }

    @Override
    public void reset() {

    }

    private boolean isPinSet() {
        return correctPin != UNSET_PIN;
    }
}
