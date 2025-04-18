package user;

import btoproject.*;
import user.HDBManager;
import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;

public class HDBManagerController {
    private List<BTOProject> btoProjects = new ArrayList<>();

    public boolean createListing() {
        // to be completed
        return false;
    }

    public boolean editListing(BTOProject project, int choice, Object newValue) {
        switch (choice) {
            case 1 -> {
                project.setProjectName((String) newValue);
                return true;}
            case 2 -> {
                project.setNeighbourhood((String) newValue);
                return true;}
            case 3 -> {
                // code to edit the types of flats
                return true;}
            case 4 -> {
                // code to print and choose the type of flats to edit
                // code to edit the number of available rooms
                return true;
            }
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

    public boolean deleteProject(String projectName, String managerNric) {
        return btoProjects.removeIf(p -> p.getProjectName().equals(projectName) && p.getManager().equals(managerNric));
    }

    public boolean toggleVisibility(BTOProject project, String visible) {
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

    // function to view HDB Officer registration

    // function to approve or reject HDB Officers registration

    // function to approve or reject applicant's BTO application

    // function to approve or reject applicant's request to withdraw

    // function to generate a report of the list of applicants and their flat booking
    // (flat type, project name, age, martial status)
    // able to filter

    // function to view enquiries for all projects

    // function to reply to enquiries

}