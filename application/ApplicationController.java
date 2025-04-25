package application;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles operations related to user applications within the system, like submitting new applications, processing approvals or rejections,
 * and retrieving application records by ID
 */
public class ApplicationController {

    /**
     * Used a list to store all application instances handled by controller.
     * This will be filtered in subclasses depending on application type.
     */
    protected List<Application> applications = new ArrayList<>();

    /**
     * Adds a new application to the system by saving it to the application repository.
     * @param application the Application to be submitted
     */
    public void addApplication(Application application) {
        ApplicationRepository.createApplication(application);
    }

    /**
     * Approves a pending application. If successful, updates the application in the repository.
     * @param application the Application object to be approved
     * @return true if the application was successfully approved, false otherwise
     */
    public boolean approveApplication(Application application) {
        boolean approved = application.approveApplication();
        if (approved) {
            ApplicationRepository.updateApplication(application);
        }
        return approved;
    }

    /**
     * Rejects a pending application. If successful, updates the application in the repository.
     * @param application the Application object to be rejected
     * @return true if the application was successfully rejected, false otherwise
     */
    public boolean rejectApplication(Application application) {
        boolean rejected = application.rejectApplication();
        if (rejected) {
            ApplicationRepository.updateApplication(application);
        }
        return rejected;
    }

    /**
     * Retrieves all applications currently stored
     * @return list of all applications currently held by this controller
     */
    public List<Application> getAllApplications() {
        return new ArrayList<>(applications);
    }

    /**
     * Searches for a specific application by its application ID.
     * @param applicationID the unique ID of the application
     * @return the matching application if found, or null if not found
     */
    public Application getApplicationByID(String applicationID) {
        for (Application app : applications) {
            if (app.getApplicationID().equals(applicationID)) {
                return app;
            }
        }
        return null;
    }
}
