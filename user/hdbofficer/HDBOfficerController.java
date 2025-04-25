package user.hdbofficer;

import application.*;
import project.Project;
import project.ProjectController;
import project.UnitType;
import session.Session;
import user.User;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller class to manage HDB Officer operations
 * Allows officer to register for a project, checking registration status,
 * updating flat availability, updating applicant bookings, and generating booking receipts
 */
public class HDBOfficerController {

    /**
     * Registers HDB officer to a project.
     * Verifies if the officer is already assigned to a project and if there are available slots in the project.
     * Creates a project officer application for the officer and stores it in the application repository.
     * @param projectId The ID of the project the officer registers for
     * @return true if registration is successful, false otherwise.
     */
    public static boolean registerForProject(int projectId) {
        HDBOfficer officer = (HDBOfficer) Session.getSession().getCurrentUser();

        if (officer.hasAssignedProject()) {
            System.out.println("Already assigned to a project");
            return false;
        }

        Project project = ProjectController.getProjectById(projectId);
        if (project == null || project.getOfficerSlots() <= 0) {
            System.out.println("Invalid project or no slots left");
            return false;
        }

        String applicationID = String.valueOf(ApplicationRepository.findMaxApplicationID() + 1);

        ProjectOfficerApplication app = new ProjectOfficerApplication(applicationID, LocalDate.now(), officer);
        ApplicationRepository.createApplication(app);

        System.out.println("Applied to handle project: " + project.getProjectName());
        return true;
    }

    /**
     * Checks the registration status of the HDB Officer.
     * Returns a message indicating whether the officer is registered for a project and the approval status.
     * @return string representing the registration status
     */
    public static String checkRegistrationStatus() {
        HDBOfficer officer = (HDBOfficer) Session.getSession().getCurrentUser();
        if (!officer.hasAssignedProject()) return "Not registered for any project";
        return officer.isRegistrationApproved() ? "Approved" : "Pending";
    }

    /**
     * Retrieves the project assigned to the current HDB Officer.
     * @return The assigned project, or null if no project is assigned
     */
    public static Project getAssignedProject() {
        HDBOfficer officer = (HDBOfficer) Session.getSession().getCurrentUser();
        if (!officer.hasAssignedProject()) return null;
        return ProjectController.getProjectById(officer.getAssignedProjectId());
    }

    /**
     * Updates the availability of flats in the assigned project.
     * @param unitName The name of the unit to update.
     * @param newAvailable The new availability value for the unit.
     * @return true if the update is successful, false otherwise.
     */
    public static boolean updateFlatAvailability(String unitName, int newAvailable) {
        HDBOfficer officer = (HDBOfficer) Session.getSession().getCurrentUser();
        if (!officer.isRegistrationApproved()) return false;

        Project project = getAssignedProject();
        if (project == null) return false;

        for (UnitType unit : project.getListOfUnits()) {
            if (unit.getName().equalsIgnoreCase(unitName)) {
                unit.setAvailable(newAvailable);
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the booking status of an applicant for a flat.
     * The booking is allowed only if the applicant meets the criteria of marital status and flat type
     * @param applicantId The ID of the applicant.
     * @param flatType The type of the flat being booked.
     * @return true if the booking is successfully updated, false otherwise.
     */
    public static boolean updateApplicantBooking(String applicantId, String flatType) {
        HDBOfficer officer = (HDBOfficer) Session.getSession().getCurrentUser();
        if (!officer.isRegistrationApproved()) return false;

        List<Application> allApplications = ApplicationRepository.getAllApplications();
        for (Application app : allApplications) {
            if (app instanceof BTOApplication bto &&
                    bto.getApplicantId().equals(applicantId) &&
                    bto.getProjectId().equals(String.valueOf(officer.getAssignedProjectId())) &&
                    bto.getApplicationStatus() == ApplicationStatus.APPROVED) {

                if (!bto.getApplicant().getMaritalStatus() && !flatType.equals("2-Room")) {
                    System.out.println("Unmarried applicants can only book 2-Room.");
                    return false;
                }

                bto.setApplicationStatus(ApplicationStatus.BOOKED);
                ApplicationRepository.updateApplication(bto);
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a flat booking receipt.
     * The receipt includes details such as applicant information, project, flat type, and booking date.
     * @param applicantId The ID of the applicant.
     * @return A formatted booking receipt string, or null if no receipt can be generated.
     */
    public static String generateBookingReceipt(String applicantId) {
        HDBOfficer officer = (HDBOfficer) Session.getSession().getCurrentUser();
        if (!officer.isRegistrationApproved()) return null;

        List<Application> allApplications = ApplicationRepository.getAllApplications();
        for (Application app : allApplications) {
            if (app instanceof BTOApplication bto &&
                    bto.getApplicantId().equals(applicantId) &&
                    bto.getApplicationStatus() == ApplicationStatus.BOOKED) {

                Project project = ProjectController.getProjectById(Integer.parseInt(bto.getProjectId()));
                User applicant = bto.getApplicant();

                return String.format("""
                    === Flat Booking Receipt ===
                    Applicant Name: %s
                    Applicant ID: %s
                    Age: %d
                    Marital Status: %s
                    Project: %s
                    Neighborhood: %s
                    Flat Type: %s
                    Booking Date: %s
                    Officer: %s
                    ==========================
                    """,
                        applicant.getName(),
                        applicantId,
                        applicant.getAge(),
                        applicant.getMaritalStatus() ? "Married" : "Single",
                        project.getProjectName(),
                        project.getNeighbourhood(),
                        bto.getUnitType() != null ? bto.getUnitType().getName() : "N/A",
                        LocalDate.now(),
                        officer.getName()
                );
            }
        }
        return null;
    }
}
