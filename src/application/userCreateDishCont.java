package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Alerts.alerts;
import Model.Component;
import Model.Customer;
import Model.Dish;
import Model.Restaurant;
import Utils.DishType;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class userCreateDishCont implements Initializable {

	public Restaurant res = Restaurant.getInstance();
	@FXML 
	private 
	ListView<Component> allComp;
	@FXML 
	private ListView<Component> orgComp;
	@FXML
	private ComboBox<Dish> allDishs;
	@FXML
	private Button add;
	@FXML
	private Button remove;
	@FXML
	private Button addShop;
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
	private Button load;




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		if(Restaurant.getInstance().getDishes().isEmpty()){

			alerts.showAlert(AlertType.WARNING, "CREATE MY DISH", "Oops! There's No Dishes In The Resturant Yet!", ButtonType.CLOSE);
		}else {
			allDishs.getItems().clear();
			for(Dish d :Restaurant.getInstance().getDishes().values()) 
				if(d!= null) {
				allDishs.getItems().add(d);	
				}
				// TODO: handle exception
				alerts.showAlert(AlertType.WARNING, "CREATE MY DISH", "Oops! There's No Dishes In The Resturant Yet!", ButtonType.CLOSE);
			
			
		}

		for( Component newComp : Restaurant.getInstance().getComponenets().values()) {
			if(newComp == null) {
				alerts.showAlert(AlertType.WARNING, "CREATE MY DISH", "Oops! There's No Components In The Resturant Yet!", ButtonType.CLOSE);
			}
			else {
				allComp.getItems().add(newComp);
			}
		}
	}
	
	@FXML void loadDish(ActionEvent event) {
		Dish di	= allDishs.getSelectionModel().getSelectedItem();
		
		for( Component c : di.getComponenets()) {
			if( c == null) {
				alerts.showAlert(AlertType.WARNING, "CREATE MY DISH", "Oops! There's No Dishes In The Resturant Yet!", ButtonType.CLOSE);
			}else {
				orgComp.getItems().add(c);				
			}
		}
	}
	
	
	

	@FXML
	void addNewComp(ActionEvent event) {                            //this methods add a new component from all the component list in the system.
		Dish di	= allDishs.getSelectionModel().getSelectedItem();
		Component n = allComp.getSelectionModel().getSelectedItem();
		if(n == null) {
			alerts.showAlert(AlertType.WARNING, "CREATE MY DISH", "Please Select The component You Would Like To Add To You'r Dish!", ButtonType.CLOSE);
		}
		else {
			boolean res = di.addComponent(n);
			if(res) {
				alerts.showAlert(AlertType.WARNING, "CREATE MY DISH", "This Components Added Successfully To You'r Dish!", ButtonType.CLOSE);
				orgComp.getItems().add(n);
				allComp.getItems().remove(n);

			}
			else {
				alerts.showAlert(AlertType.WARNING, "CREATE MY DISH", "Cannot Add This Component To You'r Dish!", ButtonType.CLOSE);
			}
		}
		Restaurant.savaData();
		Restaurant.loadData();
	}
	@FXML
	void removeComp(ActionEvent event) {                             //this methods removes a component from the selected dish original components.
		Dish di	= allDishs.getSelectionModel().getSelectedItem();
		Component o = orgComp.getSelectionModel().getSelectedItem();
		if (o == null) {
			alerts.showAlert(AlertType.WARNING, "CREATE MY DISH", "Please Select The Component You Would Like To Remove From You'r Dish!", ButtonType.CLOSE);
		}else {
			boolean res = di.removeComponent(o);
			if(res) {
				alerts.showAlert(AlertType.WARNING, "CREATE MY DISH", "This Components Removed Successfully From You'r Dish!", ButtonType.CLOSE);
				orgComp.getItems().remove(o);	 

			}else {
				alerts.showAlert(AlertType.WARNING, "CREATE MY DISH", "Cannot Remove This Component From You'r Dish!", ButtonType.CLOSE);
			}
		}
		Restaurant.savaData();
		Restaurant.loadData();
	}

	@FXML void addMyNewDish(ActionEvent event) {                //in order to control the add, remove components we create a new dish.
		Dish di	= allDishs.getSelectionModel().getSelectedItem();
		Customer customer = MainCont.getUser();
		String name = di.getDishName();
		DishType type = di.getType();
		ObservableList<Component> cmps = orgComp.getItems() ;
		Double pr = di.getPrice();
		int ti = di.getTimeToMake(); 
		ArrayList<Component> finalComp = new ArrayList<>();

		for(Component co : cmps) {
			finalComp.add(co);
		}
		if(cmps != null) {
			Dish d = new Dish(name, type, finalComp, ti);
			boolean res = Restaurant.getInstance().addDish(d);
			if(res) {
				alerts.showAlert(AlertType.INFORMATION, "Add Dish To The System",
						"Added Successfully!", ButtonType.OK);
//				customer.getShop().add(d);

			}else {
				alerts.showAlert(AlertType.ERROR, "Add Dish To The System",
						"Failed to add the given dish!",
						ButtonType.OK);
			}
		}
		else {
			alerts.showAlert(AlertType.ERROR, "CREATE MY DISH", "Cannot Create This Dish, Please add You'r Componnets!", ButtonType.OK);
		}
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


}
