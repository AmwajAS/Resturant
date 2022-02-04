package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Alerts.alerts;
import Model.Cook;
import Model.Restaurant;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class removeCookCont implements Initializable {

	public Restaurant res = Restaurant.getInstance();
	
	@FXML Label cookL;
	@FXML 
	private ListView<Cook> cookList;
	@FXML Text cookFind;
	@FXML 
	private Button removeCook;
	@FXML ImageView wallPaper;
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
		cookList.getItems().clear();
		for(Cook c : Restaurant.getInstance().getCooks().values()) {
			if(c != null)
				cookList.getItems().add(c);
		}	
	}

	@FXML 
	void removeCook(ActionEvent event) {
		Cook cook = cookList.getSelectionModel().getSelectedItem();
		if(cook == null) {
			alerts.showAlert(AlertType.ERROR, "REMOVE COOK", "Please Select a cook to remove!", ButtonType.OK);
			return;
		}
		else {
			if (Restaurant.getInstance().removeCook(cook)) {

				alerts.showAlert(AlertType.INFORMATION, "REMOVE COOK", "Removed Successfully", ButtonType.FINISH);
				cookList.getItems().remove(cook);

			}
			else {
				alerts.showAlert(AlertType.ERROR, "REMOVE COOK", "Cannot Remove The Selected Cook!", ButtonType.CLOSE);
			}
		}
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
			Parent root = FXMLLoader.load(getClass().getResource("RemoveCook.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Removing A Cook");
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



