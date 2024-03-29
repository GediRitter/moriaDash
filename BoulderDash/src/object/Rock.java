package object;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Rock extends SuperObject {

	GamePanel gp;
	
	public Rock(GamePanel gp, int x, int y) {//
		this.gp = gp;
		
		setValues(x, y); 
		getRockImage();
	}

	public void getRockImage() {
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/rock.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
 
	public void setValues(int x, int y) {
		super.x = x;
		super.y = y;
		col = x / gp.tileSize;
		row = y / gp.tileSize;
		super.name = "rock";
	}
	
	public void update() {
		
		blocked = false;
		blockedLeft = false;
		blockedRight = false;
		slide = false;
		
		gp.checker.checkCollisionObj(this);
		
		if(!blocked || !blockedLeft || !blockedRight) {
			
			positionCounter++;
			
			if(positionCounter == 3) {
				
				if(!blocked) y += gp.tileSize;
				else if(!blockedLeft) x -= gp.tileSize;
				else if(!blockedRight) x += gp.tileSize;
				
				fallingCount++;
				
				gp.tm.mapTileNum[col][row] = 0;
				
				col = x / gp.tileSize;
				row = y / gp.tileSize;
				
				gp.tm.mapTileNum[col][row] = 4;
				
				positionCounter = 0;
			}
		}
		
		if(slide) {
			positionCounter++;
			
			if(positionCounter == 3) {
				
				if(!blockedLeft) x -= gp.tileSize;
				else if(!blockedRight) x += gp.tileSize;
				
				
				gp.tm.mapTileNum[col][row] = 0;
				
				col = x / gp.tileSize;
				row = y / gp.tileSize;
				
				gp.tm.mapTileNum[col][row] = 4;
				
				positionCounter = 0;
			}
		}
		
		gp.checker.kill(this);
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
}
