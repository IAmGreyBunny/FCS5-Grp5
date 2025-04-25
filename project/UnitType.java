package project;

/**
 * Represents a specific type of housing unit in a BTO project.
 * Each UnitType is associated with a project and includes details like
 * unit name, availability, total units and price per unit.
 * Used to manage and display housing unit options during applications and project setup.
 */
public class UnitType {
    private int unitTypeId;
    private int projectId;
    private String name;
    private int available;
    private int total;
    private double pricePerUnit;

    /**
     * Constructs a new UnitType object with the given attributes.
     *
     * @param unitTypeId   Unique ID of the unit type.
     * @param projectId    ID of the project this unit type belongs to.
     * @param name         Name or type of the unit.
     * @param available    Number of units currently available.
     * @param total        Total number of units of this type.
     * @param pricePerUnit Price of a single unit.
     */
    public UnitType(int unitTypeId,int projectId, String name, int available, int total, double pricePerUnit)
    {
        this.unitTypeId = unitTypeId;
        this.projectId = projectId;
        this.name = name;
        this.available = available;
        this.total = total;
        this.pricePerUnit = pricePerUnit;
    }

    /**
     * @return ID of the project this unit type belongs to.
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * Sets the project ID for this unit type.
     * @param projectId ID of the project.
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    /**
     * @return Unique ID of the unit type.
     */
    public int getUnitTypeId() {
        return unitTypeId;
    }

    /**
     * Sets the unique ID for this unit type.
     * @param unitTypeId New unit type ID.
     */
    public void setUnitTypeId(int unitTypeId) {
        this.unitTypeId = unitTypeId;
    }

    /**
     * @return Price of a single unit.
     */
    public double getPricePerUnit() {
        return pricePerUnit;
    }

    /**
     * Sets the price for each unit.
     * @param pricePerUnit New price per unit.
     */
    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    /**
     * @return Total number of units of this type.
     */
    public int getTotal() {
        return total;
    }

    /**
     * Sets the total number of units.
     * @param total New total number of units.
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return Number of units currently available.
     */
    public int getAvailable() {
        return available;
    }

    /**
     * Sets the number of available units.
     * @param available New available count.
     */
    public void setAvailable(int available) {
        this.available = available;
    }

    /**
     * @return The name of the unit type.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the unit type.
     * @param name New name of the unit type.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the formatted string representation of unit type.
     * @return Formatted unit type details.
     */
    @Override
    public String toString() {
        return String.format(
                "[%d] %s | Available: %d / %d | Price: $%.2f",
                unitTypeId,
                name,
                available,
                total,
                pricePerUnit
        );
    }
}
