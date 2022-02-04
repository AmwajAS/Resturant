package application;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import Alerts.alerts;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class q2DeliveriesPerType implements Initializable {

	@FXML ImageView wallPaper;
	@FXML Label delPerTypeL;
	@FXML Pane perTypePane;
	@FXML Label typeL;
	@FXML Label dateL;
	@FXML 
	private DatePicker dateF;
	@FXML 
	private Button getPerType;
	@FXML 
	private HBox hMainBox;
	@FXML 
	private Button back;
	@FXML ImageView backImg;
	@FXML
	private Button reFresh;
	@FXML ImageView reFreshImg;
	@FXML
	private Label exp1;
	@FXML
	private Label reg1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		

		
	}


	@FXML
	void get(ActionEvent event) {                          //this method returns the number of each express and regular deliveries in the system.
		 HashMap<String, Integer> hm = Restaurant.getInstance().getNumberOfDeliveriesPerType();         		

		   	
		if(hm == null || hm.isEmpty()) {
			alerts.showAlert(AlertType.ERROR, "Get Number Of Deliveries Per Type",
					"No Results Found!", ButtonType.OK);
			return;
		}
		Integer exp =hm.get("Express delivery");
		Integer reg = hm.get("Regular delivery");
		exp1.setText(exp.toString());
		reg1.setText(reg.toString());
		

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
			Parent root = FXMLLoader.load(getClass().getResource("Q2NumOfDelPerType.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Q.2 Number Of Deliveries Per Type");
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
