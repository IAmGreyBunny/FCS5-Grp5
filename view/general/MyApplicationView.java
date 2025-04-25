package view.general;

import application.BTOApplication;
import application.BTOApplicationController;
import session.Session;
import view.HomeViewFactory;
import view.IMenuView;

import java.util.ArrayList;
import java.util.List;

public class MyApplicationView implements IMenuView {
    @Override
    public void show() {
        BTOApplicationController controller = new BTOApplicationController();
        List<BTOApplication> allApplications = controller.getBTOApplications();
        String currentUserId = String.valueOf(Session.getSession().getCurrentUser().getUid());


        // Filter applications to include only those requested by the current user
        List<BTOApplication> userApplications = new ArrayList<>();
        for (BTOApplication application : allApplications) {
            if (application.getApplicantId().equals(currentUserId)) {
                userApplications.add(application);
            }
        }


        // Display the user's applications
        System.out.println("--- Your BTO Applications ---");
        for (BTOApplication application : userApplications) {
            System.out.println("Application ID: " + application.getApplicantId() + " | Project ID: " + application.getProjectId() + " | Unit Type: " + application.getUnitType().getName() + " | Status: " + application.getApplicationStatus());

        }

        // Returns Home
        Session.getSession().setCurrentView(HomeViewFactory.getHomeViewForUser(Session.getSession().getCurrentUser()));
    }
}
