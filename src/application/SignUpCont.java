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
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
public class SignUpCont implements Initializable{

	public Restaurant res = Restaurant.getInstance();

	@FXML Label signupL;
	@FXML Label lastNameL;
	@FXML Label firstNameL;
	@FXML Label birthL;
	@FXML Label genderL;
	@FXML Label neighL;
	@FXML Label passL;
	@FXML Label confpassL;
	@FXML Label userNameL;
	@FXML 
	private TextField firstNameF;
	@FXML 
	private TextField lastNameF;
	@FXML 
	private DatePicker birthF;
	@FXML 
	private PasswordField passF;
	@FXML 
	private PasswordField confpass;
	@FXML Tooltip passHelp;
	@FXML TextField userNameF;
	@FXML 
	private Button okB;
	@FXML 
	private ComboBox<Neighberhood> neighF;
	@FXML 
	private ComboBox<Gender> genderF;
	@FXML ImageView okImg;
	@FXML ImageView wallPaper;
	@FXML 
	private CheckBox gluten;
	@FXML 
	private CheckBox lactose;
	@FXML 
	private Button signOut;
	@FXML 
	private ProgressBar progressBar;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		LocalDate nowDate = LocalDate.now();
		firstNameF.getText();
		lastNameF.getText();
		birthF.setValue(LocalDate.now());
		if(birthF.getValue().isAfter(nowDate)) {
			alerts.showAlert(AlertType.WARNING, "Invaild Birth Date","Please enter a valid birth date!" , ButtonType.OK);
			birthF.setValue(nowDate);
		}
		genderF.getItems().clear();
		for( Gender g : Gender.values()) {
			genderF.getItems().addAll(g);
		}
		neighF.getItems().clear();
		for( Neighberhood n : Neighberhood.values()) {
			neighF.getItems().addAll(n);
		}
		userNameF.getText();
		passF.getText();
		confpass.getText();
		if(!passF.getText().equals(confpass.getText())) {
			alerts.showAlert(AlertType.WARNING, "Passwords are not similear","Please try again!" , ButtonType.OK);
		}
	}

	@FXML
	void addCustomerToSystem(ActionEvent event) {

		String f = firstNameF.getText();
		String l = lastNameF.getText();
		LocalDate d = birthF.getValue();
		Gender g = genderF.getSelectionModel().getSelectedItem(); 
		Neighberhood n = neighF.getSelectionModel().getSelectedItem();
		Boolean gl = gluten.isSelected();
		Boolean la = lactose.isSelected();
		String user = userNameF.getText();
		String pass = passF.getText();




		if(f == null || f.isEmpty()) {
			alerts.showAlert(AlertType.ERROR, "Add Customer To The System",
					"Please enter you'r first name.", ButtonType.OK);
		} else if(l == null) {
			alerts.showAlert(AlertType.ERROR, "Add Customer To The System",
					"Please enter you'r last name.", ButtonType.OK);
		}else if(d == null) {
			alerts.showAlert(AlertType.ERROR, "Add Customer To The System",
					"Please enter you'r birth date.", ButtonType.OK);
		}else if(g == null) {
			alerts.showAlert(AlertType.ERROR, "Add Customer To The System",
					"Please enter you'r gender.", ButtonType.OK);
		}else if(n == null) {
			alerts.showAlert(AlertType.ERROR, "Add Customer To The System",
					"Please enter you'r neighborhood.", ButtonType.OK);
		}else if(user == null) {
			alerts.showAlert(AlertType.ERROR, "Add Customer To The System",
					"Please enter you'r username.", ButtonType.OK);
		}else if(pass == null) {
			alerts.showAlert(AlertType.ERROR, "Add Customer To The System",
					"Please enter you'r password.", ButtonType.OK);
		}else if(!passF.getText().equals(confpass.getText())) {
				alerts.showAlert(AlertType.WARNING, "Sign Up","Passwords are not The Same" , ButtonType.OK);
		}else {
			// add
			Customer cust = new Customer(f, l, d, g, n, la, gl, user, pass);
			boolean res = Restaurant.getInstance().addCustomer(cust);
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
		Restaurant.savaData();
		Restaurant.loadData();


	}
	@FXML
	void clear(ActionEvent event) {
		clearing();
	}


	private void clearing() {
		firstNameF.setText("");
		lastNameF.setText("");
		birthF.setValue(LocalDate.now());
		genderF.getSelectionModel().select(0);
		neighF.getSelectionModel().select(0);
		gluten.setSelected(false);
		lactose.setSelected(false);
		userNameF.setText("");
		passF.setText("");
		confpass.setText("");

	}


	@FXML
	void PasswordStrenght(KeyEvent Event) {

		passF.setOnKeyReleased((KeyEvent e) -> {

			double passLength = passF.getText().length();
			String str = passF.getText();
			progressBar.setProgress(passLength/10);


			int UperCase = 0;
			char[] ps = passF.getText().toCharArray();
			for(int i = 0 ; i < passLength ; i++ ) {
				if(Character.isUpperCase(ps[i])) {
					UperCase = 1;
				}
			}

			if  (passLength < 3 && UperCase == 1)
				progressBar.setStyle("-fx-accent: red; -fx-text-box-border: transparent;");
			else {
				progressBar.setStyle("-fx-accent: red; -fx-text-box-border: transparent;");

			}
			if(passLength >= 3  && UperCase == 1)
				progressBar.setStyle("-fx-accent: yellow; -fx-text-box-border: transparent;");
			else {
				progressBar.setStyle("-fx-accent: yellow; -fx-text-box-border: transparent;");	
			}

			if(passLength >= 8 && UperCase == 1)
				progressBar.setStyle("-fx-accent: green; -fx-text-box-border: transparent;");

		});

	}




	public void SignOutClicked(ActionEvent event) throws IOException {
		signOut.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {


		});
		{
			signOut.getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("AMWAJ'S KITCHEN");
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
