package application;

import java.util.*;

public class ApplicationController {
    protected List<Application> applications = new ArrayList<>();

    public void addApplication(Application application) {
        if (application != null) {
            applications.add(application);
        }
    }

    public boolean approveApplication(Application application) {
        return application.approveApplication();
    }

    public boolean rejectApplication(Application application) {
        return application.rejectApplication();
    }

    public boolean approveWithdrawal(Application application) {
        return application.approveWithdrawal();
    }

    public boolean rejectWithdrawal(Application application) {
        return application.rejectWithdrawal();
    }

    //get all applications
    public List<Application> getAllApplications() {
        return new ArrayList<>(applications);
    }

    //get application by id (helper method)
    public Application getApplicationByID(String applicationID) {
        for (Application app : applications) {
            if (app.getApplicationID().equals(applicationID)) {
                return app;
            }
        }
        return null;
    }
}
