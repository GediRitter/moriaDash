package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import entity.Rock;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	
	//SCREEN SETTINGS
	public final int originalTileSize = 250; //250x250 Drawings
	public final int scale = 5;
	
	public final int tileSize = originalTileSize / scale;
	public final int maxScreenCol = 24;
	public final int maxScreenRow = 14;
	public final int screenWidth = maxScreenCol * tileSize;
	public final int screenHeight = maxScreenRow * tileSize;
	
	//WORLD SETTINGS
	
	//FPS
	int fps = 13;
	
	
	//SYSTEM
	TileManager tm = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Sound sound = new Sound();
	public CollisionChecker checker = new CollisionChecker(this);
	Thread gameThread;
	
	
	//ENTITY AND OBJECTS
	Player player = new Player(this, keyH);
	Rock rock = new Rock(this);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		playMusic(0);
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
		
		tm.draw(g2);
		player.draw(g2);
		rock.draw(g2);
		
		g2.dispose();
	}
	
	
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	
	public void stopMusic() {
		sound.stop();
	}
	
	public void playSE(int i) {
		sound.setFile(i);
		sound.play();
	}
}
