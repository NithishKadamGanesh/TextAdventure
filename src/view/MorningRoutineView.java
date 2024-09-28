package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//View for the Morning Routine Adventure.
// Displays a simple layout for the user to interact with the morning routine.

public class MorningRoutineView implements AdventureViewInterface {

	private JFrame frame;
	private JTextArea descriptionArea;
	private JTextArea actionsArea;
	private JTextField inputField;
	private JButton submitButton;

	// Constructor initializes the GUI components and sets up the frame.
	public MorningRoutineView() {
		frame = new JFrame("Morning Routine Adventure");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 400);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		descriptionArea = new JTextArea(4, 30);
		descriptionArea.setEditable(false); // Make the description read-only.
		descriptionArea.setLineWrap(true); // Enable line wrapping
		descriptionArea.setWrapStyleWord(true); // Wrap lines at word boundaries
		JScrollPane descriptionScroll = new JScrollPane(descriptionArea);

		actionsArea = new JTextArea(4, 30);
		actionsArea.setEditable(false); // Make the description read-only.
		JScrollPane actionsScroll = new JScrollPane(actionsArea);

		inputField = new JTextField(20);
		submitButton = new JButton("Submit");

		// Add components to the main panel.
		mainPanel.add(new JLabel("Game Description:"));
		mainPanel.add(descriptionScroll);
		mainPanel.add(new JLabel("Available Actions:"));
		mainPanel.add(actionsScroll);

		JPanel inputPanel = new JPanel(new FlowLayout());
		inputPanel.add(new JLabel("Enter action (e.g., 'wake up')"));
		inputPanel.add(inputField);
		inputPanel.add(submitButton);

		mainPanel.add(inputPanel);

		frame.add(mainPanel);
		// Center the frame on the screen
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	// Updates the description area with the latest game state.
	@Override
	public void updateDescription(String description) {
		descriptionArea.setText(description);
	}

	// Displays the available actions in the actions area.
	@Override
	public void displayAvailableActions(List<String> actions) {
		actionsArea.setText(""); // Clear previous actions
		actionsArea.append(String.join("\n", actions)); // Display updated actions
		actionsArea.append("\nexit_story (to exit and go back to selection screen)"); // Display exit_story to exit and
																						// go back to selection screen
	}

	// Gets the input from the user.
	@Override
	public String getInput() {
		return inputField.getText();
	}

	// Clears the input field after submission.
	@Override
	public void clearInput() {
		inputField.setText(""); // Clear the input field after submission
	}

	// Sets the action to be performed when the submit button is pressed.
	@Override
	public void setSubmitAction(Runnable submitAction) {
		// Submit action with the button
		submitButton.addActionListener(e -> submitAction.run());

		// Submit action with the Enter key
		inputField.addActionListener(e -> submitAction.run());
	}

	// Closes the window when the game ends or the user exits before opening a new
	// one.
	@Override
	public void closeFrame() {
		frame.dispose();
	}
}
