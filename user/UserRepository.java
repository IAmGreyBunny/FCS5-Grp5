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
    public static User findUserById(int uid){

        User user = null;

        try(
                FileInputStream file = new FileInputStream(new File("data/user/User.xlsx"));
                Workbook workbook = WorkbookFactory.create(file)
        ) {
            Sheet sheet = workbook.getSheetAt(0);

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
        }catch(Exception e){
            System.out.println("Error: ");
            e.printStackTrace();
            return null;
        }

        return user;
    }

    public static User findUserByLogin(String username, String password){
        User user = null;

        try(
                FileInputStream file = new FileInputStream(new File("data/user/User.xlsx"));
                Workbook workbook = WorkbookFactory.create(file)
        )
        {
            Sheet sheet = workbook.getSheetAt(0);
            // Go through row in the Excel sheet
            for (Row row : sheet) {
                //Get username
                Cell cell = row.getCell(1);

                // Looks for username
                if (cell.toString().equals(username)) {
                    // Checks password if username is found
                    cell = row.getCell(2);
                    if (cell.toString().equals(password)) {
                        //return user by uid
                        return user = findUserById((int) row.getCell(0).getNumericCellValue());
                    }
                }
            }
        }catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }

        return user;
    }
}
