package user;

import java.time.LocalDateTime;
import java.util.List;

public class HDBOfficer extends User {
    private String assignedProject;
    private boolean registrationApproved;
    private LocalDateTime registrationDate;

    /*
    public HDBOfficer(String name, String nric, int age, String maritalStatus, String password) {
        super(name, nric, age, maritalStatus, password, "HDB_OFFICER");
        this.assignedProject = null;
        this.registrationApproved = false;
        this.registrationDate = null;
    }

    public String getAssignedProject() { return assignedProject; }
    public boolean isRegistrationApproved() { return registrationApproved; }
    public LocalDateTime getRegistrationDate() { return registrationDate; }

    public void setAssignedProject(String projectName) {
        this.assignedProject = projectName;
    }

    public void setRegistrationApproved(boolean approved) {
        this.registrationApproved = approved;
        if (approved) {
            this.registrationDate = LocalDateTime.now();
        } else {
            this.registrationDate = null;
        }
    }

    public boolean canRegisterForProject(Project project) {
        if (assignedProject != null && !assignedProject.equals(project.getProjectName())) {
            return false;
        }

        if (hasAppliedForProject(project.getProjectName())) {
            return false;
        }

        return project.isApplicationOpen();
    }

    public boolean registerForProject(Project project) {
        if (canRegisterForProject(project)) {
            return project.addOfficer(this.getNric());
        }
        return false;
    }

    public boolean updateFlatAvailability(Project project, String flatType, int unitsSold) {
        if (!isRegistrationApproved() || !project.getProjectName().equals(assignedProject)) {
            return false;
        }

        if ("2-Room".equals(flatType)) {
            project.setUnitsType1(project.getUnitsType1() - unitsSold);
            return true;
        } else if ("3-Room".equals(flatType)) {
            project.setUnitsType2(project.getUnitsType2() - unitsSold);
            return true;
        }
        return false;
    }

    public boolean processFlatBooking(Application application) {
        if (!isRegistrationApproved() ||
                !application.getProjectName().equals(assignedProject) ||
                !application.getStatus().equals(Application.Status.SUCCESSFUL)) {
            return false;
        }

        application.setStatus(Application.Status.BOOKED);
        return true;
    }

    public String generateReceipt(Application application, User applicant) {
        if (!isRegistrationApproved() ||
                !application.getProjectName().equals(assignedProject) ||
                !application.getStatus().equals(Application.Status.BOOKED)) {
            return null;
        }

        return String.format(
                "Flat Booking Receipt\n" +
                        "====================\n" +
                        "Applicant Name: %s\n" +
                        "NRIC: %s\n" +
                        "Age: %d\n" +
                        "Marital Status: %s\n" +
                        "Project: %s\n" +
                        "Flat Type: %s\n" +
                        "Booking Date: %s\n" +
                        "Officer: %s (%s)",
                applicant.getName(),
                applicant.getNric(),
                applicant.getAge(),
                applicant.getMaritalStatus(),
                application.getProjectName(),
                application.getFlatType(),
                LocalDateTime.now().toString(),
                this.getName(),
                this.getNric()
        );
    }

    public String respondToEnquiry(Enquiry enquiry, String response) {
        if (!isRegistrationApproved() ||
                !enquiry.getProjectName().equals(assignedProject)) {
            return null;
        }

        enquiry.setResponse(response);
        enquiry.setResponseDate(LocalDateTime.now());
        return String.format(
                "Enquiry ID: %s\n" +
                        "Original Question: %s\n" +
                        "Response: %s\n" +
                        "Responded by: %s (%s) on %s",
                enquiry.getId(),
                enquiry.getContent(),
                response,
                this.getName(),
                this.getNric(),
                enquiry.getResponseDate().toString()
        );
    }

    private boolean hasAppliedForProject(String projectName) {
        return false;
    }
    */
}