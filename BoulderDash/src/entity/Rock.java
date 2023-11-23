package entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Rock extends Entity {

	GamePanel gp;
	
	public Rock(GamePanel gp) {//, int x, int y
		this.gp = gp;
		
		setDefaultValues(); //x, y
		getRockImage();
	}

	public void getRockImage() {
		
		try {
			
			idle = ImageIO.read(getClass().getResourceAsStream("/otherEntities/rock.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void setDefaultValues() { //int x, int y
//		super.x = x;
//		super.y = y;
		x = gp.tileSize * 4;
		y = gp.tileSize * 5;
		
		direction = "idle";
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(idle, x, y, gp.tileSize, gp.tileSize, null);
	}
}
