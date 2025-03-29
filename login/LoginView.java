package login;

import java.util.Scanner;

public class LoginView {
    public static void promptLogin()
    {
        String username;
        String password;

        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Login ---");
        System.out.println("Enter Username: ");
        username = scanner.next();
        System.out.println("Enter Password: ");
        password = scanner.next();

        LoginController loginController = new LoginController();
    }
}
