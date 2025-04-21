package view.general;

import view.FormView;

import java.util.HashMap;
import java.util.Scanner;

public class RegistrationView extends FormView {
    HashMap<String,String> userInput = new HashMap<>();

    @Override
    public void prompt() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        String name = "";
        String nric = "";
        String password = "";
        String confirmPassword = "";
        int age = 0;
        boolean maritalStatus;

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
        if(scanner.next().charAt(0) == 'y')
        {
            maritalStatus = true;
        }
        else if (scanner.next().charAt(0) == 'n'){
            maritalStatus = false;
        }

    }

    @Override
    public <T> T getUserInput() {
        return null;
    }

    @Override
    public void show() {
        prompt();
    }
}
