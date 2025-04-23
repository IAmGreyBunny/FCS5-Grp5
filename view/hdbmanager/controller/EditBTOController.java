package view.hdbmanager.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import project.*;
import session.Session;
import view.hdbmanager.ProjectView;

public class EditBTOController {
    HashMap<String, Object> userInput;

    public EditBTOController(HashMap<String, Object> userInput) {
        this.userInput = userInput;
    }

    public void editListing() {

        Project project = (Project) userInput.get("project");
        int projectId = project.getProjectId();
        String name = (String) userInput.get("name");
        String neighbourhood = (String) userInput.get("neighbourhood");
        LocalDate openingDate = (LocalDate) userInput.get("openingDate");
        LocalDate closingDate = (LocalDate) userInput.get("closingDate");
        int officerSlots = (int) userInput.get("officerSlots");
        boolean visibility = project.getVisibility();

        if (name.isEmpty()) {
            name = project.getProjectName();
        }
        if (neighbourhood.isEmpty()) {
            neighbourhood = project.getNeighbourhood();
        }
        if (openingDate == null) {
            openingDate = project.getApplicationOpeningDate();
        }
        if (closingDate == null) {
            closingDate = project.getApplicationClosingDate();
        }
        if (officerSlots == 0) {
            officerSlots = project.getOfficerSlots();
        }

        Project updatedProject = new Project(projectId, name, neighbourhood, openingDate, closingDate, officerSlots, visibility);
        //TODO code to edit the project

//        List<UnitType> units = project.getListOfUnits();
//        for (UnitType unit : units) {
//            if (unit.getName().equalsIgnoreCase("2-Room")) {
//                int totalUnits = (int) userInput.get("2-room totalUnits");
//                int availUnits = (int) userInput.get("2-room availableUnits");
//                double price = (double) userInput.get("2-room price");
//
//                if (availUnits == 0) {
//                    availUnits = unit.getAvailable();
//                }
//                if (totalUnits == 0) {
//                    totalUnits = unit.getTotal();
//                }
//                if (totalUnits <= availUnits) {
//                    System.out.println("Invalid total or available number of units.");
//                    System.out.println("Units not updated!");
//                    break;
//                }
//                if (price == 0) {
//                    price = unit.getPricePerUnit();
//                }
//
//                UnitType updated2Room = new UnitType("2-Room", availUnits, totalUnits, price);
//                // TODO code to edit the unit type in project
//            }
//            else if (unit.getName().equalsIgnoreCase("3-Room")) {
//                int totalUnits = (int) userInput.get("3-room totalUnits");
//                int availUnits = (int) userInput.get("3-room availableUnits");
//                double price = (double) userInput.get("3-room price");
//
//                if (availUnits == 0) {
//                    availUnits = unit.getAvailable();
//                }
//                if (totalUnits == 0) {
//                    totalUnits = unit.getTotal();
//                }
//                if (totalUnits <= availUnits) {
//                    System.out.println("Invalid total or available number of units.");
//                    System.out.println("Units not updated!");
//                    break;
//                }
//                if (price == 0) {
//                    price = unit.getPricePerUnit();
//                }
//
//                UnitType updated2Room = new UnitType("3-Room", availUnits, totalUnits, price);
//                // TODO code to edit the unit type in project
//            }
//        }

        System.out.println("Project edited");
        Session.getSession().setCurrentView(new ProjectView());
    }
}
