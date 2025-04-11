package data;

import btoproject.BTOProject;
import user.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class CSV {

    public static ArrayList<User> readUserFromCSV(File file) throws FileNotFoundException {
        ArrayList<User> userList = new ArrayList<User>();

        Scanner fileScanner = new Scanner(file);

        fileScanner.nextLine();                         // Skips Header

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();       //Gets next line

            Scanner lineScanner = new Scanner(line);    // Create scanner object to read line
            lineScanner.useDelimiter(",");       // Sets comma to be delimiter

            String userField[] = new String[5];

            for (int i = 0; i < userField.length; i++) {
                userField[i] = lineScanner.next();
            }

            //Create a User object based on information from csv file
            User user = new User();

            user.setName(userField[0]);
            user.setNric(userField[1]);
            user.setAge(Integer.parseInt(userField[2]));
            user.setPassword(userField[4]);

            if (userField[3].equals("Married"))
            {
                user.setMaritalStatus(true);
            }

            userList.add(user);

        }

        return userList;
    }
    public static ArrayList<BTOProject> readProjectFromCSV(File file) throws FileNotFoundException{
        ArrayList<BTOProject> projectList = new ArrayList<BTOProject>();

        Scanner fileScanner = new Scanner(file);

        fileScanner.nextLine();                         // Skips Header

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();       //Gets next line

            Scanner lineScanner = new Scanner(line);    // Create scanner object to read line
            lineScanner.useDelimiter(",");       // Sets comma to be delimiter

            String projectField[] = new String[13];

            for (int i = 0; i < projectField.length; i++) {
                projectField[i] = lineScanner.next();
            }

            /*
            //Create a User object based on information from csv file
            BTOProject btoProject = new BTOProject(projectField[0],projectField[1]);


            projectList.add(btoProject);
            */

        }

        return projectList;
    }

}
