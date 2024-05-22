package Main;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class GameS24 extends GameBase {
	
	
	
	
	private SpriteManager spriteManager = new SpriteManager();
	
	
	Image testMap = Toolkit.getDefaultToolkit().getImage("map1.png");
	
	//actions for characters
	String[] pose = {"dn", "lt", "rt", "up", "atkDN", "atkLT", "atkRT", "atkUP"};
	String[] poseMonster = {"dn", "lt", "rt", "up"};
	int[] count = {9,9,9,9,6,6,6,6};
	int[] countMonster = {3,3,3,3};
	
	Sprite chris;
	int currentAnimation = 0;
	private Rect attackHitbox; // Variable to hold the attack hitbox
	
	Sprite bat;
	Sprite eyeball;
	private long lastAttackTime = 0; //long provides a wider range and higher precision for storing time values, making it more suitable for tasks involving time tracking.
	private static final long ATTACK_COOLDOWN = 1000; // 1000 milliseconds = 1 second
	
	
	public static final int MAP_WIDTH = 100;   // in tiles
	public static final int MAP_HEIGHT = 100;  // in tiles
	public static final int TILE_SIZE = 16;    // in pixels
	public static final int MAP_PIXEL_WIDTH = MAP_WIDTH * TILE_SIZE*3;    // in pixels
	public static final int MAP_PIXEL_HEIGHT = MAP_HEIGHT * TILE_SIZE*3;  // in pixels
	  
	//get the screen width and height of the device being used for camera calculations
	public static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public static int SCREEN_WIDTH = gd.getDisplayMode().getWidth ();
	public static int SCREEN_HEIGHT = gd.getDisplayMode().getHeight();
	
	
	
	Rect2[] wall =
		{
				new Rect2(1920, 3, 960, 144),
				new Rect2(3840, 3, 960, 144),
				new Rect2(48, 336, 96, 1680),
				new Rect2(48, 336, 1968, 144),
				new Rect2(1920, 3, 96, 480),
				new Rect2(2784, 3, 96, 480),
				new Rect2(2784, 336, 240, 144),
				new Rect2(2928, 144, 96, 336),
				new Rect2(2928, 144, 912, 144),
				new Rect2(3744, 96, 96, 432),
				new Rect2(4704, 3, 96, 4752),
				new Rect2(1920, 1824, 432, 240),
				new Rect2(2448, 1824, 432, 240),
				new Rect2(1920, 624, 96, 2640),
				new Rect2(2784, 624, 96, 2640),
				new Rect2(1920, 2208, 240, 144),
				new Rect2(2256, 2208, 624, 144),
				new Rect2(1920, 2784, 960, 96),
				new Rect2(2400, 2208, 96, 432),
				new Rect2(1920, 1344, 720, 192),
				new Rect2(2736, 1344, 48, 192),
				new Rect2(1920, 864, 432, 240),
				new Rect2(2448, 864, 432, 240),
				new Rect2(2304, 384, 192, 144),
				new Rect2(2784, 624, 240, 336),
				new Rect2(2880, 960, 384, 144),
				new Rect2(3168, 960, 96, 912),
				new Rect2(3360, 1056, 96, 192),
				new Rect2(3360, 1392, 96, 192),
				new Rect2(3744, 480, 336, 240),
				new Rect2(3840, 3, 96, 576),
				new Rect2(4176, 480, 624, 144),
				new Rect2(4176, 768, 624, 144),
				new Rect2(4176, 480, 96, 576),
				new Rect2(3552, 960, 720, 144),
				new Rect2(3552, 960, 96, 624),
				new Rect2(3552, 1248, 624, 144),
				new Rect2(4080, 960, 96, 288),
				new Rect2(3168, 1728, 1248, 144),
				new Rect2(4512, 1728, 288, 240),
				new Rect2(3648, 1728, 96, 432),
				new Rect2(4320, 1728, 96, 288),
				new Rect2(2880, 2784, 432, 144),
				new Rect2(3216, 2448, 528, 144),
				new Rect2(3648, 2304, 96, 288),
				new Rect2(3216, 2448, 96, 432),
				new Rect2(3408, 2784, 1404, 144),
				new Rect2(3744, 2496, 240, 48),
				new Rect2(4080, 2496, 624, 48),
				new Rect2(3840, 2784, 96, 1056),
				new Rect2(2784, 3840, 1152, 144),
				new Rect2(2496, 3408, 384, 480),
				new Rect2(1920, 3408, 384, 480),
				new Rect2(2304, 3408, 48, 144),
				new Rect2(2448, 3408, 48, 144),
				new Rect2(2448, 3744, 48, 144),
				new Rect2(2304, 3744, 48, 144),
				new Rect2(2256, 2832, 96, 432),
				new Rect2(2448, 2832, 96, 432),
				new Rect2(1920, 3120, 432, 144),
				new Rect2(2448, 3120, 432, 144),
				new Rect2(864, 3840, 1152, 144),
				new Rect2(1440, 3840, 96, 960),
				new Rect2(1440, 4704, 1920, 96),
				new Rect2(3264, 3840, 96, 432),
				new Rect2(3264, 4368, 96, 432),
				new Rect2(3264, 4032, 768, 192),
				new Rect2(3936, 3888, 864, 144),
				new Rect2(3264, 4368, 768, 192),
				new Rect2(3936, 4560, 864, 96),
				new Rect2(1920, 4128, 192, 144),
				new Rect2(2688, 4128, 192, 144),
				new Rect2(1632, 4368, 192, 144),
				new Rect2(2304, 4368, 192, 144),
				new Rect2(2976, 4368, 192, 144),
				new Rect2(864, 2928, 96, 912),
				new Rect2(864, 2784, 528, 144),
				new Rect2(1488, 2784, 528, 144),
				new Rect2(240, 2928, 672, 96),
				new Rect2(240, 1824, 96, 1200),
				new Rect2(48, 1824, 1344, 144),
				new Rect2(240, 2112, 768, 144),
				new Rect2(1152, 2112, 240, 144),
				new Rect2(768, 1824, 96, 384),
				new Rect2(1296, 1824, 96, 384),
				new Rect2(240, 2496, 240, 144),
				new Rect2(624, 2496, 1296, 144),
				new Rect2(1824, 2112, 96, 480),
				new Rect2(1488, 2112, 432, 144),
				new Rect2(1488, 1824, 336, 432),
				new Rect2(1728, 624, 192, 1296),
				new Rect2(240, 1488, 1392, 144),
				new Rect2(1536, 1488, 96, 336),
				new Rect2(240, 1200, 576, 144),
				new Rect2(912, 1200, 672, 144),
				new Rect2(1488, 1296, 96, 96),
				new Rect2(144, 912, 192, 144),
				new Rect2(432, 912, 768, 144),
				new Rect2(1296, 912, 432, 144),
				new Rect2(1584, 1056, 144, 192),
				new Rect2(240, 624, 480, 144),
				new Rect2(816, 624, 768, 144),
				new Rect2(432, 432, 96, 240),
				new Rect2(1344, 432, 96, 240),
				new Rect2(1344, 432, 96, 240),


		};
	
	//Used for Door Location tool
	Rect2[] door = {
			new Rect2(300, 300, 50, 50),
			new Rect2(300, 450, 50, 50)
	};
	
	Rect2 door1 = new Rect2(1048, 256, 70, 17);
	Rect2 door2 = new Rect2(2070, 756, 72, 20);
	
//	public void initializeGame() {
//		
//		testMap = Toolkit.getDefaultToolkit().getImage("cave.png");
//		currentAnimation = 0;
//        attackHitbox = null;
//		
//		
//	}
	public void initialize() {
		
		chris = new Sprite("c", pose, 4555, 4405, count, 10, 6, spriteManager);
		bat = new Sprite("bat", poseMonster, 1780, 4245, countMonster, 20, 3, spriteManager);
		eyeball = new Sprite("eyeball", poseMonster, 1680, 4145, countMonster, 20, 3, spriteManager);
		
		int cameraX = chris.x - (SCREEN_WIDTH / 2) + (chris.w / 2);
	    int cameraY = chris.y - (SCREEN_HEIGHT / 2) + (chris.h / 2 );
	    Camera.setPosition(cameraX, cameraY);
		
	}
	
	public void inGameLoop()
	{
	
		chris.physicsOff();
		bat.physicsOff();
		eyeball.physicsOff();
			
	
		if(UP_Pressed ) {
			chris.goUP(5);
			currentAnimation = 3;
		}
		if(DN_Pressed) {
			chris.goDN(5);
			currentAnimation = 0;
		}
		if(LT_Pressed) {
			chris.goLT(5);
			currentAnimation = 1;
		}
		if(RT_Pressed) {
			chris.goRT(5);
			currentAnimation = 2;
		}
		
		chris.move();
		bat.chase(chris, 1);
		eyeball.chase(chris, 1);
		
		isAttacking();
		attackCooldown();
										
		
		//teleports player
		if(chris.overlaps(door1)) { 
			chris.x = 2084; 
			chris.y = 805;
		}
		if(chris.overlaps(door2)) chris.x = 1800-100;
		
		
		
		//CODE THAT ITERATES THROUGH wall Array AND CHECKS IF CHRIS IS OVERLAPS, THEN PUSHES OUT//
		for( int i = 0; i < wall.length; i++) {
			if(chris.overlaps(wall[i])){
				
				chris.pushedOutOf(wall[i]);
				}
			if(bat.overlaps(wall[i])){
				
				bat.pushedOutOf(wall[i]);
				}
			if(eyeball.overlaps(wall[i])){
				
				eyeball.pushedOutOf(wall[i]);
				}
		}
		
		if(bat.overlaps(eyeball)) {
			bat.pushedOutOf(eyeball);
		}
		
		if(eyeball.overlaps(bat)) {
			eyeball.pushedOutOf(bat);
		}
		
		updateSpritePositions();
		updateHealthBars();
		
		updateCamera();
	
		
	}
	
	public void updateCamera()
	{
		//centers the camera on the player
		int targetX = (int)Math.round(chris.x + (chris.w / 2) - (SCREEN_WIDTH  / 2));
	    int targetY = (int)Math.round(chris.y + (chris.h / 2) - (SCREEN_HEIGHT / 2));

	    Camera.setPosition(targetX, targetY); 
	}
	
	public void isAttacking() {
		
		if(isAttacking && currentAnimation==0) {
			chris.atkDN();
			if (attackHitbox == null) {
                attackHitbox = new Rect(chris.x, chris.y + chris.w, chris.w, chris.h); // Example dimensions
            }
			else {
	            attackHitbox = null; // Reset the attack hitbox when not attacking
	        }
		
		}
		if(isAttacking && currentAnimation==1) {
			chris.atkLT();
			if (attackHitbox == null) {
                attackHitbox = new Rect(chris.x - chris.w, chris.y, chris.w, chris.h); // Example dimensions
            }
			else {
	            attackHitbox = null; // Reset the attack hitbox when not attacking
	        }
		
		}
		if(isAttacking && currentAnimation==2) {
			chris.atkRT();
			if (attackHitbox == null) {
                attackHitbox = new Rect(chris.x + chris.w, chris.y, chris.w, chris.h); // Example dimensions
            }
			else {
	            attackHitbox = null; // Reset the attack hitbox when not attacking
	        }
		}
		if(isAttacking && currentAnimation==3) {
			chris.atkUP();
			if (attackHitbox == null) {
                attackHitbox = new Rect(chris.x, chris.y - chris.h, chris.w, chris.h); // Example dimensions
            }
			else {
	            attackHitbox = null; // Reset the attack hitbox when not attacking
	        }
		}
	}
	
	public void attackCooldown() {
		
		if (attackHitbox != null) {
			
			// Check if enough time has passed since the last attack
		    long currentTime = System.currentTimeMillis();
		    if (currentTime - lastAttackTime > ATTACK_COOLDOWN) {
		    
			if (attackHitbox.overlaps(bat)) {
				if (!chris.takeDamage(1)) {
					spriteManager.removeSprite(bat); // Remove the sprite if it's dead
				}
				// Update last attack time
	            lastAttackTime = currentTime;
			}
			if (attackHitbox.overlaps(eyeball)) {
				if (!chris.takeDamage(1)) {
					spriteManager.removeSprite(eyeball); // Remove the sprite if it's dead
				}
				
				// Update last attack time
	            lastAttackTime = currentTime;
			}
		
		    }
		    
		}
	}
	
	private void updateSpritePositions() {
        // Iterate over all sprites in the sprite manager
        for (Sprite sprite : spriteManager.getSprites()) {
            // Update the position of the sprite
        	int newX = sprite.getX(); // Example: update X position based on sprite's movement
            int newY = sprite.getY(); // Example: update Y position based on sprite's movement
            
            // Update the position of the sprite
            sprite.updatePosition(newX, newY);
        }
    }
	
	private void updateHealthBars() {
        // Iterate over all sprites in the sprite manager
        for (Sprite sprite : spriteManager.getSprites()) {
            // Get the current health of the sprite
            int currentHealth = sprite.getCurrentHealth();
            // Update the health bar for the sprite
            sprite.updateHealthBar(currentHealth);
        }
    }
	
	
	
	public void paint(Graphics pen)
	{
		
		pen.setColor(Color.BLACK);
		pen.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		pen.drawImage(testMap, 0 - Camera.x, 0 - Camera.y, MAP_PIXEL_WIDTH, MAP_PIXEL_HEIGHT, null);
		
		//Draws all the sprites in the spriteManager List
		for (Sprite sprite : spriteManager.getSprites()) {
            sprite.draw(pen);
        }
		
		// Draw attack hitbox if exists
        if (attackHitbox != null) {
            pen.setColor(Color.RED);
            pen.drawRect(attackHitbox.x - Camera.x, attackHitbox.y- Camera.y, attackHitbox.w, attackHitbox.h);
        }
		
		
		//draws wall hitBox
		for(int i = 0; i < wall.length; i++)
		{	
			wall[i].draw(pen);
		}
		
		for(int i = 0; i < door.length; i++)
		{	
			door[i].draw(pen);
		}
		
		door1.draw(pen);
		door2.draw(pen);
		
}
	
	
	
	
	
	
	public void keyPressed(KeyEvent e)
	{		
		int code = e.getKeyCode();
		
		if (code == e.VK_UP   )   UP_Pressed = true;  
		if (code == e.VK_DOWN )   DN_Pressed = true;  
		if (code == e.VK_LEFT )   LT_Pressed = true;  
		if (code == e.VK_RIGHT)   RT_Pressed = true; 
		if (code == e.VK_Z)      isAttacking = true;
		
		if (code == e.VK_P) {
			
			//TOOL FOR SIZING RECTS//
			System.out.println("This is Chris' location: " + chris);
			
			System.out.println("These are the wall coordinates: ");
			for(int i = 0; i < wall.length; i++) {
				System.out.println(wall[i]);
			}
			
			System.out.println();
			
			System.out.println("These are the test door coordinates: ");
			for(int i = 0; i < door.length; i++) {
				System.out.println(door[i]);
			}
			
			System.out.println();
			
			System.out.println("These are the actual door coordinates: ");
			System.out.println(door1);
			System.out.println(door2);
		}
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
	
	//TOOL for RESIZING RECTS//
	public void mouseDragged(MouseEvent e)
	{
		int nx = e.getX();
		int ny = e.getY();
		
		int dx = nx - mx;
		int dy = ny - my;
		
		for(int i = 0; i < wall.length; i++) {
			if(wall[i].resizer.held)  wall[i].resizeBy(dx,  dy);
		else
		if(wall[i].held)  wall[i].moveBy(dx, dy);
		}
		
		for(int i = 0; i < door.length; i++) {
			if(door[i].resizer.held)  door[i].resizeBy(dx,  dy);
		else
		if(door[i].held)  door[i].moveBy(dx, dy);
		}
		
		mx = nx;
		my = ny;
	}
	
	public void mousePressed(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
		
		for(int i = 0; i <wall.length; i++) {
		if(wall[i].contains(mx,  my))  wall[i].grabbed();
		if(wall[i].resizer.contains(mx,  my))  wall[i].resizer.grabbed();
		}
		
		for(int i = 0; i <door.length; i++) {
		if(door[i].contains(mx,  my))  door[i].grabbed();
		if(door[i].resizer.contains(mx,  my))  door[i].resizer.grabbed();
		}
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
		for(int i= 0; i < wall.length; i++) {
		wall[i].dropped();
		wall[i].resizer.dropped();
		}
		
		for(int i= 0; i < door.length; i++) {
		door[i].dropped();
		door[i].resizer.dropped();
		}
	}
	//TOOL for RESIZING RECTS//
	

}
