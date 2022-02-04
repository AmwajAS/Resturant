package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AdminCont implements Initializable {

	@FXML ImageView mangimg;
	@FXML 
	private Menu deliA;
	@FXML
	private MenuItem cook;
	@FXML
	private MenuItem delPer;
	@FXML
	private MenuItem cust;
	@FXML 
	private MenuItem dish;
	@FXML
	private MenuItem comp;
	@FXML 
	private MenuItem ord;
	@FXML 
	private MenuItem del;
	@FXML 
	private MenuItem delArea;
	@FXML 
	private VBox vBox;
	@FXML 
	private SplitMenuButton addAll;
	@FXML 
	private MenuItem coo;
	@FXML 
	private MenuItem dep;
	@FXML 
	private MenuItem cus;
	@FXML 
	private MenuItem dis;
	@FXML  
	private MenuItem com;
	@FXML
	private  MenuItem or;
	@FXML
	private MenuItem reg11;
	@FXML
	private MenuItem exp11;
	@FXML
	private MenuItem dell;
	@FXML
	private MenuItem are;
	@FXML 
	private MenuItem bla;
	@FXML 
	private SplitMenuButton removeAll;
	@FXML 
	private MenuItem coo1;
	@FXML
	private MenuItem dep1;
	@FXML 
	private MenuItem cus1;
	@FXML 
	private MenuItem dis1;
	@FXML 
	private MenuItem com1;
	@FXML 
	private MenuItem or1;
	@FXML 
	private MenuItem dell1;
	@FXML
	private MenuItem are1;
	@FXML 
	private Button dataAll;
	@FXML 
	private SplitMenuButton sqlAll;
	@FXML 
	private MenuItem Q11;
	@FXML 
	private MenuItem Q22;
	@FXML 
	private MenuItem Q33;
	@FXML
	private MenuItem Q44;
	@FXML 
	private MenuItem Q55;
	@FXML
	private MenuItem Q66;
	@FXML 
	private SplitMenuButton editAll;
    @FXML 
    private MenuItem coe;
    @FXML 
    private MenuItem dee;
    @FXML 
    private MenuItem cue;
    @FXML 
    private MenuItem die;
    @FXML 
    private MenuItem ore;
    @FXML 
    private MenuItem exe;
    @FXML 
    private MenuItem ree;
    @FXML 
    private MenuItem ae;
    @FXML 
    private Button exp1;
	
	@FXML Separator separator;
	@FXML
	private Button signOut;
	@FXML Label helloMang;
	@FXML Pane adminPane;
	@FXML Label cookLabel;
	@FXML Label QLabel;


	
    @FXML
    private Button bar1;
    @FXML
    private Button bar2;
    @FXML
    private AnchorPane side;
    @FXML
    private MenuBar menuBar1;
    @FXML
    private Menu add;
    @FXML
    private MenuItem cookA;
    @FXML
    private MenuItem delPerA;
    @FXML
    private MenuItem custA;
    @FXML
    private MenuItem dishA;
    @FXML
    private MenuItem compA;
    @FXML
    private MenuItem ordA;
    @FXML 
    private Menu daliA;
    @FXML
    private MenuItem regA;
    @FXML
    private MenuItem ExpA;
    @FXML
    private MenuItem delAreA;
    @FXML
    private MenuItem blackA;
    @FXML
    private Menu remove;
    @FXML
    private MenuItem cookR;
    @FXML
    private MenuItem delPerR;
    @FXML
    private MenuItem custR;
    @FXML
    private MenuItem dishR;
    @FXML
    private MenuItem compR;
    @FXML
    private MenuItem ordR;
    @FXML 
    private MenuItem delR;
    @FXML
    private MenuItem delAreaR;
    @FXML
    private Menu q;
    @FXML
    private MenuItem Q1;
    @FXML
    private MenuItem Q2;
    @FXML
    private MenuItem Q3;
    @FXML
    private MenuItem Q4;
    @FXML
    private MenuItem Q5;
    @FXML
    private MenuItem Q6;
    @FXML
    private Menu edit;
    @FXML
    private MenuItem cookEd;
    @FXML
    private MenuItem delPerEd;
    @FXML
    private MenuItem custEd;
    @FXML
    private MenuItem dishEd;
    @FXML
    private MenuItem compEd;
    @FXML
    private MenuItem ordEd;
    @FXML 
    private MenuItem exEd;
    @FXML 
    private MenuItem reEd;
    @FXML
    private MenuItem delAreaEd;
    @FXML
    private Menu data;
    @FXML
    private MenuItem dataAl;
    @FXML
    private Button ad;
    @FXML
    private Button re;
    @FXML
    private Button sq;
    @FXML
    private Button ed;
    @FXML
    private Button da;
    @FXML
    private Button lo;
	@FXML 
	private HBox hBoxQ;
	@FXML 
	private Button q1;
	@FXML 
	private Button q2;
	@FXML 
	private Button q3;
	@FXML 
	private Button q4;
	@FXML 
	private Button q5;
	@FXML 
	private Button q6;
	@FXML 
	private HBox hboxA;
	@FXML 
	private Button cook1;
	@FXML 
	private Button delPer1;
	@FXML 
	private Button cust1;
	@FXML 
	private Button dish1;
	@FXML 
	private Button comp1;
	@FXML 
	private Button ord1;
	@FXML 
	private Button deli1;
	@FXML
	private Button delArea1;
	
	 
	
	
	
	
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	side.setTranslateX(-211);
		bar1.setVisible(true);
		bar2.setVisible(false);
		
		
    }
    
    

    @FXML
    public void run1(MouseEvent Event) {
    	TranslateTransition slide = new TranslateTransition();
    	slide.setDuration(javafx.util.Duration.seconds(0.4));
    	slide.setNode(side);
    	
    	slide.setToX(0);
    	
    	slide.play();
    	
    	side.setTranslateX(-211);
    	slide.setOnFinished((ActionEvent e) -> {
    		bar1.setVisible(false);
    		bar2.setVisible(true);
    		
    	});                       //this two methods i used to control the side pane in the manager main page. 
    		
    	}
    
    @FXML
    public void run2(MouseEvent Event) {
    	TranslateTransition slide = new TranslateTransition();
    	slide.setDuration(javafx.util.Duration.seconds(0.4));
    	slide.setNode(side);
    	
    	slide.setToX(-211);
    	
    	slide.play();
    	
    	side.setTranslateX(-211);
    	slide.setOnFinished((ActionEvent e) -> {
    		bar1.setVisible(true);
    		bar2.setVisible(false);
    		
    	});	
    }

	
//creating shadow
	public void createShadow() {
		
		adminPane.setEffect(new DropShadow(20, Color.BLACK));	
		}
		 
	
	//////edit buttons
	public void actionOnEditCook(ActionEvent event) throws IOException {

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("EditCook.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Edit Cook");
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent t) {
		        Platform.exit();
		        System.exit(0);
		    }
		});
	
}
	
	public void actionOnEditDeliveryPerson(ActionEvent event) throws IOException {

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("EditDelPer.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Edit Delivery Person");
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent t) {
		        Platform.exit();
		        System.exit(0);
		    }
		});
	}
	public void actionOnEditDeliveryArea(ActionEvent event) throws IOException {

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
	public void actionOnEditComponent(ActionEvent event) throws IOException {

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
	public void actionOnEditOrder(ActionEvent event) throws IOException {

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("EditOrd.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Edit order");
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent t) {
		        Platform.exit();
		        System.exit(0);
		    }
		});
	}
		
		public void actionOnEditDish(ActionEvent event) throws IOException {

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
		public void actionOnEditDelivery(ActionEvent event) throws IOException {

			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("EditDelivery.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Edit Delivery");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});
	
}

	public void actionOnEditCustomer(ActionEvent event) throws IOException {

		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("EditCust.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Edit Customer");
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent t) {
		        Platform.exit();
		        System.exit(0);
		    }
		});
	
}
	//////////add Buttons
	public void actionOnAddCook(ActionEvent event) throws IOException {

			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("AddCook.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adding A Cook");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});
		
	}
	
	public void actionOnAddDelPer(ActionEvent event) throws IOException {

			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("AddDelPer.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adding A Delivery Person");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});
		
	}
	
	public void actionOnAddCust(ActionEvent event) throws IOException {

			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("AddCust.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adding A Customer");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});
		
	}
	
	public void actionOnAddDish(ActionEvent event) throws IOException {

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
	
	public void actionOnAddOrd(ActionEvent event) throws IOException {

			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("AddOrd.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adding A Order");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});
		}
		

	
	public void actionOnAddComp(ActionEvent event) throws IOException {

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

	
	public void actionOnAddRegDeli(ActionEvent event) throws IOException {
		regA.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {

		});
		{
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("AddRegDeli.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adding A Regular Delivery");
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
	
	public void actionOnAddExpDeli(ActionEvent event) throws IOException {
		ExpA.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {

		});
		{
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("AddExpDeli.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adding A Regular Delivery");
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
	
	public void actionOnAddDelArea(ActionEvent event) throws IOException {

			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("AddDeliArea.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adding A Delivery Area");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});
		
	}
	
	public void actionOnAddBlack(ActionEvent event) throws IOException {

			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("AddBlack.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Adding To The Black List");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});
		
	}
	////////////////Remove Buttons
	public void actionOnRemoveCook(ActionEvent event) throws IOException {

			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("RemoveCook.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Removing A Cook");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});
		
	}
	
	public void actionOnRemoveDelPer(ActionEvent event) throws IOException {

			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("RemoveDelPer.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Removing A Delivery Person");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});
		
	}
	
	public void actionOnRemoveCust(ActionEvent event) throws IOException {

			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("RemoveCust.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Removing A Customer");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});
		
	}
	
	public void actionOnRemoveDish(ActionEvent event) throws IOException {

			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("RemoveDish.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Removing A Dish");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});
		
	}
	
	public void actionOnRemoveComp(ActionEvent event) throws IOException {

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
	
	public void actionOnRemoveOrd(ActionEvent event) throws IOException {

			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("RemoveOrd.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Removing A Order");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});
		
	}
	
	public void actionOnRemoveDelivery(ActionEvent event) throws IOException {

			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("RemoveDeli.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Removing A Delivery");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});
		
	}
	
	public void actionOnRemoveDelArea(ActionEvent event) throws IOException {

			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("RemoveDeliArea.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Removing A Delivery Area");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});
		
	}
	
		
	public void vBoxSignOutClicked(ActionEvent event) throws IOException {
		signOut.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {

		});
		{
			signOut.getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Fatafet");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});
		}
		signOut.getScene().getWindow().hide();
	}
	

	

	////////////Queries:
	
	public void Q1Cliked(ActionEvent event) throws IOException {

			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("Q1GetDelByPer.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Q.1 Deliveries By Person");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});
		}
	
	

	public void Q2Cliked(ActionEvent event) throws IOException {

			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("Q2NumOfDelPerType.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Q.1 Number Of Deliveries Per Type");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});
		
	}
	

	
	public void Q3Cliked(ActionEvent event) throws IOException {

			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("Q3RevenueFromExpDel.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Q.3 Revenue From Express Deliveries");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});
		
	}
	

	
	public void Q4Cliked(ActionEvent event) throws IOException {

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
	
	
	
	public void Q5Cliked(ActionEvent event) throws IOException {

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
	

	public void Q6Cliked(ActionEvent event) throws IOException {
	
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
	
	

	
	public void DataCliked(ActionEvent event) throws IOException {

			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("Data.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Data");
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
