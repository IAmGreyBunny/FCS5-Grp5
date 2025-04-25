package user;

import user.applicant.Applicant;
import user.hdbmanager.HDBManager;
import user.hdbofficer.HDBOfficer;

/**
 * A factory class that creates a typed user based on given role.
 * It's responsible for casting a base User object into specific subclasses - Applicant, HDBOfficer, HDBManager
 * based on the user role.
 */
public class UserFactory {

    /**
     * Converts base User object to typed user based on the specified user role.
     * @param user The base User object to be converted.
     * @param role The role of the user, which determines the type of the user object.
     * @return A typed user object based on the given role (either Applicant, HDBOfficer, or HDBManager).
     *         Returns `null` if the role is invalid.
     */
    public static User asTypedUser(User user, UserRole role) {
        switch (role) {
            case APPLICANT:
                return new Applicant(user);
            case HDBOFFICER:
                return new HDBOfficer(user);
            case HDBMANAGER:
                return new HDBManager(user);
            default:
                System.out.println("Error creating a typed user");
        }
        return null;
    }
}
