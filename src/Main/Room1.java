package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Room1 extends Rooms{
	
	ImageLayer c = new ImageLayer("cave.png", 0, 0, 1);
	
	//actions for characters
		String[] pose = {"dn", "lt", "rt", "up", "atkDN", "atkLT", "atkRT", "atkUP"};
		String[] poseMonster = {"dn", "lt", "rt", "up"};
		int[] count = {9,9,9,9,6,6,6,6};
		int[] countMonster = {3,3,3,3};
		
		
		Rect2[] wall =
			{
				new Rect2(160, 470, 31, 841),
				new Rect2(662, 251, 382, 103),
				new Rect2(611, 155, 51, 492),
				new Rect2(194, 1300, 568, 23),
				new Rect2(759, 1238, 286, 82),
				new Rect2(201, 651, 461, 21),
				new Rect2(1037, 923, 272, 314),
				new Rect2(1501, 1112, 90, 135),
				new Rect2(1312, 987, 187, 135),
				new Rect2(1317, 283, 554, 317),
				new Rect2(1155, 313, 161, 39),
				new Rect2(1122, 186, 29, 167),
				new Rect2(1046, 222, 74, 31),
				new Rect2(2249, 1175, 93, 55),
				new Rect2(2091, 1237, 163, 24),
				new Rect2(2062, 1237, 27, 87),
				new Rect2(2343, 1113, 96, 59),
				new Rect2(1594, 1236, 184, 87),
				new Rect2(1876, 536, 44, 320),
				new Rect2(1921, 756, 147, 100),
				new Rect2(2143, 759, 292, 95),
				new Rect2(2437, 655, 59, 455),
				new Rect2(2056, 710, 101, 47),


			};
		

		Rect2 door1 = new Rect2(1048, 256, 70, 17);
		Rect2 door2 = new Rect2(2070, 756, 72, 20);
	
	
	public Room1(Sprite chris){
			super(chris);
		
	}
		
	public void inGameLoop()
	{
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
										
		//if(UP_Pressed) chris.goUP(5);
		//if(DN_Pressed) chris.goDN(5);
				
		chris.move();
		//bat.chase(chris, 1);
		eyeball.chase(chris, 1);
		
		if(chris.overlaps(door1)) { 
			chris.x = 2084; 
			chris.y = 805;
		}
		//if(chris.overlaps(door2)) chris.x = 1800-100;
		
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
        
    
		
		
		
		//if(LT_Pressed)			Camera.moveLT(10);
		//if(RT_Pressed)			Camera.moveRT(10);
		
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
		
		
	
	}
				
	
		
	public void paint(Graphics pen)
	{
		c.draw(pen);
		
		chris.draw(pen);
		
		for (Sprite sprite : spriteManager.getSprites()) {
            sprite.draw(pen);
        }
		
		// Draw attack hitbox if exists
        if (attackHitbox != null) {
            pen.setColor(Color.RED);
            pen.drawRect(attackHitbox.x, attackHitbox.y, attackHitbox.w, attackHitbox.h);
        }
		
		
		
		//draws wall hitBox
		for(int i = 0; i < wall.length; i++)
		{	
			wall[i].draw(pen);
		}
		
		
		door1.draw(pen);
		door2.draw(pen);
	}
		
	
	
	}


