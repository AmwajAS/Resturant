package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Alerts.alerts;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Model.Restaurant;
import Utils.Gender;
import Utils.Vehicle;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class addDelPerCont  implements Initializable{

	public Restaurant res = Restaurant.getInstance();


	@FXML ImageView wallPaper;
	@FXML Pane delPerPane;
	@FXML Label delperFirstL;
	@FXML Label delperDateL;
	@FXML Label delperGenderL;
	@FXML Label delperLastL;
	@FXML Label delperAreaL;
	@FXML Label delperVechileL;
	@FXML 
	private TextField delperFirstF;
	@FXML 
	private TextField delperLastF;
	@FXML 
	private DatePicker delperDateF;
	@FXML 
	private ComboBox<Vehicle> delperVehicleF;
	@FXML 
	private ComboBox<DeliveryArea> delperAreaF;
	@FXML
	private ComboBox<Gender> delperGenderF;
	@FXML Label delper;
	@FXML 
	private Button addDelper;
	@FXML
	private Button clearDelPer;
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
		delperFirstF.getText();
		delperLastF.getText();
		delperDateF.setValue(LocalDate.now());
		if(delperDateF.getValue().isAfter(nowDate)) {
			alerts.showAlert(AlertType.WARNING, "Invaild Birth Date","Please enter a valid delivery person birth date!" , ButtonType.OK);
			delperDateF.setValue(nowDate);

		}
		for( Gender g : Gender.values()) {
			delperGenderF.getItems().addAll(g);
		}

		for( Vehicle vi : Vehicle.values()) {
			delperVehicleF.getItems().addAll(vi);
		}
		for (DeliveryArea d : Restaurant.getInstance().getAreas().values()) {
			if(d != null) {
				try {
					delperAreaF.getItems().add(d);
				}catch (NullPointerException e) {
					// TODO: handle exception
					alerts.showAlert(AlertType.ERROR, "Add Delivery Person To The System", "There's No Delivery Areas In The System Yet!", ButtonType.CLOSE);
				}

			}

		}
	}


	@FXML
	void addDeliveryPerson(ActionEvent event) {

		String f = null;
		String l = null;
		LocalDate date = null;
		Gender g = null; 
		Vehicle v = null;
		DeliveryArea da = null;


		try {
			f = delperFirstF.getText();
			l = delperLastF.getText();
			date = delperDateF.getValue();
			g = delperGenderF.getSelectionModel().getSelectedItem(); 
			v = delperVehicleF.getSelectionModel().getSelectedItem();
			da = delperAreaF.getSelectionModel().getSelectedItem();
		}catch (NullPointerException e) {
			// TODO: handle exception
			alerts.showAlert(AlertType.ERROR, "Add Delivery Person To The System", "Please Fill in The Following Feilds!", ButtonType.CLOSE);

		}

		if(f == null || f.isEmpty()) {
			alerts.showAlert(AlertType.WARNING, "Add Delivery Person To The System",
					"Please enter the customer first name.", ButtonType.OK);
		} else if(l == null) {
			alerts.showAlert(AlertType.WARNING, "Add Delivery Person To The System",
					"Please enter the delivery person last name.", ButtonType.OK);
		}else if(date == null) {
			alerts.showAlert(AlertType.WARNING, "Add Delivery Person To The System",
					"Please enter the delivery person date of birth.", ButtonType.OK);
		}else if(g == null) {
			alerts.showAlert(AlertType.WARNING, "Add Delivery Person To The System",
					"Please enter the cook gender.", ButtonType.OK);
		}else if(v == null) {
			alerts.showAlert(AlertType.WARNING, "Add Delivery Person To The System",
					"Please enter the delivery person vehicle.", ButtonType.OK);
		} if(delperAreaF.getSelectionModel().getSelectedItem() == null) {
			alerts.showAlert(AlertType.WARNING, "Add Delivery Person To The System",
					"Please enter the delivery person area.", ButtonType.OK);
		}else {
			// add
			boolean res = Restaurant.getInstance().addDeliveryPerson(
					new DeliveryPerson(f, l, date, g, v, da), da);
			if(res) {
				alerts.showAlert(AlertType.INFORMATION, "Add Delivery Person To The System",
						"Added Successfully!", ButtonType.OK);

				clearing();
			}else {
				alerts.showAlert(AlertType.ERROR, "Add Delivery Person To The System",
						"Failed to add the given delivery person!",
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
		delperFirstF.setText("");
		delperLastF.setText("");
		delperDateF.setValue(LocalDate.now());
		delperGenderF.getSelectionModel().clearSelection();
		delperVehicleF.getSelectionModel().clearSelection();;
		delperAreaF.getSelectionModel().clearSelection();;

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
			Parent root = FXMLLoader.load(getClass().getResource("AddDelPer.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adding A Delivery Person");
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
