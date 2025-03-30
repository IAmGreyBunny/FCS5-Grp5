import java.util.ArrayList;

public class Applicant {
    private String NRIC;
    private String password;
    private int age;
    private String maritalStatus; // "Single" or "Married"
    private Project appliedProject;
    private ApplicationStatus applicationStatus;
    private ArrayList<String> enquiries;

    public enum ApplicationStatus {
        NONE, PENDING, SUCCESSFUL, UNSUCCESSFUL, BOOKED
    }

    public Applicant(String NRIC, int age, String maritalStatus) {
        this.NRIC = NRIC;
        this.password = "password"; // default password
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.appliedProject = null;
        this.applicationStatus = ApplicationStatus.NONE;
        this.enquiries = new ArrayList<>();
    }

    public boolean changePassword(String oldPass, String newPass) {
        if (this.password.equals(oldPass)) {
            this.password = newPass;
            return true;
        }
        return false;
    }

    public boolean canApply(Project project) {
        if (appliedProject != null || !project.isVisible()) return false;

        if (maritalStatus.equals("Single") && age >= 35)
            return project.hasFlatType("2-Room");

        if (maritalStatus.equals("Married") && age >= 21)
            return project.hasFlatType("2-Room") || project.hasFlatType("3-Room");

        return false;
    }

    public boolean apply(Project project) {
        if (canApply(project)) {
            this.appliedProject = project;
            this.applicationStatus = ApplicationStatus.PENDING;
            return true;
        }
        return false;
    }

    // View current application details
    public String viewApplicationDetails() {
        if (appliedProject == null) return "No application found.";

        return "Project: " + appliedProject.getName() +
               ", Status: " + applicationStatus;
    }

    public boolean withdrawApplication() {
        if (applicationStatus == ApplicationStatus.PENDING || 
            applicationStatus == ApplicationStatus.SUCCESSFUL) {
            appliedProject = null;
            applicationStatus = ApplicationStatus.NONE;
            return true;
        }
        return false;
    }

    public void submitEnquiry(String enquiry) {
        enquiries.add(enquiry);
    }

    // View all enquiries
    public ArrayList<String> getEnquiries() {
        return enquiries;
    }

    public boolean editEnquiry(int index, String enquiry) {
        if (index >= 0 && index < enquiries.size()) {
            enquiries.set(index, enquiry);
            return true;
        }
        return false;
    }

    public boolean deleteEnquiry(int index) {
        if (index >= 0 && index < enquiries.size()) {
            enquiries.remove(index);
            return true;
        }
        return false;
    }
}


