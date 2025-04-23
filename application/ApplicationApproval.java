package application;

public class ApplicationApproval {
    private Application application;

    public ApplicationApproval(Application application) {
        this.application = application;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}


/*
public class ApplicationApproval {
    private Application application;
    private boolean isWithdrawal;

    public ApplicationApproval(Application application, boolean isWithdrawal) {
        this.application = application;
        this.isWithdrawal = isWithdrawal;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public boolean isWithdrawal() {
        return isWithdrawal;
    }

    public void setWithdrawal(boolean withdrawal) {
        isWithdrawal = withdrawal;
    }
}

*/