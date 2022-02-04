package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Alerts.alerts;

import Model.Customer;
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

public class removeCustCont implements Initializable {
	
	public Restaurant res = Restaurant.getInstance();

	@FXML Label custL;
	@FXML Label custIdL;
	@FXML 
	private ListView<Customer> custList;
	@FXML Text help;
	@FXML 
	private Button removeust;
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
		custList.getItems().clear();
		for(Customer c : Restaurant.getInstance().getCustomers().values()) {
			if(c != null)
				custList.getItems().add(c);
		}
	}

	@FXML 
	void removeCustomer(ActionEvent event) {
		Customer cust = custList.getSelectionModel().getSelectedItem();
		if(cust == null) {
			alerts.showAlert(AlertType.ERROR, "REMOVE CUSTOMER", "Please Select a customer to remove!", ButtonType.OK);
			return;
		}
		else {
			if (Restaurant.getInstance().removeCustomer(cust)) {
				alerts.showAlert(AlertType.INFORMATION, "REMOVE CUSTOMER", "Removed Successfully", ButtonType.FINISH);
				custList.getItems().remove(cust);

			}
			else {
				alerts.showAlert(AlertType.ERROR, "REMOVE CUSTOMER", "Cannot Remove The Selected Customer!", ButtonType.CLOSE);
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
			Parent root = FXMLLoader.load(getClass().getResource("RemoveCust.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Removing A Customer");
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

