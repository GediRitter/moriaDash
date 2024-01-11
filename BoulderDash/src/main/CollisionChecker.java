package main;

import entity.Entity;
import object.SuperObject;
import tile.Tile;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		
		this.gp = gp;
		
	}
	
	public void checkCollision(Entity ent) {
		
		Tile tileAbove = gp.tm.tile[gp.tm.mapTileNum[ent.col][ent.row - 1]];
		Tile tileBeneath = gp.tm.tile[gp.tm.mapTileNum[ent.col][ent.row + 1]];
		Tile tileLeft = gp.tm.tile[gp.tm.mapTileNum[ent.col - 1][ent.row]];
		Tile tileRight = gp.tm.tile[gp.tm.mapTileNum[ent.col + 1][ent.row]];
		Tile tileHere = gp.tm.tile[gp.tm.mapTileNum[ent.col][ent.row]];
		
		if(tileHere.name == "goal") {
			gp.level++;
			gp.loadLevel(gp.level);
		}
		
		switch(ent.direction) {
		
		case "up":
			if(tileAbove.collisionPlayer) ent.blocked = true;
			if(tileAbove.removeable) ent.dirt = true;
			break;
			
		case "down":
			if(tileBeneath.collisionPlayer) ent.blocked = true;
			if(tileBeneath.removeable) ent.dirt = true;
			break;
			
		case "left":
			if(tileLeft.collisionPlayer) ent.blocked = true;
			if(tileLeft.removeable) ent.dirt = true;
			break;
			
		case "right":
			if(tileRight.collisionPlayer) ent.blocked = true;
			if(tileRight.removeable) ent.dirt = true;
			break;
		}
		
	}
	
	public void checkCollisionObj(SuperObject obj) {
		
		
		Tile tileBeneath = gp.tm.tile[gp.tm.mapTileNum[obj.col][obj.row + 1]];
		Tile tileLeft = gp.tm.tile[gp.tm.mapTileNum[obj.col - 1][obj.row]];
		Tile tileRight = gp.tm.tile[gp.tm.mapTileNum[obj.col + 1][obj.row]];
		Tile leftDown = gp.tm.tile[gp.tm.mapTileNum[obj.col - 1][obj.row + 1]];
		Tile rightDown = gp.tm.tile[gp.tm.mapTileNum[obj.col + 1][obj.row + 1]];
	
		
//		if(obj.falling) obj.playerBeneath = false;
//		
//		if(tileBeneath.collisionObj) {
//			obj.blocked = true;
//			obj.falling = false;
//		}
//		else if(isPlayer(obj.col, obj.row, 0, 1) && !obj.falling) {
//			obj.blocked = true;
//			obj.falling = false;
//			obj.playerBeneath = true;
//		}
//		else if(isPlayer(obj.col, obj.row, 0, 1) && obj.falling) {
//			obj.playerBeneath = true;
//		}
//		else {
//			if(!obj.playerBeneath)obj.falling = true;
//		}
//		
		
		if(obj.falling) obj.playerBeneath = false;
		
		if(tileBeneath.collisionObj) {
			obj.blocked = true;
			obj.fallingCount = 0;
		}
		else if(isPlayer(obj.col, obj.row, 0, 1) && obj.fallingCount == 0) {
			obj.blocked = true;
			obj.fallingCount = 0;
		}
		
		
		if(!tileBeneath.isObj && tileBeneath.name != "bricks") obj.blockedLeft = true;
		else if(tileLeft.collisionObj) obj.blockedLeft = true;
		else if(leftDown.collisionObj) obj.blockedLeft = true;
		else if(isPlayer(obj.col, obj.row, -1, 0)) obj.blockedLeft = true;
		else if(isPlayer(obj.col, obj.row, -1, 1)) obj.blockedLeft = true;
		
		if(!tileBeneath.isObj && tileBeneath.name != "bricks") obj.blockedRight = true;
		else if(tileRight.collisionObj)obj.blockedRight = true;
		else if(rightDown.collisionObj) obj.blockedRight = true;
		else if(isPlayer(obj.col, obj.row, 1, 0)) obj.blockedRight = true;
		else if(isPlayer(obj.col, obj.row, 1, 1)) obj.blockedRight = true;
			

	}
	
	public boolean isPlayer(int x,int y, int distanceX, int distanceY) {
		
		int playerCol = gp.player.col;
		int playerRow = gp.player.row;
		
		if(playerCol == x + distanceX && playerRow == y + distanceY) return true;
		else return false;
	}
	
//	public void collect(SuperObject obj) {
//		if(isPlayer(obj.col, obj.row, 0, 0)) {
//			if(!obj.falling) obj.collect();
//			else gp.player.die();
//		}
//	}
	
	public void collect(SuperObject obj) {
		if(isPlayer(obj.col, obj.row, 0, 0)) {
			if(obj.fallingCount == 0) obj.collect();
			else gp.player.die();
		}
	}
	
	public void kill(SuperObject obj) {
		if(isPlayer(obj.col, obj.row, 0, 0) && obj.fallingCount > 0) gp.player.die();
	}
}
