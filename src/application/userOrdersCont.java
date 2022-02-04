package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;

import Alerts.alerts;
import Model.Customer;
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
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class userOrdersCont implements Initializable {
	
	public Restaurant res = Restaurant.getInstance();
	
	@FXML ImageView wallPaper;
	@FXML Label prevOrd;
	@FXML Pane prevOrdPane;
	@FXML
	private ListView<Order> prevOrdList;
	@FXML
	private Button delete;
	@FXML 
	private Button add;
	@FXML Text deleteHelp;
	@FXML Text addHelp;
	@FXML HBox barHBOX;
	@FXML
	private Button myHome;
	@FXML ImageView myHomeImg;
	@FXML Tooltip myHomeHelp;
	@FXML
	private Button myShoppingCart;
	@FXML ImageView myShoppingCartImg;
	@FXML Tooltip myShoppingCartHelp;
	@FXML
	private Button resMenu;
	@FXML ImageView resMenuImg;
	@FXML Tooltip resMenuHelp;
	@FXML
	private Button myProfile;
	@FXML ImageView myProfileImg;
	@FXML Tooltip myProfileHelp;
	@FXML Separator sep2;
	@FXML Separator sep3;
	@FXML Separator sep4;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {          //shows the user's previous orders.
		// TODO Auto-generated method stub

		Customer cust = MainCont.getUser();

		boolean res = Restaurant.getInstance().getOrderByCustomer().containsKey(cust);
		if(res) {
			for(TreeSet<Order> o : Restaurant.getInstance().getOrderByCustomer().values()) { 
				prevOrdList.getItems().addAll(o);
			}
		}
		else {
			alerts.showAlert(AlertType.INFORMATION, "Preveious Orders", "There's No Orders Yet!, Lets Make you'r First Order !!", ButtonType.OK);
		}
	}

	@FXML
	void removeOrd(ActionEvent event) {                           // the user can remove the selected order from the previous list.
		Order o = prevOrdList.getSelectionModel().getSelectedItem();
		if( o != null) {
			boolean res =Restaurant.getInstance().removeOrder(o);
			if(res) {
				alerts.showAlert(AlertType.INFORMATION, "PREVIOUS ORDERS", "The Selected Order has been Removed Successfully!", ButtonType.FINISH);
				prevOrdList.getItems().remove(o);
			}
			else {
				alerts.showAlert(AlertType.INFORMATION, "PREVIOUS ORDERS", "Cannot Remove The Selected Order!", ButtonType.FINISH);
			}
		}
		alerts.showAlert(AlertType.INFORMATION, "PREVIOUS ORDERS", "Please Select Order To Remove!", ButtonType.FINISH);
		Restaurant.savaData();
		Restaurant.loadData();
	}


	public void profileClicked(ActionEvent event) throws IOException {

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("UserProfile.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("My Account Profile");
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent t) {
		        Platform.exit();
		        System.exit(0);
		        
		    }
		});
	}


	public void menuClicked(ActionEvent event) throws IOException {

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("UserMenu.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("AMWAJ'S KITCHEN Menu");
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent t) {
		        Platform.exit();
		        System.exit(0);
		        
		    }
		});
	}


	public void homeClicked(ActionEvent event) throws IOException {

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("UserMain.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Welcome to AMWAJ'S KITCHEN");
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent t) {
		        Platform.exit();
		        System.exit(0);
		        
		    }
		});
	}

	public void ShopClicked(ActionEvent event) throws IOException {

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("UserShoppingCart.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("My Shopping Cart");
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
