package Main;
import java.awt.Color;
import java.awt.Graphics;


public class Rect {
	
	public int x, y, w, h;
	
	double vx = 0;
	double vy = 0;
	
	double ay = G;
	
	static double G = .7;
	static double F = .6;
	
	boolean held = false;
	
	public void physicsOff()
	{
		vx = 0;
		vy = 0;
		
		ay = 0;
	}
	
	public Rect(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;

	}
	
	public void setVelocity(double vx, double vy)
	{
		this.vx = vx;
		this.vy = vy;
	}
	
	public void grabbed()
	{
		held = true;
	}
	
	public void dropped()
	{
		held = false;
	}
	
	public void goLT(int vx)
	{
		this.vx = -vx;		
	}
	
	public void goRT(int vx)
	{
		
		this.vx = +vx;		
	}
	
	public void goUP(int vy)
	{
		this.vy = -vy;
	}
	
	public void goDN(int vy)
	{
		this.vy = +vy;
	}
	
	
	public void jump(int h)
	{
		vy = -h;		
	}
	
	public void move()
	{
		x += vx;
		y += vy + G/2;
		
		vy += G;
	}
	
	public void moveBy(int dx, int dy)
	{
		x += dx;
		y += dy;
	}
	
	
	public void resizeBy(int dw, int dh)
	{
		w += dw;
		
		h += dh;
	}
	
	public void chase(Rect r, int dx)
	{
		if(isBelow(r))    goUP(dx);
		if(isAbove(r))    goDN(dx);
		if(isRightOf(r))  goLT(dx); 
		if(isLeftOf(r))   goRT(dx); 
		 
		
		move();
	}
	
	public void evade(Rect r, int dx)
	{
		if(isLeftOf(r))   goLT(dx); 
		if(isRightOf(r))  goRT(dx); 
		if(isAbove(r))    goUP(dx); 
		if(isBelow(r))    goDN(dx); 
		
		move();
	}
	
	public boolean isLeftOf(Rect r)
	{
		return x + w < r.x;
	}
	
	public boolean isRightOf(Rect r)
	{
		return r.x + r.w < x;
	}
	
	public boolean isAbove(Rect r)
	{
		return y + h < r.y;
	}
	
	public boolean isBelow(Rect r)
	{
		return r.y + r.h < y;
	}
	
	
	
	public boolean contains(int mx, int my)
	{
		return (mx >= x    )  &&
			   (mx <= x + w)  &&
			   (my >= y    )  &&
			   (my <= y + h);
	}
	
	
	public boolean overlaps(Rect r)
	{
		return (x + w >= r.x      ) &&				
			   (x     <= r.x + r.w) &&
			   (y + h >= r.y      ) &&			   
			   (y     <= r.y + r.h);
	}
	
	public void pushedOutOf(Rect r)
	{
		if(cameFromAbove(r)) 	pushbackUpFrom(r);
		if(cameFromBelow(r))    pushbackDownFrom(r);
		if(cameFromLeftOf(r))   pushbackLeftFrom(r);		
		if(cameFromRightOf(r))	pushbackRightFrom(r);
		
		vx *= F;
		
		if(Math.abs(vx) <= 1)  vx = 0;
	}
	
	
	public void bounceOff(Rect r)
	{
		if(cameFromAbove(r)  || cameFromBelow(r))    vy = -vy;
		if(cameFromLeftOf(r) || cameFromRightOf(r))  vx = -vx;
	}
	
	public boolean cameFromLeftOf(Rect r)
	{
		return x - vx + w < r.x;
	}
	
	public boolean cameFromRightOf(Rect r)
	{
		return r.x + r.w < x - vx;
	}
	
	public boolean cameFromAbove(Rect r)
	{
		return y - vy + h < r.y;
	}
	
	public boolean cameFromBelow(Rect r)
	{
		return r.y + r.h < y - vy;
	}
	
	public void pushbackLeftFrom(Rect r)
	{
		x = r.x - w - 1;
	}
	
	public void pushbackRightFrom(Rect r)
	{
		x = r.x + r.w + 1;
	}
	
	public void pushbackUpFrom(Rect r)
	{
		y = r.y - h - 1;
		
		vy = 0;
	}
	
	public void pushbackDownFrom(Rect r)
	{
		y = r.y + r.h + 1;
	}
	
	
	public void draw(Graphics pen)
	{
		pen.setColor(Color.GREEN);
		pen.drawRect((int)(x - Camera.x), (int)(y - Camera.y), (int)w, (int)h);
	}
	
	
	public String toString()
	{
		return "Coordinates:(" + x + ", " + y + ", " + w + ", " + h + "),";
	}

}
