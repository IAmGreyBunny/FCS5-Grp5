package application;

import user.User;
import project.UnitType;
import java.time.LocalDate;

public class Application {
    private String applicationID;
    private LocalDate applicationDate;
    private ApplicationStatus applicationStatus; //changed to fit with enum
    private User applicant;

    public Application(String applicationID, LocalDate applicationDate, User applicant) {
        this.applicationID = applicationID;
        this.applicationDate = applicationDate;
        this.applicant = applicant;
        this.applicationStatus = ApplicationStatus.PENDING; // Default status is PENDING
    }

    //getters and setters
    public String getApplicationID() {
        return applicationID;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus status) {
        this.applicationStatus = status;
    }

    public User getApplicant() {
        return applicant;
    }

    public boolean approveApplication() {
        if (applicationStatus == ApplicationStatus.PENDING) {
            this.applicationStatus = ApplicationStatus.APPROVED;
            return true;
        }
        return false;
    }

    public boolean rejectApplication() {
        if (applicationStatus == ApplicationStatus.PENDING) {
            this.applicationStatus = ApplicationStatus.REJECTED;
            return true;
        }
        return false;
    }

    public boolean approveWithdrawal() {
        if (applicationStatus == ApplicationStatus.APPROVED) {
            this.applicationStatus = ApplicationStatus.REJECTED;
            return true;
        }
        return false;
    }

    public boolean rejectWithdrawal() {
        return applicationStatus == ApplicationStatus.APPROVED;
    }
}
