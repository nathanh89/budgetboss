package com.visionarysoftwaresolutions.budgetboss.budginator.tests;

import org.junit.Test;
import com.visionarysoftwaresolutions.budgetboss.budget.Budget;
import com.visionarysoftwaresolutions.budgetboss.budginator.BudgetToJSON;

public class BudgetToJSONTests {

	@Test
	public void testConversion() {
		//Given: A budget created by BudgetBoss
		Budget testBudget = new Budget("JSONBudget");
		//When: The budget needs to be converted to JSON format for wider use
		//Then: The budget will convert to JSON
		//See console for output
		System.out.print(BudgetToJSON.convertToBudginator(testBudget));
	}

}
