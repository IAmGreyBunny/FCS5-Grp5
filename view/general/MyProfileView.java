package view.general;

import view.MenuView;

import java.util.Scanner;

public class MyProfileView extends MenuView {

    int userInput;
    Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    @Override
    public void show() {
        System.out.println("--- My Profile ---");
        System.out.println("1) Change Password");

        userInput = scanner.nextInt();

        switch (userInput){
            case 1:
                // Change Password
                break;
            default:
                System.out.println("Invalid Option");

        }
    }
}
