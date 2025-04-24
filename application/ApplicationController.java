package application;

import java.util.ArrayList;
import java.util.List;

public class ApplicationController {
    protected List<Application> applications = new ArrayList<>();

    public void addApplication(Application application) {
        ApplicationRepository.createApplication(application);
    }

    public boolean approveApplication(Application application) {
        boolean approved = application.approveApplication();
        if (approved) {
            ApplicationRepository.updateApplication(application);
        }
        return approved;
    }

    public boolean rejectApplication(Application application) {
        boolean rejected = application.rejectApplication();
        if (rejected) {
            ApplicationRepository.updateApplication(application);
        }
        return rejected;
    }

    public List<Application> getAllApplications() {
        return new ArrayList<>(applications);
    }

    //get application by id
    public Application getApplicationByID(String applicationID) {
        for (Application app : applications) {
            if (app.getApplicationID().equals(applicationID)) {
                return app;
            }
        }
        return null;
    }
}
