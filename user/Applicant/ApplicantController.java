package user.Applicant;

import java.util.List;
import java.util.ArrayList;
//
//
public class ApplicantController {
//
//    public ApplicantController(Applicant user) {
//    }
//
//    public boolean changePassword(String currentEntry, String desiredPassword) {
//        if (!user.getPassword().equals(currentEntry)) {
//            return false;
//        }
//
//        user.setPassword(desiredPassword);
//        return true;
//
//    }
//
//    public boolean isEligibleFor(Project project) {
//        if (user.hasApplied() || !project.isVisible()) {
//            return false;
//        }
//
//        String status = user.getMaritalStatus();
//
//        int age = user.getAge();
//
//        return ("Single".equalsIgnoreCase(status) && age >= 35 && project.hasFlatType("2-Room")) ||
//
//                ("Married".equalsIgnoreCase(status) && age >= 21 &&
//
//                        (project.hasFlatType("2-Room") || project.hasFlatType("3-Room")));
//
//    }
//
//    public boolean apply(Project project) {
//
//        if (!isEligibleFor(project)) {
//            return false;
//        }
//        user.setAppliedProject(project);
//        user.setApplicationStatus(Applicant.ApplicationStatus.PENDING);
//
//        return true;
//
//    }
//
//    public boolean withdrawApplication() {
//        Applicant.ApplicationStatus status = user.getApplicationStatus();
//        if (!(status == Applicant.ApplicationStatus.PENDING ||
//                status == Applicant.ApplicationStatus.SUCCESSFUL)) {
//            return false;
//
//        }
//        user.setAppliedProject(null);
//        user.setApplicationStatus(Applicant.ApplicationStatus.NONE);
//        return true;
//
//    }
//
//    public String viewApplication() {
//        Project project = user.getAppliedProject();
//        if (project == null) {
//            return "You have not applied for any project yet.";
//        }
//        return "You have applied for: " + project.getName()
//                + ". Status: " + user.getApplicationStatus() + ".";
//
//    }
//
//    public List<Project> getEligibleProjects(List<Project> allProjects) {
//        List<Project> eligible = new ArrayList<>();
//        for (Project project : allProjects) {
//            if (isEligibleFor(project)) {
//                eligible.add(project);
//            }
//
//        }
//        return eligible;
//
//    }
//
//    public boolean canBookFlat() {
//        return user.hasSuccessfulApplication() && !user.hasBookedFlat();
//    }
//
//    public void submitEnquiry(String enquiry) {
//        user.getEnquiries().add(enquiry);
//
//    }
//
//    public boolean editEnquiry(int index, String updatedEnquiry) {
//        List<String> enquiries = user.getEnquiries();
//        if (index < 0 || index >= enquiries.size()) {
//            return false;
//        }
//        enquiries.set(index, updatedEnquiry);
//        return true;
//    }
//
//    public boolean deleteEnquiry(int index) {
//        List<String> enquiries = user.getEnquiries();
//        if (index < 0 || index >= enquiries.size()) {
//            return false;
//        }
//        enquiries.remove(index);
//        return true;
//
//    }
//
}