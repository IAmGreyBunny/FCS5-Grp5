package user;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class HDBManager extends User{
    private BTOListing activeProject;
    private List<BTOListing> btoListings = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public HDBManager (String name, String nric, int age, boolean maritalStatus,String password) {
        super(name, nric, age, maritalStatus, password);
    }

    public boolean createListing(String projectName, String neighbourhood, int twoRoomFlats, int threeRoomFlats,
                                int avail2Room, int avail3Room, String openingDate, String closingDate) {
        if (activeProject != null) {
            System.out.print("Cannot create new projects as there is an active project!");
            return false;
        }
        else {
            String manager = super.getNric();
            int availOfficerSlots;
            BTOListing newProject = new BTOListing(projectName, neighbourhood, twoRoomFlats, threeRoomFlats,
                                                    avail2Room, avail3Room, openingDate, closingDate, manager, availOfficerSlots);
            return true;
        }
    }

    public boolean editListing(String projectName) {
        for (BTOListing project : btoListings) {
            if (project.getName().equals(projectName)) {
                if (project.getHDBmanager().equals(super.getNric())) {
                    project.edit();
                    return true;
                }
                else {
                    System.out.print("No permission to edit this BTO Listing!!");
                    return false;
                }
            }
        } 
        System.out.print("Project not found!");
        return false;
    }

    public boolean deleteListing(String projectName) {
        for (BTOListing project : btoListings) {
            if (project.getName().equals(projectName)) {
                if (project.getHDBmanager().equals(super.getNric())) {
                    btoListings.remove(project);
                    if (activeProject == project) {
                        activeProject = null;
                    }
                    return true;
                }
                else {
                    System.out.println("No permission to delete this BTO Listing!");
                    return false;
                }
            }
        }
        System.out.println("Project not found!");
        return false;
    }

    public boolean toggleVisibility(BTOListing project, boolean visible) {
        project.setVisibility(visible);           // function that changes the visibility of the project
        return true;
    }

    public void viewAllProjects(List<BTOListing> btoListing) {
        for (BTOListing project : btoListing) {
            project.printProject();           // functions to print details of the project
        }
    }

    public void viewOwnProjects(List<BTOListing> btoListing) {
        for (BTOListing project : btoListing) {
            if (project.getHDBmanager().equals(super.getNric())) {      // function that returns the HDB manager in charge
                project.printProject();                            // function that prints the details of the project
            }
        }
    }

    public void viewOfficerApplication(List<officerApplication> officerApp) {    // assuming there is a class for officer applications
        for (officerApplication app : officerApp) {
            officerApplication.printApplication();        // function that prints the details of the officer's application
        }
    }

    public boolean officerApplications(BTOListing project, officerApplication officer) {   // assuming there is a class for officer applications
        int officerApp;
        System.out.println(officer.getOfficerID() + "'s officer application for BTO Project " + project.getName());  // get the ID of the officer applying and the name of the project
        System.out.print("\nEnter 1 to APPROVE and 2 to REJECT: ");
        officerApp = sc.nextInt();

        if (officerApp == 1) {      // approve
            if (project.getNoOfAvailOfficers() > 0) {
                return project.approvedOfficers(officer.officerID);    // function to add details of the approved officers
            }
            else {
                System.out.println("No more vacancies!");
            }
        }
        return false;
    }

    public boolean btoApplications(BTOListing project, Application applicant) {
        int appChoice;
        System.out.println(applicant.getApplicantID() + "'s BTO application for BTO Project " + project.getName());
        System.out.print("\n\nApplication's details: \n");
        applicant.printDetails();               // function to print the details of the appliant
        System.out.print("\n\nProject Details: \n");
        project.printProject();                // function to print details of the project
        System.out.print("\n\nEnter 1 to APPROVE and 2 to REJECT: ");
        appChoice = sc.nextInt();

        if (appChoice == 1) {
            return project.approveApplication(applicant.getApplicantID(), applicant.getRoomType());     // function to add details of the approved applications
        }
        return false;
    }

    public boolean withdrawApplication(BTOListing project, Application applicant) {
        int withdrawChoice;
        System.out.println(applicant.getApplicantID() + "'s request to withdraw from BTO Project " + project.getName());
        withdrawChoice = sc.nextInt();

        if (withdrawChoice == 1) {
            return project.withdrawApplication(applicant.getApplicantID());     // function to withdraw application
        }
        return false;
    }

    public void viewEnquiries(List<btoEnquiries> enquiries) {
        for (btoEnquiries question : enquiries) {
            question.printEnquiry();           // function to print enquiry
        }
    }

    public void viewOwnEnquiries(BTOListing project, int enquiryID, String response) {
        if (!project.getManager().equal(super.getNric())) {
            System.out.print("Cannot respond to this enquiry");
            return;
        }
        project.replyEnquiry(enquiryID, response);        // function to add response to the enquiry
    }
}
