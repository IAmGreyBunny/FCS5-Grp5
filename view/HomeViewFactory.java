package view;

import session.Session;
import user.User;
import user.applicant.Applicant;
import user.hdbmanager.HDBManager;
import user.hdbofficer.HDBOfficer;
import view.general.DefaultHomeView;
import view.hdbmanager.HDBManagerHomeView;
import view.hdbofficer.HDBOfficerHomeView;

import static user.UserRole.APPLICANT;

public class HomeViewFactory {
    public static View getHomeViewForUser(User user)
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
