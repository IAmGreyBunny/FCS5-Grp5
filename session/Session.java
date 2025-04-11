package session;

import data.GlobalData;
import user.CurrentUser;

public class Session {
    private CurrentUser currentUser = null;
    private GlobalData globalData = GlobalData.getGlobalData();

    public void loadInitialData()
    {
    }
}
