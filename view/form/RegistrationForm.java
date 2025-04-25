package view.form;

import registration.RegistrationController;
import view.IFormView;

import java.util.HashMap;
import java.util.Scanner;

/**
 * A form view that handles the process of registering a new user.
 * This class implements IFormView and interacts with RegistrationController to validate and register the user.
 */

public class RegistrationForm implements IFormView {
    /**
     * @param userInput userInput is a HashMap that contains user inputs for registering the user.
     */
    HashMap<String, Object> userInput = new HashMap<>();

    /**
     * This method prompts the user to input their details for registration.
     * It validates the inputs and interacts with RegistrationController to register the user.
     */
    @Override
    public void prompt() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        String name = "";
        String nric = "";
        String password = "";
        String confirmPassword = "";
        int age = 0;
        boolean maritalStatus = false;

        /**
         * @param name name is the user's name for registration.
         */
        System.out.print("Enter name: ");
        name = scanner.next().trim();

        /**
         * @param nric nric is the user's NRIC for registration.
         */
        System.out.print("Enter NRIC: ");
        nric = scanner.next().trim();

        /**
         * @param password password is the user's password for registration.
         * @param confirmPassword confirmPassword is the user's confirmation of the password for registration.
         */
        do{
            System.out.print("Enter password: ");
            password = scanner.next();
            System.out.print("Enter password again: ");
            confirmPassword = scanner.next();
        }
        while(!(password.equals(confirmPassword)));

        /**
         * @param age age is the user's age for registration.
         */
        System.out.print("Enter age: ");
        age = scanner.nextInt();

        /**
         * @param maritalStatus maritalStatus is the user's marital status for registration.
         */
        System.out.print("Marital Status (y/n) : ");
        char ch;
        do{
            ch = scanner.next().trim().toLowerCase().charAt(0);
            if(ch == 'y')
            {
                maritalStatus = true;
            }
            else if (ch == 'n'){
                maritalStatus = false;
            }
        }while(ch != 'y' && ch!='n');

        /**
         * puts the respective value and key of the user's inputs into the UserInput
         * creates the new listing and add it to the database
         */
        userInput.put("name",name);
        userInput.put("nric",nric);
        userInput.put("password",password);
        userInput.put("age",age);
        userInput.put("maritalStatus",maritalStatus);

        RegistrationController registrationController = new RegistrationController(userInput);
        registrationController.register();
    }

    /**
     * Retrieves the user input map containing details for registration.
     * @return userInput which is a HashMap with keys "name", "nric", "password", "age", and "maritalStatus" representing the user's inputs.
     */
    @Override
    public HashMap<String, Object> getUserInput() {
        return userInput;
    }

    @Override
    public void show() {
        prompt();
    }
}
