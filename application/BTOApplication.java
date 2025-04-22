package application;

import project.Project;
import user.User;
import java.time.LocalDate;

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
