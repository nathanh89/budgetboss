package com.visionarysoftwaresolutions.budgetboss.cli.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.visionarysoftwaresolutions.budgetboss.cli.InputValidator;

public class InputValidatorTests {
		
	@Test
	public void testNonIntegerRejection(){
		//And: The input must be an integer (for selecting menu options numerically)
		//When: The user inputs something
		//Then: The input will only validate if it is an integer
		assertFalse(InputValidator.inputNotAnInteger("8"));
		assertFalse(InputValidator.inputNotAnInteger("1"));
		assertTrue(InputValidator.inputNotAnInteger("toCheck"));
	}
	
	@Test
	public void testValidatePathLinux(){
		//And: A valid directory path is needed
		//When: The user inputs something
		//Then: The input will only validate if it is a valid path, ending in "/"
		String currentUser = System.getProperty("user.name");
		assertTrue(InputValidator.pathIsInvalid("/home/" + currentUser + "/Documents"));
		assertFalse(InputValidator.pathIsInvalid("/home/" + currentUser + "/Documents/"));
		assertTrue(InputValidator.pathIsInvalid("/home/" + currentUser + "/notevenarealdirectory/"));
	}
}
	