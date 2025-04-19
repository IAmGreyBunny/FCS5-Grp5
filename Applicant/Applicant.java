package Applicant;

import java.util.ArrayList;
import java.util.List;

public class Applicant extends User {

    public enum ApplicationStatus {

        NONE, PENDING, SUCCESSFUL, UNSUCCESSFUL, BOOKED

    }

    private Project appliedProject;

    private ApplicationStatus applicationStatus;

    private List<String> enquiries;


    public Applicant(String nric, int age, String maritalStatus) {

        super(nric, age, maritalStatus);

        this.appliedProject = null;

        this.applicationStatus = ApplicationStatus.NONE;

        this.enquiries = new ArrayList<>();

    }

    public Project getAppliedProject() {

        return appliedProject;

    }


    public void setAppliedProject(Project appliedProject) {

        this.appliedProject = appliedProject;

    }


    public ApplicationStatus getApplicationStatus() {

        return applicationStatus;

    }


    public void setApplicationStatus(ApplicationStatus applicationStatus) {

        this.applicationStatus = applicationStatus;

    }


    public List<String> getEnquiries() {

        return enquiries;

    }


    public void updateEnquiries(List<String> newEnquiries) {

        if (newEnquiries == null) {

            this.enquiries = new ArrayList<>();

        } else {

            this.enquiries = newEnquiries;

        }

    }


    public boolean hasSuccessfulApplication() {

        return this.applicationStatus == ApplicationStatus.SUCCESSFUL;

    }


    public boolean hasApplied() {

        return this.appliedProject != null;

    }


    public boolean hasBookedFlat() {

        return this.applicationStatus == ApplicationStatus.BOOKED;

    }

}