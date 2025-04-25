package user.hdbmanager;

import user.User;

/**
 * Represents a HDB Manager who manages all housing projects.
 * It extends the User class but has specific operations like
 * overseeing BTO housing process, project management
 */
public class HDBManager extends User {

    /**
     * Constructs a new HDB Manager with the specified details.
     * @param uid the unique identifier for the HDB Manager.
     * @param name the name of the HDB Manager.
     * @param age the age of the HDB Manager.
     * @param maritalStatus the marital status of the HDB Manager (true if married/false if single).
     */
    public HDBManager(int uid, String name, int age, boolean maritalStatus) {
        super(uid, name, age, maritalStatus);
    }

    /**
     * Constructs a new HDB Manager using an existing details
     * @param user from which the HDB Manager's attributes will be initialised.
     */
    public HDBManager(User user) {
        super(user.getUid(), user.getName(), user.getAge(), user.getMaritalStatus());
    }
}