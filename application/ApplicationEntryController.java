package application;

import application.ApplicationEntry;
import project.Project;
import user.User;
import java.util.ArrayList;
import java.util.List;

public class ApplicationEntryController {

    private List<ApplicationEntry> applicationEntries;
    private List<Project> projects;
    private List<User> users;

    public ApplicationEntryController(List<ApplicationEntry> applicationEntries,
                                      List<Project> projects,
                                      List<User> users) {
        this.applicationEntries = applicationEntries;
        this.projects = projects;
        this.users = users;
    }

    //creating new appln
    public boolean createApplication(String applicationId, int applicantId,
                                     String projectId, String flatType) {
        // Validate inputs
        if (applicationId == null || applicationId.trim().isEmpty()) {
            return false;
        }

        User applicant = getUserById(applicantId);
        Project project = getProjectById(projectId);

        if (applicant == null || project == null) {
            return false;
        }

        //validate flat type based on applicant marital status
        if (!isValidFlatType(applicant, flatType)) {
            return false;
        }

        //check if applicant already has active application
        if (hasActiveApplication(applicantId)) {
            return false;
        }

        //create and add new application entry
        ApplicationEntry newApplication = new ApplicationEntry(applicationId, applicantId, projectId, flatType);
        return applicationEntries.add(newApplication);
    }

    //updates the application status
    public boolean updateApplicationStatus(String applicationId, String newStatus) {
        ApplicationEntry application = getApplicationById(applicationId);
        if (application == null) {
            return false;
        }
        return application.updateStatus(newStatus); // Use application variable correctly
    }

    //requests withdrawal
    public boolean requestWithdrawal(String applicationId) {
        ApplicationEntry application = getApplicationById(applicationId);
        if (application == null) {
            return false;
        }
        return application.requestWithdrawal(); // Use application variable correctly
    }

    //updates withdrawal status
    public boolean updateWithdrawalStatus(String applicationId, String newStatus) {
        ApplicationEntry application = getApplicationById(applicationId);
        if (application == null) {
            return false;
        }
        return application.updateWithdrawalStatus(newStatus); // Use application variable correctly
    }

    //gets all applications for a specific project
    public List<ApplicationEntry> getApplicationsByProject(String projectId) {
        List<ApplicationEntry> result = new ArrayList<>();
        for (ApplicationEntry application : applicationEntries) {
            if (application.getProjectId().equals(projectId)) {
                result.add(application);
            }
        }
        return result;
    }

    //gets all applications for a specific applicant
    public List<ApplicationEntry> getApplicationsByApplicant(int applicantId) {
        List<ApplicationEntry> result = new ArrayList<>();
        for (ApplicationEntry application : applicationEntries) {
            if (application.getApplicantId() == applicantId) {
                result.add(application);
            }
        }
        return result;
    }

    //helper methods
/*
    // Validates if the flat type is suitable based on the applicant's marital status
    private boolean isValidFlatType(User applicant, String flatType) {
        if (flatType == null) return false;

        // Single applicants can't apply for larger flats (e.g., "3-Room")
        if (flatType.equals("3-Room") && !applicant.isMaritalStatus()) {
            return false;
        }
        return true;
    }
 */

    //check if the applicant already has active application
    private boolean hasActiveApplication(int applicantId) {
        for (ApplicationEntry application : applicationEntries) {
            if (application.getApplicantId() == applicantId &&
                    !application.getStatus().equals("Unsuccessful") &&
                    application.getWithdrawalStatus().equals("NIL")) {
                return true;
            }
        }
        return false;
    }

    //retrieve an application by id
    private ApplicationEntry getApplicationById(String applicationId) {
        for (ApplicationEntry application : applicationEntries) {
            if (application.getApplicationId().equals(applicationId)) {
                return application; // Correct variable name "application"
            }
        }
        return null;
    }

    //retrieve user by id
    private User getUserById(int userId) {
        for (User user : users) {
            if (user.getUid() == userId) {
                return user;
            }
        }
        return null;
    }
}

/*
    // Retrieves a project by its ID
    private Project getProjectById(String projectId) {
        for (Project project : projects) {
            if (project.getProjectId().equals(projectId)) {
                return project;
            }
        }
        return null;
    }
}

*/