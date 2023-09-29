package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable{
	
	
	//SCREEN SETTINGS
	public final int originalTileSize = 250; //250x250 Drawings
	public final int scale = 5;
	
	public final int tileSize = originalTileSize / scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 9;
	public final int screenWidth = maxScreenCol * tileSize;
	public final int screenHeight = maxScreenRow * tileSize;
	
	//WORLD SETTINGS
	
	int x = tileSize;
	int y = tileSize;
	int speed = tileSize;
	
	
	//FPS
	int fps = 7;
	
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	Player player = new Player(this, keyH);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000 / fps; //0.016666 seconds
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
		
			//1. UPDATE: update information such as character positions

				update();
			
			
		
			//2. DRAW: draw the screen with the updated information
			repaint();
			
			try {
				
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;
				if(remainingTime < 0) remainingTime = 0;
				
				Thread.sleep((long)remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
				
	}
	
	public void update() {
		
		player.update();
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		player.draw(g2);
		
		g2.dispose();
	}
	
}
