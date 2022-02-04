package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Alerts.alerts;
import Model.Component;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class addCompCont implements Initializable{
	
	
	
	public Restaurant res = Restaurant.getInstance();

	@FXML ImageView compWall;
	@FXML Label comp;
	@FXML Pane compPane;
	@FXML Label compNameL;
	@FXML Label compPriceL;
	@FXML
	private CheckBox Gluten;
	@FXML
	private CheckBox Lactose;
	@FXML 
	private TextField compPriceF;
	@FXML
	private TextField compNameF;
	@FXML
	private Button addComp;
	@FXML
	private Button clearComp;
	@FXML 
	private HBox hMainBox;
	@FXML 
	private Button back;
	@FXML ImageView backImg;
	@FXML 
	private Button reFresh;
	@FXML ImageView reFreshImg;
	private double p;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		compNameF.getText();
		compPriceF.getText();
		
	}



	@FXML
	void addComponent(ActionEvent event) {    //This methods adds a component to the system in condition the fields are not null.

		String n = compNameF.getText();

		try {
			p = Double.parseDouble(compPriceF.getText());
		} catch (NumberFormatException e) {
			// TODO: handle exception
			alerts.showAlert(AlertType.WARNING,"ADD COMPONENT",
					"Please enter the component price in a nmber format only!.", ButtonType.OK);
		}

		Boolean g = Gluten.isSelected();
		Boolean l = Lactose.isSelected();


		if(n == null ) {
			alerts.showAlert(AlertType.WARNING, "ADD COMPONENT",
					"Please enter the component name.", ButtonType.OK);
		} else if(p == 0) {
			alerts.showAlert(AlertType.WARNING,"ADD COMPONENT",
					"Please enter the component price.", ButtonType.OK);
		}else {
			// add
			boolean res = Restaurant.getInstance().addComponent(
					new Component(n, l, g, p));

			if(res) {
				alerts.showAlert(AlertType.INFORMATION, "ADDCOMPONENT",
						"Added Successfully!", ButtonType.OK);
				clearing();
			}else {
				alerts.showAlert(AlertType.ERROR, "ADD COMPONENT",
						"Failed to add the given component!",
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
		compNameF.setText("");
		compPriceF.setText("");
		Gluten.setSelected(false);
		Lactose.setSelected(false);
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
			Parent root = FXMLLoader.load(getClass().getResource("AddComp.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adding A Component");
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
