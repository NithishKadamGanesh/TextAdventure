package parser;

//A simple text parser for separating verbs and nouns.

public class TextParser {

	//Parses the user input into a verb and a noun.
	// param input The user input string.
	// return An array with two elements: verb and noun.

	public static String[] parseInput(String input) {
		return input.trim().toLowerCase().split(" ");
	}

	// Validates if the input is in the correct format (verb + noun).
	// param input The input to validate.
	// return true if valid, false otherwise.

	public static boolean isValid(String input) {
		String[] parts = input.split(" ");
		return parts.length == 2;
	}
}
