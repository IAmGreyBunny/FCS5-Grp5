package application;

import user.User;

import java.time.LocalDate;

/**
 * Represents base class for all types of applications submitted by users,
 * like BTO applications or officer registration requests.
 */
public class Application {
    private String applicationID;
    private LocalDate applicationDate;
    private ApplicationStatus applicationStatus; // changed to fit with enum
    private User applicant;

    /**
     * Constructs new application with the provided ID, submission date, and applicant.
     * The default status is set to PENDING.
     *
     * @param applicationID   unique ID for the application
     * @param applicationDate date the application was submitted
     * @param applicant       user who submitted the application
     */
    public Application(String applicationID, LocalDate applicationDate, User applicant) {
        this.applicationID = applicationID;
        this.applicationDate = applicationDate;
        this.applicant = applicant;
        this.applicationStatus = ApplicationStatus.PENDING; // Default status is PENDING
    }

    /**
     * @return the unique ID of the application
     */
    public String getApplicationID() {
        return applicationID;
    }

    /**
     * @return the date the application was submitted
     */
    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    /**
     * @return the current status of the application
     */
    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    /**
     * Updates status of the application.
     * @param status the new status to set
     */
    public void setApplicationStatus(ApplicationStatus status) {
        this.applicationStatus = status;
    }

    /**
     * @return the user who submitted this application
     */
    public User getApplicant() {
        return applicant;
    }

    /**
     * Tries to approve the application. Approval is only successful if current status is pending
     * @return true if approved successfully, false otherwise
     */
    public boolean approveApplication() {
        if (applicationStatus == ApplicationStatus.PENDING) {
            this.applicationStatus = ApplicationStatus.APPROVED;
            return true;
        }
        return false;
    }

    /**
     * Rejects the application if it is currently pending
     * @return true if rejected successfully, false otherwise
     */
    public boolean rejectApplication() {
        if (applicationStatus == ApplicationStatus.PENDING) {
            this.applicationStatus = ApplicationStatus.REJECTED;
            return true;
        }
        return false;
    }

    /**
     * Approves a withdrawal request by marking an already approved application as rejected
     * @return true if withdrawal is approved and status updated, false otherwise
     */
    public boolean approveWithdrawal() {
        if (applicationStatus == ApplicationStatus.APPROVED) {
            this.applicationStatus = ApplicationStatus.REJECTED;
            return true;
        }
        return false;
    }

    /**
     * Rejects a withdrawal request
     * @return true if the application is currently approved, false otherwise
     */
    public boolean rejectWithdrawal() {
        return applicationStatus == ApplicationStatus.APPROVED;
    }
}
