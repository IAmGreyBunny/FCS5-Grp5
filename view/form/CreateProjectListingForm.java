package view.form;

import session.Session;
import view.FormView;

import java.util.HashMap;

public class CreateProjectListingForm extends FormView {
    @Override
    public void prompt() {
        // get all the user input into variables(use the InputValidator functions to validate)
        // except projectId, ProjectRepo.findMax
        // put all the user inputs into userInput based on the keys found in controller(exact string match)
        // createListingWithUserInput(HashMap<String, Object> userInput)
        // update currentView with new CreateUnitTypeForm()

        Session.getSession().setCurrentView(new CreateUnitTypeForm(projectId));
    }

    @Override
    public HashMap<String,Object> getUserInput() {
        return null;
    }

    @Override
    public void show() {

    }
}
