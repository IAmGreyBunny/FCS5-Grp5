package view.hdbmanager.controller;

import java.time.LocalDate;
import java.util.HashMap;
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

        UnitType unit = (UnitType) userInput.get("unitType");

        if (unit != null) {
            String unitName = (String) userInput.get("unitName");
            int totalUnits = (int) userInput.get("totalUnits");
            int availableUnits = (int) userInput.get("availableUnits");
            double price = (double) userInput.get("price");

            updatedProject.addUnitType(unitName, availableUnits, totalUnits, price);
        }

        System.out.println("Project edited");
        Session.getSession().setCurrentView(new ProjectView());
    }
}
