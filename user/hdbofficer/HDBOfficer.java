package user.hdbofficer;

import project.Project;
import user.User;

public class HDBOfficer extends User {
    private int assignedProjectId = -1;  // -1 means not assigned
    private boolean registrationApproved = false;

    public HDBOfficer(int uid, String name, int age, boolean maritalStatus) {
        super(uid, name, age, maritalStatus);
    }

    public HDBOfficer(User user) {
        super(user.getUid(), user.getName(), user.getAge(), user.getMaritalStatus());
    }

    public int getAssignedProjectId() {
        return assignedProjectId;
    }

    public void setAssignedProjectId(int projectId) {
        this.assignedProjectId = projectId;
    }

    public boolean isRegistrationApproved() {
        return registrationApproved;
    }

    public void setRegistrationApproved(boolean approved) {
        this.registrationApproved = approved;
    }

    public boolean hasAssignedProject() {
        return assignedProjectId != -1;
    }
}
