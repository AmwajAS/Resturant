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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class removeCompCont implements Initializable {
	
	public Restaurant res = Restaurant.getInstance();

	@FXML Label compL;
	@FXML Label compIdL;
	@FXML 
	private ListView<Component> compList;
	@FXML Text help;
	@FXML
	private Button removeComp;
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
		compList.getItems().clear();
		for(Component co : Restaurant.getInstance().getComponenets().values()) {
			if(co != null)
				compList.getItems().add(co);
		}
	}

	@FXML 
	void removeComponent(ActionEvent event) {
		Component comp = compList.getSelectionModel().getSelectedItem();
		if(comp == null) {
			alerts.showAlert(AlertType.ERROR, "REMOVE COMPONENT", "Please Select a component to remove!", ButtonType.OK);
			return;
		}
		else {
			if (Restaurant.getInstance().removeComponent(comp)) {
				alerts.showAlert(AlertType.INFORMATION, "REMOVE COMPONENT", "Removed Successfully", ButtonType.FINISH);
				compList.getItems().remove(comp);

			}
			else {
				alerts.showAlert(AlertType.ERROR, "REMOVE COMPONENT", "Cannot Remove The Selected Component!", ButtonType.CLOSE);
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
			Parent root = FXMLLoader.load(getClass().getResource("RemoveComp.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Removing A Component");
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
