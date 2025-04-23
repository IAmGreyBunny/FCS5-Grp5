package application;

public enum ApplicationStatus {
    APPROVED(2),
    PENDING(1),
    REJECTED(0);

    private final int value;

    ApplicationStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ApplicationStatus fromValue(int value) {
        for (ApplicationStatus status : values()) {
            if (status.getValue() == value) return status;
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
