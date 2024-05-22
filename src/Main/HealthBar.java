package Main;

import java.awt.Color;
import java.awt.Graphics;


public class HealthBar {
	
	private int x, y, width, height;
    private int maxHealth;
    private int currentHealth;
    
    public HealthBar(int x, int y, int width, int height, int maxHealth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth; // Start with full health
    }
    
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
        // Ensure health doesn't go below 0
        if (this.currentHealth < 0) {
            this.currentHealth = 0;
        }
        // Ensure health doesn't exceed maximum
        if (this.currentHealth > maxHealth) {
            this.currentHealth = maxHealth;
        }
    }
    
 // Method to update health bar's position
    public void setPosition(int x, int y) {
        this.x = x - Camera.x;
        this.y = y - Camera.y;
    }
    
    public void draw(Graphics g) {
        // Calculate the width of the health bar based on current health
        int healthBarWidth = (int) ((double) currentHealth / maxHealth * width);
        
        // Draw the background of the health bar
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
        
        // Draw the health portion of the health bar
        g.setColor(Color.GREEN);
        g.fillRect(x, y, healthBarWidth, height);
    }

}
