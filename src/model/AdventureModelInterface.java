package model;

import java.util.List;

//Interface for all adventure models.
//Models represent the core game logic and state management.

public interface AdventureModelInterface {

	// Perform an action in the adventure, given a verb and a noun.
	// param verb The action verb (e.g., "grab").
	// param noun The action target (e.g., "backpack").
	// return true if the action is valid, false otherwise.

	boolean performAction(String verb, String noun);

	// Get the current description of the game state.
	// return The current game description.

	String getDescription();

	// Get the available actions that can be performed in the game.
	// return A list of available actions.

	List<String> getAvailableActions();
}
