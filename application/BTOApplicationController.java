package application;

import project.UnitType;
import user.User;
import user.UserRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller for managing BTO applications. It handles applicants applying to BTO projects,
 * approving or rejecting applications, and processing withdrawal requests.
 */
public class BTOApplicationController extends ApplicationController {

    /**
     * Processes an application for a BTO project by a user.
     * First it checks if the applicant exists, then verifies if they have already applied for the given project.
     * If not, it generates a new application ID, creates a new application and stores it in the application repository.
     * @param applicantId the ID of the applicant applying for the project
     * @param projectId   the ID of the BTO project the applicant is applying to
     * @param unitType    the type of unit the applicant is applying for
     * @return true if the application was successfully created and stored, false otherwise
     */
    public boolean applyToBTO(String applicantId, String projectId, UnitType unitType) {
        User applicant = UserRepository.findUserById(Integer.parseInt(applicantId));
        if (applicant == null) return false;

        for (Application app : applications) {
            if (app instanceof BTOApplication) {
                BTOApplication existingApplication = (BTOApplication) app;
                if (existingApplication.getApplicantId().equals(applicantId) && existingApplication.getProjectId().equals(projectId)) {
                    System.out.println("You have already applied for this project");
                    return false;
                }
            }
        }

        String applicationID = String.valueOf(ApplicationRepository.findMaxApplicationID() + 1);  // Generate new ID

        BTOApplication application = new BTOApplication(
                applicationID,
                LocalDate.now(),
                applicant,
                projectId,
                applicantId,
                unitType
        );

        // update to repo
        ApplicationRepository.createApplication(application);
        applications.add(application);
        return true;
    }

    /**
     * Retrieves all BTO applications from the list of applications.
     * Filters the general applications list to return only those of type BTO applications
     * @return a list of all BTO applications
     */
    public List<BTOApplication> getBTOApplications() {
        List<BTOApplication> result = new java.util.ArrayList<>();
        for (Application app : applications) {
            if (app instanceof BTOApplication) {
                result.add((BTOApplication) app);
            }
        }
        return result;
    }

    /**
     * Approves a given BTO application and changes its status to approved.
     * Updates the application status in the application repository only if the approval is successful.
     * @param application the BTO application to be approved
     * @return true if the application was approved successfully, false otherwise
     */
    public boolean approveBTOApplication(BTOApplication application) {
        boolean approved = approveApplication(application);
        if (approved) {
            ApplicationRepository.updateApplication(application);
        }
        return approved;
    }

    /**
     * Rejects a given BTO application and changes its status to rejected.
     * Updates the application status in the application repository if the rejection is successful.
     * @param application the BTO application to be rejected
     * @return true if the application was rejected successfully, false otherwise
     */
    public boolean rejectBTOApplication(BTOApplication application) {
        boolean rejected = rejectApplication(application);
        if (rejected) {
            ApplicationRepository.updateApplication(application);
        }
        return rejected;
    }

    /**
     * Approves a withdrawal request for a BTO application.
     * If the application is already approved the status is changed to rejected.
     * Updates the application status in the application repository if the approval is successful.
     * @param application the BTO application for which the withdrawal is to be approved
     * @return true if the withdrawal was approved and the status updated, false otherwise
     */
    public boolean approveWithdrawal(BTOApplication application) {
        boolean result = application.approveWithdrawal();
        if (result) {
            ApplicationRepository.updateApplication(application);
        }
        return result;
    }

    /**
     * Rejects a withdrawal request for a BTO application.
     * Updates the application status in the application repository if the rejection is successful.
     * @param application the BTO Application for which the withdrawal is to be rejected
     * @return true if the withdrawal was rejected and the status updated, false otherwise
     */
    public boolean rejectWithdrawal(BTOApplication application) {
        boolean result = application.rejectWithdrawal();
        if (result) {
            ApplicationRepository.updateApplication(application);
        }
        return result;
    }
}
