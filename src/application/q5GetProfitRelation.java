package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;
import Alerts.alerts;
import Model.Dish;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class q5GetProfitRelation  implements Initializable{

	@FXML ImageView wallPaper;
	@FXML Label profitL;
	@FXML ScrollPane proSrcPane;
	@FXML ListView<Dish> profit;
	@FXML Button getProfit;
	@FXML HBox hMainBox;
	@FXML Button back;
	@FXML ImageView backImg;
	@FXML Button reFresh;
	@FXML ImageView reFreshImg;




	@FXML
	void get(ActionEvent event) {

		TreeSet<Dish> dis = Restaurant.getInstance().getProfitRelation();
		if(dis == null || dis.isEmpty()) {
			alerts.showAlert(AlertType.ERROR, "Get Profit Relation", 
					"No Results Found!", ButtonType.CLOSE);
			return;
		}
		profit.getItems().clear();
		for (Dish ds : dis) {
			if(ds!=null)
				profit.getItems().add(ds);
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
			Parent root = FXMLLoader.load(getClass().getResource("Q5ProfitRelation.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Q.5 Profit Relation");
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
