package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ResourceBundle;

import Alerts.alerts;
import Model.Component;
import Model.Cook;
import Model.Customer;
import Model.Dish;
import Model.Restaurant;
import Utils.Expertise;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class userQueriesCont implements Initializable{

	@FXML ImageView wallPaper;
	@FXML TabPane tabPane;
	@FXML Tab q1Pane;
	@FXML ListView<Dish> relv;
	@FXML Button q1Button;

	@FXML ListView<Cook> cookExp;
	@FXML ComboBox<Expertise> exp;
	@FXML Button q2Button;

	@FXML ListView<Component> popComp;
	@FXML Button q3Button;

	@FXML ImageView q1Img;
	@FXML Tab q2Pane;
	@FXML ImageView q2Img;
	@FXML Tab q3Pane;
	@FXML ImageView q3Img;
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
	@FXML ImageView wall2;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		exp.getItems().clear();
		for(Expertise e : Expertise.values()) {
			exp.getItems().add(e);
		}
		
		
		
	}

	@FXML
	void getPopComp(ActionEvent event) {                                ///this methods returns the popular component appearance in the dishes in the system.
		LinkedList<Component> comp = Restaurant.getInstance().getPopularComponents();
		if(comp == null || comp.isEmpty()) {
			alerts.showAlert(AlertType.ERROR, "Get Popular Components", 
					"No Results Found!", ButtonType.CLOSE);
			return;
		}
		popComp.getItems().clear();
		for (Component co : comp) {
			if(co!=null)
				popComp.getItems().add(co);
		} 	
	}

	@FXML
	void getRelvDishList(ActionEvent event) {                        //this methods returns and show the customer profit dish according to his sensitivity.
		Customer customer = MainCont.getUser();
		Collection<Dish> custDish = Restaurant.getInstance().getReleventDishList(customer);
		if(customer != null) {
			if( Restaurant.getInstance().getCustomers().containsKey(customer.getId())) {
				Restaurant.getInstance().getReleventDishList(customer);
				if(custDish == null) {
					alerts.showAlert(AlertType.WARNING, "GET RELEVANT DISH LIST", "Oops, There's No Dish List For You Yet!", ButtonType.CLOSE);
				}
				else {
					for(Dish d : custDish) {
						relv.getItems().addAll(d);
					}
				}

			}
		}
	}
	@FXML 
	void getCookExp(ActionEvent event){                              //this methods returns the cook list in the system with the selected expertise.
		Expertise ex = exp.getSelectionModel().getSelectedItem();

		if(ex == null) {
			alerts.showAlert(AlertType.WARNING, "GET COOK BY EXPERTISE", "Please Select The Cook Expert", ButtonType.CLOSE);
		}
		else {
			ArrayList<Cook> cook = Restaurant.getInstance().GetCooksByExpertise(ex);
			if( cook.isEmpty()) {
				alerts.showAlert(AlertType.WARNING, "GET COOK BY EXPERTISE", "There's No Cooks With This Expert", ButtonType.CLOSE);
			}
			else {
				for(Cook c : cook) 
					cookExp.getItems().addAll(c);
			}
		}
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
