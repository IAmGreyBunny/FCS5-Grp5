package btoproject;

import java.time.LocalDate;

public class BTOProject {
    private String projectName;
    private String location;
    private int totalUnits;
    private double pricePerUnit;
    private LocalDate estimatedCompletionDate;

    // Constructor
    public BTOProject(String projectName, String location, int totalUnits, double pricePerUnit, LocalDate estimatedCompletionDate) {
        this.projectName = projectName;
        this.location = location;
        this.totalUnits = totalUnits;
        this.pricePerUnit = pricePerUnit;
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

    public int getTotalUnits() {
        return totalUnits;
    }

    public void setTotalUnits(int totalUnits) {
        this.totalUnits = totalUnits;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public LocalDate getEstimatedCompletionDate() {
        return estimatedCompletionDate;
    }

    public void setEstimatedCompletionDate(LocalDate estimatedCompletionDate) {
        this.estimatedCompletionDate = estimatedCompletionDate;
    }

    // Method to calculate total project value
    public double calculateTotalValue() {
        return totalUnits * pricePerUnit;
    }

    // Method to check if project is completed
    public boolean isCompleted() {
        return LocalDate.now().isAfter(estimatedCompletionDate);
    }

    // toString Method
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
}
