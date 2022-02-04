package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Alerts.alerts;
import Model.Customer;
import Model.Dish;
import Model.Order;
import Model.Restaurant;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class userShoppingCartCont implements Initializable {

	public Restaurant res = Restaurant.getInstance();

	@FXML ImageView wallPaper;
	@FXML Pane shopPane;
	@FXML
	private ListView<Dish> shopList;

	@FXML
	private Button payB;
	@FXML
	private Button addToOrd;
	@FXML
	private Button deleteFromOrd;
	@FXML ImageView payImg;
	@FXML ImageView logoDeli;
	@FXML Label shopL;
	@FXML HBox barHBOX;
	@FXML
	private Button myHome;
	@FXML ImageView myHomeImg;
	@FXML Tooltip myHomeHelp;
	@FXML
	private Button myProfile;
	@FXML ImageView myProfileImg;
	@FXML Tooltip myProfileHelp;
	@FXML
	private Button myOrd;
	@FXML ImageView myOrdImg;
	@FXML Tooltip myOrdHelp;
	@FXML 
	private Button resMenu;
	@FXML ImageView resMenuImg;
	@FXML Tooltip resMenuHelp;
	@FXML Separator sep2;
	@FXML Separator sep3;
	@FXML Separator sep4;
	@FXML DialogPane thankDialog;
	@FXML Label thank;
	@FXML ImageView thankImg;
	@FXML Label thank2;
	@FXML Label thank3;
	@FXML 
	private Button okk;
	@FXML Label P;
	@FXML Text tottalp;


	private Double tottal =0.0;
	Customer c = MainCont.getUser();



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		for(Dish d : Main.shop) {
			shopList.getItems().addAll(d);
		}

		for(Dish di : Main.shop) {
			tottal += di.calcDishPrice();

		}
		try {
			tottalp.setText(tottal.toString());
		} catch (NullPointerException e) {
			// TODO: handle exception
		}

	}




	@FXML
	void removeFromOrd(ActionEvent event){
		Dish dish = shopList.getSelectionModel().getSelectedItem();                       //this methods removes the selected dish from the order.
		if(dish!= null) {
			boolean res = Main.shop.remove(dish);
			if(res) {
				alerts.showAlert(AlertType.INFORMATION, "SHOPPIMG CART", "The Dish Removed Successfully From You'r Order!", ButtonType.FINISH);			
				shopList.getItems().remove(dish);
			}
			else {
				alerts.showAlert(AlertType.ERROR, "SHOPPING CART", "Cannot Remove The Selected Dish!", ButtonType.CLOSE);
			}
		}
		alerts.showAlert(AlertType.ERROR, "SHOPPING CART", "Please Select A Dish To Remove!", ButtonType.CLOSE);
		Restaurant.savaData();
		Restaurant.loadData();
	}



	@FXML
	void tapOnPay(ActionEvent event) throws IOException {
		//this method added the customer order to the system.
		shopList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		Customer c = MainCont.getUser();
		ObservableList<Dish> dishes;
		ArrayList<Dish> mydish =new ArrayList<>();


		if(!Main.shop.isEmpty()) {
			alerts.showAlert(AlertType.WARNING, "Shopping cart","please Select dishes from the menu;", ButtonType.CLOSE);
			try {
				dishes = shopList.getItems();
				mydish.addAll(dishes);
			}catch (NullPointerException e) {
			// TODO: handle exception
		}
			}
		else {
			// add	

			Order o = new Order(c, Main.shop,null);
			boolean res = Restaurant.getInstance().addOrder(o);

			if(res) {
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("AlertWithImg.fxml"));
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Order placed Successfully!");
				primaryStage.show();

			}else {
				alerts.showAlert(AlertType.ERROR, "Add Order To The System",
						"Failed to add the given Order, The Customer Is Sensitive To The Selected Dish Components, Or The Customer is in The Black list!",
						ButtonType.OK);
			}
		}
		Restaurant.savaData();
		Restaurant.loadData();
		Main.shop.clear();
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

	public void OrderClicked(ActionEvent event) throws IOException {

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("UserOrders.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("My Previos Orders");
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
}
