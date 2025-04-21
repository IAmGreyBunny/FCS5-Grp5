package application;

import project.Project;
import java.time.LocalDate;

public class ApplicationEntry {
    private String applicationId;
    private int applicantId;
    private String projectId;
    private LocalDate applicationDate;
    private String status;
    private String flatType;
    private String withdrawalStatus;

    public ApplicationEntry(String applicationId, int applicantId, String projectId, String flatType) {
        this.applicationId = applicationId;
        this.applicantId = applicantId;
        this.projectId = projectId;
        this.applicationDate = LocalDate.now(); //set appln date to current date
        this.status = "Pending"; //default
        this.flatType = flatType;
        this.withdrawalStatus = "NIL"; //default
    }

    //getters and setters
    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFlatType() {
        return flatType;
    }

    public void setFlatType(String flatType) {
        this.flatType = flatType;
    }

    public String getWithdrawalStatus() {
        return withdrawalStatus;
    }

    public void setWithdrawalStatus(String withdrawalStatus) {
        this.withdrawalStatus = withdrawalStatus;
    }

    public boolean updateStatus(String newStatus) {
        this.status = newStatus;
        return true;
    }

    public boolean requestWithdrawal() {
        if (!"Pending".equals(this.withdrawalStatus)) {
            this.withdrawalStatus = "Pending";  //update to pending if not already pending
            return true;
        }
        return false;
    }

    public boolean updateWithdrawalStatus(String newStatus) {
        this.withdrawalStatus = newStatus;
        return true;
    }

    public boolean isWithdrawalRequested() {
        return "Pending".equals(this.withdrawalStatus);
    }
}
