package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Alerts.alerts;
import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Model.ExpressDelivery;
import Model.Order;
import Model.Restaurant;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class editDeliveryCont implements Initializable{


	
	@FXML
    private Button Exit;
    @FXML
    private ChoiceBox<Delivery> deliveryField;
    @FXML
    private ChoiceBox<DeliveryPerson> deliveryPersonField;
    @FXML
    private ChoiceBox<DeliveryArea> delAreaField;
    @FXML
    private TextField postageField;
    @FXML
    private DatePicker delDate;
    @FXML
    private ListView<Order> deliveryOrders;
    @FXML
    private ChoiceBox<Order> ordersField;
    @FXML
    private CheckBox isDelivered;
	@FXML
	private Button reFresh;
	@FXML
	private Button back;
    	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
 
    	deliveryField.getItems().clear();
    	for (Delivery d : Restaurant.getInstance().getDeliveries().values()) {
    		if (d != null)
    			deliveryField.getItems().add(d);
    	}
    	
    	deliveryPersonField.getItems().clear();
	    	for (DeliveryPerson d : Restaurant.getInstance().getDeliveryPersons().values()) {
	    		if (d != null)
	    			deliveryPersonField.getItems().add(d);
	    	}
	    	
	    delAreaField.getItems().clear();
	    for (DeliveryArea d : Restaurant.getInstance().getAreas().values()) {
	    	if (d != null)
	    		delAreaField.getItems().add(d);
	    }
	    
	    ordersField.getItems().clear();
	    for (Order o : Restaurant.getInstance().getOrders().values()) {
	    	if (o != null)
	    		ordersField.getItems().add(o);
	    }
    }
    @FXML
	void load(ActionEvent event) {	
    	Delivery d = deliveryField.getSelectionModel().getSelectedItem();
	    if (d != null) {
	    	if (d instanceof Model.RegularDelivery) {
	    		deliveryOrders.getItems().clear();
	    	for (Order o : ((Model.RegularDelivery) d).getOrders()) {
	    		if (o != null)
	    			deliveryOrders.getItems().add(o);
	    		}
	    	} else {
	    		deliveryOrders.getItems().clear();
	    		Double postage =  ((ExpressDelivery) d).getPostage();
		    	postageField.setText(String.valueOf(postage));
	    	}
	    	deliveryPersonField.getSelectionModel().select(d.getDeliveryPerson());
	    	delAreaField.getSelectionModel().select(d.getArea());
	    	delDate.setValue(d.getDeliveredDate());
	    	isDelivered.setSelected(d.isDelivered());
	    }
    }
    
    public void initSection() {	
    	ordersField.getItems().clear();
	    for (Order o : Restaurant.getInstance().getOrders().values()) {
	    	if (o != null)
	    		ordersField.getItems().add(o);
	    }
	    	
	    Delivery d = deliveryField.getSelectionModel().getSelectedItem();
	    if (d != null) {
	    	if (d instanceof Model.RegularDelivery) {
	    		deliveryOrders.getItems().clear();
	    	for (Order o : ((Model.RegularDelivery) d).getOrders()) {
	    		if (o != null)
	    			deliveryOrders.getItems().add(o);
	    		}
	    	
	    	} 
	    	else {
	    		double p = ((ExpressDelivery) d).getPostage();
	    		postageField.setText(String.valueOf(p));
	    	}
	    	deliveryPersonField.getSelectionModel().select(d.getDeliveryPerson());
	    	delAreaField.getSelectionModel().select(d.getArea());
	    	delDate.setValue(d.getDeliveredDate());
	    	isDelivered.setSelected(d.isDelivered());
	    }
    }
    
    @FXML
    void add(ActionEvent event) {	
    	 Delivery d = deliveryField.getSelectionModel().getSelectedItem();
    	 if(d == null) {
			  alerts.showAlert(AlertType.ERROR, "Update Delivery",
	    				"The Delivery Not Choosen Yet.", ButtonType.OK);
		  }
    	 if (d instanceof Model.RegularDelivery) {
    	 Order dOrder = ordersField.getSelectionModel().getSelectedItem();
    	 if(dOrder == null) {
			  alerts.showAlert(AlertType.ERROR, "Update Delivery",
	    				"The Order Not Choosen Yet.", ButtonType.OK);
		  } else {
			Boolean res =  ((Model.RegularDelivery) d).addOrder(dOrder);
			if (res) {
				alerts.showAlert(AlertType.INFORMATION, "Update Delivery", 
        				"Added Successfully.",
        				ButtonType.FINISH);
			} else {
				alerts.showAlert(AlertType.ERROR, "Update Delivery", 
        				"Cannot Remove The Selected Items.",
        				ButtonType.FINISH);
			}
		  }
    	} else {
    		alerts.showAlert(AlertType.ERROR, "Update Delivery", 
    				"Cannot Add More Orders To An Express Delivery.",
    				ButtonType.FINISH);
    	}
    	  Restaurant.savaData();
		  Restaurant.loadData();
    	  initSection();
    }
    
   
    @FXML
    void remove(ActionEvent event) {	
    	 Delivery d = deliveryField.getSelectionModel().getSelectedItem();
    	 if(d == null) {
    		 alerts.showAlert(AlertType.ERROR, "Update Delivery",
	    				"The Delivery Not Choosen Yet.", ButtonType.OK);
		  }
    	 else if (d instanceof Model.RegularDelivery) {
    	 Order dOrder = deliveryOrders.getSelectionModel().getSelectedItem();
    	 if(dOrder == null) {
    		 alerts.showAlert(AlertType.ERROR, "Update Delivery",
	    				"The Order Not Choosen Yet.", ButtonType.OK);
		  } else {
			Boolean res =  ((Model.RegularDelivery) d).removeOrder(dOrder);
			if (res) {
				alerts.showAlert(AlertType.INFORMATION, "Update Delivery", 
        				"Removed Successfully.",
        				ButtonType.FINISH);
			} else {
				alerts.showAlert(AlertType.ERROR, "Update Delivery", 
        				"Cannot Remove The Selected Items.",
        				ButtonType.FINISH);
			}
		  }
    	} else {
    		alerts.showAlert(AlertType.ERROR, "Update Delivery", 
    				"Cannot Remove Order From An Express Delivery.",
    				ButtonType.FINISH);
    	}
    	  Restaurant.savaData();
		  Restaurant.loadData();
    	  initSection();
    }
    
    @FXML
    void update(ActionEvent event) {	
    	 Delivery d = deliveryField.getSelectionModel().getSelectedItem();
    	 if(d == null) {
    		 alerts.showAlert(AlertType.ERROR, "Update Delivery",
	    				"The Delivery Not Choosen Yet.", ButtonType.OK);
		  } else {
			  DeliveryPerson dPerson = deliveryPersonField.getSelectionModel().getSelectedItem();
			  DeliveryArea dArea = delAreaField.getSelectionModel().getSelectedItem();
			  String postagString = postageField.getText();
			  LocalDate dDate = delDate.getValue();
			  Boolean delivered = isDelivered.isSelected();
			  if(dPerson == null ) {
				  alerts.showAlert(AlertType.ERROR, "Update Delivery",
		    				"Please enter the delivery person.", ButtonType.OK);
			  } else if(dArea == null) {
				  alerts.showAlert(AlertType.ERROR, "Update Delivery",
		    				"Please enter the delivery area.", ButtonType.OK);
			  } else if(dDate == null) {
				  alerts.showAlert(AlertType.ERROR, "Update Delivery",
		    				"Please enter the deliver date.", ButtonType.OK);
			  } else if (d instanceof Model.RegularDelivery) {
				  d.setArea(dArea);
				  d.setDelivered(delivered);
				  d.setDeliveredDate(dDate);
				  d.setDeliveryPerson(dPerson);
				  alerts.showAlert(AlertType.INFORMATION, "Update Delivery",
		    				"Update Successfuly.", ButtonType.OK);
				  
			  }
			  else { if(postagString == null) {
				  alerts.showAlert(AlertType.ERROR, "Update Delivery",
			    				"Please enter the postage of the delivery.", ButtonType.OK);
				  } else {
					 try { 
						 Double p = Double.parseDouble(postagString);
						  d.setArea(dArea);
						  d.setDelivered(delivered);
						  d.setDeliveredDate(dDate);
						  d.setDeliveryPerson(dPerson);
						  ((Model.ExpressDelivery) d).setPostage(p);
						  alerts.showAlert(AlertType.INFORMATION, "Update Delivery",
				    				"Update Successfuly.", ButtonType.OK);
					 } catch (NumberFormatException e) {
						 System.out.println(e.getStackTrace());
					}  
				  }
			  }
		  }
    	  Restaurant.savaData();
		  Restaurant.loadData();
    	  initSection();
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
			Parent root = FXMLLoader.load(getClass().getResource("EditRegDel.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Edit Regular Delivery");
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