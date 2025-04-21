package login;

import session.Session;
import user.User;
import user.UserFactory;
import user.UserRepository;
import user.UserRole;
import validator.InputValidator;
import view.general.StartUpView;

import java.util.HashMap;

public class AuthController {
    public static boolean authenticate(HashMap<String,String> userInput){
        //Checks userInput
        if(!InputValidator.validateNric(userInput.get("username")))
        {
            System.out.println("Invalid Username(NRIC) Format");
            return false;
        }
        //Authenticate
        try{
            User user = UserRepository.findUserByLogin(userInput.get("username"),userInput.get("password"));
            if(user!=null)
            {
                UserRole userRole = UserRepository.getUserRole(user.getUid());
                user = UserFactory.asTypedUser(user,userRole);
                Session.getSession().setCurrentUser(user);
                return true;
            }
            else{
                System.out.println("Wrong Password");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;

    }

    public static boolean changeLoginInformation(HashMap<String,String> userInput)
    {
        String username = userInput.get("username");
        String password = userInput.get("password");

        //Update username

        //Update password

        Session.getSession().setCurrentUser(null);
        Session.getSession().setCurrentView(new StartUpView());
        return true;
    }
}
