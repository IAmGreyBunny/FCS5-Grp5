package project;

import session.Session;
import user.User;
import user.UserRepository;
import user.applicant.Applicant;
import user.hdbofficer.HDBOfficer;
import view.general.ProjectListingView;
import view.hdbmanager.ManagerProjectManagementView;

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

    public static void createListing(HashMap<String, Object> userInput) {
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
        Session.getSession().setCurrentView(new ManagerProjectManagementView());
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