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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class BlackListCont implements Initializable{
	
	public Restaurant res = Restaurant.getInstance();

	@FXML Label custIdL;
	@FXML Label blackList;
	@FXML Pane blackListPane;
	@FXML ImageView wallPaper;
	@FXML 
	private ListView<Customer> blackListV;
	@FXML 
	private ListView<Customer> custId;
	@FXML 
	private Button clearBlack;
	@FXML 
	private Button addBlack;
	@FXML 
	private HBox hMainBox;
	@FXML 
	private Button back;
	@FXML 
	private Button reFresh;
	@FXML ImageView reFreshImg;
	@FXML ImageView backImg;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		for (Customer c : Restaurant.getInstance().getCustomers().values()) {
			if(c != null)
				custId.getItems().add(c);
		}
		blackListV.getItems().clear();	
	}


	@FXML
	void addCustomerToBlack(ActionEvent event) {
		Customer customer = custId.getSelectionModel().getSelectedItem();
		if( customer == null) {
			alerts.showAlert(AlertType.WARNING, "ADD CUSTOMER To The BLACK LIST",
					"Please Select The Customer You want to add to the Black List.", ButtonType.OK);
		}else {
			// add
			boolean res = Restaurant.getInstance().addCustomerToBlackList(customer);
			if(res) {
				alerts.showAlert(AlertType.INFORMATION, "ADD CUSTOMER To The BLACK LIST",
						"Added Successfully!", ButtonType.OK);
				blackListV.getItems().add(custId.getSelectionModel().getSelectedItem());   			

			}else {
				alerts.showAlert(AlertType.ERROR, "ADD CUSTOMER To The BLACK LIST",
						"Failed to add the given Customer To The Black List!",
						ButtonType.OK);
			}
		}
		Restaurant.savaData();
		Restaurant.loadData();
	}

	@FXML
	void clear(ActionEvent event) {
		clearing();
	}

	private void clearing() {

		custId.getSelectionModel().clearSelection();
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
			Parent root = FXMLLoader.load(getClass().getResource("AddBlackr.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adding A Customer To The Black List");
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
