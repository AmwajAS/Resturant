package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Customer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class UserCont implements Initializable{
	

	/**
	 * 
	 */
	@FXML HBox barHBOX;
	@FXML 
	private Button myProfile;
	@FXML ImageView myProfileImg;
	@FXML Tooltip myProfileHelp;
	@FXML
	private Button myShoppingCart;
	@FXML ImageView myShoppingCartImg;
	@FXML Tooltip myShoppingCartHelp;
	@FXML
	private Button myOrd;
	@FXML ImageView myOrdImg;
	@FXML Tooltip myOrdHelp;
	@FXML
	private Button resMenu;
	@FXML ImageView resMenuImg;
	@FXML Tooltip resMenuHelp;
	@FXML
	private Button help;
	@FXML ImageView helpImg;
	@FXML Tooltip helpHelp;
	@FXML
	private Button signOutB;
	@FXML ImageView signOutBImg;
	@FXML Tooltip signOutBHelp;
	@FXML Separator sep1;
	@FXML Separator sep2;
	@FXML Separator sep3;
	@FXML Separator sep4;
	@FXML Separator sep5;
	@FXML
	private Button m2;
	@FXML
	private Button m1;
	@FXML
	private Button s1;
	@FXML
	private Button signOut;
	@FXML Text custWel;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		custWel.setText(MainCont.getUser().getFirstName());
		
	}
	
	
	
	
	
	public void create(ActionEvent event) throws IOException {

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("UserCreateDish.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Create My Dish");
		primaryStage.show();
} 
	public void hBoxSignOutClicked(ActionEvent event) throws IOException {
		signOut.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {

		});
		{
			signOut.getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Fatafet");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent t) {
					Platform.exit();
					System.exit(0);
				}
			});
		}
		signOut.getScene().getWindow().hide();
	}
	
	
	public void profileClicked(ActionEvent event) throws IOException {

			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("UserProfile.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("My Account Profile");
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
		primaryStage.setTitle("Fatfet Menu");
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

	public void aboutClicked(ActionEvent event) throws IOException {

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("AboutUs.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("About Us & Help");
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);
			}
		});
}
	public void queriesClicked(ActionEvent event) throws IOException {

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("UserQueries.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Queries");
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
