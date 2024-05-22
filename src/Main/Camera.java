package Main;


public class Camera {
	
	public static int x;
	public static int y;
	
	
	public static void setPosition(int x, int y)
	{
		Camera.x = x;
		Camera.y = y;
	}
	
	public static void moveLT(int dx)
	{
		x -= dx;
	}
	
	public static void moveRT(int dx)
	{
		x += dx;
	}
	
	
	public static void moveUP(int dy)
	{
		y -= dy;
	}
	
	public static void moveDN(int dy)
	{
		y += dy;
	}

}
