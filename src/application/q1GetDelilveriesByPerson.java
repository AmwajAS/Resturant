package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.TreeSet;
import Alerts.alerts;
import Model.Delivery;
import Model.DeliveryPerson;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class q1GetDelilveriesByPerson implements Initializable{


	@FXML ImageView wallPaper;
	@FXML Label delByPerL;
	@FXML Pane q1Pane;
	@FXML
	private DatePicker monthF;
	@FXML Label monthL;
	@FXML 
	private ComboBox<DeliveryPerson> delPerF;
	@FXML Label delPerL;
	@FXML 
	private Button get;
	@FXML 
	private ListView<Delivery> byDelPer;
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
		
		for (DeliveryPerson d : Restaurant.getInstance().getDeliveryPersons().values()) {
			if(d != null)
				delPerF.getItems().add(d);
		}
		
	}



	@FXML
	void get(ActionEvent event) {                      //this methods adds to the list the results of the get delivery by person q.
		DeliveryPerson dp = delPerF.getSelectionModel().getSelectedItem();
		LocalDate date = monthF.getValue();
		int date2 = date.getMonthValue();
		if(dp != null) {
			TreeSet<Delivery> res = Restaurant.getInstance().getDeliveriesByPerson(dp, date2);
			if(res == null || res.isEmpty()) {
				alerts.showAlert(AlertType.ERROR, "Get Deliveries By Delivery Person", 
						"No Results Found!", ButtonType.CLOSE);
				return;
			}
			byDelPer.getItems().clear();
			for (Delivery deli : res) {
				if(deli!=null)
					byDelPer.getItems().add(deli);
			}
		}else {
			alerts.showAlert(AlertType.ERROR, "Get Deliveries By Delivery Person", 
					"You need to select a delivery person and date before click on get button!", ButtonType.CLOSE);
		}
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
			Parent root = FXMLLoader.load(getClass().getResource("Q1GetDelByPer.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Q.1 Deliveries By Person");
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
