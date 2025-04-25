package view.general;


import application.BTOApplicationController;
import application.BTOApplication;
import application.ApplicationStatus;
import session.Session;
import view.HomeViewFactory;
import view.IMenuView;


import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * A menu view that allows users to book a flat if their application status is APPROVED.
 * This class implements IMenuView and interacts with BTOApplicationController to retrieve and update the applications.
 */
public class BookFlatView implements IMenuView {


    Scanner scanner = new Scanner(System.in).useDelimiter("\n");


    /**
     * This method displays all BTO applications requested by the current user and allows booking if the status is APPROVED.
     */
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


        System.out.println("--- Your BTO Applications ---");
        for (BTOApplication application : userApplications) {
            System.out.println("Application ID: " + application.getApplicantId() + " | Project ID: " + application.getProjectId() + " | Unit Type: " + application.getUnitType().getName() + " | Status: " + application.getApplicationStatus());
        }


        System.out.print("Enter Application ID to book a flat:");
        String applicationId = scanner.next();


        BTOApplication selectedApplication = null;
        for (BTOApplication application : userApplications) {
            if (application.getApplicantId().equals(applicationId)) {
                selectedApplication = application;
                break;
            }
        }


        if (selectedApplication != null && selectedApplication.getApplicationStatus() == ApplicationStatus.APPROVED) {
            selectedApplication.setApplicationStatus(ApplicationStatus.BOOKED);
            controller.updateApplicationStatus(selectedApplication);
            System.out.println("Flat booked successfully. Application status updated to BOOKED.");
        } else {
            System.out.println("Failed to book flat. Either the application ID is invalid or the application status is not APPROVED.");
        }
        // Returns Home
        Session.getSession().setCurrentView(HomeViewFactory.getHomeViewForUser(Session.getSession().getCurrentUser()));
    }
}




