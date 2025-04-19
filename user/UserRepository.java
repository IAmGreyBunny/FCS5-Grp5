package user;

import config.Config;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UserRepository {

    // Create User in database
    public static void createUser(User user) {
        try(
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("UserInfo")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {

            Sheet sheet = workbook.getSheetAt(0);

            Row newRow = sheet.createRow(sheet.getLastRowNum()+1);
            newRow.createCell(0).setCellValue(user.getUid());
            newRow.createCell(1).setCellValue(user.getName());
            newRow.createCell(2).setCellValue(user.getAge());
            //newRow.createCell(3).setCellValue(user.getMaritalStatus());


        }
        catch (Exception e)
        {
            System.out.println("Error");
            e.printStackTrace();
        }


        // Insert UserInfo

        // Insert UserRole

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

        //Update user entry

    }

    // Update username

    // Update password

    //Read Operations
    public static int findMaxId(){
        int maxId=0;
        try(
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("UserInfo")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {

            Sheet sheet = workbook.getSheetAt(0);

            for(int i = 1; i <= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);
                if((int) row.getCell(0).getNumericCellValue() > maxId) {
                    maxId = (int) row.getCell(0).getNumericCellValue();
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error");
            e.printStackTrace();
        }

        return maxId;
    }

    public static User findUserById(int uid){

        User user = null;

        try(
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("UserInfo")));
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
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("UserLogin")));
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
