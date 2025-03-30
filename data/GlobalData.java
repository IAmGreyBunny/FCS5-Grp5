package data;

import user.User;

import java.util.ArrayList;

public class GlobalData {
    private static GlobalData globalData = null; // References itself

    private static ArrayList<User> listOfUsers;

    private GlobalData() //Private constructor to prevent creation of this class in other places
    {

    }

    public static GlobalData getGlobalData() {
        if(globalData == null) //Checks if global data exists
        {
            globalData = new GlobalData();
        }

        return globalData;
    }
}
