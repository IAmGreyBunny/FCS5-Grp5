package application;

import user.User;
import java.time.LocalDate;

public class ProjectOfficerApplication extends Application {

    public ProjectOfficerApplication(String applicationID, LocalDate applicationDate, User applicant) {
        super(applicationID, applicationDate, applicant);
    }

}
