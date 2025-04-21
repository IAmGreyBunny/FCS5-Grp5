package registration;

import user.User;
import user.UserRepository;

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

        User user = new User(maxId+1, (String) userInput.get("name"), (int) userInput.get("age"), (boolean) userInput.get("maritalStatus"));
        UserRepository.createUserInfo(user);
        UserRepository.createUserLogin(user, (String) userInput.get("username"), (String) userInput.get("password"));
    }
}
