package user;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UserRepository {

    // Create User in database
    public static void createUser(User user) {

    }

    // Delete User in database
    public static void deleteUser(User user) {
        // Find user by id

        // Delete user in UserInfo Table

        // Delete user in User Table

        // Check for traces in project data and delete accordingly

        // Check for traces in enquiries and delete accordingly
    }

    // Updates User in database
    public static void updateUser(User user) {
        //Find the row in the UserInfo table and update accordingly

        //Find the row in UserLogin table and update accordingly

        //Update user entry

    }

    //Read Operations
    public static User findUserById(int uid) throws IOException {
        FileInputStream file;
        Workbook workbook;
        Sheet sheet;

        User user = null;

        try {
            file = new FileInputStream(new File("data/user/User.xlsx"));
            workbook = WorkbookFactory.create(file);
            sheet = workbook.getSheetAt(0);
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            return null;
        }

        // Go through row in the Excel sheet
        for (Row row : sheet) {
            //Get uid
            Cell cell = row.getCell(0);

            // Compare uid
            if (cell.getNumericCellValue() == uid) {
                //Gets other info
                String name = row.getCell(1).toString();
                int age = (int) row.getCell(2).getNumericCellValue();
                boolean maritalStatus = row.getCell(3).getBooleanCellValue();

                user = new User(uid,name,age,maritalStatus);
            }
        }

        // Close resources
        workbook.close();
        file.close();

        return user;
    }

    public static User findUserByLogin(String username, String password) throws IOException {
        FileInputStream file;
        Workbook workbook;
        Sheet sheet;

        User user = null;

        try {
            file = new FileInputStream(new File("data/user/User.xlsx"));
            workbook = WorkbookFactory.create(file);
            sheet = workbook.getSheetAt(0);
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            return null;
        }

        // Go through row in the Excel sheet
        for (Row row : sheet) {
            //Get username
            Cell cell = row.getCell(1);

            // Looks for username
            if (cell.toString().equals(username)) {
                // Checks password if username is found
                cell = row.getCell(2);
                if (cell.toString().equals(password)) {
                    System.out.println("User found");
                    //get uid
                }
            }
        }

        // Close resources
        workbook.close();
        file.close();

        return user;
    }
}
