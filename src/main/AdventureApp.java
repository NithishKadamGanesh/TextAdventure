package main;

import controller.*;
import model.*;
import view.*;

import javax.swing.*;

//Main application class for choosing and starting an adventure.

public class AdventureApp {

	private JFrame frame;

	public AdventureApp() {
		frame = new JFrame("Choose Your Adventure");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 150);

		// Create panel and buttons for the adventures
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Choose your Adventure:");

		JButton caveExplorerButton = new JButton("Cave Explorer");
		JButton morningRoutineButton = new JButton("Morning Routine");

		// Add action listeners to each button
		caveExplorerButton.addActionListener(e -> startCaveExplorerAdventure());
		morningRoutineButton.addActionListener(e -> startMorningRoutineAdventure());

		// Add components to the panel
		panel.add(label);
		panel.add(caveExplorerButton);
		panel.add(morningRoutineButton);

		// Add panel to the frame
		frame.add(panel);
		// Center the frame on the screen
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void startCaveExplorerAdventure() {
		frame.dispose(); // Close the selection screen
		AdventureModelInterface model = new CaveExplorerModel();
		AdventureViewInterface view = new CaveExplorerView();
		new CaveExplorerController(model, view, this::returnToSelection); // Pass a callback to return to selection
	}

	private void startMorningRoutineAdventure() {
		frame.dispose(); // Close the selection screen
		AdventureModelInterface model = new MorningRoutineModel();
		AdventureViewInterface view = new MorningRoutineView();
		new MorningRoutineController(model, view, this::returnToSelection); // Pass a callback to return to selection
	}

	// This method reopens the selection screen after the adventure ends
	private void returnToSelection() {
		SwingUtilities.invokeLater(() -> new AdventureApp()); // Reopen the selection screen
	}

	public static void main(String[] args) {
		new AdventureApp(); // Start the selection screen
	}
}
