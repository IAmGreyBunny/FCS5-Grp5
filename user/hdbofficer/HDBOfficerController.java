package user.hdbofficer;

import application.*;
import project.Project;
import project.ProjectController;
import project.UnitType;
import session.Session;
import user.User;

import java.time.LocalDate;
import java.util.List;

public class HDBOfficerController {

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

    public static String checkRegistrationStatus() {
        HDBOfficer officer = (HDBOfficer) Session.getSession().getCurrentUser();
        if (!officer.hasAssignedProject()) return "Not registered for any project";
        return officer.isRegistrationApproved() ? "Approved" : "Pending";
    }

    public static Project getAssignedProject() {
        HDBOfficer officer = (HDBOfficer) Session.getSession().getCurrentUser();
        if (!officer.hasAssignedProject()) return null;
        return ProjectController.getProjectById(officer.getAssignedProjectId());
    }

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
