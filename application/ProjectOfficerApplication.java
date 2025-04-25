package application;

import user.User;
import java.time.LocalDate;

/**
 * Represents an application made by a project officer.
 * It includes all the functionality of the base Application class,
 * like managing the application ID, date, status, and applicant information.
 */
public class ProjectOfficerApplication extends Application {

    /**
     * Constructs a new Project Officer Application with the specified details.
     * @param applicationID the unique ID of the application.
     * @param applicationDate the date when the application was submitted.
     * @param applicant the project officer applying for the position.
     */
    public ProjectOfficerApplication(String applicationID, LocalDate applicationDate, User applicant) {
        super(applicationID, applicationDate, applicant);
    }

}
