package application;

/**
 * Enum representing the statuses an application can have - BOOKED, APPROVED, PENDING, REJECTED
 * Each status is associated with an integer value
 */
public enum ApplicationStatus {

    /**
     * Status indicating that the application is successfully booked.
     */
    BOOKED(3),

    /**
     * Status indicating that the application is approved.
     */
    APPROVED(2),

    /**
     * Status indicating that the application is pending approval or rejection.
     */
    PENDING(1),

    /**
     * Status indicating that the application has been rejected.
     */
    REJECTED(0);

    private final int value;

    /**
     * Constructor to initialise the application status with its integer value.
     * @param value the integer value associated with the status
     */
    ApplicationStatus(int value) {
        this.value = value;
    }

    /**
     * Retrieves the integer value associated with status
     * @return the integer value representing the status
     */
    public int getValue() {
        return value;
    }

    /**
     * Converts the integer value to the corresponding application status.
     * @param value the integer value to convert to a status
     * @return the corresponding application status
     * @throws IllegalArgumentException if the value doesn't match any status
     */
    public static ApplicationStatus fromValue(int value) {
        for (ApplicationStatus status : values()) {
            if (status.getValue() == value) return status;
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
