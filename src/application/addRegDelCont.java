package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.TreeSet;
import Alerts.alerts;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Model.Order;
import Model.RegularDelivery;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class addRegDelCont implements Initializable{

	public Restaurant res = Restaurant.getInstance();

	@FXML ImageView wallPaper;
	@FXML Label regDelL;
	@FXML Label regDelPerL;
	@FXML Label regDelAreaL;
	@FXML 
	private CheckBox isDelReg;
	@FXML Label regDateL;
	@FXML Label ordRegL;
	@FXML 
	private Button addReg;
	@FXML 
	private Button clearReg;
	@FXML 
	private ComboBox<DeliveryPerson> regDelPerF;
	@FXML 
	private ComboBox<DeliveryArea> regDelAreaF;
	@FXML 
	private ListView<Order> regOrdF;
	@FXML 
	private DatePicker regDateF;
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
		LocalDate nowDate = LocalDate.now();

		for( DeliveryPerson di :Restaurant.getInstance().getDeliveryPersons().values()) {
			if(di != null)
				regDelPerF.getItems().addAll(di);
		}

		for (DeliveryArea da : Restaurant.getInstance().getAreas().values()) {
			if(da != null)
				regDelAreaF.getItems().add(da);
		}

		regOrdF.getItems().clear();
		for(Order o : Restaurant.getInstance().getOrders().values()) {
			if(o != null)
				regOrdF.getItems().addAll(o);
		}
		regDateF.setValue(nowDate);

	}


	@FXML
	void addRegularDelivery(ActionEvent event) {


		DeliveryPerson dp = null;
		DeliveryArea dar = null;
		Boolean isDel = null;
		LocalDate date = null;
		ObservableList<Order> or=null;

		
		try {
			dp = regDelPerF.getSelectionModel().getSelectedItem();
			dar = regDelAreaF.getSelectionModel().getSelectedItem();
			isDel = isDelReg.isSelected();
			date = regDateF.getValue();
			or = (ObservableList<Order>) regOrdF.getSelectionModel().getSelectedItems();
		}catch (NullPointerException e) {
			// TODO: handle exception
			alerts.showAlert(AlertType.ERROR, "Add Regular Delivery To The System", "fill in the following feilds", ButtonType.CLOSE);
		}

		TreeSet<Order> oo =new TreeSet<>();
		for(Order o : or) {
			oo.add(o);
		}


		if(dp == null) {
			alerts.showAlert(AlertType.WARNING, "ADD REGULAR DELIVERY To The System",
					"Please enter a Delivery Person.", ButtonType.OK);
		} else if (dar == null) {
			alerts.showAlert(AlertType.WARNING, "ADD REGULAR DELIVERY To The System",
					"Please enter a Delivery Area.", ButtonType.OK);
		}else if(date == null ) {
			alerts.showAlert(AlertType.WARNING, "ADD REGULAR DELIVERY To The System",
					"Please enter the Delivery Date.", ButtonType.OK);
		}else if(or == null) {
			alerts.showAlert(AlertType.WARNING, "ADD REGULAR DELIVERY To The System",
					"Please enter the Orders for the delivery.", ButtonType.OK);
		} else {
			// add
			boolean res = Restaurant.getInstance().addDelivery(
					new RegularDelivery( oo, dp, dar, isDel, date));
			if(res) {
				alerts.showAlert(AlertType.INFORMATION, "ADD REGULAR DELIVERY To The System",
						"Added Successfully!", ButtonType.OK);

				clearing();
			}else {
				alerts.showAlert(AlertType.ERROR, "ADD REGULAR DELIVERY To The System",
						"Failed to add the given Delivery!",
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

		regDelPerF.getSelectionModel().clearSelection();
		regDelAreaF.getSelectionModel().clearSelection();
		isDelReg.setSelected(false);
		regDateF.setValue(LocalDate.now());
		regOrdF.getSelectionModel().clearSelection();;


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
			Parent root = FXMLLoader.load(getClass().getResource("AddDeliArea.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adding A Regular Delivery");
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
