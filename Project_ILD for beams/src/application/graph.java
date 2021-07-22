package application;
import java.awt.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class graph implements Initializable{

	public Stage stage;
	public Scene scene;
	public Parent root;
	public static double vrr[];
	double X;
	   
    @FXML
    private Button btGoback;
    @FXML
    private LineChart<?, ?> lineChart;
    @FXML
    private Label lbValues;

    @FXML
    void Values(ActionEvent r)throws IOException {
    /*	int k =0;
    	int h=0;
    	SecondController secondcon = new SecondController();
    	String[] Stringg =new String[101*(secondcon.beamlength.length)];
    	
    	for(int i=0;i<secondcon.beamlength.length;i++) {
    		 for (int j = 0; j < 101; j++) {
    			 
        	    	X= X+ 0.01*(secondcon.beamlength[i]);
        	    	Stringg[h] =("Value of x:"+X+"y: "+vrr[k]+"/n");
        	    	k++;
        	    	h++;
        	
			 }
    	}*/
    	
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		System.out.println("Intializeable ");
		SecondController secondcon = new SecondController();
	
		// In Between
		if(secondcon.supportinbetween.length()==11) { 
		
			Inbetween ild1 = new Inbetween();
			double array[][]=ild1.main(secondcon.numberofbeams,secondcon.pointofanalysis,secondcon.beamlength,secondcon.shearormoment,secondcon.endsupports);
			vrr =new double[array[0].length];
			
			for (int i = 0; i < array[0].length; i++){
				vrr[i] = array[0][i];
			}
    
			//Defining the x and y axes
			NumberAxis xAxis = new NumberAxis();
			NumberAxis yAxis = new NumberAxis();
        
			//Setting labels for the axes
			xAxis.setLabel("x");
			yAxis.setLabel("y(x)");
        
	    	lineChart.setTitle("Influence line Diagram");
	    	
	    	XYChart.Series series = new XYChart.Series();
	    	series.setName("Influence line");
    	
	    	int i;
	    	double X=0;
    	
	    	// Value of X
	    	float valueofx[] = new float[secondcon.beamlength.length+1];
	    	valueofx[0]=0;
			for (int r1 = 0; r1 < secondcon.beamlength.length; r1++) {
				for (int m = 0; m < r1+1; m++) {
					valueofx[r1+1]+=SecondController.beamlength[m];
				}System.out.println("Value of x: "+valueofx[r1+1]);
			}
		
			// Get Data to line Chart
			int k =0;
			for(i=0;i<secondcon.beamlength.length;i++) {
				for (int j = 0; j < 101; j++) {
    			 series.getData().add(new XYChart.Data(X,vrr[k]));
    			 X= X+ 0.01*(secondcon.beamlength[i]);
    			 k++;
				}
			}
    	
			lineChart.getData().add(series); 
    	
		}
		
		
		//At Supports
		if(secondcon.supportinbetween.length()==10) { 
			Atsupport atsupport =new Atsupport();
			double array[][]=atsupport.main(secondcon.numberofbeams,secondcon.pointofanalysis,secondcon.beamlength,secondcon.shearormoment,secondcon.endsupports);
			vrr =new double[array[0].length];
	    	for (int i = 0; i < array[0].length; i++) {
	    		vrr[i] = array[0][i];
			}
	    	
	    	//Defining the x and y axes
	        NumberAxis xAxis = new NumberAxis();
	        NumberAxis yAxis = new NumberAxis();
	        
	        //Setting labels for the axes
	        xAxis.setLabel("x");
	        yAxis.setLabel("y(x)");
	        
	    	lineChart.setTitle("Influence line Diagram");
	    	
	    	XYChart.Series series = new XYChart.Series();
	    	series.setName("Influence line");
	    	
	    	int i;
	    	X=0;
	    	
	    	float valueofx[] = new float[secondcon.beamlength.length];
			valueofx[0]=0;
			for (int r1 = 0; r1 < secondcon.beamlength.length; r1++) {
				for (int m = 0; m < r1+1; m++) {
					valueofx[r1]+=SecondController.beamlength[m];
				}System.out.println("Value of x: "+valueofx[r1]);
			}
			
			int k =0;
	    	for(i=0;i<secondcon.beamlength.length;i++) {
	    		 for (int j = 0; j < 101; j++) {
	    				series.getData().add(new XYChart.Data(X,vrr[k]));
	        	    	X= X+ 0.01*(secondcon.beamlength[i]);
	        	    	k++;
	        	
				 }
	    	}
	    	
	    	lineChart.getData().add(series); 
	    	
		}
		
    	
	}


}

