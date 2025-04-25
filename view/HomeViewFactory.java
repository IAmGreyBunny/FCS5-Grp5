package view;

import user.User;
import user.hdbmanager.HDBManager;
import user.hdbofficer.HDBOfficer;
import view.general.DefaultHomeView;
import view.hdbmanager.HDBManagerHomeView;
import view.hdbofficer.HDBOfficerHomeView;

public class HomeViewFactory {
    public static IView getHomeViewForUser(User user)
    {
        if (user instanceof HDBManager)
        {
            return new HDBManagerHomeView();
        }
        else if (user instanceof HDBOfficer)
        {
            return new HDBOfficerHomeView();
        }
        else {
            return new DefaultHomeView();
        }
    }
}
