package user;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HDBManager extends User{
    private BTOProject activeProject;
    private List<BTOProject> btoProject = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public HDBManager (String name, String nric, int age, boolean maritalStatus, String password) {
        super(name, nric, age, maritalStatus, password);
    }

    public boolean createListing(String projectName, String neighbourhood, int twoRoomFlats, int threeRoomFlats,
                                int avail2Room, int avail3Room, LocalDate openingDate, LocalDate closingDate) {
        if (activeProject != null) {
            System.out.print("Cannot create new projects as there is an active project!");
            return false;
        }
        else {
            String manager = super.getNric();
            int availOfficerSlots;
            System.out.println("Number of Officer slots for BTO Project, " + projectName + ": ");
            availOfficerSlots = sc.nextInt();
            BTOProject newProject = new BTOProject(projectName, neighbourhood, openingDate, closingDate, availOfficerSlots);   //to update this
            String twoRoom = "2-room";
            String threeRoom = "3-room";
            newProject.addUnitType(twoRoom, avail2Room, twoRoomFlats);
            newProject.addUnitType(threeRoom, avail3Room, threeRoomFlats);
            return true;
        }
    }

    public boolean editListing(BTOProject project) {
        int choice = information();
        switch (choice) {
            case 1:
                editName(project);
                break;
            case 2:
                editNeighbourhood(project);
                break;
            case 3:
                editUnitType(project);
                break;
            case 4:
                editUnitType(project);
                break;
            case 5:
                editOpeningDate(project);
                break;
            case 6:
                editClosingDate(project);
                break;
            case 7:
                editOfficerSlot(project);
                break;
            default: System.out.println("Invalid choice!");
        }
    }            

    protected int information() {
        int choice;
        System.out.println("Information to be edited:");
        System.out.println("1. Project Name"); 
        System.out.println("2. Neighbourhood"); 
        System.out.println("3. Types of Flats"); 
        System.out.println("4. Total Number of Units");
        System.out.println("5. Application Opening Date");
        System.out.println("6. Application Closing Date");
        System.out.println("7. Available HDB Officer Slots");
        do {
            System.out.println("Enter choice: ");
            choice = sc.nextInt();        
        } while (1>choice || choice>7);
        return choice;
    }
    protected void editName(BTOProject project) {
        System.out.printf("\nCurrent project name: " + project.getProjectName());
        System.out.println("Enter new project name: ");
        String newName = sc.nextLine();
        project.setProjectName(newName);
    }
    protected void editNeighbourhood(BTOProject project) {
        System.out.printf("\nCurrent neighbourhood: " + project.getNeighbourhood());
        System.out.println("Enter new neighbourhood: ");
        String newNeighbourhood = sc.nextLine();
        project.setNeighbourhood(newNeighbourhood);
    }
    protected void editUnitType(BTOProject project) {
        System.out.println("1. Add unit type");
        System.out.println("2. Delete unit type");
        int option;
        do {
            System.out.println("Enter action: ");
            option = sc.nextInt();
        } while (1<option || option>2);
        System.out.println("Current list of units: ");
        ArrayList<BTOUnitType> unitType = project.getListOfUnits();
        for (int i=0; i<unitType.size(); i++) {
            System.out.printf("\n%s", unitType.get(i).getName());
        }
        switch (option) {
            case 1:
                System.out.println("Enter new unit type: ");
                String newUnitName = sc.nextLine();
                System.out.println("Enter total number of units: ");
                int newTotalUnits = sc.nextInt();
                project.addUnitType(newUnitName, newTotalUnits, newTotalUnits);
                break;
            case 2:
                int deleteUnit;
                do{
                    System.out.println("Enter unit type to be deleted: ");
                    deleteUnit = sc.nextInt();
                } while (1<deleteUnit || deleteUnit>2);
                unitType.remove(deleteUnit-1);
                break;
            default: System.out.println("Invalid choice!");

        }
    }
    protected void editTotalUnits(BTOProject project) {
        System.out.println("Current units: ");
        ArrayList<BTOUnitType> unitType = project.getListOfUnits();
        for (int i=0; i<unitType.size(); i++) {
            System.out.printf("\n%s - total: %d", unitType.get(i).getName(), unitType.get(i).getTotal());
        }
        System.out.println("Enter unit type: ");
        int unitTotal = sc.nextInt();
        System.out.println("Enter new total number of units: ");
        int newTotal = sc.nextInt();
        unitType.get(unitTotal-1).setTotal(newTotal);
    }
    protected void editOpeningDate(BTOProject project) {
        System.out.println("Current opening date: ");
        System.out.print(project.getApplicationOpeningDate());
        System.out.println("Enter new opening date (dd/MM/yyyy): ");
        String openDate = sc.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate newDate = LocalDate.parse(openDate, formatter);
            project.setApplicationOpeningDate(openDate);
            System.out.println("Opening date updated successfully.");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy format.");
        }
    }
    protected void editClosingDate(BTOProject project) {
        System.out.println("Current closing date: ");
        System.out.print(project.getApplicationClosingDate());
        System.out.println("Enter new closing date (dd/MM/yyyy): ");
        String closeDate = sc.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate newDate = LocalDate.parse(closeDate, formatter);
            project.setApplicationClosingDate(closeDate);
            System.out.println("Closing date updated successfully.");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy format.");
        }
    }
    protected void editOfficerSlot(BTOProject project) {
        System.out.printf("\nCurrent number of Officer Slots: %d", project.getOfficerSlots());
        System.out.println("Enter new number of officer slots: ");
        int newSlots = sc.nextInt();
        project.setOfficerSlots(newSlots);
    }

    public boolean deleteListing(String projectName) {
        Optional<BTOProject> projectOpt = btoProject.stream()
            .filter(p -> p.getProjectName().equals(projectName) && p.getManager().equals(super.getNric()))
            .findFirst();

        if (projectOpt.isPresent()) {
            BTOProject project = projectOpt.get();
            btoProject.remove(project);
            if (activeProject == project) {
                activeProject = null;
            }
            return true;
        } else {
            System.out.println("No permission or project not found!");
            return false;
        }
    }

    public boolean toggleVisibility(BTOProject project, boolean visible) {
        project.setVisibility(visible);           // function that changes the visibility of the project
        return true;
    }

    public void viewAllProjects(List<BTOProject> btoProject) {
        btoProject.stream().forEach(p -> System.out.println(p));
    }

    public void viewOwnProjects(List<BTOProject> btoProject) {
        btoProject.stream()
            .filter(p -> p.getHDBManager().equals(super.getNric()))
            .forEach(p -> System.out.println(p));
    }

    public void viewOfficerApplication(List<officerApplicationEntry> officerApp) {    // assuming there is a class for officer applications
        officerApp.stream().forEach(o -> System.out.println(o));
    }

    public boolean officerApplications(BTOProject project, officerApplicationEntry officer) {   // assuming there is a class for officer applications
        int officerApp;
        System.out.println(officer.getOfficerID() + "'s officer application for BTO Project " + project.getName());  // get the ID of the officer applying and the name of the project
        System.out.print("\nEnter 1 to APPROVE and 2 to REJECT: ");
        officerApp = sc.nextInt();

        if (officerApp == 1) {      // approve
            if (project.getNoOfAvailOfficers() > 0) {               // method to get the number of officers
                return project.approvedOfficers(officer.officerID);    // function to add details of the approved officers
            }
            else {
                System.out.println("No more vacancies!");
            }
        }
        return false;
    }

    public boolean btoApplications(BTOProject project, BTOApplicationEntry applicant) {
        int appChoice;
        System.out.println(applicant.getApplicantID() + "'s BTO application for BTO Project " + project.getName());
        System.out.print("\n\nApplication's details: \n");
        applicant.printDetails();               // function to print the details of the appliant
        System.out.print("\n\nProject Details: \n");
        project.toString();                // function to print details of the project
        System.out.print("\n\nEnter 1 to APPROVE and 2 to REJECT: ");
        appChoice = sc.nextInt();

        if (appChoice == 1) {
            return project.approveApplication(applicant.getApplicantID(), applicant.getRoomType());     // function to add details of the approved applications
        }
        return false;
    }

    public boolean withdrawApplication(BTOProject project, BTOApplicationEntry applicant) {
        int withdrawChoice;
        System.out.println(applicant.getApplicantID() + "'s request to withdraw from BTO Project " + project.getName());
        withdrawChoice = sc.nextInt();

        if (withdrawChoice == 1) {
            return project.withdrawApplication(applicant.getApplicantID());     // function to withdraw application
        }
        return false;
    }

    public void viewEnquiries(List<Enquiries> enquiries) {
        enquiries.stream().forEach(e -> System.out.println(e));

    }

    public void viewOwnEnquiries(BTOProject project, int enquiryID, String response) {
        if (!project.getHDBManager().equals(super.getNric())) {
            System.out.print("Cannot respond to this enquiry");
            return;
        }
        project.replyEnquiry(enquiryID, response);        // function to add response to the enquiry
    }
}
