package Alert;



import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Class for create alert box
 * @author Hayato Kawai
 *
 */
public class AlertBox  {
	/**
	 * set title and message for alert box then show.
	 * @param title for window title.
	 * @param message that want to show.
	 */
	public static void display(String title,String message) {
		
	try {	Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		
		Label label = new Label();
		label.setText(message);
		Button closeButt = new Button("OK");
		closeButt.setOnAction(e -> window.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label,closeButt);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		scene.setCursor(new ImageCursor(new Image("/picture/Cursor.png")));
		
		window.setScene(scene);
		window.showAndWait();
	} catch(Exception e) {
	}
	}
}
