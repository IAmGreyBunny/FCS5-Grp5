package application;

import project.*;
import user.User;
import java.util.*;
import java.time.LocalDate;

public class ProjectOfficerApplicationController extends ApplicationController {

    public boolean createOfficerApplication(String applicationID, User applicant) {
        if (applicant != null) {
            ProjectOfficerApplication app = new ProjectOfficerApplication(applicationID, LocalDate.now(), applicant);
            applications.add(app);
            return true;
        }
        return false;
    }

    public List<ProjectOfficerApplication> getOfficerApplications() {
        List<ProjectOfficerApplication> result = new ArrayList<>();
        for (Application app : applications) {
            if (app instanceof ProjectOfficerApplication) {
                result.add((ProjectOfficerApplication) app);
            }
        }
        return result;
    }
}
