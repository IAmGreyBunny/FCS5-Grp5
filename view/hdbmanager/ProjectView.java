package view.hdbmanager;

import session.Session;
import view.MenuView;
import btoproject.BTOProject;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProjectView extends MenuView {
    private int userInput;
    private List<BTOProject> btoProject = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in).useDelimiter("\n");


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
                    System.out.println("=== Edit Listing ===");
                    printProjects();
                    System.out.println("Enter project to be edited: ");
                    // code to edit existing listing
                    break;
                case 3:
                    printProjects();
                    System.out.println("Enter project to be deleted: ");
                    // code to delete listing
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

    public void printProjects() {
        for (BTOProject project : btoProject) {
            System.out.println(project.getProjectName() + " -- " + project.getVisibility());
        }
    }

    public void toggleVisibility() {
        System.out.println("==== Toggle Visibility ====\n");
        printProjects();

        String bto;
        BTOProject foundProject = null;
        do {
            System.out.println("\nEnter Project Name: ");
            bto = scanner.next();
            for (BTOProject project : btoProject) {
                if (project.getProjectName().equalsIgnoreCase(bto)) {
                    foundProject = project;
                    break;
                }
            }
            if (foundProject == null) {
                System.out.println("Project not found!");
            }
        } while (foundProject == null);
        
        String visibility;
        do {
            System.out.println("Enter new visibility (on/off): ");
            visibility = scanner.next();
        } while (!visibility.equalsIgnoreCase("on") && !visibility.equalsIgnoreCase("off"));

        // to set the visibility of the specified project
    }
    
    public void viewProjects() {
        System.out.println("==== View Project Listings ====");
        System.out.println("1. View All Projects");
        System.out.println("2. View My Projects");

        int choice = scanner.nextInt();
        if (btoProject.isEmpty()) {
            System.out.println("No projects created!");
            return;
        }
        switch (choice) {
            case 1:
                System.out.println("== All Projects ==");
                btoProject.stream()
                    .forEach(/* to print the details of each project */);
                break;
            case 2:
                String managerNric = Session.getSession().getCurrentUser().getNric();
                System.out.println("== My Projects ==");
                List<BTOProject> myProjects = btoProject.stream()
                    .filter(project -> project.getManager().getNric().equals(managerNric))
                    .collect(Collectors.toList());

                if (myProjects.isEmpty()) {
                    System.out.println("You have not created any projects.");
                }
                else {
                    // code to print the details of projects in myProjects
                }
            default: System.out.println("Invalid choice!");
        }
    }
}
