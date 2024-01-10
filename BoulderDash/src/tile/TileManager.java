package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	GamePanel gp;
	public Tile [] tile;
	
	public int mapTileNum [][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		
		getTileImages();
	}
	
	public void getTileImages() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/void.png"));
			tile[0].name = "void";
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/wall.png"));
			tile[1].collisionPlayer = true;
			tile[1].collisionObj = true;
			tile[1].name = "wall";
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/dirt.png"));
			tile[2].removeable = true;
			tile[2].collisionPlayer = true;
			tile[2].collisionObj = true;
			tile[2].name = "dirt";
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/bricks.png"));
			tile[3].collisionPlayer = true;
			tile[3].collisionObj = true;
			tile[3].name = "bricks";
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/void.png"));
			tile[4].collisionPlayer = true;
			tile[4].collisionObj = true;
			tile[4].name = "rock";
			tile[4].isObj = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tile/void.png"));
			tile[5].collisionObj = true;
			tile[5].name = "mithril";
			tile[5].isObj = true;
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tile/void.png"));
			tile[6].collisionObj = true;
			tile[6].name = "spawn";
			tile[6].isObj = true;
			
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tile/wall.png"));
			tile[7].collisionObj = true;
			tile[7].collisionPlayer = true;
			tile[7].name = "goal";
			tile[7].isObj = true;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadMap(String filePath) {
		
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(row < gp.maxScreenRow) {
				
				String line = br.readLine();
				String [] numbers = line.split(" ");
				
				while(col < gp.maxScreenCol) {
					
					int num = Integer.parseInt(numbers[col]);
					
					if(num == 6) {
						gp.spawnX = col * gp.tileSize;
						gp.spawnY = row * gp.tileSize;
					}
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxScreenCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		} catch(Exception e) {
			
		}
		
	}
	
	public void goalAchieved() {
		tile[7].collisionPlayer = false;
		try {
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tile/void.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void goalUnachieved() {
		tile[7].collisionPlayer = true;
		try {
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tile/wall.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int col = 0;
		int row = 0;
		
		while(row < gp.maxScreenRow) {
			
			int x = col * gp.tileSize;
			int y = row * gp.tileSize;
			
			g2.drawImage(tile[mapTileNum[col][row]].image, x, y, gp.tileSize, gp.tileSize, null);
			col++;
				
			if(col == gp.maxScreenCol) {
				col = 0;
				row++;
			}
			
		}
		
	}
	
}
