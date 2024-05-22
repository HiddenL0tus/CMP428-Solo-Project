package Main;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

import javax.swing.JFrame;

public class MainMenuTest extends Applet implements ActionListener {
	
	 private Button playButton;
	 
	 public void init() {
		 
		 // Initialize UI components
		 playButton = new Button("Play");
		 playButton.addActionListener(this);
		 
		 // Add components to the layout
		 add(playButton);
		 
		 // Other UI initialization (layout, styles, etc.)
		 
	 }
	 
	 public void actionPerformed(ActionEvent e) {
		 
		 if (e.getSource() == playButton) {
	            // Load the game
	            GameS24 game = new GameS24();
	            game.initializeGame();

	            // Start the game loop
	            Thread gameThread = new Thread(game::inGameLoop);
	            gameThread.start();

	            // Add the game to the main window (e.g., Applet or JFrame)
	            // Replace this with the appropriate code for your application
	            getParent().add(game);

	            // Transition to the game screen by removing the main menu
	            getParent().remove(this);
	        }

	    }

}
