package application;

import project.*;
import java.util.*;

//still working on this

public class ApplicationController {
    protected List<Application> applications = new ArrayList<>();

    public boolean approveApplication(Application application) {
        if (application != null) {
            application.setApplicationStatus("Approved");
            return true;
        }
        return false;
    }

    public boolean rejectApplication(Application application) {
        if (application != null) {
            application.setApplicationStatus("Rejected");
            return true;
        }
        return false;
    }

    public List<Application> getAllApplications() {
        return new ArrayList<>(applications);
    }

    public void addApplication(Application application) {
        if (application != null) {
            applications.add(application);
        }
    }
}

