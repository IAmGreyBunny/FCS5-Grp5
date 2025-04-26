package view.general.applicants;

import enquiries.Enquiry;
import enquiries.EnquiryController;
import session.Session;
import view.HomeViewFactory;
import view.IMenuView;

import java.util.Scanner;

public class ApplicantEnquiryView implements IMenuView {
    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        EnquiryController controller = new EnquiryController();

        System.out.println("-- Enquiry --");
        System.out.println("1. Create Enquiry");
        System.out.println("2. Edit Enquiry");
        System.out.println("3. Delete Enquiry");
        System.out.println("4. View All Enquiries");
        System.out.println("5. Return");

        int input;
        do {
            System.out.println("Enter choice: ");
            input = scanner.nextInt();
        } while(1<input || input>5);

        switch (input) {
            case 1 -> {
                System.out.println("Enter enquiry: ");
                String message = scanner.next();
                String senderId = String.valueOf(Session.getSession().getCurrentUser().getUid());
                Enquiry newEnquiry = null;
                newEnquiry = controller.createNewEnquiry(message, senderId, senderId);
                if (newEnquiry != null) {
                    System.out.println("Enquiry submitted.");
                }
                else {
                    System.out.println("Enquiry not submitted.");
                }
            }
            case 2 -> {
                System.out.println("-- Enquiries --");
                controller.displayAllEnquiries();
                System.out.println("Enter enquiry to edit (can only edit own enquiries): ");
                int id;
                Enquiry enquiry = null;
                do {
                    id = scanner.nextInt();
                    enquiry = controller.findEnquiryByID(id);
                } while (enquiry == null);
                if (enquiry.getSenderID().equals(String.valueOf(Session.getSession().getCurrentUser().getUid()))) {
                    System.out.println("Enter edited enquiry: ");
                    String newMessage = scanner.next();
                    enquiry.setMessageText(newMessage);
                    System.out.println("Enquiry edited");
                } else {
                    System.out.println("Unable to edit.");
                }
            }
            case 3 -> {
                System.out.println("-- Deleted Enquiry --");
                controller.displayAllEnquiries();
                System.out.println("Enter ID of enquiry to delete (you can only delete enquiries that you have created): ");
                int id;
                Enquiry enquiry = null;
                do {
                    id = scanner.nextInt();
                    enquiry = controller.findEnquiryByID(id);
                } while (enquiry == null);
                if (enquiry.getSenderID().equals(String.valueOf(Session.getSession().getCurrentUser().getUid()))) {
                    if (controller.deleteEnquiry(id)) {
                        System.out.println("Successfully deleted");
                    }
                    else {
                        System.out.println("Deletion unsuccessful");
                    }
                } else {
                    System.out.println("Cannot delete enquiry.");
                }
            }

            case 4 -> {
                System.out.println("-- View Enquiry --");
                controller.displayAllEnquiries();
                System.out.println("Enter ID of enquiry to view full thread");
                int id = scanner.nextInt();
                controller.displayThreadFrom(id);
                System.out.println("Enter any key to return: ");
                scanner.next();
            }
            default -> System.out.println("Invalid choice");
        }
        // Returns Home
        Session.getSession().setCurrentView(HomeViewFactory.getHomeViewForUser(Session.getSession().getCurrentUser()));
    }
}
