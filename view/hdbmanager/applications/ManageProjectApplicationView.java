package view.hdbmanager.applications;

import application.ApplicationStatus;
import application.BTOApplication;
import application.BTOApplicationController;
import session.Session;
import user.User;
import view.hdbmanager.HDBManagerHomeView;
import view.hdbmanager.ManageApplicationView;

import java.util.List;
import java.util.Scanner;

public class ManageProjectApplicationView extends ManageApplicationView {
    private int userInput;
    private final Scanner scanner = new Scanner(System.in);
    private final BTOApplicationController controller = new BTOApplicationController();

    @Override
    public void show() {
        System.out.println("==== BTO Project Applications ====");
        System.out.println("1. Manage Requests");
        System.out.println("2. Manage Withdraws");
        System.out.println("3. Generate Report");
        System.out.println("4. Return");

        try {
            userInput = Integer.parseInt(scanner.nextLine());

            switch (userInput) {
                case 1 -> managePendingApplications();
                case 2 -> manageWithdrawals();
                case 3 -> generateReport();
                case 4 -> Session.getSession().setCurrentView(new HDBManagerHomeView());
                default -> System.out.println("Invalid Input");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input. Please enter a number");
        }
    }

    private void managePendingApplications() {
        List<BTOApplication> applications = controller.getBTOApplications();
        List<BTOApplication> pendingApps = applications.stream()
                .filter(app -> app.getApplicationStatus() == ApplicationStatus.PENDING)
                .toList();

        if (pendingApps.isEmpty()) {
            System.out.println("No pending applications found");
            return;
        }

        for (int i = 0; i < pendingApps.size(); i++) {
            BTOApplication app = pendingApps.get(i);
            User applicant = app.getApplicant();
            System.out.printf("%d. Application ID: %s | Applicant: %s | Unit Type: %s\n",
                    i + 1, app.getApplicationID(), applicant.getName(), app.getUnitType().getName());
        }

        System.out.print("Enter application number to approve/reject: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > pendingApps.size()) {
                System.out.println("Invalid choice");
                return;
            }

            BTOApplication selectedApp = pendingApps.get(choice - 1);

            System.out.print("Approve (A) or Reject (R)? ");
            String action = scanner.nextLine().trim().toUpperCase();

            boolean updated = false;
            if (action.equals("A")) {
                updated = controller.approveApplication(selectedApp);
            } else if (action.equals("R")) {
                updated = controller.rejectApplication(selectedApp);
            }

            if (updated) {
                System.out.println("Application status updated successfully");
            } else {
                System.out.println("Failed to update status");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
    }

    private void manageWithdrawals() {
        List<BTOApplication> applications = controller.getBTOApplications();
        List<BTOApplication> approvedApps = applications.stream()
                .filter(app -> app.getApplicationStatus() == ApplicationStatus.APPROVED)
                .toList();

        if (approvedApps.isEmpty()) {
            System.out.println("No withdrawal requests available");
            return;
        }

        for (int i = 0; i < approvedApps.size(); i++) {
            BTOApplication app = approvedApps.get(i);
            User applicant = app.getApplicant();
            System.out.printf("%d. Application ID: %s | Applicant: %s | Unit: %s (Status: %s)\n",
                    i + 1, app.getApplicationID(), applicant.getName(), app.getUnitType().getName(), app.getApplicationStatus());
        }

        System.out.print("Enter application number to approve withdrawal: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > approvedApps.size()) {
                System.out.println("Invalid choice");
                return;
            }

            BTOApplication selectedApp = approvedApps.get(choice - 1);

            System.out.print("Withdraw (W) or Cancel (C)? ");
            String action = scanner.nextLine().trim().toUpperCase();

            boolean updated = false;
            if (action.equals("W")) {
                updated = controller.approveWithdrawal(selectedApp);
            } else if (action.equals("C")) {
                updated = controller.rejectWithdrawal(selectedApp);
            }

            if (updated) {
                System.out.println("Withdrawal processed");
            } else {
                System.out.println("Failed to process withdrawal");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
    }

    private void generateReport() {
        List<BTOApplication> applications = controller.getBTOApplications();

        if (applications.isEmpty()) {
            System.out.println("No applications found");
            return;
        }

        System.out.println("==== BTO Application Report ====");
        for (BTOApplication app : applications) {
            System.out.printf("ID: %s | Applicant: %s | Unit: %s | Status: %s\n",
                    app.getApplicationID(),
                    app.getApplicant().getName(),
                    app.getUnitType().getName(),
                    app.getApplicationStatus());
        }
    }
}
