package user.hdbmanager;

import btoproject.*;
import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;

public class HDBProjectController {
    private List<BTOProject> btoProjects = new ArrayList<>();

    /* public boolean createListing() {
        // TODO
        return false;
    } */

    public boolean editListing(BTOProject project, int choice, Object newValue) {
        switch (choice) {
            case 1 -> {
                project.setProjectName((String) newValue);
                return true;}
            case 2 -> {
                project.setNeighbourhood((String) newValue);
                return true;}
            /*case 3 -> {
                // TODO: code to edit the types of flats
                return true;}
            case 4 -> {
                // TODO: code to print and choose the type of flats to edit
                // code to edit the number of available rooms
                return true;
            } */
            case 5 -> {
                project.setApplicationOpeningDate((LocalDate) newValue);
                return true;}
            case 6 -> {
                project.setApplicationClosingDate((LocalDate) newValue);
            }
            case 7 -> {
                project.setOfficerSlots((int) newValue);
                return true;
            }
            default -> {return false;}
        }
        return false;
    }

    public boolean deleteProject(String projectName) {
        return btoProjects.removeIf(p -> p.getProjectName().equals(projectName));
    }

    public boolean toggleVisibility(BTOProject project, boolean visible) {
        project.setVisibility(visible);
        return true;
    }

    public List<BTOProject> getAllProjects() {
        return new ArrayList<>(btoProjects);
    }

    public List<BTOProject> getMyProjects(String managerNric) {
        return btoProjects.stream()
                .filter(p -> p.getManager().equals(managerNric))
                .collect(Collectors.toList());
    }

}