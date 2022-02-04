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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class editDelPerCont implements Initializable{

	public Restaurant res = Restaurant.getInstance();
	@FXML
	private TextField first;
	@FXML
	private TextField last;
	@FXML
	private DatePicker date;
	@FXML
	private ComboBox<Gender> gender;
	@FXML
	private ComboBox<Vehicle> vehicle;
	@FXML
	private ComboBox<DeliveryArea> delArea;
	@FXML
	private Button update;
	@FXML
	private ComboBox<DeliveryPerson> id;
	@FXML
	private Button load;
	@FXML 
	private Button back;
	@FXML 
	private Button reFresh;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		for(DeliveryPerson dp : Restaurant.getInstance().getDeliveryPersons().values()) {
			if(dp != null) {
				id.getItems().add(dp);
			}
		}
		id.getSelectionModel().getSelectedItem();

		gender.getItems().clear();
		for(Gender g : Gender.values()) {
			gender.getItems().add(g);
		}
		vehicle.getItems().clear();
		for(Vehicle v : Vehicle.values()) {
			vehicle.getItems().add(v);
		}
		delArea.getItems().clear();
		for(DeliveryArea d : Restaurant.getInstance().getAreas().values()) {
			delArea.getItems().add(d);
		}
	}
	@FXML
	void loadItem(ActionEvent event) {
		DeliveryPerson dep = id.getSelectionModel().getSelectedItem();
		String la = dep.getLastName();


		if(dep != null) {
			first.setText(dep.getFirstName());
			last.setText(la);	
			date.setValue(dep.getBirthDay());
			gender.getSelectionModel().select(dep.getGender());
			vehicle.getSelectionModel().select(dep.getVehicle());
			delArea.getSelectionModel().select(dep.getArea());

		}
	}
	@FXML
	void updateItem(ActionEvent event) {
		String f =  first.getText();
		String l =  last.getText();
		LocalDate d = date.getValue();
		Gender g = gender.getSelectionModel().getSelectedItem();
		Vehicle v = vehicle.getSelectionModel().getSelectedItem();
		DeliveryArea darea = delArea.getSelectionModel().getSelectedItem();
		DeliveryPerson p = id.getSelectionModel().getSelectedItem();



		if( first.getText() != p.getFirstName())
			p.setFirstName(f);
		else if(first == null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE DELIVERY PERSON", " Please enter a Delivery Person First Name.", ButtonType.CLOSE);

		}
		if(last.getText() != p.getLastName()) 
			p.setLastName(l);
		else if(last == null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE DELIVERY PERSON", " Please enter a Delivery Person Last Name.", ButtonType.CLOSE);

		}
		if(date.getValue() != p.getBirthDay())
			p.setBirthDay(d);
		else if(date == null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE DELIVERY PERSON", " Please enter a Delivery Person Birth Date.", ButtonType.CLOSE);

		}
		if(gender.getSelectionModel().getSelectedItem() != p.getGender())
			p.setGender(g);
		else if(gender == null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE DELIVERY PERSON", " Please enter a Delivery Person Gender.", ButtonType.CLOSE);

		}
		if(vehicle.getSelectionModel().getSelectedItem() != p.getVehicle())
			p.setVehicle(v);
		else if(vehicle == null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE DELIVERY PERSON", " Please enter a Delivery Peson Vehicle.", ButtonType.CLOSE);
		}

		if(delArea.getSelectionModel().getSelectedItem() != p.getArea())
			p.setArea(darea);
		else if(delArea == null ) {
			alerts.showAlert(AlertType.WARNING, "UPDATE DELIVERY PERSON", " Please enter a Delivery Person Deliver Area.", ButtonType.CLOSE);
		}

		if( p.getFirstName() == f && p.getLastName() == l && p.getBirthDay() == d && p.getGender() == g && p.getVehicle() == v && p.getArea() == darea)
			alerts.showAlert(AlertType.INFORMATION, "UPDATE COOK", "Updated Successfully!.", ButtonType.CLOSE);
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
			Parent root = FXMLLoader.load(getClass().getResource("EditDelPer.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Edit Delivery Person");
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
