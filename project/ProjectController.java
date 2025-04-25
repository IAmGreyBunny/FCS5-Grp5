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

/**
 * Handles operations related to BTO project and managaing units like creating,
 * editing, deleting projects and unit types, and filtering based on eligibility.
 */
public class ProjectController {
    HashMap<String, Object> userInput;

    /**
     * Constructs a ProjectController using user input.
     * @param userInput A map of user provided values used for project operations.
     */
    public ProjectController(HashMap<String, Object> userInput) {
        this.userInput = userInput;
    }

    /**
     * Creates a new BTO project using the given user input.
     * Automatically assigns the current logged in manager to the project.
     * @param userInput A map of project attributes entered by the user.
     * @return The created Project object.
     */
    public static Project createListingWithUserInput(HashMap<String, Object> userInput) {

        int projectId = ProjectRepository.findMaxProjectId() + 1;
        String name = (String) userInput.get("name");
        String neighbourhood = (String) userInput.get("neighbourhood");
        LocalDate openingDate = (LocalDate) userInput.get("openingDate");
        LocalDate closingDate = (LocalDate) userInput.get("closingDate");
        int officerSlots = (int) userInput.get("officerSlots");
        boolean visibility = true;

        Project project = new Project(projectId, name, neighbourhood, openingDate, closingDate, officerSlots, visibility);
        ProjectRepository.createProject(project);

        ProjectRepository.assignManager(projectId,Session.getSession().getCurrentUser().getUid());

        return ProjectRepository.getProjectById(projectId);
    }

    /**
     * Updates an existing project with the values provided.
     * @param oldProject The original project to be edited.
     * @param userInput  A map of updated values for the project.
     */
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

    /**
     * Deletes a project from the system.
     * @param projectId The ID of the project to delete.
     */
    public static void deleteListing(int projectId)
    {
        ProjectRepository.deleteProject(projectId);
    }

    /**
     * Creates a new unit type under the given project.
     * @param projectId  ID of the project the unit belongs to.
     * @param userInput  A map of unit attributes entered by the user.
     */
    public static void createUnitTypeWithUserInput(int projectId, HashMap<String, Object> userInput){
        int unitTypeId = ProjectRepository.findMaxUnitTypeId() + 1;
        String name = (String) userInput.get("name");
        int available = (int) userInput.get("available");
        int total = (int) userInput.get("total");
        double pricePerUnit = (double) userInput.get("pricePerUnit");

        UnitType unitType = new UnitType(unitTypeId, projectId, name, available, total, pricePerUnit);
        ProjectRepository.createUnitType(unitType);

    }

    /**
     * Updates an existing unit type.
     * @param oldUnitType The original unit type to be edited.
     * @param userInput   A map of updated values for the unit.
     */
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

    /**
     * Deletes a unit type from a project.
     * @param unitTypeId ID of the unit type to delete.
     */
    public static void deleteUnitType(int unitTypeId){
        ProjectRepository.deleteUnitType(unitTypeId);
    }

    public static void assignManager(int uid,int projectId){

    }

    /**
     * Returns a list of projects that are applicable for a given user based on their role and status.
     * @param user The user to evaluate eligibility for.
     * @return A list of applicable Project objects.
     */
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

    /**
     * Returns a list of unit types the given user is eligible to apply for.
     * @param user The user to check eligibility against.
     * @return A list of applicable unit types.
     */
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

    /**
     * Filters unit types for a specific project that are also applicable to the current user.
     * @param projectId The project to filter unit types for.
     * @return A list of applicable unit types for that project.
     */
    public static ArrayList<UnitType> getApplicableUnitTypes(int projectId) {
        ArrayList<UnitType> listOfApplicableUnits = getApplicableUnitTypes(Session.getSession().getCurrentUser());
        listOfApplicableUnits = listOfApplicableUnits.stream()
                .filter(unitType -> unitType.getProjectId() == projectId)
                .collect(Collectors.toCollection(ArrayList::new));

        return listOfApplicableUnits;
    }

    /**
     * Returns all unit types associated with a specific project.
     * @param projectId The ID of the project.
     * @return A list of unit types in the project.
     */
    public static ArrayList<UnitType> getUnitTypesByProject(int projectId) {
        return ProjectRepository.getUnitTypesByProjectId(projectId);
    }

    /**
     * Retrieves a project by its ID.
     * @param projectId The ID of the project to retrieve.
     * @return The corresponding Project object.
     */
    public static Project getProjectById(int projectId) {
        return ProjectRepository.getProjectById(projectId);
    }
}