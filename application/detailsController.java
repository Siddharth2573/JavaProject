package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class detailsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField detailsNameTextField;

    @FXML
    private DatePicker detailsDOB;

    @FXML
    private RadioButton detailsMale;

    @FXML
    private RadioButton detailsFemale;

    @FXML
    private Button detailsFinishBtn;

    @FXML
    private PasswordField detailsPassFld;

    @FXML
    private Button detailsScanButton;

    @FXML
    private Label detailsPssLabel;

    String name;
    LocalDate DOB;
    String gender;
    String password;
    byte [] salt;
    byte [] hash;
    @FXML
    void initialize() {
    	ToggleGroup tgl=new ToggleGroup();
    	detailsMale.setToggleGroup(tgl);
    	detailsFemale.setToggleGroup(tgl);
    	detailsMale.setOnAction(new EventHandler<ActionEvent>(){
          	public void handle(ActionEvent event) {
          		gender="M";
        	}
    	});
    	detailsFemale.setOnAction(new EventHandler<ActionEvent>(){
          	public void handle(ActionEvent event) {
          		gender="F";
        	}
    	});
    	
      detailsFinishBtn.setOnAction(new EventHandler<ActionEvent>(){
      	public void handle(ActionEvent event) {
      		name=detailsNameTextField.getText();
      		password=detailsPassFld.getText();
            DOB=detailsDOB.getValue();
            if(!detailsMale.isSelected() && !detailsFemale.isSelected()) gender="M";
            salt=SaltGenerator.getSalt();
            hash=HashGenerator.generateHash(password, salt);
            Stage stage=(Stage) detailsFinishBtn.getScene().getWindow();
            stage.close();
            try {
				Class.forName("com.mysql.jdbc.Driver");
				try {
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","chowk1999");
					PreparedStatement pmt=con.prepareStatement("INSERT INTO userdata VALUES(?,?,?)");
					PreparedStatement pmt1=con.prepareStatement("INSERT INTO pssmanager VALUES(?,?,?)");
					pmt.setString(1,name);
					pmt.setString(2,gender);
					pmt.setDate(3,java.sql.Date.valueOf(DOB));
					pmt1.setString(1,name);
					pmt1.setBytes(2, salt);
					pmt1.setBytes(3,hash);
					pmt.execute();
					pmt.close();
					pmt1.execute();
					pmt1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}
    });

    }
}

