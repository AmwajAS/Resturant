package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Alerts.alerts;
import Model.Order;
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

public class removeOrdCont implements Initializable{

	public Restaurant res = Restaurant.getInstance();
	
	@FXML Label ordkL;
	@FXML 
	private ListView<Order> ordList;
	@FXML Text help;
	@FXML 
	private Button removeOrd;
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
		for(Order o : Restaurant.getInstance().getOrders().values()) {
			if(o != null)
				ordList.getItems().add(o);
		}
	}

	@FXML 
	void removeOrder(ActionEvent event) {
		Order or = ordList.getSelectionModel().getSelectedItem();
		if(or == null) {
			alerts.showAlert(AlertType.ERROR, "REMOVE ORDER", "Please Select a Order to remove!", ButtonType.OK);
			return;
		}
		else {
			boolean res = Restaurant.getInstance().removeOrder(or);
			if (res) {
				alerts.showAlert(AlertType.INFORMATION, "REMOVE ORDER", "Removed Successfully", ButtonType.FINISH);
				ordList.getItems().remove(or);
			}
			else {
				alerts.showAlert(AlertType.ERROR, "REMOVE ORDER", "Cannot Remove The Selected Order!", ButtonType.CLOSE);
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
			Parent root = FXMLLoader.load(getClass().getResource("RemoveOrd.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Removing A Order");
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

