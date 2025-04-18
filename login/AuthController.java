package login;

import session.Session;
import user.UserRepository;

import java.io.IOException;
import java.util.HashMap;

public class AuthController {
    public static void authenticate(HashMap<String,String> userInput){
        //Checks userInput


        //Authenticate
        try{
            Session.getSession().setCurrentUser(
                    UserRepository.findUserByLogin(userInput.get("username"),userInput.get("password"))
            );
        }catch (Exception e){

        }

        //Update View

    }
}
