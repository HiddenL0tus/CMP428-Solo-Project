package Main;

import javax.swing.*;
import java.awt.event.*;

public class MainMenu extends JFrame{
	
	private JButton runButton;

    public MainMenu() {
        super("Run Class Example");

        runButton = new JButton("Run Class");
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to execute MyClassToRun
            	startApplet();
            }
        });

        JPanel panel = new JPanel();
        panel.add(runButton);

        getContentPane().add(panel);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void startApplet() {
        // Create a JFrame to host the applet
        JFrame frame = new JFrame("Applet Example");

        // Create an instance of your applet
        GameS24 game = new GameS24();

        // Add the applet to the frame's content pane
        frame.getContentPane().add(game);

        // Set the size of the frame
        frame.setSize(800, 600);

        // Set the default close operation
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Make the frame visible
        frame.setVisible(true);

        // Start the applet
        game.init();
        game.start();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainMenu();
            }
        });
    }

}
