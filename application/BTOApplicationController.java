package application;

import project.UnitType;
import user.User;
import java.time.LocalDate;
import java.util.List;

public class BTOApplicationController extends ApplicationController {

    public boolean createBTOApplication(String applicationID, User applicant, String projectId, UnitType unitType) {
        if (applicant != null && projectId != null && unitType != null) {
            BTOApplication app = new BTOApplication(
                    applicationID,
                    LocalDate.now(),
                    applicant,
                    projectId,
                    String.valueOf(applicant.getUid()),
                    unitType
            );

            ApplicationRepository.createApplication(app);
            applications.add(app);
            return true;
        }
        return false;
    }

    public List<BTOApplication> getBTOApplications() {
        List<BTOApplication> result = new java.util.ArrayList<>();
        for (Application app : applications) {
            if (app instanceof BTOApplication) {
                result.add((BTOApplication) app);
            }
        }
        return result;
    }

    public boolean approveBTOApplication(BTOApplication application) {
        boolean approved = approveApplication(application);
        if (approved) {
            ApplicationRepository.updateApplication(application);
        }
        return approved;
    }

    public boolean rejectBTOApplication(BTOApplication application) {
        boolean rejected = rejectApplication(application);
        if (rejected) {
            ApplicationRepository.updateApplication(application);
        }
        return rejected;
    }
}
