package application;

import project.*;
import user.User;
import java.util.*;
import java.time.LocalDate;

public class BTOApplicationController extends ApplicationController {

    public boolean createBTOApplication(String applicationID, User applicant, Project project) {
        if (applicant != null && project != null) {
            BTOApplication app = new BTOApplication(applicationID, LocalDate.now(), applicant, project);
            applications.add(app);
            return true;
        }
        return false;
    }

    public List<BTOApplication> getBTOApplications() {
        List<BTOApplication> result = new ArrayList<>();
        for (Application app : applications) {
            if (app instanceof BTOApplication) {
                result.add((BTOApplication) app);
            }
        }
        return result;
    }
}
