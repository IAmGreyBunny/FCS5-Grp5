package btoproject;

import java.time.LocalDate;
import java.util.ArrayList;

public class BTOProject {
    private String projectName;
    private String location;
    private ArrayList<BTOUnitType> listOfUnits;
    private LocalDate estimatedCompletionDate;


    // Constructor
    public BTOProject(String projectName, String location, LocalDate estimatedCompletionDate) {
        this.projectName = projectName;
        this.location = location;
        this.estimatedCompletionDate = estimatedCompletionDate;
    }

    // Getters and Setters
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<BTOUnitType> getListOfUnits() {
        return listOfUnits;
    }

    public void addUnitType(String name, int available, int total, double pricePerUnit) {
        BTOUnitType btoUnitType = new BTOUnitType(name, available, total, pricePerUnit);
        this.listOfUnits.add(btoUnitType);
    }

    public LocalDate getEstimatedCompletionDate() {
        return estimatedCompletionDate;
    }

    public void setEstimatedCompletionDate(LocalDate estimatedCompletionDate) {
        this.estimatedCompletionDate = estimatedCompletionDate;
    }

    // Method to calculate total project value
    /*
    public double calculateTotalValue() {
        return totalUnits * pricePerUnit;
    }*/

    // Method to check if project is completed
    public boolean isCompleted() {
        return LocalDate.now().isAfter(estimatedCompletionDate);
    }

    // toString Method
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
