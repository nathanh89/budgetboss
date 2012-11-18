package com.visionarysoftwaresolutions.budgetboss.menu;

import static org.fusesource.jansi.Ansi.ansi;
import org.fusesource.jansi.AnsiConsole;
import com.visionarysoftwaresolutions.budgetboss.app.BudgetBoss;
import com.visionarysoftwaresolutions.budgetboss.budget.*;
import com.visionarysoftwaresolutions.budgetboss.cli.*;
import com.visionarysoftwaresolutions.budgetboss.fileops.Salvation;

public class MainMenu implements MasterMenu {
	
	private Budget currentBudget;
	private String errorMessage = "";

	public MainMenu(Budget currentBudget){
		this.currentBudget = currentBudget;
	}
	
	public void setErrorMessage(String newMessage){
		errorMessage = newMessage;
	}
	
	public int getNumberOfOptions(){
		return menuOptions.length;
	}
			
	private MasterOption[] menuOptions = new MasterOption[]{
		new MasterOption(){String optionPrintout = Printer.getPrintout("toConsoleOption"); 
			public void optionMethod() {choseToPrintText();}
			public String printOption(){return optionPrintout;}},
		new MasterOption(){String optionPrintout = Printer.getPrintout("budgetReportOption");
			public void optionMethod() {choseBudgetReport();}
			public String printOption(){return optionPrintout;}},
		new MasterOption(){String optionPrintout = Printer.getPrintout("startEditorOption");
			public void optionMethod() {startEditor();}
			public String printOption(){return optionPrintout;}},
		new MasterOption(){String optionPrintout = Printer.getPrintout("saveBudgetOption");
			public void optionMethod() {choseToSave();}
			public String printOption(){return optionPrintout;}},
		new MasterOption(){String optionPrintout = Printer.getPrintout("openBudgetOption");
			public void optionMethod() {choseToOpen();}
			public String printOption(){return optionPrintout;}},
		new MasterOption(){String optionPrintout = Printer.getPrintout("createBudgetOption");
			public void optionMethod() {choseToCreate();}
			public String printOption(){return optionPrintout;}},
		new MasterOption(){String optionPrintout = Printer.getPrintout("exitOption");
			public void optionMethod() {choseToClose();}
			public String printOption(){return optionPrintout;}}
	};
	
	public void displayMenu(){
		AnsiConsole.out.println(ansi().eraseScreen());
		Printer.print("mainMenuHeader");
		System.out.println("Budget in use: " + currentBudget.getName() + "\n");
		Printer.printMenuOptions(menuOptions);
	}
	
	public void getOption(MasterMenu menu){
		displayMenu();
		System.out.println(errorMessage);
		InputValidator validator = new InputValidator();
		String userInput = Listener.getInput();
		while (validator.menuChoiceIsInvalid(userInput, menu)){
			displayMenu();
			System.out.println(errorMessage);
			userInput = Listener.getInput();
		}
		if(!(userInput.equalsIgnoreCase("exit"))){
			int optionChose = Integer.valueOf(userInput);
			chooseOption(optionChose);
		}
	}
	
	private void choseToPrintText(){
		if(!(currentBudget.getName().equals("No budget loaded"))){
			System.out.println(currentBudget.toString());
		}else {
			errorMessage = Printer.getPrintout("noBudgetLoaded");
			getOption(this);
		}
	}
	
	private void choseBudgetReport(){
		if(!(currentBudget.getName().equals("No budget loaded"))){
			Salvation savior = new Salvation();
			savior.generateBudgetReport(currentBudget.getName(), currentBudget);
		}else{
			errorMessage = Printer.getPrintout("noBudgetLoaded");
			getOption(this);
		}
	}
	
	private void startEditor(){
		if(!(currentBudget.getName().equals("No budget loaded"))){
			EditorMenu editor = new EditorMenu(currentBudget);
			while(editor.stillEditingBudget())
				editor.getOption(editor);
		}else{
			errorMessage = Printer.getPrintout("noBudgetLoaded");
			getOption(this);
		}
	}

	private void choseToSave(){
		if(!(currentBudget.getName().equals("No budget loaded"))){
			Salvation savior = new Salvation();
			savior.writeBudgetToDisk(currentBudget.getName(), currentBudget);
		}else{
			errorMessage = Printer.getPrintout("noBudgetLoaded");
			getOption(this);
		}
	}
	
	private void choseToOpen(){
		BudgetBoss.loadSavedBudget();
		BudgetBoss.endNeedNewBudget();
	}
		
	private void choseToCreate(){
		BudgetBoss.endLoadSavedBudget();
		BudgetBoss.needNewBudget();
	}
	
	private void choseToClose(){
		BudgetBoss.doneUsingBudgetBoss();
		BudgetBoss.endLoadSavedBudget();
		BudgetBoss.endNeedNewBudget();
	}
	
	public void chooseOption(int optionChose){
		int index = (optionChose -1);
		menuOptions[index].optionMethod();
	}
}