package view.hdbmanager;

import project.Project;
import project.ProjectController;
import project.ProjectRepository;
import session.Session;
import view.HomeViewFactory;
import view.IMenuView;
import view.form.CreateProjectListingForm;
import view.form.EditListingForm;

import java.util.*;

/**
 * Represents the view for HDB Managers to manage BTO projects.
 * It allows managers to create, edit, delete, view, and toggle the visibility of projects.
 */
public class ManagerProjectManagementView implements IMenuView {
    private int userInput;
    private final Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    private final HashMap<String, Object> input = new HashMap<>();

    /**
     * Displays the menu for managing BTO projects.
     * Returns to the manager's home view on exit.
     */
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
            userInput = Integer.parseInt(scanner.nextLine());

            switch (userInput) {
                case 1 -> Session.getSession().setCurrentView(new CreateProjectListingForm());

                case 2 -> {
                    List<Project> allProjects = ProjectRepository.getAllProjects();

                    if (allProjects.isEmpty()) {
                        System.out.println("No projects created.");
                        break;
                    }

                    allProjects.forEach(p -> System.out.println("ID: " + p.getProjectId() + "   Name: " + p.getProjectName()));
                    System.out.print("Enter project ID to edit: ");
                    int id = Integer.parseInt(scanner.nextLine());

                    Project oldProject = ProjectRepository.getProjectById(id);
                    if (oldProject == null) {
                        System.out.println("Invalid project ID.");
                        break;
                    }

                    Session.getSession().setCurrentView(new EditListingForm(oldProject));
                }

                case 3 -> deleteListing();
                case 4 -> toggleVisibility();
                case 5 -> viewListings();
                case 6 -> {
                    // Returns Home
                    Session.getSession().setCurrentView(HomeViewFactory.getHomeViewForUser(Session.getSession().getCurrentUser()));
                }
                default -> System.out.println("Invalid Input");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid Input");
        }
    }

    /**
     * Handles deletion of a project listing by prompting for the project ID.
     * Deletes the project using ProjectController.
     */
    private void deleteListing() {
        System.out.println("=== Delete Project ===");
        System.out.print("Enter ID of project to delete: ");
        int projectId = Integer.parseInt(scanner.nextLine());

        ProjectController.deleteListing(projectId);
        System.out.println("Project deleted.");
    }

    /**
     * Toggles the visibility status of a specific project.
     * Allows managers to hide or show projects from applicants.
     */
    private void toggleVisibility() {
        System.out.println("=== Toggle Visibility ===");
        List<Project> projects = ProjectRepository.getAllProjects();
        if (projects.isEmpty()) {
            System.out.println("No projects created.");
            return;
        }

        projects.forEach(p -> System.out.printf("ID: %d - %s [Current: %s]%n", p.getProjectId(), p.getProjectName(), p.getVisibility() ? "on" : "off"));
        System.out.print("Enter project ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        Project project = ProjectRepository.getProjectById(id);
        if (project != null) {
            boolean newVis = !project.getVisibility();
            project.setVisibility(newVis);
            ProjectRepository.updateProject(project);
            System.out.println("Visibility toggled to: " + (newVis ? "on" : "off"));
        } else {
            System.out.println("Project not found.");
        }
    }

    /**
     * Displays a list of project listings.
     * Managers can choose to view all projects or only those assigned to them.
     */
    private void viewListings() {
        System.out.println("=== View Project Listings ===");
        System.out.println("1. View All Projects");
        System.out.println("2. View My Projects");

        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            System.out.println("--- All Projects ---");
            ProjectRepository.getAllProjects().forEach(p ->
                    System.out.printf("ID: %d | Name: %s%n", p.getProjectId(), p.getProjectName()));
        } else if (choice == 2) {
            System.out.println("--- My Projects ---");
            int managerId = Session.getSession().getCurrentUser().getUid();
            ProjectRepository.getAllProjects().stream()
                    .filter(p -> p.getManager() != null && p.getManager().getUid() == managerId)
                    .forEach(p -> System.out.printf("ID: %d | Name: %s%n", p.getProjectId(), p.getProjectName()));
        } else {
            System.out.println("Invalid choice.");
        }
    }
}
