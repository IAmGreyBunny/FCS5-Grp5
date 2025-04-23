package application;

import project.*;
import user.User;
import java.util.*;
import java.time.LocalDate;

public class BTOApplicationController extends ApplicationController {

    public boolean createBTOApplication(String applicationID, User applicant, String projectId, UnitType unitType) {
        if (applicant != null && projectId != null && unitType != null) {
            BTOApplication app = new BTOApplication(
                    applicationID,
                    LocalDate.now(),
                    applicant,
                    projectId,
                    String.valueOf(applicant.getUid()), //check
                    unitType
            );
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
