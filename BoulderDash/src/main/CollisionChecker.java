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
			if(tileAbove.collision) ent.collisionOn = true;
			break;
			
		case "down":
			if(tileBeneath.collision) ent.collisionOn = true;
			break;
			
		case "left":
			if(tileLeft.collision) ent.collisionOn = true;
			break;
			
		case "right":
			if(tileRight.collision) ent.collisionOn = true;
			break;
		}
	}

}
