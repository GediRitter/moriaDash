package object;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.CollisionChecker;
import main.GamePanel;

public class Mithril extends SuperObject {

	GamePanel gp;
	
	public Mithril(GamePanel gp, int x, int y) {//
		this.gp = gp;
		
		setValues(x, y); 
		getRockImage();
	}

	public void getRockImage() {
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/mithrilUnfinished.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
 
	public void setValues(int x, int y) {
		super.x = x;
		super.y = y;
		col = x / gp.tileSize;
		row = y / gp.tileSize;
		super.name = "mithril";
	}
	
	public void update() {
		
		blocked = false;
		blockedLeft = false;
		blockedRight = false;
		
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
				
				gp.tm.mapTileNum[col][row] = 5;
				
				positionCounter = 0;
			}
		}
		
		gp.checker.collect(this);
		
		
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
	
	public void collect() {
		gp.playSE(10);
		
		gp.mithril++;
		gp.tm.mapTileNum[col][row] = 0;
		gp.obj[gp.searchObj(this)] = null;
	}
	
}

