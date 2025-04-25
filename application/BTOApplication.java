package application;

import project.UnitType;
import user.User;

import java.time.LocalDate;

/**
 * Represents a BTO application submitted by an applicant for a specific project and unit type
 * This class extends the general Application class and includes additional details specific to BTO applications,
 * like the associated project, applicant, and unit type.
 */
public class BTOApplication extends Application {
    private String projectId;
    private String applicantId;
    private UnitType unitType;

    /**
     * Constructs a new BTO Application with the provided details.
     * This constructor initialises a new BTO application by setting the application ID, application date,
     * applicant details, project details, and unit type.
     * @param applicationID the unique ID for this application
     * @param applicationDate the date the application is created
     * @param applicant the applicant for the BTO unit
     * @param projectId the ID of the project to which the applicant is applying
     * @param applicantId the ID of the applicant
     * @param unitType the unit type the applicant is applying for (2 room or 3-room)
     */
    public BTOApplication(String applicationID, LocalDate applicationDate, User applicant,
                          String projectId, String applicantId, UnitType unitType) {
        super(applicationID, applicationDate, applicant);
        this.projectId = projectId;
        this.applicantId = applicantId;
        this.unitType = unitType;
    }

    /**
     * Gets the ID of the project associated with this BTO application.
     * The ID uniquely identifies the BTO project that the applicant is applying to.
     * @return the project ID as string
     */
    public String getProjectId() { return projectId; }

    /**
     * Sets the ID of the project associated with this BTO application.
     * It allows the project ID to be updated if needed.
     * @param projectId the project ID to be set
     */
    public void setProjectId(String projectId) { this.projectId = projectId; }

    /**
     * Gets the ID of the applicant who submitted this BTO application.
     * It is used to uniquely identify the applicant within the system.
     * @return the applicant ID as string
     */
    public String getApplicantId() { return applicantId; }

    /**
     * Sets the ID of the applicant for this BTO application
     * @param applicantId the applicant ID to be set
     */
    public void setApplicantId(String applicantId) { this.applicantId = applicantId; }

    /**
     * Gets the unit type that the applicant is applying for in this BTO application.
     * @return the unit type that the applicant is applying for
     */
    public UnitType getUnitType() { return unitType; }

    /**
     * Sets the unit type for this BTO application.
     * It allows the unit type to be updated if needed, if applicant wants to change their choice
     * @param unitType the unit type to be set
     */
    public void setUnitType(UnitType unitType) { this.unitType = unitType; }
}