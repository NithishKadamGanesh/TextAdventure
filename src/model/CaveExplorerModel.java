package model;

import java.util.*;

//Model for the Cave Explorer Adventure.
//Tracks the current scene and actions available based on the scene.

public class CaveExplorerModel implements AdventureModelInterface {

	private String description;
	private Map<String, String> actions;
	private Set<String> performedActions;
	private String currentScene;

	public CaveExplorerModel() {
		// Start at the cave entrance
		description = "You stand at the entrance of a dark cave. You can either choose to enter the cave or just walk away. What will you do?";
		actions = new HashMap<>();
		performedActions = new HashSet<>();
		currentScene = "entrance";

		// Initial actions for entering the cave
		actions.put("go inside",
				"You enter the cave and find yourself in complete darkness. But you remembered you had a torch.");
		actions.put("walk away", "The cave seemed to dangerous, so you chose to walk away. Thank you! The End");
	}

	// Processes the player's action based on the verb and noun provided.
	@Override
	public boolean performAction(String verb, String noun) {
		String actionKey = verb + " " + noun;
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

	// Return the list of available actions based on the current state, Also remove
	// any actions already performed in this state.
	@Override
	public List<String> getAvailableActions() {
		List<String> availableActions = new ArrayList<>();
		for (Map.Entry<String, String> entry : actions.entrySet()) {
			if (!performedActions.contains(entry.getKey())) {
				String combinedActions = entry.getKey();
				availableActions.add(combinedActions);
			}
		}
		return availableActions;
	}

	// Updates the available actions based on the current scene and past actions.
	// This ensures the game progresses dynamically based on the player's choices.
	private void updateAvailableActions() {
		// Cave Entrance Scene
		if (currentScene.equals("entrance")) {
			String actionKey = "go inside";
			if (performedActions.contains(actionKey)) {
				currentScene = "Scene_2";
				description = actions.get(actionKey).toString();
				actions.clear();
				actions.put("light torch",
						"The torch lit up the cave and you see that the cave is huge, Enter deeper and explore the cave. ");
			}
		}

		if (currentScene.equals("entrance")) {
			String actionKey = "walk away";
			if (performedActions.contains(actionKey)) {
				currentScene = "Scene_2";
				description = actions.get(actionKey).toString() + "Screen will Exit Automatically";
				actions.clear(); // No further actions are available, game ends
			}
		}

		if (currentScene.equals("Scene_2")) {
			String actionKey = "light torch";
			if (performedActions.contains(actionKey)) {
				currentScene = "Scene_3";
				description = actions.get(actionKey).toString();
				actions.clear();
				actions.put("explore cave",
						"After a while you find a dark narrow tunnel. it seems dangerous but you already made your mind to explore the cave");
			}
		}

		if (currentScene.equals("Scene_3")) {
			String actionKey = "explore cave";
			if (performedActions.contains(actionKey)) {
				currentScene = "Scene_4";
				description = actions.get(actionKey).toString();
				actions.clear();
				actions.put("explore tunnel",
						"You walk through the tunnel and examine the walls and see that there are some clues on them. After reading a few clues you figure out that a monster guards a bountiful treasure chest. You get excited! and dream about what you'll do with the treasure you acquire, while daydreaming you set off traps. what do you do?");
			}
		}

		if (currentScene.equals("Scene_4")) {
			String actionKey = "explore tunnel";
			if (performedActions.contains(actionKey)) {
				currentScene = "Scene_5";
				description = actions.get(actionKey).toString();
				actions.clear();
				actions.put("dodge traps",
						"You successfully avoided the trap, narrowly escaping with your life. Elated but nervous you carry on through the tunnel and come across an underground lake ");
			}
		}

		if (currentScene.equals("Scene_5")) {
			String actionKey = "dodge traps";
			if (performedActions.contains(actionKey)) {
				currentScene = "Scene_6";
				description = actions.get(actionKey).toString();
				actions.clear();
				actions.put("swim_across lake",
						"You chose to swim in the cold and dark lake, but then you encounter a terrifying creature, you try to fight it but in the water you were helpless and thus Died. Thank You for playing. The End!");
				actions.put("find boat",
						"You look for anything that can help you cross over and found a boat and paddle.");
			}
		}

		if (currentScene.equals("Scene_6")) {
			String actionKey = "swim_across lake";
			if (performedActions.contains(actionKey)) {
				currentScene = "Scene_7";
				description = actions.get(actionKey).toString() + "Screen will Exit Automatically";
				actions.clear(); // No further actions are available, game ends
			}
		}

		if (currentScene.equals("Scene_6")) {
			String actionKey = "find boat";
			if (performedActions.contains(actionKey)) {
				currentScene = "Scene_7";
				description = actions.get(actionKey).toString();
				actions.clear();
				actions.put("cross lake",
						"You use the boat to cross over, but encounter a terrifying creature, but with the paddle you swat it away and crossed over to the other side. You venture deeper into the cave, you keep the paddle thinking it'll be helpful when you encounter the monster guarding the treasure. Time has passed but  you finally found the subcave where the monster is. You see the monster and get afraid. What do you do?");
			}
		}

		if (currentScene.equals("Scene_7")) {
			String actionKey = "cross lake";
			if (performedActions.contains(actionKey)) {
				currentScene = "Scene_8";
				description = actions.get(actionKey).toString();
				actions.clear();
				actions.put("fight monster",
						"You make your mind up and decide to fight the monster. You  struggle at first, you are persistent, your bravery and courage paid off, you slay the monster. Now you can get the treasure you so desire.");
				actions.put("run away",
						"The monster is to frightening and you decide its best that you run away. Thank you for playing. The End!");
			}
		}

		if (currentScene.equals("Scene_8")) {
			String actionKey = "fight monster";
			if (performedActions.contains(actionKey)) {
				currentScene = "Scene_9";
				description = actions.get(actionKey).toString();
				actions.clear();
				actions.put("find treasure",
						"You find the treasure, open the chest and are overjoyed. You take the treasure back with you aand live happy with riches you found. Thank you for playing. The End!");
			}
		}

		if (currentScene.equals("Scene_8")) {
			String actionKey = "run away";
			if (performedActions.contains(actionKey)) {
				currentScene = "Scene_9";
				description = actions.get(actionKey).toString() + "Screen will Exit Automatically";
				actions.clear(); // No further actions are available, game ends
			}
		}

		if (currentScene.equals("Scene_9")) {
			String actionKey = "find treasure";
			if (performedActions.contains("find treasure")) {
				currentScene = "Scene_10";
				description = actions.get(actionKey).toString() + "Screen will Exit Automatically";
				actions.clear(); // No further actions are available, game ends
			}
		}

	}
}
