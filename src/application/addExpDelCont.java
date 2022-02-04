package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import Alerts.alerts;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Model.ExpressDelivery;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class addExpDelCont implements Initializable {

	public Restaurant res = Restaurant.getInstance();

	@FXML ImageView wallPaper;
	@FXML Label expDel;
	@FXML Label expDelPerL;
	@FXML
	private ComboBox<DeliveryPerson> ExpDelPerF;
	@FXML Label expDelAreaL;
	@FXML
	private ComboBox<DeliveryArea> ExpDelAreaF;
	@FXML
	private CheckBox isDelExp;
	@FXML
	private DatePicker ExpDateF;
	@FXML Label expDateL;
	@FXML Label expOrdL;
	@FXML
	private ComboBox<Order> expOrdF;
	@FXML 
	private TextField expDelPriceF;
	@FXML Label expDelPriceL;
	@FXML Label ExpDelILS;
	@FXML 
	private Button clearExpDel;
	@FXML Button addExpDel;
	@FXML HBox hMainBox;
	@FXML
	private Button back;
	@FXML ImageView backImg;
	@FXML
	private Button reFresh;
	@FXML ImageView reFreshImg;
	private double p;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		



		for( DeliveryPerson di :Restaurant.getInstance().getDeliveryPersons().values()) {
			if(!Restaurant.getInstance().getDeliveryPersons().isEmpty())
				ExpDelPerF.getItems().addAll(di);
		}

		for (DeliveryArea da : Restaurant.getInstance().getAreas().values()) {
			if(da != null)
				ExpDelAreaF.getItems().add(da);
		}
		expOrdF.getItems().clear();
		for(Order o : Restaurant.getInstance().getOrders().values()) {
			if(o != null)
				expOrdF.getItems().addAll(o);
		}

	}



	//This methods checks if any of the given fields is'nt null and add it to the system.
	@FXML
	void addExpressDelivery(ActionEvent event) {


		DeliveryPerson dp = null;
		DeliveryArea dar = null;
		Boolean isD = null;
		LocalDate date = null;
		Order or =  null;

		try {
			dp = ExpDelPerF.getSelectionModel().getSelectedItem();
			dar = ExpDelAreaF.getSelectionModel().getSelectedItem();
			isD = isDelExp.isSelected();
			date = ExpDateF.getValue();
			or =  expOrdF.getSelectionModel().getSelectedItem();
			p = Double.parseDouble(expDelPriceF.getText());
		} catch (NumberFormatException e) {
			// TODO: handle exception
			alerts.showAlert(AlertType.WARNING,"ADD EXPRESS DELIVERY To The System",
					"Please enter the Delivery price in a number format only!.", ButtonType.OK);
		}catch (NullPointerException e) {
			// TODO: handle exception
			alerts.showAlert(AlertType.WARNING,"ADD EXPRESS DELIVERY To The System",
					"Please Fill in The Following Feilds!.", ButtonType.OK);

		}
		if(dp == null) {
			alerts.showAlert(AlertType.WARNING, "ADD EXPRESS DELIVERY To The System",
					"Please enter a Delivery Person.", ButtonType.OK);
		} else if(dar == null) {
			alerts.showAlert(AlertType.WARNING, "ADD EXPRESS DELIVERY To The System",
					"Please enter a Delivery Area.", ButtonType.OK);
		}else if(date == null) {
			alerts.showAlert(AlertType.WARNING, "ADD EXPRESS DELIVERY To The System",
					"Please enter the Delivery Date.", ButtonType.OK);
		}else if(or == null) {
			alerts.showAlert(AlertType.WARNING, "ADD EXPRESS DELIVERY To The System",
					"Please enter the Order for the delivery.", ButtonType.OK);
		}else if(p == 0.0) {
			alerts.showAlert(AlertType.WARNING, "ADD EXPRESS DELIVERY To The System",
					"Please enter the Express Delivery price.", ButtonType.OK);
		}else {
			// add
			boolean res = Restaurant.getInstance().addDelivery(
					new ExpressDelivery(dp, dar, isD, or, p, date));
			if(res) {
				alerts.showAlert(AlertType.INFORMATION, "ADD EXPRESS DELIVERY To The System",
						"Added Successfully!", ButtonType.OK);

				clearing();
			}else {
				alerts.showAlert(AlertType.ERROR, "ADD EXPRESS DELIVERY To The System",
						"Failed to add the given Express Delivery!",
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

		ExpDelPerF.getSelectionModel().clearSelection();
		ExpDelAreaF.getSelectionModel().clearSelection();
		isDelExp.setSelected(false);
		ExpDateF.setValue(LocalDate.now());
		expOrdF.getSelectionModel().clearSelection();
		expDelPriceF.setText("");

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
			primaryStage.setTitle("Adding A Express Delivery ");
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
