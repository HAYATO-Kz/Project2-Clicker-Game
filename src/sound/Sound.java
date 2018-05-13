package sound;


import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Class about sound activity
 * @author Hayato Kawai
 */
public class Sound {
	//for soundFX
	private MediaPlayer SoundFX;
	//for Background soung
	private MediaPlayer BSound;
	
	/**
	 * play soundFX
	 * @param soundFX that want to play
	 * @param volume for soundFX
	 */
	public void playEffect(String soundFX,double volume) {
		soundFX = "/music/"+soundFX+".mp3";
		URL resource = this.getClass().getResource(soundFX);
		    SoundFX = new MediaPlayer(new Media(resource.toString()));
		    SoundFX.setVolume(volume);
		    SoundFX.play();
	}
	
	/**
	 * play background music.
	 * @param bsound is background music that want to play.
	 * @param volume for background music.
	 */
	public void playBack(String bsound,double volume,String type) {
		
		bsound = "/music/"+bsound+type;	
		URL resource = this.getClass().getResource(bsound);
		BSound =new MediaPlayer(new Media(resource.toString()));
		BSound.setVolume(volume);
		 BSound.setOnEndOfMedia(new Runnable() {
		       public void run() {
		         BSound.seek(Duration.ZERO);
		       }
		   });
		  BSound.play();

	}
	
	/**
	 * stop background music.
	 */
	public void stopB() {
			BSound.stop();
	}
}
