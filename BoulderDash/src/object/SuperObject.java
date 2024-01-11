package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SuperObject {
	
	public BufferedImage image;
	public String name;
	
	public boolean blocked = false;
	public boolean blockedLeft = false;
	public boolean blockedRight = false;
	public boolean falling = true;
	public int fallingCount = 0;
	public boolean playerBeneath = false;
	
	public int x, y;
	public int col, row;
	
	public int positionCounter = 0;
	
	
	public void draw(Graphics2D g2) {
		
	}
	
	public void update() {
		
	}

	public void collect() {
		// TODO Auto-generated method stub
		
	}

}
