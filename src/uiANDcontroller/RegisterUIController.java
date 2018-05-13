package uiANDcontroller;

import Alert.AlertBox;
import Register.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import monsterANDmoney.Money;
import monsterANDmoney.Monster;
import sound.Sound;
import unitANDhero.Hero;
/**
 * Controller class for RegisterUI.
 * @author Hayato Kawai
 *
 */
public class RegisterUIController {
	@FXML
	TextField idText;
	@FXML
	PasswordField passText;
	@FXML
	Button loginButt;
	@FXML
	Button regisButt;
	@FXML
	Button change;
	
	private Database db = Database.getInstance();
	public static FXMLLoader loader;
	private GameUIController con ;
	/**
	 * check username and password from user's input.if user is member,open game.
	 * if user is not a member, require register.
	 */
	public void login (ActionEvent ae) {
		Stage stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
		String id = idText.getText().trim();
		String password = passText.getText().trim();
		if(id.isEmpty()||password.isEmpty()) {
			AlertBox.display("error", "Please input id and password");
			return;
		}
		if(!db.getMember(id,password)) {
			 AlertBox.display("error", "Please register");
		}
		else {
			db.getMember(id,password);
			AlertBox.display("Welcome", "Hi "+db.id);
			openGame(stage);
		}
		clear();
	}
	
	/**
	 * register new member
	 */
	public void regis(ActionEvent ae) {
		String id = idText.getText().trim();
		String password = passText.getText().trim();
		if(id.isEmpty()||password.isEmpty()) {
			AlertBox.display("error", "Please input id and password");
			return;
		}
		if (db.getMember(id, password)) {
			AlertBox.display("error", "already member");
			return;
		}
		db.addNewMember(id, password);
		AlertBox.display("Welcome", "May the force be with your finger");
	}
	
	/**
	 * clear all text
	 */
	public void clear() {
		idText.clear();
		passText.clear();
	}
	
	/**
	 * back to mainmenu
	 * @param ae
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
		primaryStage.setTitle("Clicker-Game");
		primaryStage.setScene(scene);
		primaryStage.show();
	} catch(Exception e) {
	}
	}
	
	/**
	 * set background, setObserver and open game window 
	 */
	public void openGame(Stage stage) {
		stage.close();
		stage = new Stage();
		try {
			 Money money = new Money();
			 Sound sound = Main.sound;
			 Monster monster = new Monster(money);
			 Hero hero = new Hero(money);
			loader = new FXMLLoader(getClass().getResource("GameUI.fxml"));
			FlowPane root = (FlowPane) loader.load();
			Scene scene = new Scene(root);
			con = loader.getController();
			con.addClass(money, monster,hero,sound);
			
			Image image = new Image("/picture/Mind Brand.png");
			BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
			BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
			Background bb = new Background(backgroundImage);
			((Region) root).setBackground(bb);
			
			stage.setOnCloseRequest(event -> con.close());
			stage.setResizable(false);
			stage.sizeToScene();
			stage.setTitle("clicker");
			stage.setScene(scene);
			stage.show();
			hero.addObserver(con);
			money.addObserver(con);
			monster.addObserver(con);
		} catch (Exception ex) {
		}
	}
	
}
