package user;

public class UserFactory {
    public User asTypedUser(User user, UserRole role)
    {
        switch(role)
        {
            case APPLICANT:
                // make an applicant from user
            case HDBOFFICER:
                // make a hdbofficer from user
            case HDBMANAGER:
                // make a hdbmanager from user
            default:
                System.out.println("Error creating a typed user");
        }
        return null;
    }
}
