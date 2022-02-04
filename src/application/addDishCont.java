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

public class addDishCont implements Initializable{

	public Restaurant res = Restaurant.getInstance();

	@FXML ImageView wallPaper;
	@FXML Pane dishPane;
	@FXML Label dishILS;
	@FXML Label dishTypeL;
	@FXML Label dishNameL;
	@FXML Label dishPriceL;
	@FXML Label dish;
	@FXML Label timeL;
	@FXML 
	private TextField dishNameF;
	@FXML 
	private TextField dishPriceF;
	@FXML Label dishCompL;
	@FXML 
	private ComboBox<DishType> dishTypeF;
	@FXML 
	private ListView<Component> dishCompF;
	@FXML
	private TextField timeF;
	@FXML 
	private Button addDish;
	@FXML 
	private Button clearDish;
	@FXML 
	private HBox hMainBox;
	@FXML 
	private Button back;
	@FXML ImageView backImg;
	@FXML
	private Button reFresh;
	@FXML ImageView reFreshImg;
	@FXML Label dishMIN;
	private double p;
	private int m;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		dishNameF.getText();
		dishTypeF.getItems().clear();
		for( DishType t : DishType.values()) {
			dishTypeF.getItems().addAll(t);
		}
		dishCompF.getItems().clear();
		for (Component c : Restaurant.getInstance().getComponenets().values()) {
			if(c != null)
				dishCompF.getItems().add(c);
		}
		dishPriceF.getText();
		timeF.getText();
		dishCompF.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	@FXML
	void addDish(ActionEvent event) {

		String n = dishNameF.getText();
		DishType t = dishTypeF.getSelectionModel().getSelectedItem();
		ObservableList<Component> co = (ObservableList<Component>)dishCompF.getSelectionModel().getSelectedItems();

		try {
			p = Double.parseDouble(dishPriceF.getText());
		} catch (NumberFormatException e) {
			// TODO: handle exception
			alerts.showAlert(AlertType.WARNING,"ADD DISH",
					"Please enter the Dish price in a number format only!.", ButtonType.OK);
		}

		try {
			m = Integer.parseInt(timeF.getText());
		} catch (NumberFormatException e) {
			// TODO: handle exception
			alerts.showAlert(AlertType.WARNING,"ADD DISH",
					"Please enter the Time in a number format only!.", ButtonType.OK);
		}

		ArrayList<Component> comp = new ArrayList<>();
		try {
			for(Component c : co) 
				comp.add(c);
		}catch (Exception e) {
			// TODO: handle exception
			alerts.showAlert(AlertType.ERROR, "Add Dish To The System", "Cannot Add, There's No Components in The System yet!", ButtonType.CLOSE);
		}



		if(n == null || n.isEmpty()) {
			alerts.showAlert(AlertType.WARNING, "Add Dish To The System",
					"Please enter the dish name.", ButtonType.OK);
		} else if(t == null) {
			alerts.showAlert(AlertType.WARNING, "Add Dish To The System",
					"Please enter the dish type.", ButtonType.OK);
		}if(dishCompF == null || comp.isEmpty()) {
			alerts.showAlert(AlertType.WARNING, "Add Dish To The System",
					"Please enter the dish components.", ButtonType.OK);
		}else if(p == 0) {
			alerts.showAlert(AlertType.WARNING, "Add Dish To The System",
					"Please enter the dish price.", ButtonType.OK);
		}else if(m == 0) {
			alerts.showAlert(AlertType.WARNING, "Add Dish To The System",
					"Please enter the dish time to be ready.", ButtonType.OK);
		}else {
			// add
			boolean res = Restaurant.getInstance().addDish(
					new Dish(n, t, comp ,m));
			if(res) {
				alerts.showAlert(AlertType.INFORMATION, "Add Dish To The System",
						"Added Successfully!", ButtonType.OK);
				clearing();
			}else {
				alerts.showAlert(AlertType.ERROR, "Add Dish To The System",
						"Failed to add the given dish!",
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
		dishNameF.setText("");	
		dishTypeF.getSelectionModel().clearSelection();
		dishCompF.getSelectionModel().clearSelection();
		dishPriceF.setText("");
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
			Parent root = FXMLLoader.load(getClass().getResource("AddDish.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adding A Dish");
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
