package com.VSSBudgetBoss.main;

import java.util.ResourceBundle;
import com.VSSBudgetBoss.budget.*;
import com.VSSBudgetBoss.cli.*;
import com.VSSBudgetBoss.fileops.*;

public class BudgetBoss {
	
	static Opener opener = new Opener();
	static TheCreator god = new TheCreator();
	static Budget currentBudget = new Budget("defaultBudget");
	static String defaultDirectory;
	
	public static ResourceBundle cliOutput = ResourceBundle.getBundle("cliOutput");
	
	public static void printPrompt(String toGet){
		System.out.println(cliOutput.getString(toGet));
	}
	
	public static void setDefaultDirectory(String newDefaultDirectory){
		defaultDirectory = newDefaultDirectory;
	}
	
	public static String getDefaultDirectory(){
		return defaultDirectory;
	}

	public static void setCurrentBudget(Budget newBudget){
		currentBudget = newBudget;
	}
		
	public static void main(String args[]){
		String currentUser = System.getProperty("user.name");
		defaultDirectory = "/home/" + currentUser + "/Documents/";
		
		System.out.println(cliOutput.getString("welcome"));
		
		while(opener.isPromptCleared())
			opener.askToOpenBudget(defaultDirectory);
		
		while(TheCreator.isSlackingOnFinances())
			god.bestMakeABudgetNow();
		
		if(!(currentBudget.getName().equals("defaultBudget"))){
			MainMenu mainMenu = new MainMenu(currentBudget);
			while(mainMenu.stillUsingBudgetBoss()){
				InputListener listener = new InputListener();
				InputValidator validator = new InputValidator();
				mainMenu.displayMenu();
				String toCheck = listener.listenForInput();
				if(validator.validatesMainMenuChoice(toCheck))
					mainMenu.menuSelection(toCheck);
			}
		}
	}
}


