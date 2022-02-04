package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Alerts.alerts;
import Model.Component;
import Model.Cook;
import Model.Customer;
import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Model.Dish;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class dataCont implements Initializable{

	@FXML ImageView wallPaper;
	@FXML Label dataL;
	@FXML Label dataHelp;

	@FXML
	private ListView<Object> dataList;
	@FXML 
	private ComboBox<String> dataSelect;
	@FXML 
	private HBox hMainBox;
	@FXML 
	private Button back;
	@FXML ImageView backImg;
	@FXML 
	private Button reFresh;
	@FXML ImageView reFreshImg;
	@FXML 
	private Button load;
	@FXML 
	private Button clear;
	@FXML Tooltip help;
	@FXML
	private Button search;
	@FXML
	private TextField id;
	private int i;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		dataSelect.getItems().addAll("Cook", "Delivery Person", "Customer", "Dish", "Component", "Order", "Delivery", "Delivery Area", "Black List");


	}


	@FXML
	void showData(ActionEvent event) {
		String str= dataSelect.getSelectionModel().getSelectedItem();
		try {
			switch (str) {
			case "Cook":{
				dataList.getItems().clear();
				if(Restaurant.getInstance().getCooks().isEmpty()) {
					alerts.showAlert(AlertType.WARNING, "All The Cooks Data", "There's No Cooks In The System Yet!",ButtonType.CLOSE);
				}else {
					for(Cook co : Restaurant.getInstance().getCooks().values())
						dataList.getItems().addAll(co);
				}
				break;
			}
			case "Delivery Person":{
				dataList.getItems().clear();

				if(Restaurant.getInstance().getDeliveryPersons().isEmpty()) {
					alerts.showAlert(AlertType.WARNING, "All The Delivery Persons Data", "There's No Delivery Persons In The System Yet!",ButtonType.CLOSE);
				}else {
					for(DeliveryPerson dp : Restaurant.getInstance().getDeliveryPersons().values())
						dataList.getItems().addAll(dp);	
				}
				break;	
			}
			case "Customer": {
				dataList.getItems().clear();

				if(Restaurant.getInstance().getCustomers().isEmpty() ) {
					alerts.showAlert(AlertType.WARNING, "All The Customers Data", "There's No Customers In The System Yet!",ButtonType.CLOSE);
				}else {
					for(Customer cus : Restaurant.getInstance().getCustomers().values())	
						dataList.getItems().addAll(cus);					
				}
				break;
			}
			case "Dish": {
				dataList.getItems().clear();

				if (Restaurant.getInstance().getDishes().isEmpty() ) {
					alerts.showAlert(AlertType.WARNING, "All The Dishes Data", "There's No Dishes In The System Yet!",ButtonType.CLOSE);
				}else {
					for(Dish ds : Restaurant.getInstance().getDishes().values())
						dataList.getItems().addAll(ds);								
				}
				break;
			}
			case "Component":{
				dataList.getItems().clear();
				if( Restaurant.getInstance().getComponenets().isEmpty()) {
					alerts.showAlert(AlertType.WARNING, "All The Components Data", "There's No Components In The System Yet!",ButtonType.CLOSE);
				}else {
					for(Component comp : Restaurant.getInstance().getComponenets().values())
						dataList.getItems().addAll(comp);		
				}
				break;
			}
			case "Order": {
				dataList.getItems().clear();

				if(Restaurant.getInstance().getOrders().isEmpty()) {
					alerts.showAlert(AlertType.WARNING, "All The Orders Data", "There's No Orders In The System Yet!",ButtonType.CLOSE);
				}else {
					for(Order o : Restaurant.getInstance().getOrders().values())
						dataList.getItems().addAll(o);	
				}
				break;
			}
			case "Delivery": {
				dataList.getItems().clear();

				if (Restaurant.getInstance().getDeliveries().isEmpty() ) {
					alerts.showAlert(AlertType.WARNING, "All The Deliveries Data", "There's No Deliveries In The System Yet!",ButtonType.CLOSE);
				}else {
					for(Delivery d : Restaurant.getInstance().getDeliveries().values())
						dataList.getItems().addAll(d);						
				}
				break;
			}
			case "Black List": {
				dataList.getItems().clear();

				if(Restaurant.getInstance().getBlackList().isEmpty()) {
					alerts.showAlert(AlertType.WARNING, "All The Black List Data", "There's No Customers In The Black List Yet!",ButtonType.CLOSE);
				}else {
					for(Customer cus : Restaurant.getInstance().getBlackList())
						dataList.getItems().addAll(cus);
				}
				break;
			}
			case "Delivery Area": {
				dataList.getItems().clear();

				if  (Restaurant.getInstance().getAreas().isEmpty()) {
					alerts.showAlert(AlertType.WARNING, "All The Delivery Areas Data", "There's No Delivery Areas In The System Yet!",ButtonType.CLOSE);
				}else{
					for(DeliveryArea da : Restaurant.getInstance().getAreas().values())
						dataList.getItems().addAll(da);
				}
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + dataSelect);
			}
		}catch (NullPointerException e) {
			// TODO: handle exception
			alerts.showAlert(AlertType.ERROR, "Data", "Please Select The Relevant Data Type!", ButtonType.CLOSE);
		}
	}

	@FXML
	void Search(ActionEvent event) {
		String str= dataSelect.getSelectionModel().getSelectedItem();
		try {
			switch (str) {
			case "Cook":{
				try {
					i = Integer.parseInt(id.getText());
				} catch (NumberFormatException e) {
					// TODO: handle exception
					alerts.showAlert(AlertType.WARNING,"DATA","Please enter the id in a number format only!.", ButtonType.OK);
				}catch (Exception e) {
					// TODO: handle exception
					alerts.showAlert(AlertType.ERROR, "DATA", "Please Enter A Id Number",ButtonType.CLOSE);
				}
				Cook c = Restaurant.getInstance().getRealCook(i);
				if(c!= null) {
					dataList.getSelectionModel().select(c);
				}
				else {
					alerts.showAlert(AlertType.ERROR, "DATA", "There's No Cook With This Id.", ButtonType.CLOSE);
				}
				break;
			}
			case "Customer":{
				try {
					i = Integer.parseInt(id.getText());
				} catch (NumberFormatException e) {
					// TODO: handle exception
					alerts.showAlert(AlertType.WARNING,"DATA","Please enter the id in a number format only!.", ButtonType.OK);
				}
				Customer c = Restaurant.getInstance().getRealCustomer(i);
				if(c != null) {
					dataList.getSelectionModel().select(c);
				}
				else {
					alerts.showAlert(AlertType.ERROR, "DATA", "There's No Customer With This Id.", ButtonType.CLOSE);
				}
				break;
			}
			case "Delivery Person":{
				try {
					i = Integer.parseInt(id.getText());
				} catch (NumberFormatException e) {
					// TODO: handle exception
					alerts.showAlert(AlertType.WARNING,"DATA","Please enter the id in a number format only!.", ButtonType.OK);
				}
				DeliveryPerson d = Restaurant.getInstance().getRealDeliveryPerson(i);
				if(d != null) {
					dataList.getSelectionModel().select(d);
				}
				else {
					alerts.showAlert(AlertType.ERROR, "DATA", "There's No Delivery Person With This Id.", ButtonType.CLOSE);
				}
				break;
			}
			case "Dish":{
				try {
					i = Integer.parseInt(id.getText());
				} catch (NumberFormatException e) {
					// TODO: handle exception
					alerts.showAlert(AlertType.WARNING,"DATA","Please enter the id in a number format only!.", ButtonType.OK);
				}
				Dish c = Restaurant.getInstance().getRealDish(i);
				if(c != null) {
					dataList.getSelectionModel().select(c);
				}
				else {
					alerts.showAlert(AlertType.ERROR, "DATA", "There's No Dish With This Id.", ButtonType.CLOSE);
				}
				break;
			}
			case "Component":{
				try {
					i = Integer.parseInt(id.getText());
				} catch (NumberFormatException e) {
					// TODO: handle exception
					alerts.showAlert(AlertType.WARNING,"DATA","Please enter the id in a number format only!.", ButtonType.OK);
				}
				Component c = Restaurant.getInstance().getRealComponent(i);
				if(c != null) {
					dataList.getSelectionModel().select(c);
				}
				else {
					alerts.showAlert(AlertType.ERROR, "DATA", "There's No Component With This Id.", ButtonType.CLOSE);
				}
				break;
			}
			case "Order":{
				try {
					i = Integer.parseInt(id.getText());
				} catch (NumberFormatException e) {
					// TODO: handle exception
					alerts.showAlert(AlertType.WARNING,"DATA","Please enter the id in a number format only!.", ButtonType.OK);
				}
				Order c= Restaurant.getInstance().getRealOrder(i);
				if(c != null) {
					dataList.getSelectionModel().select(c);
				}
				else {
					alerts.showAlert(AlertType.ERROR, "DATA", "There's No Order With This Id.", ButtonType.CLOSE);
				}
				break;
			}
			case "Delivery":{
				try {
					i = Integer.parseInt(id.getText());
				} catch (NumberFormatException e) {
					// TODO: handle exception
					alerts.showAlert(AlertType.WARNING,"DATA","Please enter the id in a number format only!.", ButtonType.OK);
				}
				Delivery c = Restaurant.getInstance().getRealDelivery(i);
				if(c != null) {
					dataList.getSelectionModel().select(c);
				}
				else {
					alerts.showAlert(AlertType.ERROR, "DATA", "There's No Delivery With This Id.", ButtonType.CLOSE);
				}
				break;
			}
			case "Delivery Area":{
				try {
					i = Integer.parseInt(id.getText());
				} catch (NumberFormatException e) {
					// TODO: handle exception
					alerts.showAlert(AlertType.WARNING,"DATA","Please enter the id in a number format only!.", ButtonType.OK);
				}
				DeliveryArea c = Restaurant.getInstance().getRealDeliveryArea(i);
				if(c != null) {
					dataList.getSelectionModel().select(c);
				}
				else {
					alerts.showAlert(AlertType.ERROR, "DATA", "There's No Delivery Area With This Id.", ButtonType.CLOSE);
				}
				break;
			}
			}
		}catch (Exception e) {
			// TODO: handle exception
			alerts.showAlert(AlertType.ERROR, "DATA", "Please Select A Data Type First!", ButtonType.CLOSE);
		}
	}


	@FXML
	void clear() {
		clearning();
	}

	public void clearning() {
		dataSelect.getSelectionModel().clearSelection();
		dataList.getItems().clear();

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
			Parent root = FXMLLoader.load(getClass().getResource("Data.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("DATA");
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
