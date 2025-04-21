package user.applicant;

import user.User;

import java.util.List;

public class Applicant extends User {
    //private HashMap<BTOProject,ApplicationStatus> appliedProject;
    private List<String> enquiries;


    public Applicant(int uid, String name, int age, boolean maritalStatus) {
        super(uid, name, age, maritalStatus);
    }

    public Applicant(User user)
    {
        super(user.getUid(),user.getName(),user.getAge(),user.getMaritalStatus());
    }
//
//    public Project getAppliedProject() {
//        return appliedProject;
//    }
//
//    public void setAppliedProject(Project appliedProject) {
//        this.appliedProject = appliedProject;
//    }
//
//
//    public ApplicationStatus getApplicationStatus() {
//        return applicationStatus;
//    }
//
//
//    public void setApplicationStatus(ApplicationStatus applicationStatus) {
//        this.applicationStatus = applicationStatus;
//    }
//
//
//    public List<String> getEnquiries() {
//        return enquiries;
//    }
//
//
//    public void updateEnquiries(List<String> newEnquiries) {
//        if (newEnquiries == null) {
//            this.enquiries = new ArrayList<>();
//        } else {
//            this.enquiries = newEnquiries;
//        }
//    }
//
//    public boolean hasSuccessfulApplication() {
//        return this.applicationStatus == ApplicationStatus.SUCCESSFUL;
//    }
//
//    public boolean hasApplied() {
//        return this.appliedProject != null;
//    }
//
//
//    public boolean hasBookedFlat() {
//        return this.applicationStatus == ApplicationStatus.BOOKED;
//    }

}