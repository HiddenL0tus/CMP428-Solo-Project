package Main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Room2 extends Rooms{
	
	ImageLayer f = new ImageLayer("forest.png", 0, 0, 1);
	int currentAnimation = 0;
	
	public Room2(Sprite chris){
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
										
				
		chris.move();
				
	}
		
	public void paint(Graphics pen)
	{
		f.draw(pen);
		
		chris.draw(pen);
	}

}
