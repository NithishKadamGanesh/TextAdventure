package controller;

import model.AdventureModelInterface;
import view.AdventureViewInterface;
import parser.TextParser;
import java.util.Timer;
import java.util.TimerTask;

// Controller for the Cave Explorer Adventure.
// Manages interactions between the CaveExplorerModel and CaveExplorerView.

public class MorningRoutineController implements AdventureControllerInterface {

	private AdventureModelInterface model;
	private AdventureViewInterface view;
	private Runnable onAdventureEnd; // Runnable to trigger the return to the selection screen

	public MorningRoutineController(AdventureModelInterface model, AdventureViewInterface view,
			Runnable onAdventureEnd) {
		this.model = model;
		this.view = view;
		this.onAdventureEnd = onAdventureEnd; // Assigning the end callback
		view.setSubmitAction(this::handleInput);
		updateView(); // update the view to display appropriate messaging for each scenario
	}

// Handles the input action from the user.

	private void handleInput() {
		String input = view.getInput();
		if (input.equalsIgnoreCase("exit_story")) {
			executeEnd();
		}
		if (TextParser.isValid(input)) {
			String[] parts = TextParser.parseInput(input);
			String verb = parts[0];
			String noun = parts[1];
			if (model.performAction(verb, noun)) {
				updateView();
				checkForEnd(); // Check if the story has ended
			} else {
				view.updateDescription("Invalid action! Please try using one of the available actions");
			}
		} else {
			if (input.equalsIgnoreCase("exit_story"))
				view.updateDescription("You are exiting the story and going back to selection screen");
			else
				view.updateDescription(
						"Please enter a valid action (verb + noun)! Please try using one of the available actions");
		}
		view.clearInput(); // Clear the input field after submission
	}

	// Implements the method from AdventureControllerInterface.
	// Handles input directly or calls other methods.

	// @param input the user input string

	@Override
	public void processInput(String input) {
		handleInput(); // This method handles the input processing
	}

	private void updateView() {
		view.updateDescription(model.getDescription()); // display the story prompt on screen
		view.displayAvailableActions(model.getAvailableActions()); // display list of available action for that scene
	}

	// Check if the current adventure has ended, and if so, display "The End"
	// and return to the adventure selection screen after a delay.
	private void checkForEnd() {
		if (model.getAvailableActions().isEmpty()) {
			executeEnd();
		}
	}

	private void executeEnd() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				view.closeFrame();
				onAdventureEnd.run(); // Trigger the return to selection screen
			}
		}, 5000); // 5000 milliseconds = 5 seconds wait for the selection screen to pop up
	}
}
