package view.form;

import registration.RegistrationController;
import view.IFormView;

import java.util.HashMap;
import java.util.Scanner;

public class RegistrationForm implements IFormView {
    HashMap<String, Object> userInput = new HashMap<>();

    @Override
    public void prompt() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        String name = "";
        String nric = "";
        String password = "";
        String confirmPassword = "";
        int age = 0;
        boolean maritalStatus = false;

        // Validation and Sanity Check - TBD
        System.out.print("Enter name: ");
        name = scanner.next().trim();
        System.out.print("Enter NRIC: ");
        nric = scanner.next().trim();
        do{
            System.out.print("Enter password: ");
            password = scanner.next();
            System.out.print("Enter password again: ");
            confirmPassword = scanner.next();
        }
        while(!(password.equals(confirmPassword)));
        System.out.print("Enter age: ");
        age = scanner.nextInt();
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

        userInput.put("name",name);
        userInput.put("nric",nric);
        userInput.put("password",password);
        userInput.put("age",age);
        userInput.put("maritalStatus",maritalStatus);

        RegistrationController registrationController = new RegistrationController(userInput);
        registrationController.register();
    }

    @Override
    public HashMap<String, Object> getUserInput() {
        return userInput;
    }

    @Override
    public void show() {
        prompt();
    }
}
