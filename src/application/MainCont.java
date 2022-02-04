package application;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Alerts.alerts;
import Model.Customer;
import Model.Restaurant;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class MainCont implements Initializable {


	@FXML AnchorPane mainpage;
	@FXML Label textLabel;
	@FXML Button clickButton;
	@FXML ImageView homeimg;
	@FXML PasswordField pass;
	@FXML TextField userid;
	@FXML ImageView inimg;
	@FXML Tooltip passtool;
	@FXML Tooltip usertool;
	@FXML Label joinus;
	@FXML Button signup;
	@FXML MediaView mediaV;
	@FXML Button vol;
	@FXML ImageView volimg;
	@FXML Slider volumeSlider;

 
	private static Customer user;

    public static Customer getUser() {
		return user;
	}

	public static void setUser(Customer user) {
		MainCont.user = user;
	}

	@FXML
    void login(ActionEvent event) throws IOException {
    	String uname = userid.getText().toString();
    	String passw = pass.getText().toString();
    	if(uname == null || uname.isEmpty())
    		alerts.showAlert(AlertType.ERROR, "Login ", 
    				"The Username is Empty, Please Enter your username!", ButtonType.CANCEL);
    	else if(passw == null || passw.isEmpty())
    		alerts.showAlert(AlertType.ERROR, "Login Scene", 
    				"The Password is Empty, Please Enter your username!", ButtonType.CANCEL);
    	else if(isAdmin(uname,passw)) {
    		Main.play.setVolume(0);
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Resturant");
			primaryStage.show();	
    	}
    	
    	else if(isUser(uname,passw)) {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("UserMain.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Resturant");
			primaryStage.show();
			Main.play.setVolume(0);
    	}else
    		alerts.showAlert(AlertType.ERROR, "Login ", 
    				"Invalid Values - No user account found, Please Enter valid username & password!", ButtonType.CANCEL);
    }

	private boolean isAdmin(String uname, String pass) {
		if(Main.ADMIN_USERNAME.equals(uname) && Main.ADMIN_PASSWORD.equals(pass))
			return true;
		return false;
	}
	
	
	private boolean isUser(String uname, String pass) {
		for (Customer c : Restaurant.getInstance().getCustomers().values()) {
			if( c!= null) {
				if(uname.equals(c.getUserName()) && pass.equals(c.getUserPassword())  )  {
					this.user = c;
					return true;
				}
			}
		}
		return false;
	
	}


	@FXML
	void actionOnSignUpButton(ActionEvent event) throws IOException {
		DropShadow shadow = new DropShadow(30, Color.ORANGE);
		signup.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
			signup.setEffect(shadow);
			
			
		});
		

		Stage primaryStage = new Stage();
		Parent root2 = FXMLLoader.load(getClass().getResource("NewUser.fxml"));
		Scene scene2 = new Scene(root2);
		primaryStage.setScene(scene2);
		primaryStage.setTitle("Resturant");
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent t) {
		        Platform.exit();
		        System.exit(0);
		    }
		});
		Main.play.setVolume(0);


	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Media media= new Media(getClass().getResource("/Images/vedioRestaurant.mp4").toString());
		Main.play = new MediaPlayer(media);
		mediaV.setMediaPlayer(Main.play);
		Main.play.setCycleCount(MediaPlayer.INDEFINITE);
		Main.play.play();
		Main.play.setAutoPlay(true);
		volumeSlider.setValue(Main.play.getVolume() * 100 );
		volumeSlider.valueProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable arg0) {
				// TODO Auto-generated method stub
				Main.play.setVolume(volumeSlider.getValue() / 100);
			}
		});	
	} 
}


