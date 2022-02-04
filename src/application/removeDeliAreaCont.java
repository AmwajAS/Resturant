package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Alerts.alerts;
import Model.DeliveryArea;
import Model.Restaurant;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class removeDeliAreaCont implements Initializable{
	
	public Restaurant res = Restaurant.getInstance();

	@FXML Label delAreaL;
	@FXML 
	private ListView<DeliveryArea> oldArea;
	@FXML Text help;
	@FXML 
	private Button removeDelArea;
	@FXML ImageView wallPaper;
	@FXML 
	private HBox hMainBox;
	@FXML 
	private Button back;
	@FXML ImageView backImg;
	@FXML 
	private Button reFresh;
	@FXML ImageView reFreshImg;
	@FXML 
	private ListView<DeliveryArea> newArea;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
			    	newArea.setVisible(false);
	    	oldArea.getItems().clear();
			for (DeliveryArea da : Restaurant.getInstance().getAreas().values()) {
				if(da !=null)
					oldArea.getItems().add(da);
			}
			oldArea.getSelectionModel().selectedItemProperty().addListener(
					new ChangeListener<DeliveryArea>() {
				@Override
				public void changed(ObservableValue<? extends DeliveryArea> arg0, DeliveryArea arg1,
						DeliveryArea arg2) {
					// TODO Auto-generated method stub
					DeliveryArea d = oldArea.getSelectionModel().getSelectedItem();
					newArea.setVisible(true);
					newArea.getItems().clear();
					for (DeliveryArea dr : Restaurant.getInstance().getAreas().values()) {
						if(dr != null && !dr.equals(d))
							newArea.getItems().add(d);
					}
					
				}
			});
	}

	    
	    @FXML
	    void remove(ActionEvent event) {
	    	DeliveryArea d1 = oldArea.getSelectionModel().getSelectedItem();
	    	DeliveryArea d2 = newArea.getSelectionModel().getSelectedItem();
	    	if(d1 == null) {
	    		alerts.showAlert(AlertType.WARNING, "REMOVE DELIVERY AREA", 
	    				"The Old Area is Not Selected Yet.",
	    				ButtonType.FINISH);
	    		return;
	    	}else if(d2 == null) {
	    		alerts.showAlert(AlertType.WARNING, "REMOVE DELIVERY AREA", 
	    				"The New Area is Not Selected Yet.",
	    				ButtonType.FINISH);
	    		return;
	    	}else {
	    		if(Restaurant.getInstance().removeDeliveryArea(d1, d2)) {
	    			alerts.showAlert(AlertType.INFORMATION, "REMOVE DELIVERY AREA", 
	        				"Removed Successfully.",
	        				ButtonType.FINISH);

	    		}else {
	    			alerts.showAlert(AlertType.ERROR, "REMOVE DELIVERY AREA", 
	        				"Cannot Remove The Selected Area.",
	        				ButtonType.FINISH);
	    		}
	    	}
			Restaurant.savaData();
			Restaurant.loadData();
	    	
	    }
	

	public void actionOnBack(ActionEvent event) throws IOException {
		back.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {

		});
		{
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Resturant Settings");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			        
			    }
			});
		}
	}
	
	public void actionOnRefresh(ActionEvent event) throws IOException {
		reFresh.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {

		});
		{
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("RemoveDeliArea.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Removing A Delivery Area");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			        
			    }
			});
		}
	}

}

