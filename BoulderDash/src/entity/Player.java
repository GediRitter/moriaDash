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
		x = gp.tileSize * 2;
		y = gp.tileSize * 2;
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
		
		if(keyH.upPressed) {
			direction = "up"; 
			updatePosition();
			animation();
		}
		else if(keyH.downPressed) {
			direction = "down";
			updatePosition();
			animation();
		}
		else if(keyH.leftPressed) {
			direction = "left";
			updatePosition();
			animation();
		}
		else if(keyH.rightPressed) {
			direction = "right";
			updatePosition();
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
		if(spriteNum == 1) spriteNum = 2;
		else if(spriteNum == 2) spriteNum = 1;
	}
	
	public void updatePosition() {
		
		positionCounter++;
		
		if(positionCounter == 2) {
			switch(direction) {
			case "idle":
				break;
			case "up":
				y -= speed;
				break;
			case "down":
				y += speed;
				break;
			case "left":
				x -= speed;
				break;
			case "right":
				x += speed;
				break;
			}
			positionCounter = 0;
		}
	}

}
