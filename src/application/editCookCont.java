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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class editCookCont  implements Initializable{

	public Restaurant res = Restaurant.getInstance();

	@FXML 
	private TextField cookEdFirstF;

	@FXML
	private Button update;

	@FXML
	private Button load;
	@FXML
	private Button reFresh;

	@FXML
	private Button back;

	@FXML
	private ComboBox<Cook> id;

	@FXML 
	private TextField last;

	@FXML
	private DatePicker cookEdDateF;

	@FXML
	private ComboBox<Expertise> exp;

	@FXML 
	private ComboBox<Gender> gen;

	@FXML
	private CheckBox isCookEd;
	@FXML
	private TextField newp;





	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		for(Cook c : Restaurant.getInstance().getCooks().values()) {
			if(c != null) {
				id.getItems().add(c);
			}
		}
		id.getSelectionModel().getSelectedItem();

		gen.getItems().clear();
		for(Gender gender : Gender.values()) {
			gen.getItems().add(gender);
		}
		exp.getItems().clear();
		for(Expertise e : Expertise.values()){
			exp.getItems().add(e);
		}
	}

	@FXML
	void loadItem(ActionEvent event) {
		Cook cook = id.getSelectionModel().getSelectedItem();
		String la = cook.getLastName();


		if(cook != null) {

			cookEdFirstF.setText(cook.getFirstName());
			gen.getSelectionModel().select(cook.getGender());
			exp.getSelectionModel().select(cook.getExpert());
			isCookEd.setSelected(cook.isChef());
			cookEdDateF.setValue(cook.getBirthDay());
			last.setText(la);
			newp.setText(la);
		}else {
			alerts.showAlert(AlertType.ERROR, "UPDATE COOK", "Please Select A Cook!", ButtonType.CLOSE);
		}
	}

	@FXML
	void updateItem(ActionEvent event) {
		String f =  cookEdFirstF.getText();
		String l =  last.getText();
		LocalDate d = cookEdDateF.getValue();
		Gender g = gen.getSelectionModel().getSelectedItem();
		Expertise e = exp.getSelectionModel().getSelectedItem();
		Boolean i = isCookEd.isSelected();
		Cook cook = id.getSelectionModel().getSelectedItem();



		if( cookEdFirstF.getText() != cook.getFirstName()) {
			cook.setFirstName(f);

		}else if(cookEdFirstF == null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE COOK", " Please enter a Cook First Name.", ButtonType.CLOSE);

		}
		if(last.getText() != cook.getLastName()) { 
			cook.setLastName(l);

		}else if(last == null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE COOK", " Please enter a Cook Last Name.", ButtonType.CLOSE);
		}
		if(cookEdDateF.getValue() != cook.getBirthDay()) {
			cook.setBirthDay(d);
		}else if(cookEdDateF == null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE COOK", " Please enter a Cook Birth Day.", ButtonType.CLOSE);

		}
		if(gen.getSelectionModel().getSelectedItem() != cook.getGender()) {
			cook.setGender(g);

		}else if(gen == null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE COOK", " Please enter a Cook Gender.", ButtonType.CLOSE);
		}
		if(exp.getSelectionModel().getSelectedItem() != cook.getExpert()) {
			cook.setExpert(e);
		}else if(cookEdFirstF == null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE COOK", " Please enter a Cook Expert.", ButtonType.CLOSE);
		}
		if(isCookEd.isSelected() != cook.isChef()) {
			cook.setChef(i);
		}

		if( cook.getFirstName() == f && cook.getLastName() == l && cook.getBirthDay() == d && cook.getGender() == g && cook.getExpert() == e && cook.isChef() == i)
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
			Parent root = FXMLLoader.load(getClass().getResource("EditCook.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Edit Cook");
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






