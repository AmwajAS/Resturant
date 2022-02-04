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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class addOrdCont implements Initializable{

	public Restaurant res = Restaurant.getInstance();

	@FXML ImageView wallPaper;
	@FXML Pane ordPane;
	@FXML Label custL;
	@FXML Label addOrderL;
	@FXML Label dishL;
	@FXML 
	private ComboBox<Customer> ordCust;
	@FXML 
	private ListView<Dish> ordDishList;
	@FXML 
	private Button addOrd;
	@FXML 
	private Button clearOrd;
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
		ordDishList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		ordCust.getItems().clear();
		for (Customer c : Restaurant.getInstance().getCustomers().values()) {
			if(c != null)
				ordCust.getItems().add(c);
		}

		for(Dish d : Restaurant.getInstance().getDishes().values()) {
			ordDishList.getItems().add(d);
		}
	}


	@FXML
	void addOrder(ActionEvent event) {                              //this methods adds a new order to the system by the manager.

		Customer c = ordCust.getSelectionModel().getSelectedItem();
		ObservableList<Dish> d = ordDishList.getSelectionModel().getSelectedItems(); 
		ArrayList<Dish> dishes = new ArrayList<>();


		if(c == null) {
			alerts.showAlert(AlertType.WARNING, "Add Order To The System",
					"Please enter the customer who ordered.", ButtonType.OK);
		} else if(d == null || d.isEmpty()) {
			alerts.showAlert(AlertType.WARNING, "Add Order To The System",
					"Please enter the dishes for the order.", ButtonType.OK);
		}else {
			// add	
			for(Dish dd : d)
				dishes.add(dd);
			Order o = new Order(c, dishes,null);
			boolean res = Restaurant.getInstance().addOrder(o);
			if(res) {
				alerts.showAlert(AlertType.INFORMATION, "Add Order To The System",
						"Added Successfully!", ButtonType.OK);
				clearing();

			}else {
				alerts.showAlert(AlertType.ERROR, "Add Order To The System",
						"Failed to add the given Order, The Customer Is Sensitive To The Selected Dish Components, Or The Customer is in The Black list!",
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

		ordCust.getSelectionModel().clearSelection();;
		ordDishList.getSelectionModel().clearSelection();;
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
			Parent root = FXMLLoader.load(getClass().getResource("AddOrd.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adding A Order");
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
