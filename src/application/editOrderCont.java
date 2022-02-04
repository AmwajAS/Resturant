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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class editOrderCont implements Initializable {
	
	public Restaurant res = Restaurant.getInstance();
	@FXML
	private Button update;
	@FXML
	private ComboBox<Order> id;
	@FXML
	private Button load;
	@FXML 
	private Button back;
	@FXML 
	private Button reFresh;
	@FXML
	private ComboBox<Customer> cust;
	@FXML
	private TextField time;
	@FXML
	private ListView<Dish> dishes;
	@FXML
	private ListView<Dish> all;
	@FXML
	private Button add;
	@FXML
	private Button remove;




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		all.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		dishes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		all.getItems().clear();
		for(Dish d: Restaurant.getInstance().getDishes().values()) {
			if( d == null) {
				alerts.showAlert(AlertType.WARNING, "EDIT ORDER", "There's No Dishes In The Resturant Yet!", ButtonType.CLOSE);
			}else {
				all.getItems().add(d);				
			}
		}

		for(Order o : Restaurant.getInstance().getOrders().values()) {
			if(o != null) {
				id.getItems().add(o);
			}
		}
		id.getSelectionModel().getSelectedItem();

		for(Customer c : Restaurant.getInstance().getCustomers().values()) {
			cust.getItems().add(c);
		}
	}

	@FXML
	void addNewDish(ActionEvent event) {
		Order or = id.getSelectionModel().getSelectedItem();

		Dish di = all.getSelectionModel().getSelectedItem();
		if(di == null) {
			alerts.showAlert(AlertType.WARNING, "EDIT ORDER", "Please Select The Dish You Would Like To Add To The Order!", ButtonType.CLOSE);
		}
		else {
			boolean res = or.addDish(di);
			if(res) {
				alerts.showAlert(AlertType.WARNING, "EDIT ORDER", "This Dish Added Successfully To The Order!", ButtonType.CLOSE);
				dishes.getItems().add(di);
				all.getItems().remove(di);
			}
			else {
				alerts.showAlert(AlertType.WARNING, "EDIT ORDER", "Cannot Add This Dish To The Order!", ButtonType.CLOSE);
			}
		}
		Restaurant.savaData();
		Restaurant.loadData();
	}



	@FXML
	void loadItem(ActionEvent event) {
		Order or = id.getSelectionModel().getSelectedItem();

		ArrayList<Dish> di = new ArrayList<>();

		if(or != null) {
			cust.getSelectionModel().select(or.getCustomer());
			for(Dish d : or.getDishes()) {
				di.add(d);
				dishes.getItems().add(d);

			}

		}else {
			alerts.showAlert(AlertType.ERROR, "UPDATE ORDER", "Please Select A Order!", ButtonType.CLOSE);
		}

	}

	@FXML
	void removeDish(ActionEvent event) {
		Order or = id.getSelectionModel().getSelectedItem();
		Dish d = dishes.getSelectionModel().getSelectedItem();
		if (d == null) {
			alerts.showAlert(AlertType.WARNING, "EDIT ORDER", "Please Select The Dish You Would Like To Remove From The Order!", ButtonType.CLOSE);
		}else {
			boolean res = or.removeDish(d);
			if(res) {
				alerts.showAlert(AlertType.WARNING, "EDIT ORDER", "This Dish Removed Successfully From The Order!", ButtonType.CLOSE);
				dishes.getItems().remove(d);	 

			}else {
				alerts.showAlert(AlertType.WARNING, "EDIT ORDER", "Cannot Remove This Dish From The Order!", ButtonType.CLOSE);
			}
		}
		Restaurant.savaData();
		Restaurant.loadData();
	}


	@FXML
	void updateItem(ActionEvent event) {
		Order ord = id.getSelectionModel().getSelectedItem();
		Customer c = cust.getSelectionModel().getSelectedItem();
		ObservableList<Dish> nd = dishes.getItems();

		ArrayList<Dish> dii = new ArrayList<>();
		for(Dish d : nd) {
			dii.add(d);
		}


		if(dii == null || dii.isEmpty()) {
			alerts.showAlert(AlertType.WARNING, "EDIT ORDER",
					"Please enter the Order Dishes.", ButtonType.OK);
		} else if(c == null) {
			alerts.showAlert(AlertType.WARNING, "EDIR ORDER",
					"Please enter the Order Customer.", ButtonType.OK);
		}else {
			// add
			boolean res = Restaurant.getInstance().addOrder(
					new Order(c, dii, null));
			boolean resu = Restaurant.getInstance().removeOrder(ord);
			if(res && resu) {
				alerts.showAlert(AlertType.INFORMATION, "Edit ORDER",
						"Edit Successfully!", ButtonType.OK);

			//clearing();
			}else {
				alerts.showAlert(AlertType.ERROR, "EDIT ORDER",
						"Failed to Edit!",
						ButtonType.OK);
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
			Parent root = FXMLLoader.load(getClass().getResource("EditOrd.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Edit Order");
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
