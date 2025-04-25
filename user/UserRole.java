package user;

/**
 * Enum representing different user roles in the system.
 * Each role corresponds to a specific user type with a unique integer value.
 * The roles available are:
 * - Applicant: Represents the applicant
 * - HBDOfficer: Represents the HDB officer
 * - HDBManager: Represents the HDB manager
 */
public enum UserRole {
    APPLICANT(2),
    HDBOFFICER(1),
    HDBMANAGER(0);

    private final int value;

    UserRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserRole fromValue(int value) {
        for (UserRole role : values()) {
            if (role.getValue() == value) return role;
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
