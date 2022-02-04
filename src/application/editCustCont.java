package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import Alerts.alerts;
import Model.Customer;
import Model.Restaurant;
import Utils.Gender;
import Utils.Neighberhood;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class editCustCont implements Initializable{
	
	public Restaurant res = Restaurant.getInstance();

	@FXML
	private Button update;
	@FXML
	private Button load;
	@FXML
	private Button reFresh;
	@FXML
	private Button back;
	@FXML 
	private ComboBox<Customer> id;
	@FXML
	private TextField first;
	@FXML
	private TextField last;
	@FXML
	private DatePicker date;
	@FXML
	private ComboBox<Neighberhood> ngh;
	@FXML
	private ComboBox<Gender> gen;
	@FXML
	private CheckBox glu;
	@FXML
	private CheckBox lac;
	@FXML
	private TextField newp;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		for(Customer c : Restaurant.getInstance().getCustomers().values()) {
			if(c != null) {
				id.getItems().add(c);
			}
		}
		id.getSelectionModel().getSelectedItem();

		gen.getItems().clear();
		for(Gender gender : Gender.values()) {
			gen.getItems().add(gender);
		}
		ngh.getItems().clear();
		for(Neighberhood n : Neighberhood.values()){
			ngh.getItems().add(n);
		}
	}


	@FXML
	void loadItem(ActionEvent event) {
		Customer customer = id.getSelectionModel().getSelectedItem();



		if(customer != null) {

			first.setText(customer.getFirstName());	
			last.setText(customer.getLastName());	
			date.setValue(customer.getBirthDay());
			gen.getSelectionModel().select(customer.getGender());
			ngh.getSelectionModel().select(customer.getNeighberhood());
			glu.setSelected(customer.isSensitiveToGluten());
			lac.setSelected(customer.isSensitiveToLactose());

		}else {
			alerts.showAlert(AlertType.ERROR, "UPDATE Customer", "Please Select A Customer!", ButtonType.CLOSE);
		}
	}
	
	@FXML
	void updateItem(ActionEvent event) {
		String f =  first.getText();
		String l =  last.getText();
		LocalDate d = date.getValue();
		Gender g = gen.getSelectionModel().getSelectedItem();
		Neighberhood n = ngh.getSelectionModel().getSelectedItem();
		Boolean gl = glu.isSelected();
		Boolean lc = lac.isSelected();
		String p2 = newp.getText(); 
		Customer customer= id.getSelectionModel().getSelectedItem();


		if(first.getText() != customer.getFirstName()) {
			customer.setFirstName(f);

		}else if(first == null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE CUSTOMER", " Please enter a Customer First Name.", ButtonType.CLOSE);

		}
		if(last.getText() != customer.getLastName()) { 
			customer.setLastName(l);

		}else if(last == null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE CUSTOMER", " Please enter a Customer Last Name.", ButtonType.CLOSE);
		}
		if(date.getValue() != customer.getBirthDay()) {
			customer.setBirthDay(d);
		}else if(date == null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE CUSTOMER", " Please enter a Customer Birth Day.", ButtonType.CLOSE);

		}
		if(gen.getSelectionModel().getSelectedItem() != customer.getGender()) {
			customer.setGender(g);

		}else if(gen == null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE CUSTOMER", " Please enter a Customer Gender.", ButtonType.CLOSE);
		}
		if(ngh.getSelectionModel().getSelectedItem() != customer.getNeighberhood()) {
			customer.setNeighberhood(n);
		}else if(ngh == null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE CUSTOMER", " Please Select a Customer Neighborhood.", ButtonType.CLOSE);
		}
		if(glu.isSelected() != customer.isSensitiveToGluten()) {
			customer.setSensitiveToGluten(gl);
		}
		if(lac.isSelected() != customer.isSensitiveToLactose()) {
			customer.setSensitiveToLactose(lc);
		}
		if( p2 != null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE CUSTOMER", " Please Enter The Customer Password.", ButtonType.CLOSE);

			if( p2 != customer.getUserPassword()) {
				alerts.showAlert(AlertType.WARNING, "UPDATE CUSTOMER", "Password Cant Be like The Old Passwords! .", ButtonType.CLOSE);

				customer.setUserPassword(p2);
			}
		}

		if( customer.getFirstName() == f && customer.getLastName() == l && customer.getBirthDay() == d && customer.getGender() == g && customer.getNeighberhood() == n && customer.isSensitiveToGluten() == gl && customer.isSensitiveToLactose() == lc)
			alerts.showAlert(AlertType.INFORMATION, "UPDATE CUSTOMER", "Updated Successfully!.", ButtonType.CLOSE);
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
			Parent root = FXMLLoader.load(getClass().getResource("EditCust.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Edit Customer");
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
