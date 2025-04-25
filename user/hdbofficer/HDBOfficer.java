package user.hdbofficer;

import project.Project;
import user.User;

/**
 * Represents a HDB Officer who oversees specific housing projects.
 * This class extends the User class to represent HDB Officers who assist with BTO project operations
 * An HDB Officer is assigned to a project, and their registration status can be approved or pending.
 */
public class HDBOfficer extends User {
    private int assignedProjectId = -1;  // -1 means not assigned
    private boolean registrationApproved = false;

    /**
     * Constructs a HDB Officer with the specified attributes
     * @param uid the unique identifier for the HDB Officer.
     * @param name the name of the HDB Officer.
     * @param age the age of the HDB Officer.
     * @param maritalStatus the marital status of the HDB Officer (true if married/false if single).
     */
    public HDBOfficer(int uid, String name, int age, boolean maritalStatus) {
        super(uid, name, age, maritalStatus);
    }

    /**
     * Constructs a HDB Officer by using attributes from User class
     * @param user the user from which the HDB Officer's attributes will be initialised.
     */
    public HDBOfficer(User user) {
        super(user.getUid(), user.getName(), user.getAge(), user.getMaritalStatus());
    }

    /**
     * Gets the ID of the project to which the officer is assigned.
     * @return the project ID assigned to the officer, or -1 if no project is assigned.
     */
    public int getAssignedProjectId() {
        return assignedProjectId;
    }

    /**
     * Sets the ID of the project to which the officer is assigned.
     * @param projectId the ID of the project to assign to the officer.
     */
    public void setAssignedProjectId(int projectId) {
        this.assignedProjectId = projectId;
    }

    /**
     * Checks if the officer's registration is approved.
     * @return true if the officer's registration is approved, false otherwise.
     */
    public boolean isRegistrationApproved() {
        return registrationApproved;
    }

    /**
     * Sets the registration approval status of the officer.
     * @param approved the approval status to set (true if approved, false otherwise).
     */
    public void setRegistrationApproved(boolean approved) {
        this.registrationApproved = approved;
    }

    /**
     * Checks if the officer is assigned to a project.
     * @return true if the officer is assigned to a project, false if the officer has no assigned project
     */
    public boolean hasAssignedProject() {
        return assignedProjectId != -1;
    }
}
