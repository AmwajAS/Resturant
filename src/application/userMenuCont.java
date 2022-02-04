package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Alerts.alerts;
import Model.Customer;
import Model.Dish;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class userMenuCont implements Initializable{

	public Restaurant res = Restaurant.getInstance();

	@FXML ImageView wallPaper;
	@FXML Label menuL;
	@FXML 
	private ListView<Dish> listMenu;
	@FXML
	private ListView<Dish> selec;
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
	private Button myOrd;
	@FXML ImageView myOrdImg;
	@FXML Tooltip myOrdHelp;
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
	@FXML 
	private Button addToOrd;
	@FXML
	private Button goToShop;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub


		for(Dish d : Restaurant.getInstance().getDishes().values()) {
			listMenu.getItems().add(d);
		}

		listMenu.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}


	@FXML
	void addToShop(ActionEvent event) {                //this methods adds the selected dishes by the customer to the array list in the main, in order to control and to call the array list in the shopping cart.

		Customer c = MainCont.getUser();
		ObservableList<Dish> dishes;
		ArrayList<Dish> mydish =new ArrayList<>();

		try {
			dishes = listMenu.getSelectionModel().getSelectedItems();
			mydish.addAll(dishes);
			for(Dish i : mydish) {
				Main.shop.add(i);
			}

			selec.getItems().clear();
			for(Dish d : Main.shop) {
				selec.getItems().add(d);
			}
		}catch (NullPointerException e) {
			// TODO: handle exception
			alerts.showAlert(AlertType.WARNING, "Menu", "Please Selected The Dishes You Would like to Order!", ButtonType.CLOSE);
//			System.out.println(c.getShop());
		}

		Restaurant.savaData();
		Restaurant.loadData();
	}



	public void goToShop(ActionEvent event) throws IOException {

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
