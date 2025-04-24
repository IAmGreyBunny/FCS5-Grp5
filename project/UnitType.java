package project;

public class UnitType {
    private int unitTypeId;
    private int projectId;
    private String name;
    private int available;
    private int total;
    private double pricePerUnit;

    public UnitType(int unitTypeId,int projectId, String name, int available, int total, double pricePerUnit)
    {
        this.unitTypeId = unitTypeId;
        this.projectId = projectId;
        this.name = name;
        this.available = available;
        this.total = total;
        this.pricePerUnit = pricePerUnit;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUnitTypeId() {
        return unitTypeId;
    }

    public void setUnitTypeId(int unitTypeId) {
        this.unitTypeId = unitTypeId;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
