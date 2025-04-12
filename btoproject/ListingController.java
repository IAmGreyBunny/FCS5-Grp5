package btoproject;

import java.util.HashMap;
import java.time.LocalDate;

//public class ListingController {
//    public static void create(HashMap<String, Object> userInput) {
//        String name = (String) userInput.get("name");
//        String location = (String) userInput.get("location");
//        LocalDate estimatedCompletionDate = (LocalDate) userInput.get("estimatedCompletionDate");
//        /*LocalDate openingDate = userInput.get("openingDate");
//        LocalDate closingDate = userInput.get("closingDate"); */
//        int officerSlots = (int) userInput.get("officerSlots");
//        BTOProject btoProject = new BTOProject(name, location, estimatedCompletionDate);
//
//        HashMap<String, Object> twoRoom = (HashMap<String,Object>) userInput.get("2-room");
//        if (twoRoom != null) {
//            int total = (int) twoRoom.get("total");
//            int available = (int) twoRoom.get("available");
//            double pricePerUnit = (double) twoRoom.get("pricePerUnit");
//            btoProject.addUnitType("2-room", available, total, pricePerUnit);
//        }
//        HashMap<String,Object> threeRoom = (HashMap<String,Object>) userInput.get("3-room");
//        if (threeRoom != null) {
//            int total = (int) threeRoom.get("total");
//            int available = (int) threeRoom.get("available");
//            double pricePerUnit = (double) threeRoom.get("pricePerUnit");
//            btoProject.addUnitType("3-room", available, total, pricePerUnit);
//        }
//
//        System.out.println("Project successfully created!");
//
//    }
//}