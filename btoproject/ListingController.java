package btoproject;

import java.util.HashMap;


//public class ListingController {
//    public static void login(HashMap<String, Object> userInput) {
//        String name = userInput.get("name");
//        String location = userInput.get("location");
//        LocalDate estimatedCompletionDate = userInput.get("estimatedCompletionDate");
//        /*LocalDate openingDate = userInput.get("openingDate");
//        LocalDate closingDate = userInput.get("closingDate"); */
//        int officerSlots = userInput.get("officerSlots");
//        BTOProject btoProject = new BTOProject(name, location, estimatedCompletionDate);
//
//        Object twoRoom = userInput.get("2-room");
//        if (twoRoom != null) {
//            int total = twoRoom.get("total");
//            int available = twoRoom.get("available");
//            double pricePerUnit = twoRoom.get("pricePerUnit");
//            btoProject.addUnitType(twoRoom, available, total, pricePerUnit);
//        }
//        Object threeRoom = userInput.get("3-room");
//        if (threeRoom != null) {
//            int total = threeRoom.get("total");
//            int available = threeRoom.get("available");
//            double pricePerUnit = threeRoom.get("pricePerUnit");
//            btoProject.addUnitType(threeRoom, available, total, pricePerUnit);
//        }
//
//        System.out.println("Project successfully created!");
//
//    }
//}
