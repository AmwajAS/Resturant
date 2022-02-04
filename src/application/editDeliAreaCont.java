package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Alerts.alerts;
import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Model.Restaurant;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class editDeliAreaCont implements Initializable{

	public Restaurant res = Restaurant.getInstance();

	@FXML
	private Button update;
	@FXML
	private ComboBox<DeliveryArea> id;
	@FXML
	private Button load;
	@FXML 
	private Button back;
	@FXML 
	private Button reFresh;
	@FXML
	private TextField name;
	@FXML
	private ListView<Neighberhood> ngh;
	@FXML
	private ListView<Neighberhood> allngh;
	@FXML
	private ListView<DeliveryPerson> per;
	@FXML
	private ListView<DeliveryPerson> allper;
	@FXML
	private ListView<Delivery> del;
	@FXML
	private ListView<Delivery> alldel;
	@FXML 
	private Button removen;
	@FXML 
	private Button removep;
	@FXML 
	private Button removed;
	@FXML 
	private Button addn;
	@FXML 
	private Button addd;
	@FXML 
	private Button addp;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		for(DeliveryArea da: Restaurant.getInstance().getAreas().values()) {
			if(da != null) {
				id.getItems().add(da);
			}

			for(Neighberhood n: Neighberhood.values()) {
				if( n == null) {
					alerts.showAlert(AlertType.WARNING, "EDIT DELIVERY AREA", "There's No Neighborhoods In The System Yet!", ButtonType.CLOSE);
				}else {
					allngh.getItems().add(n);				
				}
			}
			allper.getItems().clear();
			for(DeliveryPerson dp: Restaurant.getInstance().getDeliveryPersons().values()) {
				if( dp == null) {
					alerts.showAlert(AlertType.WARNING, "EDIT DELIVERY AREA", "There's No Delivery Persons In The System Yet!", ButtonType.CLOSE);
				}else {
					allper.getItems().add(dp);				
				}
			}
			alldel.getItems().clear();
			for(Delivery d: Restaurant.getInstance().getDeliveries().values()) {
				if( d == null) {
					alerts.showAlert(AlertType.WARNING, "EDIT DELIVERY AREA", "There's No Deliveries In The System Yet!", ButtonType.CLOSE);
				}else {
					alldel.getItems().add(d);				
				}
			}
			id.getSelectionModel().getSelectedItem();
		}	
		name.getText();
	}
	@FXML
	void loadItem(ActionEvent event) {
		if(id == null) {
			alerts.showAlert(AlertType.ERROR, "EDIT DELIVERY AREA", "There's No Delivery Area In The System Yet!", ButtonType.CLOSE);
		}
		DeliveryArea da = id.getSelectionModel().getSelectedItem();
		if(da != null) {
			
					
			for(Neighberhood ni :  da.getNeighberhoods()) {
				ngh.getItems().add(ni);
			}

			for(DeliveryPerson dp : da.getDelPersons()) {
				per.getItems().add(dp);
			}
			for(Delivery de : da.getDelivers()) {
				del.getItems().add(de);
			}
			name.setText(da.getAreaName());
		}else {
			alerts.showAlert(AlertType.ERROR, "EDIT DELIVERY AREA", "Please Select A Area!", ButtonType.CLOSE);
		}
	}
	
	
	@FXML
	void removeNeighbohood(ActionEvent event) {
		Neighberhood n = ngh.getSelectionModel().getSelectedItem();
		DeliveryArea da = id.getSelectionModel().getSelectedItem();

		if (n == null) {
			alerts.showAlert(AlertType.WARNING, "EDIT DELIVERY AREA ", "Please Select The Neighborhoods You Would Like To Remove From The Area!", ButtonType.CLOSE);
		}else {
			boolean res = da.removeNeighberhood(n);
			if(res) {
				alerts.showAlert(AlertType.INFORMATION, "EDIT DELIVERY AREA", "This Neighborhood Removed Successfully From The Area!", ButtonType.CLOSE);
				ngh.getItems().remove(n);	 
			}else {
				alerts.showAlert(AlertType.WARNING, "EDIT DELIVERY AREA", "Cannot Remove This Neighborhood From The Area!", ButtonType.CLOSE);
			}
		}
Restaurant.savaData();
Restaurant.loadData();
}
	
	@FXML
	void removeDeliveryPerson(ActionEvent event) {
		DeliveryPerson p = per.getSelectionModel().getSelectedItem();
		DeliveryArea da = id.getSelectionModel().getSelectedItem();

		if (p == null) {
			alerts.showAlert(AlertType.WARNING, "EDIT DELIVERY AREA ", "Please Select The Delivery Person You Would Like To Remove From The Area!", ButtonType.CLOSE);
		}else {
			boolean res = da.removeDelPerson(p);
			if(res) {
				alerts.showAlert(AlertType.INFORMATION, "EDIT DELIVERY AREA", "This Delivery Person Removed Successfully From The Area!", ButtonType.CLOSE);
				per.getItems().remove(p);	 
			}else {
				alerts.showAlert(AlertType.WARNING, "EDIT DELIVERY AREA", "Cannot Remove This Delivery Person From The Area!", ButtonType.CLOSE);
			}
		}
Restaurant.savaData();
Restaurant.loadData();
	}
	
	@FXML
	void removeDelivery(ActionEvent event) {
		Delivery d = del.getSelectionModel().getSelectedItem();
		DeliveryArea da = id.getSelectionModel().getSelectedItem();

		if (d == null) {
			alerts.showAlert(AlertType.WARNING, "EDIT DELIVERY AREA ", "Please Select The Delivery You Would Like To Remove From The Area!", ButtonType.CLOSE);
		}else {
			boolean res = da.removeDelivery(d);
			if(res) {
				alerts.showAlert(AlertType.INFORMATION, "EDIT DELIVERY AREA", "This Delivery Removed Successfully From The Area!", ButtonType.CLOSE);
				del.getItems().remove(d);	 
			}else {
				alerts.showAlert(AlertType.WARNING, "EDIT DELIVERY AREA", "Cannot Remove This Delivery From The Area!", ButtonType.CLOSE);
			}
		}
		Restaurant.savaData();
		Restaurant.loadData();
	}
	
	@FXML
	void addNeighborhood(ActionEvent event) {
		DeliveryArea da = id.getSelectionModel().getSelectedItem();
        Neighberhood n = allngh.getSelectionModel().getSelectedItem();
		
		if(n == null) {
			alerts.showAlert(AlertType.WARNING, "EDIT DELIVERY AREA", "Please Select The Neighborhood You Would Like To Add To The Area!", ButtonType.CLOSE);
		}
		else {
			boolean res = da.addNeighberhood(n);
			if(res) {
				alerts.showAlert(AlertType.WARNING, "EDIT DELIVERY AREA", "This Neighborhood Added Successfully To The Area!", ButtonType.CLOSE);
				ngh.getItems().add(n);
				allngh.getItems().remove(n);
			}
			else {
				alerts.showAlert(AlertType.WARNING, "EDIT DELIVERY AREA", "Cannot Add This Neghborhood To The Area!", ButtonType.CLOSE);
			}
		}
		Restaurant.savaData();
		Restaurant.loadData();
	}
	
	@FXML
	void addDeliveryPerson(ActionEvent event) {
		DeliveryArea da = id.getSelectionModel().getSelectedItem();
       DeliveryPerson p = allper.getSelectionModel().getSelectedItem();
		
		if(p == null) {
			alerts.showAlert(AlertType.WARNING, "EDIT DELIVERY AREA", "Please Select The Delivery Person You Would Like To Add To The Area!", ButtonType.CLOSE);
		}
		else {
			boolean res = da.addDelPerson(p);
			if(res) {
				alerts.showAlert(AlertType.WARNING, "EDIT DELIVERY AREA", "This Delivery Person Added Successfully To The Area!", ButtonType.CLOSE);
				per.getItems().add(p);
				allper.getItems().remove(p);
			}
			else {
				alerts.showAlert(AlertType.WARNING, "EDIT DELIVERY AREA", "Cannot Add This Delivery Person To The Area!", ButtonType.CLOSE);
			}
		}
		Restaurant.savaData();
		Restaurant.loadData();
	}
	
	@FXML
	void addDelivery(ActionEvent event) {
		DeliveryArea da = id.getSelectionModel().getSelectedItem();
       Delivery dd = alldel.getSelectionModel().getSelectedItem();
		
		if(dd == null) {
			alerts.showAlert(AlertType.WARNING, "EDIT DELIVERY AREA", "Please Select The Delivery You Would Like To Add To The Area!", ButtonType.CLOSE);
		}
		else {
			boolean res = da.addDelivery(dd);
			if(res) {
				alerts.showAlert(AlertType.WARNING, "EDIT DELIVERY AREA", "This Delivery Added Successfully To The Area!", ButtonType.CLOSE);
				del.getItems().add(dd);
				alldel.getItems().remove(dd);
			}
			else {
				alerts.showAlert(AlertType.WARNING, "EDIT DELIVERY AREA", "Cannot Add This Delivery To The Area!", ButtonType.CLOSE);
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
				Parent root = FXMLLoader.load(getClass().getResource("EditDeliArea.fxml"));
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Edit Delivery Area");
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
