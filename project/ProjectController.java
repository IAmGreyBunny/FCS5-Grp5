package project;

import session.Session;
import user.User;
import user.UserRepository;
import user.applicant.Applicant;
import user.hdbofficer.HDBOfficer;
import view.general.ProjectListingView;
//import view.hdbmanager.ManagerProjectManagementView;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectController {
    HashMap<String, Object> userInput;

    public ProjectController(HashMap<String, Object> userInput) {
        this.userInput = userInput;
    }

    public static void createListingWithUserInput(HashMap<String, Object> userInput) {

        int projectId = ProjectRepository.findMaxProjectId() + 1;
        String name = (String) userInput.get("name");
        String neighbourhood = (String) userInput.get("neighbourhood");
        LocalDate openingDate = (LocalDate) userInput.get("openingDate");
        LocalDate closingDate = (LocalDate) userInput.get("closingDate");
        int officerSlots = (int) userInput.get("officerSlots");
        boolean visibility = true;

        Project project = new Project(projectId, name, neighbourhood, openingDate, closingDate, officerSlots, visibility);
        ProjectRepository.createProject(project);

    }

    public static void editListingWithUserInput(Project oldProject, HashMap<String, Object> userInput) {

        int projectId = oldProject.getProjectId();
        String name = (String) userInput.get("name");
        String neighbourhood = (String) userInput.get("neighbourhood");
        LocalDate openingDate = (LocalDate) userInput.get("openingDate");
        LocalDate closingDate = (LocalDate) userInput.get("closingDate");
        int officerSlots = (int) userInput.get("officerSlots");
        boolean visibility = (boolean) userInput.get("visibility");

        if (name.isEmpty()) {
            name = oldProject.getProjectName();
        }
        if (neighbourhood.isEmpty()) {
            neighbourhood = oldProject.getNeighbourhood();
        }
        if (openingDate == null) {
            openingDate = oldProject.getApplicationOpeningDate();
        }
        if (closingDate == null) {
            closingDate = oldProject.getApplicationClosingDate();
        }
        if (officerSlots == -1) {
            officerSlots = oldProject.getOfficerSlots();
        }

        Project updatedProject = new Project(projectId, name, neighbourhood, openingDate, closingDate, officerSlots, visibility);

        ProjectRepository.updateProject(updatedProject);


    }

    public static void deleteListing(int projectId)
    {
        ProjectRepository.deleteProject(projectId);
    }

    public static void createUnitTypeWithUserInput(int projectId, HashMap<String, Object> userInput){
        int unitTypeId = ProjectRepository.findMaxUnitTypeId() + 1;
        String name = (String) userInput.get("name");
        int available = (int) userInput.get("available");
        int total = (int) userInput.get("total");
        double pricePerUnit = (double) userInput.get("pricePerUnit");

        UnitType unitType = new UnitType(unitTypeId, projectId, name, available, total, pricePerUnit);
        ProjectRepository.createUnitType(unitType);

    }

    public static void editUnitTypeWithUserInput(UnitType oldUnitType, HashMap<String, Object> userInput) {

        int unitTypeId = ProjectRepository.findMaxUnitTypeId() + 1;
        String name = (String) userInput.get("name");
        int available = (int) userInput.get("available");
        int total = (int) userInput.get("total");
        double pricePerUnit = (double) userInput.get("pricePerUnit");

        if (name.isEmpty()) {
            name = oldUnitType.getName();
        }
        if (available == -1) {
            available = oldUnitType.getAvailable();
        }
        if (total == -1) {
            total = oldUnitType.getTotal();
        }
        if (pricePerUnit == -1) {
            pricePerUnit = oldUnitType.getPricePerUnit();
        }

        UnitType updatedUnitType = new UnitType(unitTypeId, oldUnitType.getProjectId(), name, available,total,pricePerUnit);

        ProjectRepository.updateUnitType(updatedUnitType);


    }

    public static void deleteUnitType(int unitTypeId){
        ProjectRepository.deleteUnitType(unitTypeId);
    }

    public static ArrayList<Project> getApplicableProject(User user) {
        ArrayList<Project> listOfApplicableProjects = new ArrayList<>();

        Set<Integer> setOfProjectIds = new HashSet<Integer>();
        for (UnitType unitType : getApplicableUnitTypes(user)) {
            setOfProjectIds.add(unitType.getProjectId());
        }
        for (int id : setOfProjectIds) {
            listOfApplicableProjects.add(ProjectRepository.getProjectById(id));
        }

        if (user instanceof HDBOfficer) {
            ArrayList<Integer> excludeProjectId = ProjectRepository.getOfficerProjectsId(user.getUid());
            listOfApplicableProjects = listOfApplicableProjects.stream()
                    .filter(project -> project.getVisibility())
                    .filter(project -> !excludeProjectId.contains(project.getProjectId()))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        return listOfApplicableProjects;
    }

    public static ArrayList<UnitType> getApplicableUnitTypes(User user) {
        ArrayList<UnitType> listOfApplicableUnits = ProjectRepository.getAllUnitsType();

        if (user.getAge() < 21 && !user.getMaritalStatus()) {
            listOfApplicableUnits = new ArrayList<>();
            return listOfApplicableUnits;
        } else if (user.getAge() > 35 && !user.getMaritalStatus()) {
            listOfApplicableUnits = listOfApplicableUnits.stream()
                    .filter(unitType -> unitType.getName().equals("2-Room"))
                    .collect(Collectors.toCollection(ArrayList::new));
            return listOfApplicableUnits;
        } else {
            return listOfApplicableUnits;
        }

    }

    public static ArrayList<UnitType> getApplicableUnitTypes(int projectId) {
        ArrayList<UnitType> listOfApplicableUnits = getApplicableUnitTypes(Session.getSession().getCurrentUser());
        listOfApplicableUnits = listOfApplicableUnits.stream()
                .filter(unitType -> unitType.getProjectId() == projectId)
                .collect(Collectors.toCollection(ArrayList::new));

        return listOfApplicableUnits;
    }

    public static ArrayList<UnitType> getUnitTypesByProject(int projectId) {
        return ProjectRepository.getUnitTypesByProjectId(projectId);
    }

    public static Project getProjectById(int projectId) {
        return ProjectRepository.getProjectById(projectId);
    }
}