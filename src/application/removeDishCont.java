package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Alerts.alerts;
import Model.Dish;
import Model.Restaurant;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class removeDishCont implements Initializable {
	
	public Restaurant res = Restaurant.getInstance();

	@FXML Label diahL;
	@FXML Text help;
	@FXML 
	private ListView<Dish> dishList;
	@FXML 
	private Button removeDish;
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
		dishList.getItems().clear();
		for(Dish d : Restaurant.getInstance().getDishes().values()) {
			if(d != null)
				dishList.getItems().add(d);
		}
	}

	@FXML 
	void removeDish(ActionEvent event) {
		Dish di = dishList.getSelectionModel().getSelectedItem();
		if(di == null) {
			alerts.showAlert(AlertType.ERROR, "REMOVE DISH", "Please Select a Dish to remove!", ButtonType.OK);
			return;
		}
		else {
			if (Restaurant.getInstance().removeDish(di)) {
				alerts.showAlert(AlertType.INFORMATION, "REMOVE DISH", "Removed Successfully", ButtonType.FINISH);
				dishList.getItems().remove(di);

			}
			else {
				alerts.showAlert(AlertType.ERROR, "REMOVE DISH", "Cannot Remove The Selected Dish!", ButtonType.CLOSE);
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
			Parent root = FXMLLoader.load(getClass().getResource("RemoveDish.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Removing A Dish");
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


