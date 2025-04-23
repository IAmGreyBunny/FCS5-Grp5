package application;

import user.User;
import java.time.LocalDate;

//cross check with Applicant code

public class ProjectOfficerApplication extends Application {

    public ProjectOfficerApplication(String applicationID, LocalDate applicationDate, User applicant) {
        super(applicationID, applicationDate, applicant);
    }

}
