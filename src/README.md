# Adventure Game Project

This project is a text-based modular adventure game built using the MVC (Model-View-Controller) design pattern. The game allows players to choose between different adventure scenarios, such as a **Morning Routine** or a **Cave Exploration**, and interact with the game world by inputting simple text commands.

### Key Components

1. **Controller**: Handles the logic of the game, takes input from the user, updates the model, and updates the view.
   - `AdventureControllerInterface.java`: Defines methods for starting and controlling the adventure.
   - `MorningRoutineController.java`: Controls the logic of the morning routine scenario.
   - `CaveExplorerController.java`: Controls the logic of the cave exploration scenario.

2. **Model**: Manages the game's data and state, keeping track of the player's progress and available actions.
   - `AdventureModelInterface.java`: Interface for defining the model methods.
   - `MorningRoutineModel.java`: Implements the logic for the morning routine adventure.
   - `CaveExplorerModel.java`: Implements the logic for the cave exploration adventure.

3. **View**: Responsible for displaying the game's output to the user.
   - `AdventureViewInterface.java`: Interface for defining view methods.
   - `MorningRoutineView.java`: Displays the morning routine adventure to the user.
   - `CaveExplorerView.java`: Displays the cave explorer adventure to the user.

4. **Parser**: Processes user input.
   - `TextParser.java`: Parses the user input and breaks it into verb-noun combinations.

5. **Main Application**:
   - `AdventureApp.java`: The entry point of the application. It allows the user to select which adventure they want to play and starts the corresponding controller.

## How to Run

1. Compile the project using a Java compiler (e.g., `javac`).
2. Run the `AdventureApp` class to start the game. 
   - Players will be presented with an option to choose between the Morning Routine or Cave Explorer scenarios.
   - Input text commands in the format of "verb noun" (e.g., "wake up", "go inside") to interact with the game.

## Example Commands

- Morning Routine:
  - "wake up" - Starts the day.
  - "go back to sleep" - Ends the day.

- Cave Explorer:
  - "go inside" - Enter the cave.
  - "walk away" - End the adventure.

## Design Pattern

This project uses the **Model-View-Controller (MVC)** design pattern:
- **Model**: Represents the data and business logic.
- **View**: Displays the output and provides the user interface.
- **Controller**: Handles input, processes data, and updates the view.

## Future Improvements

- Expand the storylines for each adventure.
- Add additional scenarios and more interactive elements.
- Implement a more sophisticated input parsing system for handling complex commands.
- Develop a graphical user interface (GUI) for a more interactive experience.
