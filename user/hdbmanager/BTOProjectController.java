package user.hdbmanager;

import project.Project;
import project.UnitType;
import view.hdbmanager.ProjectView;

import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class BTOProjectController {
    private List<Project> btoProjects = new ArrayList<>();


    /* public boolean createListing() {
        // TODO
        return false;
    } */

    public boolean editName(Project project, String name) {
        if (name != null && !name.trim().isEmpty()) {
            project.setProjectName(name);
            return true;
        }
        return false;
    }

    public boolean editNeighbourhood(Project project, String neighbourhood) {
        if (neighbourhood != null && !neighbourhood.trim().isEmpty()) {
            project.setNeighbourhood(neighbourhood);
            return true;
        }
        return false;
    }
    public boolean addUnit(Project project, String flat, int available, int total, double price) {
        project.addUnitType(flat, available, total, price);
        return true;
    }

    public boolean deleteUnit(Project project, String name) {
        if (name != null && !name.trim().isEmpty()) {
            return project.deleteUnitType(name);
        }
        return false;
    }

    public boolean editTotalUnits(UnitType units, String total) {
        try {
            if (total!=null && !total.trim().isEmpty()) {
                int iTotal = Integer.parseInt(total);
                units.setTotal(iTotal);
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Total units not updated.");
        }
        return false;
    }

    public boolean editAvailUnits(UnitType unit, String avail) {
        try {
            if (avail != null && !avail.trim().isEmpty()) {
                int availInt = Integer.parseInt(avail);
                unit.setAvailable(availInt);
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Available units not updated.");
        }
        return false;
    }

    public boolean editOpeningDate(Project project, String date) {
        try {
            if (date != null && !date.trim().isEmpty()) {
                LocalDate newDate = LocalDate.parse(date);
                project.setApplicationOpeningDate(newDate);
                return true;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Opening date not updated.");
        }
        return false;
    }

    public boolean editClosingDate(Project project, String date) {
        try {
            if (date != null && !date.trim().isEmpty()) {
                LocalDate newDate = LocalDate.parse(date);
                project.setApplicationClosingDate(newDate);
                return true;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Closing date not updated.");
        }
        return false;
    }

    public boolean editOSlots (Project project, String num) {
        try {
            if (num != null && !num.trim().isEmpty()) {
                int iNum = Integer.parseInt(num);
                project.setOfficerSlots(iNum);
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Number of officer slots not updated.");
        }
        return false;
    }

    public boolean deleteProject(String projectName) {
        return btoProjects.removeIf(p -> p.getProjectName().equals(projectName));
    }

    public boolean toggleVisibility(Project project, boolean visible) {
        project.setVisibility(visible);
        return true;
    }

    public List<Project> getAllProjects() {
        return new ArrayList<>(btoProjects);
    }

    public List<Project> getMyProjects(String managerId) {
        return btoProjects.stream()
                .filter(p -> p.getManager().equals(managerId))
                .collect(Collectors.toList());
    }

}