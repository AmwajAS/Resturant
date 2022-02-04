package application;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class q4GetPopularComponent implements Initializable{

	@FXML ImageView wallPaper;
	@FXML Label popL;
	@FXML Button getComp;
	@FXML 
	private ListView<Component> popComp;
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
		popComp.getItems().clear();
		for (Component c : Restaurant.getInstance().getComponenets().values()) {
			if(c != null)
				popComp.getItems().add(c);
		}
	}


	@FXML
	void get(ActionEvent event) {

		LinkedList<Component> comp = Restaurant.getInstance().getPopularComponents();
		if(comp == null || comp.isEmpty()) {
			alerts.showAlert(AlertType.ERROR, "Get Popular Components", 
					"No Results Found!", ButtonType.CLOSE);
			return;
		}
		popComp.getItems().clear();
		for (Component co : comp) {
			if(co!=null)
				popComp.getItems().add(co);
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
			Parent root = FXMLLoader.load(getClass().getResource("Q4GetPopularComp.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Q.4 Popular Component");
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
