package view;

import java.util.List;


 //Interface for all adventure views.
 //Views are responsible for displaying the game state and receiving user input.

public interface AdventureViewInterface {

 
    // Updates the description area with the current game state.
    //@param description The description to display.
    void updateDescription(String description);
    
     //Displays the available actions that can be performed in the game.
     //Clears the previous actions before displaying the new list.
     //param actions A list of available actions.
    void displayAvailableActions(List<String> actions);

     //Gets the user's input from the text field.
     //return The input string.
     
    String getInput();

    //Clears the input field after use.
    void clearInput();

    //Sets the action to be performed when the submit button is clicked.
    //param action The action to execute on submit.
     
    void setSubmitAction(Runnable action);
    
    // Close the frame
    public void closeFrame();
}
