package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Alerts.alerts;
import Model.Component;
import Model.Dish;
import Model.Restaurant;
import Utils.DishType;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class editDishCont implements Initializable{


public Restaurant res = Restaurant.getInstance();
	@FXML
	private Button update;
	@FXML
	private ComboBox<Dish> id;
	@FXML
	private Button load;
	@FXML 
	private Button back;
	@FXML 
	private Button reFresh;
	@FXML
	private ComboBox<DishType> type;
	@FXML
	private TextField time;
	@FXML
	private ListView<Component> comps;
	@FXML
	private ListView<Component> all;
	@FXML
	private Button add;
	@FXML
	private Button remove;
	@FXML 
	private TextField name;
	private int m;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		for(Dish d: Restaurant.getInstance().getDishes().values()) {
			id.getItems().add(d);
		}

		all.getItems().clear();
		for(Component c: Restaurant.getInstance().getComponenets().values()) {
			if( c == null) {
				alerts.showAlert(AlertType.WARNING, "EDIT DISH", "There's No Componentss In The Resturant Yet!", ButtonType.CLOSE);
			}else {
				all.getItems().add(c);				
			}
		}
		for(DishType ty : DishType.values()) {
			type.getItems().add(ty);
		}

		id.getSelectionModel().getSelectedItem();
	}


	@FXML
	void addNewComp(ActionEvent event) {

		Dish di = id.getSelectionModel().getSelectedItem();
		Component com = all.getSelectionModel().getSelectedItem();

		if(com == null) {
			alerts.showAlert(AlertType.WARNING, "EDIT DISH", "Please Select The Component You Would Like To Add To The DISH!", ButtonType.CLOSE);
		}
		else {
			boolean res = di.addComponent(com);
			if(res) {
				alerts.showAlert(AlertType.WARNING, "EDIT DISH", "This Component Added Successfully To The Dish!", ButtonType.CLOSE);
				comps.getItems().add(com);	
			}
			else {
				alerts.showAlert(AlertType.WARNING, "EDIT DISH", "Cannot Add This Component To The Dish!", ButtonType.CLOSE);
			}
		}
		Restaurant.savaData();
		Restaurant.loadData();
	}



	@FXML
	void loadItem(ActionEvent event) {
		Dish di = id.getSelectionModel().getSelectedItem();
		ArrayList<Component> co = new ArrayList<>();
		for(Component cm : di.getComponenets()) {
			co.add(cm);
		}


		if(di != null) {
			name.setText(di.getDishName());
			type.getSelectionModel().select(di.getType());
			for(Component c : co) {
				comps.getItems().add(c);
			}

			time.setText(Integer.toString((int) di.calcDishPrice()));
		}else {
			alerts.showAlert(AlertType.ERROR, "UPDATE DISH", "Please Select A Dish To Update!", ButtonType.CLOSE);
		}

	}

	@FXML
	void removeComp(ActionEvent event) {
		Component oc = comps.getSelectionModel().getSelectedItem();
		Dish di = id.getSelectionModel().getSelectedItem();

		if (oc == null) {
			alerts.showAlert(AlertType.WARNING, "EDIT DISH", "Please Select The Component You Would Like To Remove From The Dish!", ButtonType.CLOSE);
		}else {
			boolean res = di.removeComponent(oc);
			if(res) {
				alerts.showAlert(AlertType.INFORMATION, "EDIT DISH", "This Component Removed Successfully From The Dish!", ButtonType.CLOSE);
				comps.getItems().remove(oc);	 

			}else {
				alerts.showAlert(AlertType.WARNING, "EDIT DISH", "Cannot Remove This Component From The Dish!", ButtonType.CLOSE);
			}
		}
		Restaurant.savaData();
		Restaurant.loadData();
	}


	@FXML
	void updateItem(ActionEvent event) {
		Dish di = id.getSelectionModel().getSelectedItem();
		String n =  name.getText();
		DishType t =  type.getSelectionModel().getSelectedItem();
		ObservableList<Component> nc = comps.getSelectionModel().getSelectedItems();
		try {
			m = Integer.parseInt(time.getText());
		} catch (NumberFormatException e) {
			// TODO: handle exception
			alerts.showAlert(AlertType.WARNING,"EDIT DISH",
					"Please enter the Time in a number format only!.", ButtonType.OK);
		}

		ArrayList<Component> cp = new ArrayList<>();
		for(Component c : nc) {
			cp.add(c);
		}


		if(n == null || n.isEmpty()) {
			alerts.showAlert(AlertType.WARNING, "Add Dish To The System",
					"Please enter the dish name.", ButtonType.OK);
		} else if(t == null) {
			alerts.showAlert(AlertType.WARNING, "Add Dish To The System",
					"Please enter the dish type.", ButtonType.OK);
		}else if(nc == null) {
			alerts.showAlert(AlertType.WARNING, "Add Dish To The System",
					"Please enter the dish components.", ButtonType.OK);
		}else {
			// add
			boolean res = Restaurant.getInstance().addDish(
					new Dish(n, t, cp ,m));
			Restaurant.getInstance().removeDish(di);
			if(res) {
				alerts.showAlert(AlertType.INFORMATION, "Edit DISH",
						"Edit Successfully!", ButtonType.OK);
//				clearing();
			}else {
				alerts.showAlert(AlertType.ERROR, "EDIT DISH",
						"Failed to add the given dish!",
						ButtonType.OK);
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
			Parent root = FXMLLoader.load(getClass().getResource("EditDish.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Edit Dish");
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
