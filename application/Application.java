package application;

import user.User;
import java.time.LocalDate;

//cross check with Applicant code

public abstract class Application {
    protected String applicationID;
    protected LocalDate applicationDate;
    protected String applicationStatus;
    protected User applicant;

    public Application(String applicationID, LocalDate applicationDate, User applicant) {
        this.applicationID = applicationID;
        this.applicationDate = applicationDate;
        this.applicant = applicant;
        this.applicationStatus = "Pending";
    }

    public String getApplicationID() {
        return applicationID;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicationStatus(String status) {
        this.applicationStatus = status;
    }
}

