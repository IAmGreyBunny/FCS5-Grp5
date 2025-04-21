package registration;

import session.Session;
import user.User;
import user.UserRepository;
import user.UserRole;
import view.general.LoginView;

import java.util.HashMap;

public class RegistrationController {

    HashMap<String, Object> userInput;

    public RegistrationController(HashMap<String, Object> userInput)
    {
        this.userInput = userInput;
    }

    public void register()
    {
        int maxId = UserRepository.findMaxId();
        boolean validateInput = true; //Placeholder for method to validate input

        if(validateInput)
        {
            User user = new User(maxId+1, (String) userInput.get("name"), (int) userInput.get("age"), (boolean) userInput.get("maritalStatus"));
            UserRepository.createUserInfo(user);
            UserRepository.createUserLogin(user, (String) userInput.get("username"), (String) userInput.get("password"));
            UserRepository.setUserRole(user.getUid(), UserRole.APPLICANT);
            System.out.println("Successfully Registered User");
            Session.getSession().setCurrentView(new LoginView());
        }else {
            System.out.println("Error creating user");
        }
    }
}
