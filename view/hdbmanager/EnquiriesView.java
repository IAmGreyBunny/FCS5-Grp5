package view.hdbmanager;

import session.Session;
import view.IMenuView;
import java.util.InputMismatchException;
import java.util.Scanner;
import enquiries.Enquiry;
import enquiries.EnquiryController;


public class EnquiriesView implements IMenuView {
    private final Scanner scanner;
    private final EnquiryController enquiryController;

    public EnquiriesView() {
        this.enquiryController = new EnquiryController();
        this.scanner = new Scanner(System.in);
    }

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
                    // return to HDBManagerHomeView
                    Session.getSession().setCurrentView(new HDBManagerHomeView());
                    break;
                default:
                    System.out.println("Invalid Input. Please try again.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
    }

    // to display all enquiries
    private void viewAllEnquiries() {
        System.out.println("\n===== All Enquiries =====");
        enquiryController.displayAllEnquiries();
    }

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


/*
public class EnquiriesView extends MenuView {
    private int userInput;

    @Override
    public void show() {
        System.out.println("====== Manage Enquiries ======");
        System.out.println("1. View All Enquiries");
        System.out.println("2. Reply to Enquiries");
        System.out.println("3. Return");

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        try {
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    // code to view all enquiries
                    break;
                case 2:
                    // code to answer enquiry
                    break;
                case 3:
                    Session.getSession().setCurrentView(new HDBManagerHomeView());
                    break;
                default: System.out.println("Invalid Input");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }
    }
    
}

 */
