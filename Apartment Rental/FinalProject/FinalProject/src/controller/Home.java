package controller;


import database.ApartmentsDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import controller.SearchController;

public class Home{
	@FXML
	private Button SearchButton, LogOutButton;
	@FXML
	private TextField Location, PriceMin, PriceMax, BedMin, BathMin;


	@FXML
	public void Search(ActionEvent event){
		try{
			String address = Location.getText();
			String bedrooms = BedMin.getText();
			String bathrooms = BathMin.getText();
			String MaxPrice = PriceMax.getText();
			String MinPrice = PriceMin.getText();
			database.ApartmentsDB.fill_Apartments_DB(address, bedrooms, bathrooms, MinPrice, MaxPrice);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		if (ApartmentsDB.apartmentCount() == 0){
			showStage();
		}
		
		else
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/application/Search.fxml"));
				Scene scene = new Scene(root);
				Stage stage = (Stage) SearchButton.getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
	}	

	@FXML
	public void LogOut(ActionEvent event){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/LogIn.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) LogOutButton.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	public static void showStage(){
		Stage newStage = new Stage();
		VBox comp = new VBox();
		Label message1 = new Label("Sorry,");
		Text message2 = new Text("your search didn't return any results");
		comp.getChildren().add(message1);
		comp.getChildren().add(message2);
		message1.setLayoutX(5);
		message1.setLayoutY(56);
		message2.setLayoutX(20);
		message2.setLayoutY(80);
		

		Scene stageScene = new Scene(comp, 250, 175);
		newStage.setScene(stageScene);
		newStage.show();
	}


}
