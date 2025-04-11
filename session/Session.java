package session;

import data.GlobalData;
import user.User;
import view.View;

public class Session {
    private User currentUser = null;
    private View currentView = null;
    private GlobalData globalData = GlobalData.getGlobalData();

    public void loadInitialData() {
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public View getCurrentView() {
        return currentView;
    }

    public void setCurrentView(View currentView) {
        this.currentView = currentView;
    }
}
