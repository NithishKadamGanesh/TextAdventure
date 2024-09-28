package model;

import java.util.*;

// Model for the Morning Routine Adventure.
// Tracks the current scene and actions available based on the scene.

public class MorningRoutineModel implements AdventureModelInterface {

	private String description;
	private Map<String, String> actions;
	private Set<String> performedActions;
	private String currentScene;
	private String actionKey;

	public MorningRoutineModel() {
		// Start at the bedroom scene
		description = "You are currently a sleep, its 6:30 in morning and your alarm rings. You have an important meeting today so its important to get ahead start. What will you do?";
		actions = new HashMap<>();
		performedActions = new HashSet<>();
		currentScene = "bedroom";

		// Initial actions for waking up in the bedroom
		actions.put("wake up",
				"you turn off the alram, you're still tired but know that you have to get your day started");
		actions.put("ignore alarm",
				"You missed your meeting and got fired for it. Tough luck, do better and try again. The End!");
	}

	// Processes the player's action based on the verb and noun provided.
	@Override
	public boolean performAction(String verb, String noun) {
		actionKey = verb + " " + noun;
		// Check if the action is valid by looking it up in the actions map.
		if (actions.containsKey(actionKey) && !performedActions.contains(actionKey)) {
			description = actions.get(actionKey); // Update description based on action.
			performedActions.add(actionKey); // Mark this action as performed.
			updateAvailableActions(); // Update available actions depending on the current state.
			return true; // Action was successfully performed.
		}
		// If the action was not valid, return false to indicate failure.
		return false;
	}

	// Retrieves the current description of the game state.
	@Override
	public String getDescription() {
		return description;
	}

	// Retrieves the list of available actions based on the current state.
	@Override
	public List<String> getAvailableActions() {
		List<String> availableActions = new ArrayList<>();
		for (Map.Entry<String, String> entry : actions.entrySet()) {
			if (!performedActions.contains(entry.getKey())) {
				// Combine action
				String combinedActions = entry.getKey();
				availableActions.add(combinedActions);
			}
		}
		return availableActions;
	}

	// Updates the available actions based on the current scene and past actions.
	// This ensures the game progresses dynamically based on the player's choices.
	private void updateAvailableActions() {

		if (currentScene.equals("bedroom")) {
			if (performedActions.contains(actionKey) && !performedActions.contains("make bed")) {
				currentScene = "bedroom";
				description = actions.get(actionKey).toString();
				actions.clear();
				actions.put("make bed",
						"Your bed is messy, Remember to make your bed as soon as you get up, it helps keep your room tidy!. Your in the bath room, what's next?");
			}
		}

		// Bathroom Scene
		if (currentScene.equals("bedroom")) {
			if (performedActions.contains("wake up") && performedActions.contains("make bed")) {
				currentScene = "bathroom";
				description = actions.get(actionKey).toString();
				actions.clear();
				actions.put("brush teeth", "Good, you're not rotting your teeth, People hate bad breath");
				actions.put("take shower", "Great you finally had a Bath, Its been a week");
				actions.put("use toilet", "Nice, you took a Massive Dump");
			} else if (performedActions.contains("ignore alarm")) {
				description = actions.get(actionKey).toString() + "Screen will Exit Automatically";
				actions.clear(); // No further actions are available, game ends
			}
		}

		// Kitchen Scene
		if (currentScene.equals("bathroom") && performedActions.contains("brush teeth")
				&& performedActions.contains("take shower")) {
			currentScene = "kitchen";
			description = actions.get(actionKey).toString();
			actions.clear();
			actions.put("make breakfast", "You prepare eggs and toast, they were yum");
			actions.put("drink coffee", "Amazing!, You needed that morning boost");
			actions.put("check phone",
					"Great, you didn't getting any news of the world ending, But you still have an important meeting to attend, you're getting late");
			actions.put("pack lunch", "you're running late you just pack an apple.");
		}

		// Exiting Scene
		if (currentScene.equals("kitchen") && performedActions.contains("pack lunch")) {
			currentScene = "exit_home";
			description = actions.get(actionKey).toString();
			actions.clear();
			actions.put("grab keys", "Nice, you're not going to lock your-self out");
			actions.put("wear shoes", "dress to impress, and kick say lods");
			actions.put("exit home", "whew!, you just made it in time great work not losing your job. The End");
		}

		// Ensure user takes his keys and wears his shoes before exit home can be
		// executed
		if (currentScene.equals("exit_home") && performedActions.contains("grab keys")
				&& performedActions.contains("wear shoes") && performedActions.contains("exit home")) {
			if (performedActions.contains(actionKey)) {
				currentScene = "exit";
				description = actions.get(actionKey).toString() + "Screen will Exit Automatically";
				actions.clear(); // No further actions are available, game ends
			}
		} else if (currentScene.equals("exit_home") && performedActions.contains("exit home")
				&& (!performedActions.contains("grab keys") || !performedActions.contains("wear shoes"))) {
			currentScene = "exit_home";
			description = "You can't exit home without keys and shoes";
			performedActions.remove("exit home");
			actions.put("exit home", "whew!, you just made it in time great work not losing your job. The End");
		}
	}
}
