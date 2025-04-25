package session;

import user.User;
import view.general.StartUpView;
import view.IView;

/**
 * Singleton class that represents the current session in the system.
 * It stores the current user and the current view and manages other session related tasks.
 */
public class Session {

    private static Session session = null;

    private User currentUser = null;
    private IView currentView = null;

    /**
     * Private constructor to prevent instantiation outside of this class.
     */
    private Session() {

    }

    /**
     * Loads the initial data and sets the initial view for the session.
     * This is called when the session is first initialised.
     */
    public void loadInitialData() {
        currentView = new StartUpView();
    }

    /**
     * Returns the singleton instance of the Session class.
     * If the session has not been created yet then a new instance is created.
     * @return The singleton instance of the Session class.
     */
    public static Session getSession() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }

    /**
     * Gets the current user associated with the session.
     * @return The current user.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the current user for the session.
     * @param currentUser The user to be set as the current user.
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Gets the current view associated with the session.
     * @return The current view.
     */
    public IView getCurrentView() {
        return currentView;
    }

    /**
     * Sets the current view for the session.
     * @param currentView The view to be set as the current view.
     */
    public void setCurrentView(IView currentView) {
        this.currentView = currentView;
    }
}
