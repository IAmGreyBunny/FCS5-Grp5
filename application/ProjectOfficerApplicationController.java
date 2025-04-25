package application;

import user.User;
import java.time.LocalDate;
import java.util.List;

/**
 * Responsible for managing Project Officer applications.
 * Has operations to create, approve, reject, and retrieve project officer applications.
 */
public class ProjectOfficerApplicationController extends ApplicationController {

    /**
     * Creates a new Project Officer application and stores it in the repository.
     * @param applicationID the unique ID of the application.
     * @param applicant the project officer applying for the position.
     * @return true if the application was created successfully, false otherwise.
     */
    public boolean createOfficerApplication(String applicationID, User applicant) {
        if (applicant != null) {
            ProjectOfficerApplication app = new ProjectOfficerApplication(applicationID, LocalDate.now(), applicant);

            ApplicationRepository.createApplication(app);
            applications.add(app);
            return true;
        }
        return false;
    }

    /**
     * Retrieves all project officer applications.
     * @return list of all project officer applications.
     */
    public List<ProjectOfficerApplication> getOfficerApplications() {
        List<ProjectOfficerApplication> result = new java.util.ArrayList<>();
        for (Application app : applications) {
            if (app instanceof ProjectOfficerApplication) {
                result.add((ProjectOfficerApplication) app);
            }
        }
        return result;
    }

    /**
     * Approves a given project officer application.
     * Updates the application status to APPROVED
     * @param application the project officer application to approve.
     * @return true if the application was approved successfully, false otherwise.
     */
    public boolean approveOfficerApplication(ProjectOfficerApplication application) {
        boolean approved = approveApplication(application);
        if (approved) {
            ApplicationRepository.updateApplication(application);
        }
        return approved;
    }

    /**
     * Rejects a given project officer application.
     * Updates the application status to REJECTED.
     * @param application the project officer application to reject.
     * @return true if the application was rejected successfully, false otherwise.
     */
    public boolean rejectOfficerApplication(ProjectOfficerApplication application) {
        boolean rejected = rejectApplication(application);
        if (rejected) {
            ApplicationRepository.updateApplication(application);
        }
        return rejected;
    }
}
