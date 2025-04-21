package user.hdbofficer;

import btoproject.*;
import user.*;
import java.time.LocalDate;
import java.util.*;

public class HDBOfficerController {
    private List<BTOProject> btoProjects;
    private List<BTOApplication> applications;
    private List<User> users;
    private HDBOfficer currentOfficer;

    public HDBOfficerController(List<BTOProject> projects, List<BTOApplication> applications,
                                List<User> users, HDBOfficer officer) {
        this.btoProjects = projects;
        this.applications = applications;
        this.users = users;
        this.currentOfficer = officer;
    }

    public boolean registerForProject(String projectId) {
        if (currentOfficer.getProjectHandling() != null) return false;
        if (hasAppliedForProject(projectId)) return false;

        BTOProject newProject = getProjectById(projectId);
        if (newProject == null || isRegisteredForOverlappingProject(newProject)) return false;

        currentOfficer.setProjectHandling(projectId);
        currentOfficer.setRegistrationApproved(false);
        return true;
    }

    public String checkRegistrationStatus() {
        if (currentOfficer.getProjectHandling() == null) return "Not registered for any project";
        return currentOfficer.isRegistrationApproved() ? "Approved" : "Pending";
    }

    public BTOProject getHandledProjectDetails() {
        if (currentOfficer.getProjectHandling() == null) return null;
        return getProjectById(currentOfficer.getProjectHandling());
    }

    public boolean updateFlatAvailability(String flatType, int availableUnits) {
        if (!currentOfficer.isRegistrationApproved()) return false;

        BTOProject project = getHandledProjectDetails();
        if (project == null) return false;

        BTOUnitType unit = project.getUnitType(flatType);
        if (unit == null) return false;

        unit.setAvailable(availableUnits);
        return true;
    }

    public boolean updateApplicantBooking(String applicantId, String flatType) {
        if (!currentOfficer.isRegistrationApproved()) return false;

        BTOApplication app = getApplicantApplication(applicantId);
        if (app == null || !app.getStatus().equals("Successful")) return false;

        User applicant = getUserById(Integer.parseInt(applicantId));
        if (applicant == null) return false;

        if (!applicant.isMaritalStatus() && !flatType.equals("2-Room")) return false;

        app.setStatus("Booked");
        app.setFlatType(flatType);
        return true;
    }

    public String generateBookingReceipt(String applicantId) {
        if (!currentOfficer.isRegistrationApproved()) return null;

        BTOApplication app = getApplicantApplication(applicantId);
        if (app == null || !app.getStatus().equals("Booked")) return null;

        User applicant = getUserById(Integer.parseInt(applicantId));
        if (applicant == null) return null;

        BTOProject project = getProjectById(app.getProjectId());
        if (project == null) return null;

        return String.format(
                "=== Flat Booking Receipt ===\n" +
                        "Applicant Name: %s\n" +
                        "Applicant ID: %s\n" +
                        "Age: %d\n" +
                        "Marital Status: %s\n" +
                        "Project: %s\n" +
                        "Neighborhood: %s\n" +
                        "Flat Type: %s\n" +
                        "Booking Date: %s\n" +
                        "Officer: %s\n" +
                        "==========================",
                applicant.getName(),
                applicantId,
                applicant.getAge(),
                applicant.isMaritalStatus() ? "Married" : "Single",
                project.getProjectName(),
                project.getNeighbourhood(),
                app.getFlatType(),
                LocalDate.now(),
                currentOfficer.getName()
        );
    }

    public List<BTOEnquiry> getProjectEnquiries() {
        if (!currentOfficer.isRegistrationApproved()) return Collections.emptyList();

        BTOProject project = getHandledProjectDetails();
        if (project == null) return Collections.emptyList();

        return project.getEnquiries();
    }

    public boolean replyToEnquiry(String enquiryId, String replyText) {
        if (!currentOfficer.isRegistrationApproved()) return false;

        for (BTOEnquiry enquiry : getProjectEnquiries()) {
            if (enquiry.getEnquiryId().equals(enquiryId)) {
                enquiry.setReply(replyText);
                enquiry.setRepliedBy(currentOfficer.getUid());
                return true;
            }
        }
        return false;
    }

    //helper methods

    private boolean hasAppliedForProject(String projectId) {
        return applications.stream()
                .anyMatch(app -> app.getApplicantId() == currentOfficer.getUid() &&
                        app.getProjectId().equals(projectId));
    }

    private boolean isRegisteredForOverlappingProject(BTOProject newProject) {
        return btoProjects.stream()
                .filter(p -> currentOfficer.getProjectHandling() != null &&
                        currentOfficer.getProjectHandling().equals(p.getProjectId()))
                .anyMatch(existing -> isOverlappingPeriod(existing, newProject));
    }

    private boolean isOverlappingPeriod(BTOProject p1, BTOProject p2) {
        return !p1.getApplicationClosingDate().isBefore(p2.getApplicationOpeningDate()) &&
                !p1.getApplicationOpeningDate().isAfter(p2.getApplicationClosingDate());
    }

    private BTOProject getProjectById(String projectId) {
        return btoProjects.stream()
                .filter(p -> p.getProjectId().equals(projectId))
                .findFirst()
                .orElse(null);
    }

    private User getUserById(int userId) {
        return users.stream()
                .filter(u -> u.getUid() == userId)
                .findFirst()
                .orElse(null);
    }

    private BTOApplication getApplicantApplication(String applicantId) {
        return applications.stream()
                .filter(app -> app.getApplicantId() == Integer.parseInt(applicantId) &&
                        app.getProjectId().equals(currentOfficer.getProjectHandling()))
                .findFirst()
                .orElse(null);
    }
}
