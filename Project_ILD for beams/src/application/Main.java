package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		
			Parent root = FXMLLoader.load(getClass().getResource("First.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Image im= new Image(getClass().getResource("ild.png").toURI().toString());
			primaryStage.getIcons().add(im);
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Influence Line Diagram");
		//	primaryStage.setResizable(false);
			primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
