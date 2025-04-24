package config;

import java.util.Map;

public class Config {
    public static final Map<String, String> filepath = Map.ofEntries(
            Map.entry("ProjectDetails","data/project/ProjectDetails.xlsx"),
            Map.entry("ProjectManager","data/project/ProjectManager.xlsx"),
            Map.entry("ProjectOfficer","data/project/ProjectOfficer.xlsx"),
            Map.entry("UnitType","data/project/UnitType.xlsx"),
            Map.entry("UserInfo","data/user/UserInfo.xlsx"),
            Map.entry("UserLogin","data/user/UserLogin.xlsx"),
            Map.entry("UserRole","data/user/UserRole.xlsx"),
            Map.entry("Applications", "data/application/Applications.xlsx")
    );

}
