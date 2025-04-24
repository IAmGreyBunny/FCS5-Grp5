package user.applicant;

import project.Project;
import enquiries.Enquiry;
import enquiries.EnquiryController;

import java.util.ArrayList;
import java.util.List;

public class ApplicantController {
    private final Applicant applicant;
    private final EnquiryController enquiryController;

    public ApplicantController(Applicant applicant, EnquiryController enquiryController) {
        this.applicant = applicant;
        this.enquiryController = enquiryController;
    }

    public boolean changePassword(String currentPassword, String newPassword) {
        if (!applicant.getPassword().equals(currentPassword)) {
            return false;
        }
        applicant.setPassword(newPassword);
        return true;
    }

    public boolean isEligibleFor(Project project) {
        if (applicant.hasApplied() || !project.isVisible()) {
            return false;
        }
        boolean married = applicant.getMaritalStatus(); // use getter from User
        int age = applicant.getAge();
        if (!married && age >= 35) {
            return project.hasFlatType("2-Room");
        }
        if (married && age >= 21) {
            return project.hasFlatType("2-Room") || project.hasFlatType("3-Room");
        }
        return false;
    }

    public boolean apply(Project project) {
        if (!isEligibleFor(project)) {
            return false;
        }
        applicant.setAppliedProject(project);
        applicant.setApplicationStatus(Applicant.ApplicationStatus.PENDING);
        return true;
    }

    public boolean withdrawApplication() {
        Applicant.ApplicationStatus status = applicant.getApplicationStatus();
        if (status != Applicant.ApplicationStatus.PENDING
                && status != Applicant.ApplicationStatus.SUCCESSFUL
                && status != Applicant.ApplicationStatus.BOOKED) {
            return false;
        }
        applicant.setAppliedProject(null);
        applicant.setApplicationStatus(Applicant.ApplicationStatus.NONE);
        return true;
    }

    public String viewApplication() {
        Project project = applicant.getAppliedProject();
        if (project == null) {
            return "No application found.";
        }
        return String.format("Applied to: %s, Status: %s",
                project.getProjectName(),
                applicant.getApplicationStatus());
    }

    public List<Project> getEligibleProjects(List<Project> allProjects) {
        List<Project> list = new ArrayList<>();
        for (Project project : allProjects) {
            if (isEligibleFor(project)) {
                list.add(project);
            }
        }
        return list;
    }

    public boolean canBookFlat() {
        return applicant.hasSuccessfulApplication() && !applicant.hasBookedFlat();
    }

    public Enquiry submitEnquiry(String messageText, String recipientID) {
        Enquiry enquiry = enquiryController.createNewEnquiry(
                messageText,
                String.valueOf(applicant.getUid()),
                recipientID
        );
        applicant.getEnquiries().add(enquiry);
        return enquiry;
    }

    public boolean editEnquiry(int enquiryID, String updatedMessage) {
        Enquiry enquiry = enquiryController.findEnquiryByID(enquiryID);
        if (enquiry == null || !String.valueOf(applicant.getUid()).equals(enquiry.getSenderID())) {
            return false;
        }
        enquiry.setMessageText(updatedMessage);
        return true;
    }

    public boolean deleteEnquiry(int enquiryID) {
        Enquiry enquiry = enquiryController.findEnquiryByID(enquiryID);
        if (enquiry == null || !String.valueOf(applicant.getUid()).equals(enquiry.getSenderID())) {
            return false;
        }
        applicant.getEnquiries().remove(enquiry);
        return true;
    }

    public void viewEnquiryThread(int enquiryID) {
        enquiryController.displayThreadFrom(enquiryID);
    }
}
