package Main;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class Rooms {
	
	boolean UP_Pressed = false;
	boolean DN_Pressed = false;
	boolean LT_Pressed = false;
	boolean RT_Pressed = false;
	boolean isAttacking = false;	
	
	
	public SpriteManager spriteManager = new SpriteManager();
	
	static Sprite chris;
	static Sprite bat;
	static Sprite eyeball;
	
	static int currentAnimation = 0;
	static Rect attackHitbox;
	public static long lastAttackTime = 0; //long provides a wider range and higher precision for storing time values, making it more suitable for tasks involving time tracking.
	public static final long ATTACK_COOLDOWN = 1000; // 1000 milliseconds = 1 second
	
	
	public Rooms(Sprite chris)
	{
		Rooms.chris = chris;
		Rooms.bat = bat;
		Rooms.eyeball = eyeball;
			
	}
	
	public void keyPressed(KeyEvent e)
	{		
		int code = e.getKeyCode();
		
		if (code == e.VK_UP   )   UP_Pressed = true;  
		if (code == e.VK_DOWN )   DN_Pressed = true;  
		if (code == e.VK_LEFT )   LT_Pressed = true;  
		if (code == e.VK_RIGHT)   RT_Pressed = true; 
		if (code == e.VK_Z)      isAttacking = true;
		
		
	}
	
	public void keyReleased(KeyEvent e)
	{
		int code = e.getKeyCode();

		if (code == e.VK_UP   )   UP_Pressed = false;  
		if (code == e.VK_DOWN )   DN_Pressed = false;  
		if (code == e.VK_LEFT )   LT_Pressed = false;  
		if (code == e.VK_RIGHT)   RT_Pressed = false;
		if (code == e.VK_Z)      {
			isAttacking = false;
			attackHitbox = null; // Reset the attack hitbox
		}
		
	}
	
	
	
	public abstract void inGameLoop();
	
	public abstract void paint(Graphics pen);

}
