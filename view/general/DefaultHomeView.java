
package view.general;


import view.MenuView;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import btoproject.BTOProject;

public class DefaultHomeView extends MenuView {
    private int userInput;
    private List<BTOProject> btoProject = new ArrayList<>();

    @Override
    public void show() {
        System.out.println("Welcome!");
        System.out.println("1) Apply for BTO Project");
        System.out.println("2) View ALL projects");
        System.out.println("3) View Applied BTO Projects");
        System.out.println("4) Book Flat");
        System.out.println("5) Request Withdrawal");
        System.out.println("6) Submit Enquiry");
        System.out.println("7) View Enquiry");
        System.out.println("8) Edit Enquiry");
        System.out.println("9) Delete Enquiry");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        try {
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    // code to print BTO projects that can be applied for
                    // code to apply for BTO Project
                    break;
                case 2:
                    System.out.println("== All Projects ==");
                    List<BTOProject> visibleProject = printProjects();
                    int i=1;
                    visibleProject.forEach(project -> System.out.println((i+1) + ") " + project.getProjectName()
                            + "\n  Location: " + project.getNeighbourhood()
                            + "\n  Available Units: " + project.getListOfUnits()
                            + "\n  Opening Date: " + project.getApplicationOpeningDate()
                            + "\n  Closing Date: " + project.getApplicationClosingDate() + "\n"));
                        // this part can be changed if there is function to print the details of the project in BTOProject
                    break;
                case 3:
                    // code to view projects that person has applied for
                    break;
                case 4:
                    // book flat (only when has a BTO that has been approved of)
                    break;
                case 5:
                    System.out.println("== Request Withdrawal ==");
                    // code to print out the project that indv has applied for
                    System.out.println("Confirm withdrawal? (Enter Y to confirm) ");
                    String withdrawInput = scanner.next();
                    if (withdrawInput.toLowerCase().equalsIgnoreCase("y")) {
                        // code to withdraw application
                    }
                    else {
                        System.out.println("Withdrawal unsuccessful!");
                    }
                    break;
                case 6:
                    // submit enquiry (waiting for enquiry class)
                    break;
                case 7:
                    // view enquiry (waiting for enquiry class)
                    break;
                case 8:
                    // edit enquiry (must have submitted an enquiry) (waiting for enquiry class)
                    break;
                case 9:
                    // delete enquiry (must have submitted an enquiry) (still waiting)
                    break;
                default: System.out.println("Invalid Input");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
    }

    public List<BTOProject> printProjects() {
        List<BTOProject> visibleProjects = btoProject.stream()
            .filter(project -> project.getVisibility().equalsIgnoreCase("on"))
            .collect(Collectors.toList());

        return visibleProjects;
    }
    
}
