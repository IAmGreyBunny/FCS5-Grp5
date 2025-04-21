package login;

import session.Session;
import user.User;
import user.UserFactory;
import user.UserRepository;
import user.UserRole;

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
                UserRole userRole = UserRepository.getUserRole(user.getUid());
                user = UserFactory.asTypedUser(user,userRole);
                Session.getSession().setCurrentUser(user);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;

    }
}
