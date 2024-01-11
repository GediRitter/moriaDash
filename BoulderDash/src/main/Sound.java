package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	Clip clip;
	URL soundURL [] = new URL [30];

	public Sound() {
		
//		soundURL[0] = getClass().getResource("/sound/A Journey in the Dark 16bit.wav");
		soundURL[0] = getClass().getResource("/sound/Moria Mashup.wav");
		soundURL[1] = getClass().getResource("/sound/MudStep.wav");
		soundURL[2] = getClass().getResource("/sound/The Bridge of Khazad Dum 16bit.wav");
		soundURL[3] = getClass().getResource("/sound/Blood splatter.wav");
		soundURL[4] = getClass().getResource("/sound/EP3_1_EXPLOSIONM.wav");
		soundURL[5] = getClass().getResource("/sound/Punch.wav");
		soundURL[6] = getClass().getResource("/sound/Thanos snap.wav");
		soundURL[7] = getClass().getResource("/sound/WilhelmScream.wav");
		soundURL[8] = getClass().getResource("/sound/Footstep SFX Walking.wav");
		soundURL[9] = getClass().getResource("/sound/EXP_ATEROID_03.wav");
		soundURL[10] = getClass().getResource("/sound/CHAR_ICON_APP.wav");
		soundURL[11] = getClass().getResource("/sound/CHAR_ICON_SLIDE.wav");

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
