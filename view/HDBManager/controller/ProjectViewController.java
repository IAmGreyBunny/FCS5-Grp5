package view.hdbmanager.controller;

import btoproject.BTOProject;
import btoproject.BTOUnitType;
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
        List<BTOProject> projects = controller.getAllProjects();
        BTOProject target = projects.stream().filter(p -> p.getProjectName().equalsIgnoreCase(projectName)).findFirst().orElse(null);
        if (target != null) {
            System.out.println("Editing Project...");
            BTOProject edited = EditBTOForm(target);
            controller.editProject(target, edited);
            System.out.println("Project updated!");
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
                List<BTOProject> projects = controller.getAllProjects;
                projects.forEach(p -> System.out.println(p.getProjectName()));

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