package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import tile.Tile;

public class Player extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	
	public boolean keyPressed;
	public int keyCode;
	public boolean alive;
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		x = gp.tileSize * 2;
		y = gp.tileSize * 2;
		col = x / gp.tileSize;
		row = y / gp.tileSize;
		speed = gp.tileSize;
		
		direction = "idle";
	}
	
	public void getPlayerImage() {
		
		try {
			
			idle = ImageIO.read(getClass().getResourceAsStream("/player/gandalfIdle.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/gandalfUp1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/gandalfUp2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/gandalfDown1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/gandalfDown2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/gandalfLeft1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/gandalfLeft2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/gandalfRight1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/gandalfRight2.png"));
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		blocked = false;
		
		
		if(keyH.keyPressed) {
			keyPressed = true;
			dirt = false;
		}
		
		if(keyPressed && keyH.keyCode != 0) keyCode = keyH.keyCode;
		
		
		if(keyCode == KeyEvent.VK_W) direction = "up";
		if(keyCode == KeyEvent.VK_S) direction = "down";
		if(keyCode == KeyEvent.VK_A) direction = "left";		
		if(keyCode == KeyEvent.VK_D) direction = "right"; 
		if(keyCode == 0) direction = "idle"; 
		
		gp.checker.checkCollision(this);
		updatePosition();
		animation();
		
		
		
//		if(keyH.upPressed) {
//			direction = "up"; 
//			updatePosition();
//			animation();
//		}
//		else if(keyH.downPressed) {
//			direction = "down";
//			updatePosition();
//			animation();
//		}
//		else if(keyH.leftPressed) {
//			direction = "left";
//			updatePosition();
//			animation();
//		}
//		else if(keyH.rightPressed) {
//			direction = "right";
//			updatePosition();
//			animation();
//		}
//		else {
//			direction = "idle";
//		}
		
		col = x / gp.tileSize;
		row = y / gp.tileSize;
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "idle":
			image = idle;
			break;
		case "up":
			if(spriteNum == 1) image = up1;
			if(spriteNum == 2) image = up2;
			break;
		case "down":
			if(spriteNum == 1) image = down1;
			if(spriteNum == 2) image = down2;
			break;
		case "left":
			if(spriteNum == 1) image = left1;
			if(spriteNum == 2) image = left2;
			break;
		case "right":
			if(spriteNum == 1) image = right1;
			if(spriteNum == 2) image = right2;
			break;
		}
		
		if(alive) g2.drawImage(image, x, y ,gp.tileSize, gp.tileSize, null);
	}
	
	public void animation() {
		if(spriteNum == 1) spriteNum = 2;
		else if(spriteNum == 2) spriteNum = 1;
	}
	
	public void updatePosition() {
		
		positionCounter++;
		
		if(positionCounter == 2) {

			if(dirt) {
				removeDirt();
				blocked = false;
			}
			else if(direction != "idle") gp.playSE(8);
			
			switch(direction) {
			case "idle":
				break;
			case "up":
				if(!blocked) y -= speed;
				break;
			case "down":
				if(!blocked) y += speed;
				break;
			case "left":
				if(!blocked) x -= speed;
				break;
			case "right":
				if(!blocked) x += speed;
				break;
			}
			
			keyPressed = false;
			keyCode = 0;
			
			positionCounter = 0;
		}
	}
	
	public void removeDirt() {
		
		switch(direction) {
		case "idle":
			break;
		case "up":
			gp.tm.mapTileNum[col][row - 1] = 0;
			break;
		case "down":
			gp.tm.mapTileNum[col][row + 1] = 0;
			break;
		case "left":
			gp.tm.mapTileNum[col - 1][row] = 0;
			break;
		case "right":
			gp.tm.mapTileNum[col + 1][row] = 0;
			break;
		}
		
		gp.playSE(1);
		dirt = false;

	}
	
	public void die() {
		
		gp.playSE(3);
		gp.playSE(5);
		gp.playSE(7);


		
		alive = false;
		explode();
		gp.repaint();
		gp.sleep();
		gp.loadLevel(gp.level);
	}
	
	public void explode() {
		
		Tile tileAbove = gp.tm.tile[gp.tm.mapTileNum[col][row - 1]];
		Tile tileBeneath = gp.tm.tile[gp.tm.mapTileNum[col][row + 1]];
		Tile tileLeft = gp.tm.tile[gp.tm.mapTileNum[col - 1][row]];
		Tile tileRight = gp.tm.tile[gp.tm.mapTileNum[col + 1][row]];
		Tile leftDown = gp.tm.tile[gp.tm.mapTileNum[col - 1][row - 1]];
		Tile rightDown = gp.tm.tile[gp.tm.mapTileNum[col + 1][row - 1]];
		Tile leftUp = gp.tm.tile[gp.tm.mapTileNum[col - 1][row + 1]];
		Tile rightUp = gp.tm.tile[gp.tm.mapTileNum[col + 1][row + 1]];
		
		gp.playSE(4);
		gp.playSE(6);

		
		if(tileAbove.name != "wall") gp.tm.mapTileNum[col][row - 1] = 0;
		if(tileBeneath.name != "wall") gp.tm.mapTileNum[col][row + 1] = 0;
		if(tileLeft.name != "wall") gp.tm.mapTileNum[col - 1][row] = 0;
		if(tileRight.name != "wall") gp.tm.mapTileNum[col + 1][row] = 0;
		if(leftDown.name != "wall") gp.tm.mapTileNum[col - 1][row + 1] = 0;
		if(rightDown.name != "wall") gp.tm.mapTileNum[col + 1][row + 1] = 0;
		if(leftUp.name != "wall") gp.tm.mapTileNum[col - 1][row - 1] = 0;
		if(rightUp.name != "wall") gp.tm.mapTileNum[col + 1][row - 1] = 0;
		
		gp.checkObjects();
		
	}

}
