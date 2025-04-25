package view.hdbmanager;

import project.Project;
import project.ProjectController;
import project.ProjectRepository;
import session.Session;
import view.MenuView;
import view.form.CreateProjectListingForm;
import view.form.EditListingForm;

import java.time.LocalDate;
import java.util.*;

public class ManagerProjectManagementView extends MenuView {
    private int userInput;
    private final Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    private final HashMap<String, Object> input = new HashMap<>();

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
                case 2 -> Session.getSession().setCurrentView(new EditListingForm());
                case 3 -> deleteListing();
                case 4 -> toggleVisibility();
                case 5 -> viewListings();
                case 6 -> Session.getSession().setCurrentView(new HDBManagerHomeView());
                default -> System.out.println("Invalid Input");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid Input");
        }
    }

    private void createListing() {
        System.out.println("=== Create Listing ===");
        System.out.print("Enter project name: ");
        input.put("name", scanner.nextLine());

        System.out.print("Enter neighbourhood: ");
        input.put("neighbourhood", scanner.nextLine());

        System.out.print("Enter opening date (yyyy-MM-dd): ");
        input.put("openingDate", LocalDate.parse(scanner.nextLine()));

        System.out.print("Enter closing date (yyyy-MM-dd): ");
        input.put("closingDate", LocalDate.parse(scanner.nextLine()));

        System.out.print("Enter officer slots: ");
        input.put("officerSlots", Integer.parseInt(scanner.nextLine()));

        input.put("visibility", true); // default to visible

        ProjectController.createListingWithUserInput(input);
        System.out.println("Project created successfully!");
    }

    private void editListing() {
        System.out.println("=== Edit Listing ===");
        List<Project> allProjects = ProjectRepository.getAllProjects();

        if (allProjects.isEmpty()) {
            System.out.println("No projects created.");
            return;
        }

        allProjects.forEach(p -> System.out.println("ID: " + p.getProjectId() + "   Name: " + p.getProjectName()));
        System.out.print("Enter project ID to edit: ");
        int id = Integer.parseInt(scanner.nextLine());

        Project oldProject = ProjectRepository.getProjectById(id);
        if (oldProject == null) {
            System.out.println("Invalid project ID.");
            return;
        }

        System.out.print("Enter new name (leave blank to skip): ");
        String name = scanner.nextLine();
        input.put("name", name);

        System.out.print("Enter new neighbourhood (leave blank to skip): ");
        String neighbourhood = scanner.nextLine();
        input.put("neighbourhood", neighbourhood);

        System.out.print("Enter new opening date (yyyy-MM-dd, leave blank to skip): ");
        String openStr = scanner.nextLine();
        input.put("openingDate", openStr.isEmpty() ? null : LocalDate.parse(openStr));

        System.out.print("Enter new closing date (yyyy-MM-dd, leave blank to skip): ");
        String closeStr = scanner.nextLine();
        input.put("closingDate", closeStr.isEmpty() ? null : LocalDate.parse(closeStr));

        System.out.print("Enter officer slots (-1 to keep current): ");
        int slots = Integer.parseInt(scanner.nextLine());
        input.put("officerSlots", slots);

        System.out.print("Visibility (true/false): ");
        input.put("visibility", Boolean.parseBoolean(scanner.nextLine()));

        ProjectController.editListingWithUserInput(oldProject, input);
        System.out.println("Project updated.");
    }

    private void deleteListing() {
        System.out.println("=== Delete Project ===");
        System.out.print("Enter ID of project to delete: ");
        int projectId = Integer.parseInt(scanner.nextLine());

        ProjectController.deleteListing(projectId);
        System.out.println("Project deleted.");
    }

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
