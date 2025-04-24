package project;

import java.util.HashMap;

public class ListingController {
    HashMap<String, Object> userInput;

    public ListingController(HashMap<String, Object> userInput) { this.userInput = userInput;}

    public static void create(HashMap<String, Object> userInput) {
//        // TODO -- function from projectRepository to find the max id for projectId
//        boolean validateCreation = true;
//
//        if (validateCreation) {
//            Project project = new Project(maxId + 1, (String) userInput.get("name"), (String) userInput.get("neighbourhood"), (LocalDate) userInput.get("openingDate"), (LocalDate) userInput.get("closingDate"), (int) userInput.get("officerSlots"), false);
//            // TODO code to create project and its respective units in repository
//
//            System.out.println("Successfully created");
//            Session.getSession().setCurrentView(new ProjectView());
//        } else {
//            System.out.println("Error creating listing");
//        }



    }
}