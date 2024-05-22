package Main;
import java.awt.Graphics;


public class Sprite extends Rect{
	
Animation[] animation;
int[] animationFrames;
private HealthState health;
private HealthBar healthBar;
private SpriteManager spriteManager;
	
	//the number indicates the action that we chose to store in that element of the array
	int action = 0;
	
	
	boolean moving = false;
	
	
	//change count to an array when animations have different # of images, to store the amount of images int[] count
	public Sprite(String name, String[] pose, int x, int y, int[] count, int duration, int maxHealth, SpriteManager spriteManager)
	{
		super(x, y, 50, 50);
		
		//length = amount of total poses, movement, attacking etc 
		animation = new Animation[pose.length];
		animationFrames = new int[pose.length];
		
		for(int i = 0; i < animation.length; i++)
		{
			animationFrames[i] = count[i]; 
			animation[i] = new Animation(name + "_" + pose[i], animationFrames[i], duration);
		}
		
		this.health = new HealthState(maxHealth);
		
		// Add this sprite to the sprite manager
		this.spriteManager = spriteManager;
		spriteManager.addSprite(this);
		
		// Initialize health bar
        this.healthBar = new HealthBar(x, y - 10, 45, 5, maxHealth); // Adjust position and size as needed
		
	}
	
	public void goDN(int dy)
	{
		super.goDN(dy);

		action = 0;

		moving = true;
	}
	
	public void goLT(int dx)
	{
		super.goLT(dx);
		
		action = 1;
		
		moving = true;
	}
	
	public void goRT(int dx)
	{
		super.goRT(dx);

		action = 2;

		moving = true;
	}
	
	public void goUP(int dy)
	{
		super.goUP(dy);

		action = 3;
		
		moving = true;
	}
	
	public void atkDN()
	{
		action = 4;
		
		moving = true;
		
	}
	
	public void atkLT()
	{
		action = 5;
		
		moving = true;
	}
	
	public void atkRT()
	{
		action = 6;
		
		moving = true;
	}
	
	
	
	public void atkUP()
	{
		action = 7;
		
		moving = true;
	}
	
	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}
	
	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}
	
	public boolean takeDamage(int damageAmount) {
		
        health.takeDamage(damageAmount);
        
        if (!health.isAlive()) {
            // Handle sprite death
            return false;
        }
        else return true;
    }
	
	// Method to update health bar
    public void updateHealthBar(int currentHealth) {
        this.healthBar.setCurrentHealth(currentHealth);
    }

    public int getCurrentHealth() {
        return this.health.getCurrentHealth();
    }
    
 // Method to update sprite's position
    public void updatePosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
        
        // Update health bar's position relative to sprite's new position
        this.healthBar.setPosition(newX, newY - 10); // Adjust position as needed
    }
    
	
	public void draw(Graphics pen)
	{	
		
		// Draw health bar
        healthBar.draw(pen);
		
		//idle character logic
        if(!moving) //idle character logic
		{		
			pen.drawImage(animation[action].stillImage(), x - Camera.x, y - Camera.y, w, h, null);
		}	
		else
		{
			pen.drawImage(animation[action].nextImage(), x - Camera.x, y - Camera.y, w, h, null);				
			
		}
		
		moving = false;
		
		
		
		
		//Draws Sprites hitBox
		super.draw(pen);
		
	}

	

	
	
	

}
