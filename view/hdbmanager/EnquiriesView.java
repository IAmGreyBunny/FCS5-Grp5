package view.hdbmanager;

import session.Session;
import view.HomeViewFactory;
import view.IMenuView;
import java.util.InputMismatchException;
import java.util.Scanner;
import enquiries.Enquiry;
import enquiries.EnquiryController;

/**
 * This class is responsible for managing all enquiries in the system.
 * It provides functionality for viewing all enquiries, replying to an enquiry and returning to the home view.
 */
public class EnquiriesView implements IMenuView {
    private final Scanner scanner;
    private final EnquiryController enquiryController;

    /**
     * Constructor to initialise the EnquiriesView.
     * Initialises the EnquiryController and scanner for user input.
     */
    public EnquiriesView() {
        this.enquiryController = new EnquiryController();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the options for managing enquiries.
     * This method provides the following options:
     * - View all enquiries
     * - Reply to an enquiry
     * - Return to the home view
     */
    @Override
    public void show() {
        System.out.println("====== Manage Enquiries ======");
        System.out.println("1. View All Enquiries");
        System.out.println("2. Reply to Enquiries");
        System.out.println("3. Return");

        try {
            int userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    // code to view all enquiries
                    viewAllEnquiries();
                    break;
                case 2:
                    // code to answer enquiry
                    replyToEnquiry();
                    break;
                case 3:
                    // Returns Home
                    Session.getSession().setCurrentView(HomeViewFactory.getHomeViewForUser(Session.getSession().getCurrentUser()));
                    break;
                default:
                    System.out.println("Invalid Input. Please try again.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
    }

    /**
     * Displays all the enquiries.
     */
    // to display all enquiries
    private void viewAllEnquiries() {
        System.out.println("\n===== All Enquiries =====");
        enquiryController.displayAllEnquiries();
    }

    /**
     * Allows the user to reply to an enquiry.
     * The user is prompted to enter an enquiry ID and if the enquiry exists they can reply to it.
     * The reply is then added to the enquiry using EnquiryController.
     */
    //to allow replying to an enquiry
    private void replyToEnquiry() {
        System.out.print("Enter the Enquiry ID to reply to: ");
        int enquiryID;

        //validate enquiry id input
        try {
            enquiryID = Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format");
            return;
        }

        // find the enquiry by id
        Enquiry original = enquiryController.findEnquiryByID(enquiryID);
        if (original == null) {
            System.out.println("Enquiry not found");
            return;
        }

        // display original message (js for context, maybe can remove)
        System.out.println("Original Message: " + original.getMessageText());

        System.out.print("Enter your reply: ");
        String replyText = scanner.next();

        if (replyText.trim().isEmpty()) {
            System.out.println("Reply cannot be empty.");
            return;
        }

        // get current officer's id from the session and the original sender's id
        String senderID = Session.getSession().getCurrentUser().getUid() + "";
        String recipientID = original.getSenderID();

        enquiryController.addReply(enquiryID, replyText, senderID, recipientID);
        System.out.println("Reply submitted successfully");
    }
}


