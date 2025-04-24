package application;

import user.User;
import java.time.LocalDate;
import java.util.List;

public class ProjectOfficerApplicationController extends ApplicationController {

    public boolean createOfficerApplication(String applicationID, User applicant) {
        if (applicant != null) {
            ProjectOfficerApplication app = new ProjectOfficerApplication(applicationID, LocalDate.now(), applicant);

            ApplicationRepository.createApplication(app);
            applications.add(app);
            return true;
        }
        return false;
    }

    public List<ProjectOfficerApplication> getOfficerApplications() {
        List<ProjectOfficerApplication> result = new java.util.ArrayList<>();
        for (Application app : applications) {
            if (app instanceof ProjectOfficerApplication) {
                result.add((ProjectOfficerApplication) app);
            }
        }
        return result;
    }

    public boolean approveOfficerApplication(ProjectOfficerApplication application) {
        boolean approved = approveApplication(application);
        if (approved) {
            ApplicationRepository.updateApplication(application);
        }
        return approved;
    }

    public boolean rejectOfficerApplication(ProjectOfficerApplication application) {
        boolean rejected = rejectApplication(application);
        if (rejected) {
            ApplicationRepository.updateApplication(application);
        }
        return rejected;
    }
}
