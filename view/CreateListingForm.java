//package view;
//
//import listing.ListingController;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeParseException;
//import java.util.HashMap;
//import java.util.Scanner;
//
//public class CreateListingForm extends FormView {
//
//    HashMap<String, Object> userInput = new HashMap<>();
//
//    @Override
//    public void prompt() {
//        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
//
//        System.out.println("--- Create BTO Project ---");
//        System.out.println("Enter name of new BTO Project: ");
//        String name = scanner.next();
//        userInput.put("name",name);
//
//        System.out.println("Enter location: ");
//        String location = scanner.next();
//        userInput.put("location",location);
//
//        LocalDate estimatedCompletionDate = getValidDate(scanner, "Enter estimated completion date (YYYY-MM-DD): ");
//        userInput.put("estimatedCompletionDate",estimatedCompletionDate);
//
//        /* LocalDate openingDate = getValidDate(scanner, "Enter application opening date (YYYY-MM-DD): ");
//        userInput.put("openingDate",openingDate);
//
//        LocalDate closingDate = getValidDate(scanner, "Enter application closing date (YYYY-MM-DD): ");
//        userInput.put("closingDate", closingDate); */
//
//        System.out.println("Are there 2-room flat types in this project (Y/N)? ");
//        String input2 = scanner.next();
//        switch (input2) {
//            case "Y" :
//                HashMap<String, Object> twoRoom = new HashMap<>();
//                twoRoom.put("name", "2-room");
//                getUnitData(scanner, twoRoom);
//                userInput.put("2-room", twoRoom);
//                break;
//            case "N" :
//                userInput.put("2-room", null);
//                break;
//        }
//
//        System.out.println("Are there 3-room flat types in this project (Y/N)? ");
//        String input3 = scanner.next();
//        switch (input3) {
//            case "Y" :
//                HashMap<String, Object> threeRoom = new HashMap<>();
//                threeRoom.put("name", "3-room");
//                getUnitData(scanner, threeRoom);
//                userInput.put("3-room", threeRoom);
//                break;
//            case "N" :
//                userInput.put("3-room", null);
//                break;
//        }
//
//        int officer;
//        do {
//            System.out.println("Enter number of officer slots (max 10): ");
//            officer = scanner.nextInt();
//            if (officer<0 || officer>10) {
//                System.out.println("Invalid number!");
//            }
//            else {break;}
//        } while (true);
//        userInput.put("officerSlots", officer);
//
//        System.out.println("Creating project...");
//        ListingController.create(this.getUserInput());
//
//    }
//
//    @Override
//    public HashMap<String,Object> getUserInput() {
//        return userInput;
//    }
//
//    @Override
//    public void show() {
//        prompt();
//    }
//
//    private LocalDate getValidDate(Scanner scanner, String message) {
//        while (true) {
//            System.out.println(message);
//            String input = scanner.next();
//
//            try {
//                return LocalDate.parse(input);
//            } catch (DateTimeParseException e) {
//                System.out.println("Invalid date format!");
//            }
//        }
//    }
//
//    private void getUnitData(Scanner scanner, HashMap<String, Object> unit) {
//        System.out.println("Enter total number of units: ");
//        int total = scanner.nextInt();
//        unit.put("total",total);
//
//        System.out.println("Enter number of units available: ");
//        int avail = scanner.nextInt();
//        unit.put("available",avail);
//
//        System.out.println("Enter price per unit: ");
//        double price = scanner.nextDouble();
//        unit.put("pricePerUnit",price);
//
//    }
//
//}