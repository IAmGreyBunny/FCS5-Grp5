package session;

import data.GlobalData;
import user.User;
import view.StartUpView;
import view.View;

public class Session {
    private static Session session = null;

    private User currentUser = null;
    private View currentView = null;
    private GlobalData globalData = GlobalData.getGlobalData();

    private Session() {

    }

    public void loadInitialData() {
        currentView = new StartUpView();
    }

    public static Session getSession() {
        if (session == null) {
            session = new Session();
        }
        return session;
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
