package view.form;

import view.FormView;

import java.util.HashMap;

public class CreateUnitTypeForm extends FormView {

    CreateUnitTypeForm(int projectId){

    }

    @Override
    public void prompt() {
        // get all the user input into variables(use the InputValidator functions to validate)
        // except projectId, ProjectRepo.findMaxUnitTypeId
        // put all the user inputs into userInput based on the keys found in controller(exact string match)
        // createUnitTypeWithUserInput(HashMap<String, Object> userInput)
        // update currentView with new CreateUnitTypeForm()
    }

    @Override
    public HashMap<String,Object> getUserInput() {
        return null;
    }

    @Override
    public void show() {

    }
}
