package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class jfx2Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addFilesBtn;

    @FXML
    private Button openFilesBtn;

    @FXML
    private ListView<File> selectFilesList;

    @FXML
    private Label selectedFilesLabel;

    @FXML
    void initialize() {
    	addFilesBtn.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent event) {
                        FileChooser fc=new FileChooser();
                        File selectedFile=fc.showOpenDialog(null);
                        if(selectedFile!=null) {
                        	 selectFilesList.getItems().add(selectedFile.getAbsoluteFile());
                        }
        	}
        });
    	openFilesBtn.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent event) {
                File selectedFile=selectFilesList.getSelectionModel().getSelectedItem();
                try {
                	Desktop.getDesktop().open(selectedFile);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
});
        

    }
}

