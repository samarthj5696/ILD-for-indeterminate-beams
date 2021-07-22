package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SecondController implements Initializable {
	public Stage stage;
	public Scene scene;
	public Parent root;
	
    @FXML
    public Button btCaluclate;
    
    @FXML
    public TextField tfBeamleangth;

    @FXML
    private Button btAdd;
    
    @FXML
    public static LineChart<?, ?> lineChart;
    
    @FXML
    private Label lbNaming;
    
    @FXML
    private Label lbLabel;
    
    public static int numberofbeams;
    public static int pointofanalysis;
    public static String endsupports;
    public static String shearormoment; 
    public static double beamlength[]; 
    public static int i;
    public static String supportinbetween;
 
    
    public void store(int n, int k, String EndSupport,String shearmoment,String inbetweensupport) {
    	
    	//Storing Values
    	numberofbeams = n;
    	pointofanalysis=k;
    	endsupports=EndSupport;
    	shearormoment=shearmoment;
    	supportinbetween=inbetweensupport;
    	
    	//Printing n and k
    	System.out.println("numberofbeams "+ numberofbeams);
    	System.out.println("pointofanalysis "+ pointofanalysis);  
    	
    	if(supportinbetween.length()==11) {
    	beamlength = new double[numberofbeams+1]; // Defining variable - Lengths of beams
    	}
    	if(supportinbetween.length()==10) {
        	beamlength = new double[numberofbeams]; // Defining variable - Lengths of beams
        	}
    }
    
    // Add Button Method
    @FXML
    void addbeamlength(ActionEvent r)throws IOException {
    	
    	//If Text Field is empty
    	if(tfBeamleangth.getText() == null ||tfBeamleangth.getText().trim().isEmpty()){
            Alert fail= new Alert(AlertType.INFORMATION);
            fail.setHeaderText("Error");
            fail.setContentText("you havent typed something");
            fail.showAndWait();
            return;
        } 
    	
    	
    	if(supportinbetween.length()==11) { // in Between
    		
    		if(i<numberofbeams) {lbLabel.setText(""+(i+2));} // top number label
    		else{lbLabel.setText("");} 
    	
    		beamlength[i]= Float.parseFloat(tfBeamleangth.getText()); // Input as beam length
    		System.out.println("BeamLength "+ (i+1) + " : " + beamlength[i]);
    		
    		btCaluclate.setVisible(false);
    		if(i<numberofbeams+1) {i++;}
    			if(i>=numberofbeams+1) {
    				btAdd.setVisible(false);
    				btAdd.setDisable(true);
    				btCaluclate.setVisible(true);
    				lbLabel.setVisible(false);
    				tfBeamleangth.setVisible(false);
    			}tfBeamleangth.setText("");	 // clearing text field
    	}
    	
    	
    	
    	if(supportinbetween.length()==10) { // at Support
    		
    		if(i<numberofbeams) {lbLabel.setText(""+(i+2));} // top label number
    		else{lbLabel.setText("");}
    	
    		beamlength[i]= Float.parseFloat(tfBeamleangth.getText()); // Input as beam length
    		System.out.println("BeamLength "+ (i+1) + " : " + beamlength[i]);
	    		
	    	btCaluclate.setVisible(false);
	    	if(i<numberofbeams) {i++;}
	    	if(i>=numberofbeams) {
    			btAdd.setVisible(false);
    			btAdd.setDisable(true);
    			btCaluclate.setVisible(true);
    			lbLabel.setVisible(false);
    			tfBeamleangth.setVisible(false);
    		}	tfBeamleangth.setText("");	
     	}
    }

    
    // Calculate Button
    @FXML
    void switchtograph(ActionEvent r)throws IOException {
    
        	Parent root = FXMLLoader.load(getClass().getResource("graph.fxml")); // Scene change to graph
    		stage = (Stage)((Node)r.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();	
    		
	//	System.out.println("Goes to Main ");
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btCaluclate.setVisible(false);
		
	}


}