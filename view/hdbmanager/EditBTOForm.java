package view.hdbmanager;

import view.FormView;

import java.util.Scanner;
import java.util.HashMap;

public class EditBTOForm extends FormView {
    private Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    private HashMap<String,String> userInput;

    @Override
    public void prompt() {
        System.out.println("=== Edit BTO Project ===");
        System.out.println("Leave field empty to keep current values.");
        
        System.out.println("Project name: ");
        String name = scanner.next();

        System.out.println("Neighbourhood: ");
        String neighbourhood = scanner.next();

        System.out.println("Application opening date: ");
        String openingDate = scanner.next();

        System.out.println("Application closing date: ");
        String closingDate = scanner.next();

        System.out.println("Officer slots: ");
        String officerSlots = scanner.next();


        System.out.println("\n=== Editing Unit Types ===");
    
        System.out.print("Unit Name: ");
        String newName = scanner.next();


        System.out.print("Available Units: ");
        String newAvailable = scanner.next();


        System.out.print("Total Units: ");
        String newTotal = scanner.next();


        System.out.print("Price Per Unit: ");
        String newPrice = scanner.next();

        //ProjectViewController.handleEditing(this.getUserInput());

    }

    @Override
    public HashMap<String,String> getUserInput() {
        return userInput;
    }

    @Override
    public void show() {
        prompt();
    }

    
}