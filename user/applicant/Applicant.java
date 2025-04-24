package user.applicant;

import project.Project;
import user.User;
import enquiries.Enquiry;

import java.util.ArrayList;
import java.util.List;

public class Applicant extends User {
    private Project appliedProject = null;
    private ApplicationStatus applicationStatus = ApplicationStatus.NONE;
    private final List<Enquiry> enquiries = new ArrayList<>();

    public enum ApplicationStatus {
        NONE, PENDING, SUCCESSFUL, UNSUCCESSFUL, BOOKED
    }

    public Applicant(int uid, String name, int age, boolean maritalStatus) {
        super(uid, name, age, maritalStatus);
    }

    public Applicant(User user) {
        super(user.getUid(), user.getName(), user.getAge(), user.getMaritalStatus());
    }

    public Project getAppliedProject() {
        return appliedProject;
    }

    public void setAppliedProject(Project appliedProject) {
        this.appliedProject = appliedProject;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public List<Enquiry> getEnquiries() {
        return enquiries;
    }

    public boolean hasSuccessfulApplication() {
        return applicationStatus == ApplicationStatus.SUCCESSFUL;
    }

    public boolean hasApplied() {
        return appliedProject != null;
    }

    public boolean hasBookedFlat() {
        return applicationStatus == ApplicationStatus.BOOKED;
    }
}
