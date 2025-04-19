package login;

import session.Session;
import user.User;
import user.UserRepository;

import java.io.IOException;
import java.util.HashMap;

public class AuthController {
    public static boolean authenticate(HashMap<String,String> userInput){
        //Checks userInput


        //Authenticate
        try{
            User user = UserRepository.findUserByLogin(userInput.get("username"),userInput.get("password"));
            if(user!=null)
            {
                Session.getSession().setCurrentUser(user);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;

    }
}
