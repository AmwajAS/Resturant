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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class editCompCont implements Initializable {
	
	public Restaurant res = Restaurant.getInstance();
	
	@FXML
	private Button update;
	@FXML
	private Button load;
	@FXML
	private Button reFresh;
	@FXML
	private Button back;
	@FXML
	private ComboBox<Component> id;
	@FXML
	private CheckBox glu;
	@FXML
	private CheckBox lac;
	@FXML
	private TextField price;
	@FXML
	private TextField name;
	private double p;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		for(Component c : Restaurant.getInstance().getComponenets().values()) {
			if(c != null) {
				id.getItems().addAll(c);
			}
		}
	}
	
	@FXML
	void loadItem(ActionEvent event) {
		Component comp = id.getSelectionModel().getSelectedItem();

		if(comp != null) {

			name.setText(comp.getComponentName());
			glu.setSelected(comp.isHasGluten());
			lac.setSelected(comp.isHasLactose());
			price.setText(Integer.toString((int) comp.getPrice()));

		}else {
			alerts.showAlert(AlertType.ERROR, "UPDATE COMPONENT", "Please Select A Coomponent!", ButtonType.CLOSE);
		}
	}

	@FXML
	void updateItem(ActionEvent event) {
		String f =  name.getText();;
		Boolean gl = glu.isSelected();
        Boolean lc = lac.isSelected();
		try {
			p = Double.parseDouble(price.getText());
		} catch (NumberFormatException e) {
			// TODO: handle exception
			alerts.showAlert(AlertType.WARNING,"EDIT COMPONENT",
					"Please enter the component price in a nmber format only!.", ButtonType.OK);
		}

		Component com = id.getSelectionModel().getSelectedItem();

		
		if( name.getText() != com.getComponentName()) {
			com.setComponentName(f);

		}else if(name == null) {
			alerts.showAlert(AlertType.WARNING, "UPDATE COMPONENT", " Please enter a Component Name.", ButtonType.CLOSE);

		}
		if(glu.isSelected() != com.isHasGluten()) {
			com.setHasGluten(gl);
		}
		if(lac.isSelected() != com.isHasGluten()) {
			com.setHasLactose(lc);
		}
		if( p != com.getPrice()) {
			com.setPrice(p);
		}else if(p == 0) {
			alerts.showAlert(AlertType.WARNING, "UPDATE COMPONENT", " Please enter a Component Price.", ButtonType.CLOSE);

		}

		if( com.getComponentName() == f && com.isHasGluten() == gl && com.isHasLactose() == lc && com.getPrice() == p)
			alerts.showAlert(AlertType.INFORMATION, "UPDATE COMPONENT", "Updated Successfully!.", ButtonType.CLOSE);
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
			Parent root = FXMLLoader.load(getClass().getResource("EditComp.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Edit Component");
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
