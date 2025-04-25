package session;

import user.User;
import view.general.StartUpView;
import view.IView;

public class Session {
    private static Session session = null;

    private User currentUser = null;
    private IView currentView = null;

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

    public IView getCurrentView() {
        return currentView;
    }

    public void setCurrentView(IView currentView) {
        this.currentView = currentView;
    }
}
