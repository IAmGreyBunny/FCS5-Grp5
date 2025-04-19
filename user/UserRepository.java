package user;

import config.Config;
import org.apache.poi.ss.usermodel.*;

import java.io.*;

public class UserRepository {

    // Create in UserInfo
    public static void createUserInfo(User user) {
        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("UserInfo")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {

            Sheet sheet = workbook.getSheetAt(0);

            Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
            newRow.createCell(0).setCellValue(user.getUid());
            newRow.createCell(1).setCellValue(user.getName());
            newRow.createCell(2).setCellValue(user.getAge());
            newRow.createCell(3).setCellValue(user.getMaritalStatus());

            try (FileOutputStream outFile = new FileOutputStream(Config.filepath.get("UserInfo"))) {
                workbook.write(outFile);
            }

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }

    }

    // Create User in UserLogin
    public static void createUserLogin(User user, String username, String password) {
        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("UserLogin")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {

            Sheet sheet = workbook.getSheetAt(0);

            Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
            newRow.createCell(0).setCellValue(user.getUid());
            newRow.createCell(1).setCellValue(username);
            newRow.createCell(2).setCellValue(password);

            try (FileOutputStream outFile = new FileOutputStream(Config.filepath.get("UserLogin"))) {
                workbook.write(outFile);
            }

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    // Updates in UserInfo
    public static void updateUserInfo(User user) {
        //Find the row in the UserInfo table and update accordingly

        //Update user entry
        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("UserInfo")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if ((int) row.getCell(0).getNumericCellValue() == user.getUid()) {
                    row.getCell(0).setCellValue(user.getUid());
                    row.getCell(1).setCellValue(user.getName());
                    row.getCell(2).setCellValue(user.getAge());
                    row.getCell(3).setCellValue(user.getMaritalStatus());
                }
            }

            try (FileOutputStream outFile = new FileOutputStream(Config.filepath.get("UserInfo"))) {
                workbook.write(outFile);
            }

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }

    }

    // Updates in UserLogin
    public static void updateUserLogin(User user, String username, String password) {
        //Find the row in the UserInfo table and update accordingly

        //Update user entry
        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("UserInfo")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if ((int) row.getCell(0).getNumericCellValue() == user.getUid()) {
                    row.getCell(0).setCellValue(user.getUid());
                    row.getCell(1).setCellValue(username);
                    row.getCell(2).setCellValue(password);
                }
            }

            try (FileOutputStream outFile = new FileOutputStream(Config.filepath.get("UserInfo"))) {
                workbook.write(outFile);
            }

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }

    }

    //Read Operations
    public static int findMaxId() {
        int maxId = 0;
        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("UserInfo")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if ((int) row.getCell(0).getNumericCellValue() > maxId) {
                    maxId = (int) row.getCell(0).getNumericCellValue();
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        return maxId;
    }

    public static User findUserById(int uid) {

        User user = null;

        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("UserInfo")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {
            Sheet sheet = workbook.getSheetAt(0);

            // Go through row in the Excel sheet
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(1);

                //Get uid
                Cell cell = row.getCell(0);

                // Compare uid
                if (cell.getNumericCellValue() == uid) {
                    //Gets other info
                    String name = row.getCell(1).toString();
                    int age = (int) row.getCell(2).getNumericCellValue();
                    boolean maritalStatus = row.getCell(3).getBooleanCellValue();

                    user = new User(uid, name, age, maritalStatus);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
            return null;
        }

        return user;
    }

    public static User findUserByLogin(String username, String password) {
        User user = null;

        try (
                FileInputStream file = new FileInputStream(new File(Config.filepath.get("UserLogin")));
                Workbook workbook = WorkbookFactory.create(file)
        ) {
            Sheet sheet = workbook.getSheetAt(0);
            // Go through row in the Excel sheet
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

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
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }

        return user;
    }
}
