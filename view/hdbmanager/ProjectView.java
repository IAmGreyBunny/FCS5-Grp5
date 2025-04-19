package view.hdbmanager;

import session.Session;
import view.MenuView;
import btoproject.BTOProject;
import view.hdbmanager.HDBManagerHomeView;
import user.hdbmanager.HDBProjectController;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;

public class ProjectView extends MenuView {
    private int userInput;
    private Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    private HDBProjectController controller = new HDBProjectController();

    @Override
    public void show() {
        System.out.println("==== Manage BTO Project ====");
        System.out.println("1. Create New Project Listing");
        System.out.println("2. Edit Project Listing");
        System.out.println("3. Delete Project Listing");
        System.out.println("4. Toggle Visibility");
        System.out.println("5. View Project Listings");
        System.out.println("6. Return");

        try {
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    // code to create new listing (CreateListingForm)
                    break;
                case 2:
                    handleEditing();
                    break;
                case 3:
                    handleDeletion();
                    break;
                case 4:
                    toggleVisibility();
                    break;
                case 5:
                    viewProjects();
                    break;
                case 6:
                    Session.getSession().setCurrentView(new HDBManagerHomeView());
                    break;
                default: System.out.println("Invalid Input");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
    }

    private void handleEditing() {
        System.out.println("=== Edit Listing ===");
        printProjects();
        System.out.println("Enter project name to be edited: ");
        String projectName = scanner.next();
        List<BTOProject> projects = controller.getAllProjects();
        BTOProject target = projects.stream().filter(p -> p.getProjectName().equalsIgnoreCase(projectName)).findFirst().orElse(null);
        if (target != null) {
            System.out.println("-- Current Details --");
            System.out.println("1. Project Name: " + target.getProjectName());
            System.out.println("2. Neighbourhood: " + target.getNeighbourhood());
            /*  TODO option to print the types of flats available
            System.out.println("3. Types of Flats: ");
                TODO portion to specify which flat to edit
            System.out.println("4. Choose the type of flats to edit");
            */
            System.out.println("5. Opening Date: " + target.getApplicationOpeningDate());
            System.out.println("6. Closing Date " + target.getApplicationClosingDate());
            System.out.println("7. Officer Slots: " + target.getOfficerSlots());
            System.out.println("\nEnter portion to edit: ");
            int portion = scanner.nextInt();
            Object newValue = null;
            try {
                switch (field) {
                    case 1 -> {
                        System.out.println("Enter new project name: ");
                        newValue = scanner.next();
                    }
                    case 2 -> {
                        System.out.println("Enter new neighbourhood: ");
                        newValue = scanner.next();
                    }
                    /* TODO case 3 and 4
                    case 3 -> {
                        
                    }
                    case 4 -> {

                    } */
                    case 5,6 -> {
                        System.out.println("Enter new date (yyyy-mm-dd): ");
                        newValue = LocalDate.parse(scanner.next());
                    }
                    case 7 -> {
                        System.out.println("Enter new number of slots: ");
                        newValue = scanner.nextInt();
                    }
                    default -> {
                        System.out.println("Invalid selection.");
                        return;
                    }
                }
                if (controller.editListing(target, choice, newValue)) {
                    System.out.println("Successfully edited!");
                }
                else {System.out.println("Failed to update.");}
            } catch (Exception e) {
                System.out.println("Project not found.");
            }
        }
    }

    private void handleDeletion() {
        System.out.println("=== Delete Listing ===");
        printProject();
        System.out.println("Enter project to be deleted: ");
        String delete = scanner.next();
        if (controller.deleteProject(delete)) {
            System.out.println("Project deleted.");
        } else {System.out.println("Project not found");}
    }

    public void toggleVisibility() {
        System.out.println("=== Toggle Visibility ===");
        printProject();

        String projectName;
        BTOProject foundProject = null;
        List<BTOProject> projects = controller.getAllProjects();

        do {
            System.out.println("Enter project name: ");
            projectName = scanner.next();
            for (BTOProject project : projects) {
                if (project.getProjectName().equalsIgnoreCase(projectName)) {
                    foundProject = project;
                    System.out.println("Current visibility: " + foundProject.getVisibility());
                    break;
                }
            }
            if (foundProject == null) {
                System.out.println("Project not found");
            }
        } while (foundProject == null);

        String visibility;
        do {
            System.out.println("Enter new visibility (on/off): ");
            visibility = scanner.next();
        } while (!visibility.equalsIgnoreCase("on") && !visibility.equalsIgnoreCase("off"));

        boolean visible = visibility.equalsIgnoreCase("on");
        if (controller.toggleVisibility(foundProject, visible)) {
            System.out.println("Successfully toggled.");
        } else {System.out.println("Toggle unsuccessful.");}
    }

    public void printProject() {
        List<BTOProject> projects = controller.getAllProjects();
        if (projects.isEmpty()) {
            System.out.println("No projects available!");
            return;
        }
        
        for (BTOProject project : projects) {
            System.out.println(project.getProjectName());
        }
    }

    public void viewProjects() {
        System.out.println("===== View Project Listings ===");
        System.out.println("1. View All Projects");
        System.out.println("2. View My Projects");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.println("=== All Projects");
                List<BTOProject> projects = controller.getAllProjects;
                for (BTOProject project : projects) {
                    System.out.println("project.getProjectName()");
                }
            }
            case 2 -> {
                String managerId = Session.getSession().getCurrentUser().getUid();
                List<BTOProject> myProjects = controller.getMyProjects(managerId);
                if (myProjects.isEmpty()) {
                    System.out.println("No projects created.");
                }
                else {
                    myProjects.forEach(p -> System.out.println(p.getProjectName()));
                }
            }
            default -> System.out.println("Invalid choice!");
        }
    }
}
