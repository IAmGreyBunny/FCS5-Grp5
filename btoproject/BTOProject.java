package btoproject;

import user.HDBManager;

import java.time.LocalDate;
import java.util.ArrayList;

public class BTOProject {
    private String projectName;
    private String neighbourhood;
    private ArrayList<BTOUnitType> listOfUnits;
    private LocalDate applicationOpeningDate;
    private LocalDate applicationClosingDate;
    private int officerSlots;
    private String visibility;

    private HDBManager manager;

    public BTOProject(String projectName, String neighbourhood, LocalDate applicationOpeningDate, LocalDate applicationClosingDate, int officerSlots) {
        this.projectName = projectName;
        this.neighbourhood = neighbourhood;
        this.applicationOpeningDate = applicationOpeningDate;
        this.applicationClosingDate = applicationClosingDate;
        this.officerSlots = officerSlots;
        this.visibility = "off";            // default is "off"
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

    public ArrayList<BTOUnitType> getListOfUnits() {
        return listOfUnits;
    }

    public void addUnitType(String name, int available, int total, double pricePerUnit) {
        BTOUnitType btoUnitType = new BTOUnitType(name, available, total, pricePerUnit);
        this.listOfUnits.add(btoUnitType);
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

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /*
    @Override
    public String toString() {
        return "BTOProject{" +
                "projectName='" + projectName + '\'' +
                ", location='" + location + '\'' +
                ", totalUnits=" + totalUnits +
                ", pricePerUnit=" + pricePerUnit +
                ", estimatedCompletionDate=" + estimatedCompletionDate +
                '}';
    }
    */
}
