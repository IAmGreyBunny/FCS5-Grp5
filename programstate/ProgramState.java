package programstate;

import data.GlobalData;
import user.CurrentUser;

import java.io.File;

public class ProgramState {
    private CurrentUser currentUser = null;
    private GlobalData globalData = GlobalData.getGlobalData();

    public void loadInitialData()
    {
    }
}
