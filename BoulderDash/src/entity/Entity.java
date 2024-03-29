package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	
	public int x, y;
	public int col, row;
	public int speed;
	
	public BufferedImage idle, up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	
	public int positionCounter = 0;
	public int spriteNum = 1;
	
	public boolean blocked = false;
	public boolean dirt;
	public boolean pushLeft, pushRight;

}
