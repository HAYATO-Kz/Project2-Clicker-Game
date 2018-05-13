package uiANDcontroller;

import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
/**
 * Controller class for howtoplayUI
 * @author Hayato Kawai
 *
 */
public class htwUIcontroller {
	/**
	 * back to mainmenu
	 */
	public void back(ActionEvent ae) {
		try {
		Stage primaryStage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainmenuUI.fxml"));
		BorderPane root = (BorderPane)loader.load();
		Scene scene = new Scene(root);
		scene.setCursor(new ImageCursor(new Image("/picture/Cursor.png")));
		Image image = new  Image("/picture/htwBackground.jpg");
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, false, true, true, true);
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
		Background bb = new Background(backgroundImage);
		((Region) root).setBackground(bb);
		primaryStage.sizeToScene();
		primaryStage.setTitle("clicker");
		primaryStage.setScene(scene);
		primaryStage.show();
	} catch(Exception e) {
	}
	}
}
