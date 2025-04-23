package view.hdbmanager.controller;

import project.Project;
import project.UnitType;
import session.Session;
import user.hdbmanager.BTOProjectController;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProjectViewController {
    private BTOProjectController controller = new BTOProjectController();
    private Scanner scanner;

    public ProjectViewController(Scanner scanner){
        this.scanner = scanner;
    }

    public void createNewProject() {
        // TODO code to create new project
    }

    public void handleEditing() {
        System.out.println("=== Edit Listing ===");
        printProject();
        System.out.println("Enter project name to be edited: ");
        scanner.nextLine();
        String projectName = scanner.next();
        List<Project> projects = controller.getAllProjects();
        Project target = projects.stream().filter(p -> p.getProjectName().equalsIgnoreCase(projectName)).findFirst().orElse(null);
        if (target != null) {
            System.out.println("For each of the following, enter new information (if any).");
            System.out.println("Leave empty if none.\n");
            System.out.println("Current project name: " + target.getProjectName());
            System.out.println("Enter new project name: ");
            String name = scanner.next();
            if (controller.editName(target, name)) {
                System.out.println("Project name updated to '" + target.getProjectName() + "'.");
            }

            System.out.println("Current neighbourhood: " + target.getNeighbourhood());
            System.out.println("Enter new neighbourhood: ");
            String neighbourhood = scanner.next();
            if (controller.editNeighbourhood(target, neighbourhood)) {
                System.out.println("Project neighbourhood updated to '" + target.getNeighbourhood() + "'.");
            }

            System.out.println("Current types of units: ");
            ArrayList<UnitType> unit = target.getListOfUnits();
            for (UnitType type : unit) {
                System.out.println(type.getName());
            }
            System.out.println("Add or Delete unit type: ");
            String unitType = scanner.next().toLowerCase();
            while (!unitType.isEmpty() && (!unitType.equals("add") && !unitType.equals("delete"))) {
                System.out.println("Invalid input! Enter either 'Add' or 'Delete' or leave empty!");
                unitType = scanner.next().toLowerCase();
            }
            if (unitType.equals("add")) {
                System.out.println("Enter new unit type: ");
                String flat = scanner.next();
                System.out.println("Enter number of available units: ");
                int available = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter total number of units: ");
                int total = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter price per unit: ");
                double price = scanner.nextDouble();
                scanner.nextLine();
                if (controller.addUnit(target, flat, available, total, price)) {
                    System.out.println("Unit type added!");
                }
            }
            else if (unitType.equals("delete")) {
                System.out.println("Enter unit type to be deleted: ");
                String delete = scanner.next();
                if (controller.deleteUnit(target, delete)) {
                    System.out.println("Unit type successfully deleted.");
                }
                else {System.out.println("Deletion unsuccessful.");}
            }
            
            for (UnitType units : unit) {
                System.out.println("Enter new total number of " + units.getName() + " units: ");
                String total = scanner.next();
                if (controller.editTotalUnits(units, total)) {
                    System.out.println("New total number of units: " + units.getTotal());
                }
                
                System.out.println("Enter new available number of " + units.getName() + " units: ");
                String avail = scanner.next();
                try {
                    int availInt = Integer.parseInt(avail);
                    int totalInt = Integer.parseInt(total);
                
                    if (availInt <= totalInt) {
                        if (controller.editAvailUnits(units, avail)) {
                            System.out.println("New number of available units: " + units.getAvailable());
                        }
                    } else {
                        System.out.println("Invalid input! Available number of units cannot be greater than Total number of units!");
                        System.out.println("Number of available units not updated!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Not a valid number.");
                }
                

            }

            System.out.println("Current Application Opening Date: " + target.getApplicationOpeningDate());
            System.out.println("Enter new opening date (yyyy-mm-dd): ");
            String openingDate = scanner.next();
            if (controller.editOpeningDate(target, openingDate)) {
                System.out.println("New opening date: " + target.getApplicationOpeningDate());
            }

            System.out.println("Current Application Closing Date: " + target.getApplicationClosingDate());
            System.out.println("Enter new closing date (yyyy-mm-dd): ");
            String closingDate = scanner.next();
            if (controller.editClosingDate(target, closingDate)) {
                System.out.println("New closing date: " + target.getApplicationClosingDate());
            }

            System.out.println("Current number of officer slots: " + target.getOfficerSlots());
            System.out.println("Enter new number of slots: ");
            String slots = scanner.next();
            if (controller.editOSlots(target, slots)) {
                System.out.println("New number of officer slots: " + target.getOfficerSlots());
            }
        } else {System.out.println("Project not found.");}
    }

    public void handleDeletion() {
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
        Project foundProject = null;
        List<Project> projects = controller.getAllProjects();

        do {
            System.out.println("Enter project name: ");
            projectName = scanner.next();
            for (Project project : projects) {
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
        List<Project> projects = controller.getAllProjects();
        if (projects.isEmpty()) {
            System.out.println("No projects available!");
            return;
        }
        
        projects.forEach(p -> System.out.println(p.getProjectName()));
    }

    public void viewProjects() {
        System.out.println("===== View Project Listings ===");
        System.out.println("1. View All Projects");
        System.out.println("2. View My Projects");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.println("=== All Projects ===");
                List<Project> projects = controller.getAllProjects();
                projects.forEach(p -> System.out.println(p.getProjectName()));

            }
            case 2 -> {
                int managerId = Session.getSession().getCurrentUser().getUid();
                List<Project> myProjects = controller.getMyProjects(managerId);
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