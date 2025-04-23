package application;

import project.UnitType;

//this class allows manager to approve/reject applications and withdrawals + updates the application status & unit availability

public class ApplicationApprovalController {

    public boolean approveApplication(ApplicationApproval applicationApproval) {
        Application application = applicationApproval.getApplication();

        if (application != null && application.getApplicationStatus().equals("Pending")) {
            application.setApplicationStatus("Approved");

            if (application instanceof BTOApplication) {
                BTOApplication btoApp = (BTOApplication) application;
                UnitType unitType = btoApp.getUnitType();
                unitType.setAvailable(unitType.getAvailable() - 1); //num of available units is decremented
            }

            return true;
        }
        return false;
    }

    public boolean rejectApplication(ApplicationApproval applicationApproval) {
        Application application = applicationApproval.getApplication();

        if (application != null && application.getApplicationStatus().equals("Pending")) {
            application.setApplicationStatus("Rejected");
            return true;
        }
        return false;
    }

    public boolean approveWithdrawal(ApplicationApproval applicationApproval) {
        Application application = applicationApproval.getApplication();

        if (application != null && application.getApplicationStatus().equals("Approved")) {
            application.setApplicationStatus("Withdrawn");

            if (application instanceof BTOApplication) {
                BTOApplication btoApp = (BTOApplication) application;
                UnitType unitType = btoApp.getUnitType();
                unitType.setAvailable(unitType.getAvailable() + 1); //um of available units is incremented
            }

            return true;
        }
        return false;
    }

    public boolean rejectWithdrawal(ApplicationApproval applicationApproval) {
        Application application = applicationApproval.getApplication();

        return application != null && application.getApplicationStatus().equals("Approved");
    }
}
