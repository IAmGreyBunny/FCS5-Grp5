package user;

import user.applicant.Applicant;
import user.hdbofficer.HDBOfficer;

public class UserFactory {
    public User asTypedUser(User user, UserRole role)
    {
        switch(role)
        {
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
