package application;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

import Alerts.alerts;
import Model.DeliveryArea;
import Model.Restaurant;
import Utils.Neighberhood;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class addDeliAreaCont implements Initializable {

	public Restaurant res = Restaurant.getInstance();

	@FXML ImageView wallPaper;
	@FXML Pane delAreaPane;
	@FXML Label areaNameL;
	@FXML Label deliveryAreaL;
	@FXML Label neighL;
	@FXML Label deliPerL;
	@FXML Label deliL;
	@FXML Label timeL;
	@FXML 
	private TextField areaNameF;
	@FXML 
	private TextField timeF;
	@FXML 
	private Button addDelArea;
	@FXML 
	private Button clearDelArea;
	@FXML 
	private HBox hMainBox;
	@FXML 
	private Button back;
	@FXML ImageView backImg;
	@FXML 
	private Button reFresh;
	@FXML ImageView reFreshImg;
	@FXML 
	private ListView<Neighberhood> neigh;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		areaNameF.getText();
		neigh.getItems().clear();
		for( Neighberhood n : Neighberhood.values()) {
			if(n != null)
				neigh.getItems().addAll(n);
		}
		timeF.getText();
		neigh.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	// adding the delivery area to the system in condition that the fields are not null;
	@FXML
	void addDeliveryArea(ActionEvent event) {
		ObservableList<Neighberhood> ngh = neigh.getSelectionModel().getSelectedItems();
		HashSet<Neighberhood> nn =new HashSet<>();
		String name = null;
		int t = 0;




		try {
			name = areaNameF.getText();
			for(Neighberhood n : ngh) {
				nn.add(n);
			}

		} catch (NullPointerException e) {
			// TODO: handle exception
			alerts.showAlert(AlertType.ERROR, "ADD DELIVERY AREA To The System", "Please Fill in The Following Feilds", ButtonType.CLOSE);
		}
		try {

			t = Integer.parseInt(timeF.getText());
		} catch (NumberFormatException e) {
			// TODO: handle exception
			alerts.showAlert(AlertType.WARNING, "ADD DELIVERY AREA To The System ", "Please Enter The Time in Integer Numbers Only!", ButtonType.CLOSE);
		}

		if(name == null || name.isEmpty()) {
			alerts.showAlert(AlertType.WARNING, "ADD DELIVERY AREA To The System",
					"Please enter a Delivery Area Name.", ButtonType.OK);
		} else if(ngh == null) {
			alerts.showAlert(AlertType.WARNING, "ADD DELIVERY AREA To The System",
					"Please enter a the Neighborhood in the Delivery Area.", ButtonType.OK);
		}else {
			// add
			boolean res = Restaurant.getInstance().addDeliveryArea(
					new DeliveryArea(name, nn, t));
			if(res) {
				alerts.showAlert(AlertType.INFORMATION, "ADD DELIVERY Area To The System",
						"Added Successfully!", ButtonType.OK);


				clearing();
			}else {
				alerts.showAlert(AlertType.ERROR, "ADD DELIVERY Area To The System",
						"Failed to add the given Delivery Area!",
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

		areaNameF.setText("");;
		neigh.getSelectionModel().clearSelection();
		timeF.setText("");

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
			Parent root = FXMLLoader.load(getClass().getResource("AddDeliArea.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adding A Delivery Area");
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
