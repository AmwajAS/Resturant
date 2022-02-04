package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;
import Alerts.alerts;
import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Model.Order;
import Model.Restaurant;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class q6CreateAlMacine implements Initializable {

	@FXML Label deliL;
	@FXML 
	private HBox hMainBox;
	@FXML 
	private Button back;
	@FXML ImageView backImg;
	@FXML 
	private Button reFresh;
	@FXML ImageView reFreshImg;
	@FXML 
	private Button get;
	@FXML 
	private ComboBox<DeliveryPerson> delPerF;
	@FXML 
	private ComboBox<DeliveryArea> delAreaF;
	@FXML 
	private ListView<Order> allOrd;
	@FXML 
	private ListView<Delivery> res;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		delPerF.getItems().clear();
		for(DeliveryPerson dp : Restaurant.getInstance().getDeliveryPersons().values()) {
			delPerF.getItems().add(dp);
		}

		for(DeliveryArea da : Restaurant.getInstance().getAreas().values()) {
			delAreaF.getItems().add(da);
		}
		//		allOrd.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		for(Order or : Restaurant.getInstance().getOrders().values()) {
			allOrd.getItems().add(or);
		}	
	}


	@FXML void getRes(ActionEvent event) {
		DeliveryPerson dep = delPerF.getSelectionModel().getSelectedItem();
		DeliveryArea dar = delAreaF.getSelectionModel().getSelectedItem();
		ObservableList<Order> ords = allOrd.getSelectionModel().getSelectedItems();


		TreeSet<Order> o = new TreeSet<>(ords);

		if(dep == null) {
			alerts.showAlert(AlertType.WARNING, "CREATE AL MACINE", "Please Select A Delivery Person", ButtonType.CLOSE);
		}
		if(dar == null) {
			alerts.showAlert(AlertType.WARNING, "CREATE AL MACINE", "Please Select A Delivery Area", ButtonType.CLOSE);
		}
		if(allOrd == null) {
			alerts.showAlert(AlertType.WARNING, "CREATE AL MACINE", "There's No Orders In The System Yet!", ButtonType.CLOSE);

		}
		else {
			TreeSet<Delivery> rest = Restaurant.getInstance().createAIMacine(dep, dar, o);

			for(Delivery d : rest) {
				res.getItems().add(d);
			}
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
			Parent root = FXMLLoader.load(getClass().getResource("Q6AlMacine.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Q.6 Al Macine");
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
