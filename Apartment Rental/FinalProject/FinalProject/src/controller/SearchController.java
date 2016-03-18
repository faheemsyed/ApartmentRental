package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import database.ApartmentsDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SearchController implements Initializable{
	@FXML
	private Button Rent1, Rent2, Rent3, Rent4, Rent5, backButton, LogOutButton, Prev, Next;
	@FXML
	private Text Location1, Location2, Location3, Location4, Location5,
	Price1, Price2, Price3, Price4, Price5,
	Beds1, Beds2, Beds3, Beds4, Beds5,
	Bath1, Bath2, Bath3, Bath4, Bath5,
	Lister1, Lister2, Lister3, Lister4, Lister5;

	@FXML
	private ImageView Image1, Image2, Image3, Image4, Image5;
	private static ImageView[] images = new ImageView[5];
	private Text[] Locations = new Text[5];
	private Text[] Prices = new Text[5];
	private Text[] Beds = new Text[5];
	private Text[] Baths = new Text[5];
	private Text[] Listers = new Text[5];
	private Button[] Rent = new Button[5];

	private int Page = 0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		images[0] = Image1;
		images[1] = Image2;
		images[2] = Image3;
		images[3] = Image4;
		images[4] = Image5;

		Locations[0] = Location1;
		Locations[1] = Location2;
		Locations[2] = Location3;
		Locations[3] = Location4;
		Locations[4] = Location5;

		Prices[0] = Price1;
		Prices[1] = Price2;
		Prices[2] = Price3;
		Prices[3] = Price4;
		Prices[4] = Price5;

		Beds[0] = Beds1;
		Beds[1] = Beds2;
		Beds[2] = Beds3;
		Beds[3] = Beds4;
		Beds[4] = Beds5;

		Baths[0] = Bath1;
		Baths[1] = Bath2;
		Baths[2] = Bath3;
		Baths[3] = Bath4;
		Baths[4] = Bath5;

		Listers[0] = Lister1;
		Listers[1] = Lister2;
		Listers[2] = Lister3;
		Listers[3] = Lister4;
		Listers[4] = Lister5;
		
		Rent[0] = Rent1;
		Rent[1] = Rent2;
		Rent[2] = Rent3;
		Rent[3] = Rent4;
		Rent[4] = Rent5;
		
		setPage();
		changeLabels();
	}

	private void setPic(){
		int size = 0;
		if (ApartmentsDB.apartmentCount() > 5)
			size = 5;
		else
			size = ApartmentsDB.apartmentCount();
		for(int i = 0; i < size; i++){
			String path = ApartmentsDB.Picture(i);
			File file = new File("pics/" + path);
			Image image = new Image(file.toURI().toString());
			images[i].setImage(image);
		}
	}
	
	@FXML
	public void Rent(ActionEvent event){
		String buttonName;
		Object source = event.getSource();
		int i = 0;;
		
		if (source instanceof Button) { //should always be true when clicked
		    Button clickedBtn = (Button) source; // that's the button that was clicked
		    buttonName = clickedBtn.getId();
		    System.out.println(buttonName);
		    for(i = 0; i < 5; i++){
		    	if (buttonName.equals(Rent[i].getId()))
		    		break;
		    }
		}
		changeScene(i);
	}
	
	@FXML
	public void changeScene(int i){
		try {
			controller.Lister.ListerInfo(ApartmentsDB.Lister(i), ApartmentsDB.ListerPhone(i));
			Parent root = FXMLLoader.load(getClass().getResource("/application/Lister.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) Rent[i].getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	
	@FXML
	public void changeLabels(){
		setVisibleTrue();
		setVisible();
		changeLocation();
		changePrice();
		changeBeds();
		changeBaths();
		changeListerName();
		setPic();
	}

	@FXML 
	public void changeLocation(){
		int size = 5;

		for(int i = 0; i < size; i++){
			if (Locations[i].isVisible()){
				Locations[i].setText( ApartmentsDB.Address(i) + ", " + ApartmentsDB.City(i) + ", " +
						ApartmentsDB.State(i) + ", " + ApartmentsDB.Zip(i));
			}
		}
	}

	@FXML
	public void changePrice(){
		int size = 5;

		for(int i = 0; i < size; i++){
			if (Prices[i].isVisible()){
				Prices[i].setText("$" + ApartmentsDB.Price(i));
			}
		}
	}

	public void changeBeds(){
		int size = 5;

		for(int i = 0; i < size; i++){
			if (Beds[i].isVisible()){
				Beds[i].setText(ApartmentsDB.Bed(i));
			}
		}
	}

	public void changeBaths(){
		int size = 5;

		for(int i = 0; i < size; i++){
			if (Baths[i].isVisible()){
				Baths[i].setText(ApartmentsDB.Bath(i));
			}
		}
	}

	public void changeListerName(){
		int size = 5;

		for(int i = 0; i < size; i++){
			if (Listers[i].isVisible()){
				Listers[i].setText(ApartmentsDB.Lister(i));
			}
		}
	}

	public void setVisible(){
		int apartmentsToShow = ApartmentsDB.apartmentCount()-(Page*5);
		
		if(apartmentsToShow < 5) {
			for(int i = 4; i > apartmentsToShow-1; i--){
				Locations[i].setVisible(false);
				Prices[i].setVisible(false);
				Beds[i].setVisible(false);
				Baths[i].setVisible(false);
				Listers[i].setVisible(false);
				Rent[i].setVisible(false);
			}	
		}
	}

	@FXML
	public void setVisibleTrue(){
		for(int i = 0; i < 5; i++){
			Locations[i].setVisible(true);
			Prices[i].setVisible(true);
			Beds[i].setVisible(true);
			Baths[i].setVisible(true);
			Listers[i].setVisible(true);
			Rent[i].setVisible(true);
		}	
	}

	@FXML
	public void back(ActionEvent event){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Home.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) backButton.getScene().getWindow();
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

	@FXML
	public void nextPage(ActionEvent event){
		//Goes forward one page
	}

	@FXML
	public void prevPage(ActionEvent event){
		//Goes back one page
	}
	
	
	public void setPage(){
		//Number of pages the search returned
	}

}
