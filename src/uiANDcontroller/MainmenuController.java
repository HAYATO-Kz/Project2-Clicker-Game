package uiANDcontroller;


import Alert.AlertBox;
import Register.Database;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * Controller class for mainmenuUI.fxml
 * @author Hayato Kawai
 */
public class MainmenuController {

	public static FXMLLoader loader;
	Database data = Database.getInstance();
	
	/**
	 * create table and set new window background.
	 */
	public void start(ActionEvent e) {
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		try {
			data.creatDatabase();
			data.creatTable();
			loader = new FXMLLoader(getClass().getResource("RegisterUI.fxml"));
			FlowPane root = (FlowPane) loader.load();
			Scene scene = new Scene(root);
			scene.setCursor(new ImageCursor(new Image("/picture/Cursor.png")));
			Image image = new  Image("/picture/htwBackground.jpg");
			BackgroundSize backgroundSize = new BackgroundSize(100, 100, false, true, true, true);
			BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
			Background bb = new Background(backgroundImage);
			((Region) root).setBackground(bb);
			stage.sizeToScene();
			stage.setTitle("clicker");
			stage.setScene(scene);
			stage.show();
		} catch (Exception ex) {
		}
	}
	
	/**
	 * open howtoplayUI window
	 */
	public void htw(ActionEvent ae) {
		Stage stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
		try {
			data.creatDatabase();
			data.creatTable();
			loader = new FXMLLoader(getClass().getResource("howtoplayUI.fxml"));
			FlowPane root = (FlowPane) loader.load();
			Scene scene = new Scene(root);
			scene.setCursor(new ImageCursor(new Image("/picture/Cursor.png")));
			Image image = new  Image("/picture/htwBackground.jpg");
			BackgroundSize backgroundSize = new BackgroundSize(100, 100, false, true, true, true);
			BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
			Background bb = new Background(backgroundImage);
			((Region) root).setBackground(bb);
			stage.sizeToScene();
			stage.setTitle("clicker");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
		}
	}
}
