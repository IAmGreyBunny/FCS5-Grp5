package project;

import user.hdbmanager.HDBManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents the Build To Order housing project.
 * Contains key attributes including project details, unit types, application dates,
 * number of officer slots, and visibility status.
 */
public class Project {
    private int projectId;
    private String projectName;
    private String neighbourhood;
    private ArrayList<UnitType> listOfUnits;
    private LocalDate applicationOpeningDate;
    private LocalDate applicationClosingDate;
    private int officerSlots;
    private boolean visibility;   // false represents "off" and true represents "on"

    private HDBManager manager;

    /**
     * Constructs a new Project with the specified attributes.
     * @param projectId              Unique ID for the project.
     * @param projectName            Name of the project.
     * @param neighbourhood          Location of the project.
     * @param applicationOpeningDate Start date for applications.
     * @param applicationClosingDate End date for applications.
     * @param officerSlots           Number of officer slots available.
     * @param visibility             Whether the project is visible to users.
     */
    public Project(int projectId, String projectName, String neighbourhood, LocalDate applicationOpeningDate, LocalDate applicationClosingDate, int officerSlots, boolean visibility) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.neighbourhood = neighbourhood;
        this.applicationOpeningDate = applicationOpeningDate;
        this.applicationClosingDate = applicationClosingDate;
        this.officerSlots = officerSlots;
        this.visibility = false;            // default is false - "off"
        this.manager = manager;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public ArrayList<UnitType> getListOfUnits() {
        return listOfUnits;
    }

    /**
     * Adds a new UnitType to the project.
     * @param unitTypeId   ID of the unit type.
     * @param projectId    ID of the associated project.
     * @param name         Name of the unit type.
     * @param available    Number of available units.
     * @param total        Total number of units.
     * @param pricePerUnit Price per unit.
     */
    public void addUnitType(int unitTypeId,int projectId,String name, int available, int total, double pricePerUnit) {
        UnitType unitType = new UnitType(unitTypeId,projectId,name, available, total, pricePerUnit);
        this.listOfUnits.add(unitType);
    }

    /**
     * Deletes a unit type from the project by name.
     * @param name The name of the unit type to remove.
     * @return true if the unit was found and removed, false otherwise.
     */
    public boolean deleteUnitType(String name) {
        if (listOfUnits == null) {
            return false;
        }

        return listOfUnits.removeIf(unit -> unit.getName().equalsIgnoreCase(name));
    }

    public LocalDate getApplicationOpeningDate() {
        return applicationOpeningDate;
    }

    public void setApplicationOpeningDate(LocalDate applicationOpeningDate) {
        this.applicationOpeningDate = applicationOpeningDate;
    }

    public LocalDate getApplicationClosingDate() {
        return applicationClosingDate;
    }

    public void setApplicationClosingDate(LocalDate applicationClosingDate) {
        this.applicationClosingDate = applicationClosingDate;
    }

    public int getOfficerSlots() {
        return officerSlots;
    }

    public void setOfficerSlots(int officerSlots) {
        this.officerSlots = officerSlots;
    }

    public HDBManager getManager() {
        return manager;
    }

    public void setManager(HDBManager manager) {
        this.manager = manager;
    }

    public boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    /**
     * Returns string representation of the project which is formatted for display.
     * @return A string describing the project details.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format(
                "[%d] %s (%s)\n\tApplication Open: %s  Application Closed: %s",
                projectId,
                projectName,
                neighbourhood,
                applicationOpeningDate.format(formatter),
                applicationClosingDate.format(formatter)
        );
    }

}
