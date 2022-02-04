package application;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import Model.Dish;
import Model.Restaurant;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String SERILIZABLE_PATH = "Rest.ser";
	public static Stage primaryStage = null;
	public static final String ADMIN_USERNAME = "Manager";
	public static final String ADMIN_PASSWORD = "manager";
	public static Restaurant res;
	public static javafx.scene.media.MediaPlayer play= null;
	public static ArrayList<Dish> shop = new ArrayList<>();


	@Override
	public void start(Stage stage) throws IOException {

		primaryStage = stage;
		Parent root =FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Fatafet");
		primaryStage.show();

		stage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				try {
					stop();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}


	public static void main(String[] args)throws IOException, ClassNotFoundException {
//		res = Restaurant.getInstance();
		launch(args);
	}




}
