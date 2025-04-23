package user.hdbmanager;

import project.Project;
import project.ProjectRepository;
import session.Session;
import view.form.CreateListingForm;
import view.form.EditListingForm;

import java.util.*;
import java.util.stream.Collectors;

public class BTOProjectController {
    private List<Project> btoProjects = ProjectRepository.getAllProjects();
    HashMap<String, Object> userInput;

    public BTOProjectController() {}



     public void createListing() {
        Session.getSession().setCurrentView(new CreateListingForm());
        Session.getSession().getCurrentView().show();
    }

    public void editListing() {
        Session.getSession().setCurrentView(new EditListingForm());
        Session.getSession().getCurrentView().show();
    }

    public void deleteProject(int projectId) {

        if (btoProjects.removeIf(p -> p.getProjectId() == projectId)) {
            System.out.println("Project deleted.");
        }
        else {
            System.out.println("Deletion unsuccessful");
        }
    }

    public boolean validId(int projectId) {
        for (Project project : btoProjects) {
            if (project.getProjectId() == projectId) {
                return true;
            }

        }
        return false;
    }

    public boolean toggleVisibility(Project project) {
        project.setVisibility(!project.getVisibility());
        return true;
    }

    public List<Project> getAllProjects() {
        return btoProjects;
    }

    public List<Project> getMyProjects(int managerId) {
        return btoProjects.stream()
                .filter(p -> ProjectRepository.getProjectManagerId(p.getProjectId()) == managerId)
                .collect(Collectors.toList());
    }

}