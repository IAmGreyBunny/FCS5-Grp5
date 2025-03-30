package login;

import user.User;

import java.util.Scanner;

public class LoginView {
    public static void promptLogin() {
        String username;
        String password;

        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Login ---");
        System.out.println("Enter Username: ");
        username = scanner.next();
        System.out.println("Enter Password: ");
        password = scanner.next();

        System.out.println("Logging in... ");
        LoginController loginController = new LoginController();

        if (loginController.login(username, password)) {
            System.out.println("Logged in successfully");
        } else {
            System.out.println("Login Failed");
        }

    }
}
