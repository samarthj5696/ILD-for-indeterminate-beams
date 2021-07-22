package application;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import com.sun.javafx.logging.Logger;
import com.sun.javafx.logging.PlatformLogger.Level;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FirstController implements Initializable {

	public Stage stage;
	public Scene scene;
	public Parent root;
	
	public String EndSupports; // End Support
	public String momentshear; // Moment or Shear
	public int numberofbeam; // Number of beam (N)
    public int pointofinterest; // Point of analysis (K)
    public String supportinbetween; // Support or In between

    @FXML
    public Button Next;
    @FXML
    public TextField tfNumber;
    @FXML
    public ChoiceBox<String> cbChoice;
    @FXML
    public TextField tfPointofinterest;   
    @FXML
    private ChoiceBox<String> cbEndsupport;   
    @FXML
    private ChoiceBox<String> cbSupportBetween;
    
    // Contains of choice box
    public String[] choices = {"Pinned End Support","Left End Fixed Support","Both the End Fixed Support" };
    public String[] shearormoment = {"Shear","Moment"};
    public String[] supportbetween  = {"at support","in between "};
    
    //Choice Box Initializing
    public void initialize(URL url, ResourceBundle rb) {
    cbChoice.getItems().addAll(choices); 
    cbEndsupport.getItems().addAll(shearormoment); 
    cbSupportBetween.getItems().addAll(supportbetween);
    }
    
    //Button Next
    @FXML
    void switchtoSecond(ActionEvent r)throws IOException {

    	//Error if no input in TextField
    	if(tfNumber.getText() == null ||tfNumber.getText().trim().isEmpty()){
            Alert fail= new Alert(AlertType.INFORMATION);
            fail.setHeaderText("Error");
            fail.setContentText("you havent typed something");
            fail.showAndWait();
            return;
        } 
    	
    	if(tfPointofinterest.getText() == null ||tfPointofinterest.getText().trim().isEmpty()){
            Alert fail= new Alert(AlertType.INFORMATION);
            fail.setHeaderText("Error");
            fail.setContentText("you havent typed something");
            fail.showAndWait();
            return;
        } 
    	
    	//Error TextField  is not a integer
    	double number = 0;
    	try {
    	    number = Double.parseDouble(tfNumber.getText());
    	} catch (NumberFormatException ex) {
    		Alert fail= new Alert(AlertType.INFORMATION);
            fail.setHeaderText("Error");
            fail.setContentText("Number of Beam is integer only");
            fail.showAndWait();
            return;
    	}
    	
    	try {
    	    number = Double.parseDouble(tfPointofinterest.getText());
    	} catch (NumberFormatException ex) {
    		Alert fail= new Alert(AlertType.INFORMATION);
            fail.setHeaderText("Error");
            fail.setContentText("Point of Interest is integer only");
            fail.showAndWait();
            return;
    	}

    	//Error if no input in choice box
    	if(cbChoice.getValue() == null ||cbChoice.getValue().trim().isEmpty()){
            Alert fail= new Alert(AlertType.INFORMATION);
            fail.setHeaderText("Error");
            fail.setContentText("you havent Selected End Support Type ");
            fail.showAndWait();
            return;
        } 
    	
    	//Error if no input in choice box End Support Type 
    	if(cbChoice.getValue() == null ||cbChoice.getValue().trim().isEmpty()){
            Alert fail= new Alert(AlertType.INFORMATION);
            fail.setHeaderText("Error");
            fail.setContentText("you havent Selected End Support Type ");
            fail.showAndWait();
            return;
        } 
    	
    	//Error if no input in choice box End Support Type 
    	if(cbEndsupport.getValue() == null ||cbEndsupport.getValue().trim().isEmpty()){
            Alert fail= new Alert(AlertType.INFORMATION);
            fail.setHeaderText("Error");
            fail.setContentText("you havent Selected Moment Shear Type ");
            fail.showAndWait();
            return;
        } 
    	
    	//Error if no input in choice box End Support Type 
    	if(cbSupportBetween.getValue() == null ||cbSupportBetween.getValue().trim().isEmpty()){
            Alert fail= new Alert(AlertType.INFORMATION);
            fail.setHeaderText("Error");
            fail.setContentText("you havent Selected support or in between Type ");
            fail.showAndWait();
            return;
        } 
    	
    	
    	//Storing values
    	EndSupports = cbChoice.getValue();
    	momentshear = cbEndsupport.getValue();
    	supportinbetween = cbSupportBetween.getValue();
    	numberofbeam = Integer.parseInt(tfNumber.getText());
    	pointofinterest = Integer.parseInt(tfPointofinterest.getText());
    	
    	System.out.println(EndSupports);
    	System.out.println(momentshear);
    	System.out.println(numberofbeam);
    	System.out.println(pointofinterest);
    	System.out.println(supportinbetween);
    	
    	//Loading Second Scene
    	Parent root = FXMLLoader.load(getClass().getResource("Second.fxml"));
		stage = (Stage)((Node)r.getSource()).getScene().getWindow();
		
			// Sending Values to SecondController Class
			SecondController control = new SecondController();
			control.store(numberofbeam,pointofinterest,EndSupports,momentshear,supportinbetween);
    	
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();	
		System.out.println("Goes to Second ");
    
    }
    
    
    @FXML
    void Close(ActionEvent r)throws IOException {
    	System.exit(0);
    }
  
}


