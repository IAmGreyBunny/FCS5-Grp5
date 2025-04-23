package application;

import user.User;
import project.UnitType;
import java.time.LocalDate;

public class BTOApplication extends Application {
    private String projectId;
    private String applicantId;
    private UnitType unitType;

    public BTOApplication(String applicationID, LocalDate applicationDate, User applicant,
                          String projectId, String applicantId, UnitType unitType) {
        super(applicationID, applicationDate, applicant);
        this.projectId = projectId;
        this.applicantId = applicantId;
        this.unitType = unitType;
    }

    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }

    public String getApplicantId() { return applicantId; }
    public void setApplicantId(String applicantId) { this.applicantId = applicantId; }

    public UnitType getUnitType() { return unitType; }
    public void setUnitType(UnitType unitType) { this.unitType = unitType; }
}


/*
public class BTOApplication extends Application {
    private Project project;

    public BTOApplication(String applicationID, LocalDate applicationDate, User applicant, Project project) {
        super(applicationID, applicationDate, applicant);
        this.project = project;
    }


    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
*/