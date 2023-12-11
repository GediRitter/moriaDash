package object;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.GamePanel;

public class ObjectManager {
	
	GamePanel gp;
	
	public ObjectManager(GamePanel gp) {
		this.gp = gp;
	}

	public void draw(Graphics2D g2) {
		for(int i = 0; i < gp.obj.length; i++) {
			if(gp.obj[i] != null)gp.obj[i].draw(g2);
		}
	}
	
	public void loadObj() {
		
		int col = 0;
		int row = 0;
		
		int counter = 0;
		
		while(row < gp.maxScreenRow) {
			
			int num = gp.tm.mapTileNum[col][row];
			

			if(num == 4) {
				gp.obj[counter] = new Rock(gp, col * gp.tileSize, row * gp.tileSize);
				counter++;
			}
			if(num == 5) {
				gp.obj[counter] = new Mithril(gp, col * gp.tileSize, row * gp.tileSize);
				counter++;
			}
			
			col++;
				
			if(col == gp.maxScreenCol) {
				col = 0;
				row++;
			}
			
		}
		
					
		
	}
	
	public void update() {
		for(int i = 0; i < gp.obj.length; i++) {
			if(gp.obj[i] != null)gp.obj[i].update();
		}
	}
	
}
