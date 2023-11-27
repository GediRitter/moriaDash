package main;

import entity.Entity;
import tile.Tile;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		
		this.gp = gp;
		
	}
	
	public void checkTile(Entity ent) {
		
		Tile tileAbove = gp.tm.tile[gp.tm.mapTileNum[ent.col][ent.row - 1]];
		Tile tileBeneath = gp.tm.tile[gp.tm.mapTileNum[ent.col][ent.row + 1]];
		Tile tileLeft = gp.tm.tile[gp.tm.mapTileNum[ent.col - 1][ent.row]];
		Tile tileRight = gp.tm.tile[gp.tm.mapTileNum[ent.col + 1][ent.row]];
		
		switch(ent.direction) {
		
		case "up":
			if(tileAbove.collision) ent.blocked = true;
			if(tileAbove.name == "dirt") {
				gp.tm.mapTileNum[ent.col][ent.row - 1] = 0;
				gp.playSE(1);
			}
			break;
			
		case "down":
			if(tileBeneath.collision) ent.blocked = true;
			if(tileBeneath.name == "dirt") {
				gp.tm.mapTileNum[ent.col][ent.row + 1] = 0;
				gp.playSE(1);
			}
			break;
			
		case "left":
			if(tileLeft.collision) ent.blocked = true;
			if(tileLeft.name == "dirt") {
				gp.tm.mapTileNum[ent.col - 1][ent.row] = 0;
				gp.playSE(0);
			}
			break;
			
		case "right":
			if(tileRight.collision) ent.blocked = true;
			if(tileRight.name == "dirt") {
				gp.tm.mapTileNum[ent.col + 1][ent.row] = 0;
				gp.playSE(1);
			}
			break;
		}
	}

}
