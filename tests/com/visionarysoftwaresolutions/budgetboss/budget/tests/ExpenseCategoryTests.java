package com.visionarysoftwaresolutions.budgetboss.budget.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.visionarysoftwaresolutions.budgetboss.budget.Expense;
import com.visionarysoftwaresolutions.budgetboss.budget.ExpenseCategory;

public class ExpenseCategoryTests {

	@Test
	public void testGetSpent() {
		//Given an ExpenseCategory
		ExpenseCategory fhExpenses = new ExpenseCategory("Food/Household");
		//With some expenses
		LocalDate today = new LocalDate(2012, 11, 15);
		Expense firstExpense = new Expense(new BigDecimal("13.55"), today, "Fry's Food");
		Expense secondExpense = new Expense(new BigDecimal("25.38"), today, "Wal-Mart");
		Expense thirdExpense = new Expense(new BigDecimal("19.93"), today, "Fresh and Easy");
		fhExpenses.addExpense(firstExpense);
		fhExpenses.addExpense(secondExpense);
		fhExpenses.addExpense(thirdExpense);
		//When a user wants to know the total spent in that category
		BigDecimal spent = fhExpenses.getSpent();
		//Then the correct total will be reached
		assertTrue(spent.equals(new BigDecimal("58.86")));
	}

}
