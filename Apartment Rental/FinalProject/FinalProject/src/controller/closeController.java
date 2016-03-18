package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class closeController {
	@FXML
	private Button closeBtn;
	
	@FXML
	public void Close(ActionEvent event){
		Stage stage = (Stage) closeBtn.getScene().getWindow();
		stage.close();
	}
}
