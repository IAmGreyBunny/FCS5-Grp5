package application;

import project.UnitType;
import user.User;
import user.UserRepository;

import java.time.LocalDate;
import java.util.List;

public class BTOApplicationController extends ApplicationController {

    public boolean applyToBTO(String applicantId, String projectId, UnitType unitType) {
        //find applicant
        User applicant = UserRepository.findUserById(Integer.parseInt(applicantId));
        if (applicant == null) return false;

        //to check if applicant has already applied to project
        for (Application app : applications) {
            if (app instanceof BTOApplication) {
                BTOApplication existingApplication = (BTOApplication) app;
                if (existingApplication.getApplicantId().equals(applicantId) && existingApplication.getProjectId().equals(projectId)) {
                    System.out.println("You have already applied for this project");
                    return false;
                }
            }
        }

        //to generate new application id
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

    public boolean approveWithdrawal(BTOApplication application) {
        boolean result = application.approveWithdrawal();
        if (result) {
            ApplicationRepository.updateApplication(application);
        }
        return result;
    }

    public boolean rejectWithdrawal(BTOApplication application) {
        boolean result = application.rejectWithdrawal();
        if (result) {
            ApplicationRepository.updateApplication(application);
        }
        return result;
    }
}
