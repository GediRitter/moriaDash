package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.ObjectManager;
import object.Rock;
import object.SuperObject;
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
	public TileManager tm = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Sound sound = new Sound();
	public CollisionChecker checker = new CollisionChecker(this);
	Thread gameThread;
	
	
	//ENTITY AND OBJECTS
	Player player = new Player(this, keyH);
	ObjectManager om = new ObjectManager(this);
	public SuperObject [] obj = new SuperObject[30];
	
	//STATS
	public int mithril = 0;
	public int mithrilGoal;
	public int level = 1;
	public int spawnX, spawnY;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		loadLevel(level);
		playMusic(0);
	}
	
	public void loadLevel(int level) {
		
		mithril = 0;
		tm.goalUnachieved();
		switch(level) {
		case 1:
			tm.loadMap("/maps/moriaFirstStage.txt");
			mithrilGoal = 11;
			break;
		case 2:
			tm.loadMap("/maps/moriaSecondStage.txt");
			mithrilGoal = 10;
			break;
		}
		
		om.emptyObj();
		om.loadObj();
		
		player.alive = true;
		player.x = spawnX;
		player.y = spawnY;
		
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
		om.update();
		
		if(mithril == mithrilGoal) tm.goalAchieved();
		
		System.out.println(mithril);
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		tm.draw(g2);
		player.draw(g2);
		om.draw(g2);
		
		g2.dispose();
	}
	
	public void checkObjects() {
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) checker.checkExistence(obj[i]);
		}
		
	}
	
	public int searchObj(SuperObject object) {
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] == object) return i;
		}
		return -1;
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
	
	public void sleep() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
