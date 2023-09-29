package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		x = gp.tileSize;
		y = gp.tileSize;
		speed = gp.tileSize;
		
		direction = "idle";
	}
	
	public void getPlayerImage() {
		
		try {
			
			idle = ImageIO.read(getClass().getResourceAsStream("/player/gandalfIdle.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/gandalfIdle.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/gandalfIdle.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/gandalfIdle.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/gandalfIdle.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/gandalfLeft1Temp.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/gandalfLeft2Temp.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/gandalfRight1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/gandalfRight2.png"));
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if(keyH.upPressed) {
			y -= speed;
			direction = "up";
			animation();
		}
		else if(keyH.downPressed) {
			y += speed;
			direction = "down";
			animation();
		}
		else if(keyH.leftPressed) {
			x -= speed;
			direction = "left";
			animation();
		}
		else if(keyH.rightPressed) {
			x += speed;
			direction = "right";
			animation();
		}
		else {
			direction = "idle";
		}
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
		
		g2.drawImage(image, x, y ,gp.tileSize, gp.tileSize, null);
	}
	
	public void animation() {
		spriteCounter++;
		if(spriteCounter > 10) {
			if(spriteNum == 1) spriteNum = 2;
			else if(spriteNum == 2) spriteNum = 1;
			spriteCounter = 0;
		}
	}

}