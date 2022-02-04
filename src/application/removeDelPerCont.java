package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Alerts.alerts;
import Model.DeliveryPerson;
import Model.Restaurant;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class removeDelPerCont implements Initializable {

public Restaurant res = Restaurant.getInstance();

	@FXML Label delPerL;
	@FXML 
	private ListView<DeliveryPerson> delPerList;
	@FXML Text help;
	@FXML 
	private Button removeDelPer;
	@FXML ImageView wallPaper;
	@FXML 
	private HBox hMainBox;
	@FXML 
	private Button back;
	@FXML ImageView backImg;
	@FXML 
	private Button reFresh;
	@FXML ImageView reFreshImg;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		delPerList.getItems().clear();
		for(DeliveryPerson d : Restaurant.getInstance().getDeliveryPersons().values()) {
			if(d != null)
				delPerList.getItems().add(d);
		}
	}
	@FXML 
	void removeDeliveryPerson(ActionEvent event) {
		DeliveryPerson deliveryPerson = delPerList.getSelectionModel().getSelectedItem();
		if(deliveryPerson == null) {
			alerts.showAlert(AlertType.ERROR, "REMOVE DELIVERY PERSON", "Please Select a delivery person to remove!", ButtonType.OK);
			return;
		}
		else {
			if (Restaurant.getInstance().removeDeliveryPerson(deliveryPerson)) {
				alerts.showAlert(AlertType.INFORMATION, "REMOVE DELIVERY PERSON", "Removed Successfully", ButtonType.FINISH);
				delPerList.getItems().remove(deliveryPerson);

			}
			else {
				alerts.showAlert(AlertType.ERROR, "REMOVE DELIVERY PERSON", "Cannot Remove The Selected delivery person!", ButtonType.CLOSE);
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
			Parent root = FXMLLoader.load(getClass().getResource("RemoveDelPer.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Removing A Delivery Person");
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

