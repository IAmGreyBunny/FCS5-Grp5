package view.hdbmanager;

import session.Session;
import view.MenuView;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;

public class ProjectView extends MenuView {
    private int userInput;
    private Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    //private ProjectViewController controller = new ProjectViewController(scanner);

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
                    //controller.createNewProject();
                    break;
                case 2:
                    //controller.handleEditing();
                    break;
                case 3:
                    //controller.handleDeletion();
                    break;
                case 4:
                    //controller.toggleVisibility();
                    break;
                case 5:
                    //controller.viewProjects();
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

    
}
