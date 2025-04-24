//package view.hdbmanager;
//
//import project.Project;
//import project.ProjectController;
//import project.ProjectRepository;
//import session.Session;
//import view.MenuView;
//
//import java.util.*;
//
//public class ManagerProjectManagementView extends MenuView {
//    private int userInput;
//    private Scanner scanner = new Scanner(System.in).useDelimiter("\n");
//    private ProjectController projectController = new ProjectController();
//    private List<Project> projects = ProjectRepository.getAllProjects();
//    HashMap<String, Object> input = new HashMap<>();
//
//
//    @Override
//    public void show() {
//        System.out.println("==== Manage BTO Project ====");
//        System.out.println("1. Create New Project Listing");
//        System.out.println("2. Edit Project Listing");
//        System.out.println("3. Delete Project Listing");
//        System.out.println("4. Toggle Visibility");
//        System.out.println("5. View Project Listings");
//        System.out.println("6. Return");
//
//        int projectId = 0;
//
//        try {
//            userInput = scanner.nextInt();
//
//            switch (userInput) {
//                case 1:
//                    System.out.println("=== Create Listing ===");
//                    projectController.createListing();
//                    break;
//                case 2:
//                    System.out.println("=== Edit Listing ===");
//                    List<Project> printProjects = projectController.getAllProjects();
//                    if (printProjects.isEmpty()) {
//                        System.out.println("No projects created.");
//                    }
//                    else {
//                        printProjects.forEach(p -> System.out.println("ID: " + p.getProjectId() + "   Name: " + p.getProjectName()));
//                    }
//                    projectController.editListing();
//                    break;
//                case 3:
//                    System.out.println("=== Delete Project ===");
//                    System.out.println("Enter ID of project to be deleted: ");
//                    projectId = scanner.nextInt();
//                    projectController.deleteProject(projectId);
//                    break;
//                case 4:
//                    System.out.println("=== Visibility ===");
//                    if (projects.isEmpty()) {
//                        System.out.println("No projects created.");
//                        break;
//                    }
//                    System.out.println("- Project id -");
//                    projects.forEach(p -> System.out.println(p.getProjectId()));
//                    System.out.println("Enter project id: ");
//                    projectId = scanner.nextInt();
//                    for (Project project : projects) {
//                        if (project.getProjectId() == projectId) {
//                            System.out.println("Current visibility: " + (project.getVisibility() ? "on" : "off"));
//                            if (projectController.toggleVisibility(project)) {
//                                System.out.println("Set to new visibility: " + (project.getVisibility() ? "on" : "off"));
//                            }
//                            else {
//                                System.out.println("Toggling unsuccessful)");
//                            }
//                            break;
//                        }
//                    }
//                    System.out.println("Project not found");
//                    break;
//                case 5:
//                    System.out.println("=== View Project Listings ===");
//                    System.out.println("1. View All Projects");
//                    System.out.println("2. View My Projects");
//
//                    int choice = scanner.nextInt();
//                    switch (choice) {
//                        case 1 -> {
//                            System.out.println("--- All Projects ---");
//                            List<Project> printProject = projectController.getAllProjects();
//                            if (printProject.isEmpty()) {
//                                System.out.println("No projects created.");
//                            }
//                            else {
//                                printProject.forEach(p -> System.out.println("ID: " + p.getProjectId() + "   Name: " + p.getProjectName()));
//                            }
//                        }
//                        case 2 -> {
//                            System.out.println("--- My Projects ---");
//                            int managerId = Session.getSession().getCurrentUser().getUid();
//                            List<Project> printProject = projectController.getMyProjects(managerId);
//                            if (printProject.isEmpty()) {
//                                System.out.println("No projects created.");
//                            }
//                            else {
//                                printProject.forEach(p -> System.out.println("ID: " + p.getProjectId() + "   Name: " + p.getProjectName()));
//                            }
//                        }
//                        default -> {
//                            System.out.println(("Invalid choice!"));
//                        }
//                    }
//                    break;
//                case 6:
//                    Session.getSession().setCurrentView(new HDBManagerHomeView());
//                    Session.getSession().getCurrentView().show();
//                    break;
//                default: System.out.println("Invalid Input");
//            }
//
//        } catch (InputMismatchException e) {
//            System.out.println("Invalid Input");
//        }
//        Session.getSession().setCurrentView(new ManagerProjectManagementView());
//    }
//
//
//}
