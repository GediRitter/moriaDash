package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	Clip clip;
	URL soundURL [] = new URL [30];

	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/A Journey in the Dark 16bit.wav");
//		soundURL[0] = getClass().getResource("/sound/The Bridge of Khazad Dum 16bit.wav");
//		soundURL[0] = getClass().getResource("/sound/Moria Mashup.wav");
		soundURL[1] = getClass().getResource("/sound/MudStep.wav");
	}
	
	public void setFile(int i) {
		
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}catch(Exception e) {
			
		}
		
	}
	
	public void play() {
		
		clip.start();
		
	}
	
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	
	public void stop() {
		
		clip.stop();
		
	}
}
