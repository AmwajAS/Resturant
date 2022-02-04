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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class addCustCont implements Initializable{
	
	public Restaurant res = Restaurant.getInstance();

	@FXML ImageView custWall;
	@FXML Pane custPane;
	@FXML Label custFirstL;
	@FXML Label custLastL;
	@FXML Label custDateL;
	@FXML Label custGenderL;
	@FXML Label custNeighL;
	@FXML Label customer;
	@FXML
	private ComboBox<Gender> custGenderF;
	@FXML 
	private DatePicker custDateF;
	@FXML 
	private TextField custFirstF;
	@FXML 
	private TextField custLastF;
	@FXML 
	private Button addCust;
	@FXML
	private Button clearCust;
	@FXML
	private ComboBox<Neighberhood> custNeighF;
	@FXML 
	private CheckBox GlutenSen;
	@FXML
	private CheckBox LactoseSen;
	@FXML 
	private HBox hMainBox;
	@FXML 
	private Button back;
	@FXML ImageView backImg;
	@FXML
	private Button reFresh;
	@FXML ImageView reFreshImg;
	@FXML Label userNL;
	@FXML Label userPL;
	@FXML 
	private TextField userNF;
	@FXML 
	private TextField userPF;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		LocalDate nowDate = LocalDate.now();
		custFirstF.getText();
		custLastF.getText();
		custDateF.setValue(LocalDate.now());
		if(custDateF.getValue().isAfter(nowDate)) {
			alerts.showAlert(AlertType.WARNING, "Invaild Birth Date","Please enter a valid customer birth date!" , ButtonType.OK);
			custDateF.setValue(nowDate);

		}
		custGenderF.getItems().clear();
		for( Gender g : Gender.values()) {
			custGenderF.getItems().addAll(g);
		}
		custNeighF.getItems().clear();
		for( Neighberhood n : Neighberhood.values()) {
			custNeighF.getItems().addAll(n);
		}
		userNF.getText();
		userPF.getText();

	}

	@FXML
	void addCustomer(ActionEvent event) throws IOException{

		String fn = custFirstF.getText();
		String ln = custLastF.getText();
		LocalDate cud = custDateF.getValue();
		Gender gn = custGenderF.getSelectionModel().getSelectedItem(); 
		Neighberhood n = custNeighF.getSelectionModel().getSelectedItem();
		Boolean isGlu = GlutenSen.isSelected();
		Boolean isLac = LactoseSen.isSelected();
		String user = userNF.getText();
		String pass = userPF.getText();




		if(fn == null || fn.isEmpty()) {
			alerts.showAlert(AlertType.WARNING, "Add Customer To The System",
					"Please enter the customer first name.", ButtonType.OK);
		} else if(ln == null) {
			alerts.showAlert(AlertType.WARNING, "Add Customer To The System",
					"Please enter the customer last name.", ButtonType.OK);
		}else if(cud == null) {
			alerts.showAlert(AlertType.WARNING, "Add Customer To The System",
					"Please enter the customer date of birth.", ButtonType.OK);
		}else if(gn == null) {
			alerts.showAlert(AlertType.WARNING, "Add Customer To The System",
					"Please enter the customer gender.", ButtonType.OK);
		}else if(n == null) {
			alerts.showAlert(AlertType.WARNING, "Add Customer To The System",
					"Please enter the customer neighborhood.", ButtonType.OK);

		}else if(user == null) {
			alerts.showAlert(AlertType.WARNING, "Add Customer To The System",
					"Please enter the customer username.", ButtonType.OK);
		}else if(pass == null) {
			alerts.showAlert(AlertType.WARNING, "Add Customer To The System",
					"Please enter the customer password.", ButtonType.OK);

		}else {
			// add
			boolean res = Restaurant.getInstance().addCustomer(
					new Customer(fn, ln, cud, gn, n, isGlu, isLac, user , pass));
			if(res) {
				alerts.showAlert(AlertType.INFORMATION, "Add Customer To The System",
						"Added Successfully!", ButtonType.OK);


				  			clearing();
			}else {
				alerts.showAlert(AlertType.ERROR, "Add Customer To The System",
						"Failed to add the given customer!",
						ButtonType.OK);
			}
		}			
		//saving data
Restaurant.savaData();
Restaurant.loadData();
	}

	@FXML
	void clear(ActionEvent event) {
		clearing();
	}

	private void clearing() {
		custFirstF.setText("");
		custLastF.setText("");
		custDateF.setValue(LocalDate.now());
		custGenderF.getSelectionModel().clearSelection(); ;
		custNeighF.getSelectionModel().clearSelection();;
		GlutenSen.setSelected(false);
		LactoseSen.setSelected(false);
		userNF.setText("");
		userPF.setText("");



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
			Parent root = FXMLLoader.load(getClass().getResource("AddCust.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adding A Customer");
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



