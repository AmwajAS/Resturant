package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import Alerts.alerts;
import Model.Cook;
import Model.Restaurant;
import Utils.Expertise;
import Utils.Gender;
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


public class addCookCont implements Initializable{

	public Restaurant res = Restaurant.getInstance();

	@FXML ImageView wallPaper;
	@FXML Pane cookPane;
	@FXML Label cook;
	@FXML Label cookGenderL;
	@FXML Label cookExpL;
	@FXML Label cookFirstL;
	@FXML Label cookLastL;
	@FXML Label cookDateL;
	@FXML Label cookhelp;
	@FXML
	private TextField cookFirstF;
	@FXML 
	private TextField cookLastF;
	@FXML 
	private DatePicker cookDateF;
	@FXML 
	private ComboBox<Gender> cookGenderF;
	@FXML 
	private CheckBox isCook;
	@FXML 
	private ComboBox<Expertise> cookExpF;
	@FXML 
	private Button addCook;
	@FXML 
	private Button clearCook;
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
		cookFirstF.getText();
		cookLastL.getText();
		cookDateF.setValue(LocalDate.now());
		if(cookDateF.getValue().isAfter(nowDate)) {
			alerts.showAlert(AlertType.WARNING, "Invaild Birth Date","Please enter a valid cook birth date!" , ButtonType.OK);
			cookDateF.setValue(nowDate);
		}
		cookExpF.getItems().clear();
		for( Expertise exp : Expertise.values()) {
			cookExpF.getItems().addAll(exp);
		}
		cookGenderF.getItems().clear();
		for( Gender g : Gender.values()) {
			cookGenderF.getItems().addAll(g);
		}
	}

	@FXML
	void addCook(ActionEvent event)  {      //This methods adds a cook to the system in condition the fields are not null.

		LocalDate nowDate = null;
		String cfn = null;
		String cln = null;
		LocalDate cd = null;
		Boolean isC = null;
		Gender cg = null;
		Expertise ex = null;

		try {
			nowDate = LocalDate.now();
			cfn = cookFirstF.getText();
			cln = cookLastF.getText();
			cd = cookDateF.getValue();
			if( cd.isAfter(nowDate)) {
				alerts.showAlert(AlertType.WARNING, "Invaild Birth Date","Please enter a valid cook birth date!" , ButtonType.OK);
				cookDateF.setValue(nowDate);
			}
			isC = isCook.isSelected();
			cg = cookGenderF.getSelectionModel().getSelectedItem();
			ex = cookExpF.getSelectionModel().getSelectedItem();

		}catch (NullPointerException e) {
			// TODO: handle exception
			alerts.showAlert(AlertType.ERROR, "Add Cook To The System", "Please Fill in The Foloowing Feilds", ButtonType.CLOSE);
		}

		if(cfn == null || cfn.isEmpty()) {
			alerts.showAlert(AlertType.WARNING, "ADD COOK To The System",
					"Please enter the cook first name.", ButtonType.OK);
		} else if(cln == null) {
			alerts.showAlert(AlertType.WARNING, "ADD COOK To The System",
					"Please enter the cook last name.", ButtonType.OK);
		}else if(cd == null) {
			alerts.showAlert(AlertType.WARNING, "ADD COOK To The System",
					"Please enter the cook date of birth.", ButtonType.OK);
		}else if(cg == null) {
			alerts.showAlert(AlertType.WARNING, "ADD COOK To The System",
					"Please enter the cook gender.", ButtonType.OK);
		}else if(ex == null) {
			alerts.showAlert(AlertType.WARNING, "ADD COOK To The System",
					"Please enter the cook expertise.", ButtonType.OK);

		}else {
			// add
			boolean res = Restaurant.getInstance().addCook(
					new Cook(cfn, cln, cd, cg, ex, isC));
			if(res) {
				alerts.showAlert(AlertType.INFORMATION, "ADD COOK To The System",
						"Added Successfully!", ButtonType.OK);
							clearing();
			}else {
				alerts.showAlert(AlertType.ERROR, "ADD COOK To The System",
						"Failed to add the given cook!",
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
		cookFirstF.setText("");
		cookLastF.setText("");
		cookDateF.setValue(LocalDate.now());
		cookExpF.getSelectionModel().clearSelection();;
		isCook.setSelected(false);
		cookGenderF.getSelectionModel().clearSelection();;


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

	public void actionRefresh(ActionEvent event) throws IOException {
		reFresh.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {

		});
		{
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("AddCook.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adding A Cook");
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
