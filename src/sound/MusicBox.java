package sound;

import javafx.scene.paint.Color;

/**
 * Enum class for music
 * @author Hayato Kawai
 *
 */
public enum MusicBox {
	CreativeMind("Creativeminds",".mp3"),Dubstep("Dubstep",".mp3"),BlueSky("Blue Sky",".mp3"),
	DancingAlone("Dancing Alone",".mp3"),Flight("Flight",".mp3"),Goodbye("Goodbye",".mp3"),Horizon("Horizon",".mp3"),
	Implosion("Implosion",".mp3"),NewAge("New Age",".mp3"),Opening("Opening",".mp3"),Scifi("Scifi",".mp3"),
	Superior("Superior",".mp3"),TheForest("The Forest",".mp3");

	public String name;
	public String typeM;

	
	private MusicBox (String name, String typeM) {
			this.name= name;
			this.typeM = typeM;
	}
	
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return type for music.
	 */
	public String getTypeM() {
		return this.typeM;
	}
}
