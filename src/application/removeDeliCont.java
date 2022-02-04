package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Alerts.alerts;
import Model.Delivery;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class removeDeliCont implements Initializable{
	
	public Restaurant res = Restaurant.getInstance();
	
	@FXML Label delL;
	@FXML Label delIdL;
	@FXML 
	private ListView<Delivery> deliList;
	@FXML Text help;
	@FXML 
	private Button removeDel;
	@FXML ImageView wallPaper;
	@FXML ScrollPane deliScroll;
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
		deliList.getItems().clear();
		for(Delivery di : Restaurant.getInstance().getDeliveries().values()) {
			if(di != null)
				deliList.getItems().add(di);
		}
	}

	@FXML 
	void removeDelivery(ActionEvent event) {
		Delivery del = deliList.getSelectionModel().getSelectedItem();
		if(del == null) {
			alerts.showAlert(AlertType.ERROR, "REMOVE DELIVERY", "Please Select a Delivery to remove!", ButtonType.OK);
			return;
		}
		else {
			if (Restaurant.getInstance().removeDelivery(del)) {
				alerts.showAlert(AlertType.INFORMATION, "REMOVE DELIVERY", "Removed Successfully", ButtonType.FINISH);
				deliList.getItems().remove(del);

			}
			else {
				alerts.showAlert(AlertType.ERROR, "REMOVE DELIVERY", "Cannot Remove The Selected Delivery!", ButtonType.CLOSE);
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
			Parent root = FXMLLoader.load(getClass().getResource("RemoveDeli.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Removing A Delivery");
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

