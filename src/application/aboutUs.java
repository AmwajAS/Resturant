package application;
import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class aboutUs {
	
	@FXML ImageView wallPaper;
	@FXML Pane aboutPane;
	@FXML Label about1;
	@FXML Label about2;
	@FXML Label about3;
	@FXML Label about4;
	@FXML Label about5;
	@FXML Label about6;
	@FXML Label about7;
	@FXML ImageView about4Img;
	@FXML ImageView about5Img;
	@FXML HBox barHBOX;
	@FXML Button myHome;
	@FXML ImageView myHomeImg;
	@FXML Tooltip myHomeHelp;
	@FXML Button myShoppingCart;
	@FXML ImageView myShoppingCartImg;
	@FXML Tooltip myShoppingCartHelp;
	@FXML Button myOrd;
	@FXML ImageView myOrdImg;
	@FXML Tooltip myOrdHelp;
	@FXML Button resMenu;
	@FXML ImageView resMenuImg;
	@FXML Tooltip resMenuHelp;
	@FXML Separator sep2;
	@FXML Separator sep3;
	@FXML Separator sep4;
	
	public void goToShop(ActionEvent event) throws IOException {

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

	

}
