package uiANDcontroller;
	
import java.net.URL;

import Register.Database;
import javafx.application.Application;
import javafx.stage.Stage;
import sound.Sound;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.fxml.FXMLLoader;
/**
 * Main class
 * @author Hayatp Kawai
 *
 */
public class Main extends Application {
	public  FXMLLoader loader;
	public static Sound sound;
	@Override
	public void start(Stage primaryStage) {
		try {
			sound = new Sound();
			sound.playBack("The Forest",0.3,".mp3");
			URL url = getClass().getResource("MainmenuUI.fxml");
			loader =  new FXMLLoader (url);
			BorderPane root = (BorderPane)loader.load();
			Scene scene = new Scene(root);
			Image image = new  Image("/picture/htwBackground.jpg");
			BackgroundSize backgroundSize = new BackgroundSize(100, 100, false, true, true, true);
			BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
			Background bb = new Background(backgroundImage);
			((Region) root).setBackground(bb);
			root.setCursor(new ImageCursor(new Image("/picture/Cursor.png")));
			primaryStage.sizeToScene();
			primaryStage.setTitle("Clicker-Game");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
