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
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class userProfileCont  implements Initializable{

	@FXML ImageView wallPaper;
	@FXML Pane pane1;
	@FXML Pane pane2;
	@FXML Label firstL;
	@FXML Label lastL;
	@FXML Label dateL;
	@FXML Label genL;
	@FXML Label neighbL;
	@FXML Label passwL;
	@FXML Label userNL;

	@FXML 
	private CheckBox glu;
	@FXML
	private CheckBox lac;
	@FXML 
	private TextField firstF;
	@FXML
	private TextField lastF;
	@FXML 
	private TextField userNF;
	@FXML
	private TextField passwF;
	@FXML 
	private DatePicker dateF;
	@FXML
	private ComboBox<Neighberhood> neighbF;
	@FXML
	private ComboBox<Gender> gend;
	@FXML
	private Button submit;
	@FXML Label myPro;
	@FXML
	private Button ask;
	@FXML ImageView askImg;
	@FXML
	private HBox barHBOX;
	@FXML Button myHome;
	@FXML ImageView myHomeImg;
	@FXML Tooltip myHomeHelp;
	@FXML 
	private Button myShoppingCart;
	@FXML ImageView myShoppingCartImg;
	@FXML Tooltip myShoppingCartHelp;
	@FXML 
	private Button myOrd;
	@FXML ImageView myOrdImg;
	@FXML Tooltip myOrdHelp;
	@FXML
	private  Button resMenu;
	@FXML ImageView resMenuImg;
	@FXML Tooltip resMenuHelp;
	@FXML Separator sep2;
	@FXML Separator sep3;
	@FXML Separator sep4;
	@FXML
	private TextField old;
	@FXML
	private TextField newp;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private Text text;
	@FXML
	private Button ask1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub


		firstF.setText(MainCont.getUser().getFirstName());
		lastF.setText(MainCont.getUser().getLastName());
		dateF.setValue(MainCont.getUser().getBirthDay());
		for(Gender g : Gender.values()) {
			gend.getItems().add(g);
		}
		gend.getSelectionModel().select(MainCont.getUser().getGender());
		for(Neighberhood n : Neighberhood.values()) {
			neighbF.getItems().add(n);
		}
		neighbF.getSelectionModel().select(MainCont.getUser().getNeighberhood());
		glu.setSelected(MainCont.getUser().isSensitiveToGluten());
		lac.setSelected(MainCont.getUser().isSensitiveToLactose());

		text.setVisible(false);
		ask1.setVisible(false);

	}


	@FXML
	void update(ActionEvent event) {                      //////////this methods updating the user data .
		Customer customer= MainCont.getUser();
		String oldP =customer.getUserPassword();
		LocalDate da = LocalDate.now();

		String f =  null;
		String l = null;
		LocalDate d = null;
		Gender g = null;
		Neighberhood n = null;
		Boolean gl = null;
		Boolean lc = null;
		String p1 = null;
		String p2 = null;     

		try {
			f =  firstF.getText();
			l =  lastF.getText();
			d = dateF.getValue();
			g = gend.getSelectionModel().getSelectedItem();
			n = neighbF.getSelectionModel().getSelectedItem();
			gl = glu.isSelected();
			lc = lac.isSelected();

		} catch (NullPointerException e) {
			// TODO: handle exception
			alerts.showAlert(AlertType.WARNING, "Profile", "Please Fill in the Fields.", ButtonType.CLOSE);
		}
		p1 = old.getText();
		p2 = newp.getText();


		if(firstF.getText() != customer.getFirstName()) {
			customer.setFirstName(f);

		}else if(firstF == null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE CUSTOMER", " Please enter a First Name.", ButtonType.CLOSE);

		}
		if(lastF.getText() != customer.getLastName()) { 
			customer.setLastName(l);

		}else if(lastF == null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE CUSTOMER", " Please enter a Last Name.", ButtonType.CLOSE);
		}
		if(dateF.getValue() != customer.getBirthDay()) {
			customer.setBirthDay(d);
		}else if(dateF == null ||dateF.getValue().isAfter(da)) {
			alerts.showAlert(AlertType.WARNING, "UPDATE CUSTOMER", " Please enter a  Valid Birth Day.", ButtonType.CLOSE);

		}
		if(gend.getSelectionModel().getSelectedItem() != customer.getGender()) {
			customer.setGender(g);

		}else if(gend == null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE CUSTOMER", " Please enter a Gender.", ButtonType.CLOSE);
		}
		if(neighbF.getSelectionModel().getSelectedItem() != customer.getNeighberhood()) {
			customer.setNeighberhood(n);
		}else if(neighbF == null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE CUSTOMER", " Please Select a Neighborhood.", ButtonType.CLOSE);
		}
		if(glu.isSelected() != customer.isSensitiveToGluten()) {
			customer.setSensitiveToGluten(gl);
		}
		if(lac.isSelected() != customer.isSensitiveToLactose()) {
			customer.setSensitiveToLactose(lc);
		}
		if(old.getText() == null && newp.getText() == null ) {          //if the user is not interested to change his password, the old password will stay his password.
			customer.setUserPassword(oldP);
			if( customer.getFirstName() == f && customer.getLastName() == l && customer.getBirthDay() == d && customer.getGender() == g && customer.getNeighberhood() == n && customer.isSensitiveToGluten() == gl && customer.isSensitiveToLactose() == lc && oldP == customer.getUserPassword() )
				alerts.showAlert(AlertType.INFORMATION, "UPDATE CUSTOMER", "Updated Successfully!.", ButtonType.CLOSE);

		}else if( old.getText() != null && newp != null){                // if the user is interested to change his password.

			if(old.getText() != newp.getText() && p2 != customer.getUserPassword()) {
				customer.setUserPassword(p2);
			}
			if( customer.getFirstName() == f && customer.getLastName() == l && customer.getBirthDay() == d && customer.getGender() == g && customer.getNeighberhood() == n && customer.isSensitiveToGluten() == gl && customer.isSensitiveToLactose() == lc && p2 == customer.getUserPassword() )
				alerts.showAlert(AlertType.INFORMATION, "UPDATE CUSTOMER", "Updated Successfully!.", ButtonType.CLOSE);
		}
		Restaurant.savaData();
		Restaurant.loadData();

	}

	@FXML
	void PasswordStrenght(KeyEvent Event) {

		newp.setOnKeyReleased((KeyEvent e) -> {

			double passLength =newp.getText().length();
			String str = newp.getText();
			progressBar.setProgress(passLength/10);


			int UperCase = 0;
			char[] ps = newp.getText().toCharArray();
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
	@FXML
	void onText(ActionEvent event) {


		text.setVisible(true);	
		ask1.setVisible(true);
		ask.setVisible(true);

	}
	@FXML
	void onText2(ActionEvent event) {
		text.setVisible(false);
		ask.setVisible(true);
		ask1.setVisible(false);
	}


	public void homeClicked(ActionEvent event) throws IOException {

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("UserMain.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Welcome to AMWAJ'S KITCHEN");
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);

			}
		});
	}


	public void ShopClicked(ActionEvent event) throws IOException {

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("UserShoppingCart.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("My Shopping Cart");
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);

			}
		});
	}

	public void menuClicked(ActionEvent event) throws IOException {

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("UserMenu.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("AMWAJ'S KITCHEN Menu");
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);

			}
		});
	}

	public void OrderClicked(ActionEvent event) throws IOException {

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("UserOrders.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("My Previos Orders");
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
