package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Lister implements Initializable{
	private static String LName;
	private static String LPhone;
	
	@FXML
	private Text ListerName, ListerNumber;
	
	@FXML
	private Button backButton, LogOutButton;
	
	public static void ListerInfo(String Name, String Phone){
		LName = Name;
		LPhone = Phone;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ListerName.setText(LName);
		ListerNumber.setText(LPhone);	
	}
	
	@FXML
	public void back(ActionEvent event){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Search.fxml"));
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
	
/*
	public static void showStage(){
		Stage newStage = new Stage();
		VBox comp = new VBox();
		Label message1 = new Label("Your message was sent to the lister");
		comp.getChildren().add(message1);
		message1.setLayoutX(5);
		message1.setLayoutY(56);
		

		Scene stageScene = new Scene(comp, 250, 175);
		newStage.setScene(stageScene);
		newStage.show();
	}
	
	@FXML
	public static void send(Action event){
		showStage();
	}*/
	
/*	private void sendEmail(){

		  String to = "Ehab.Makary@gmail.com";
	      String from = "Ehab.Makary@gmail.com";
	      String host = "localhost";
	      Properties properties = System.getProperties();
	      properties.setProperty("mail.smtp.host", host);
	      Session session = Session.getDefaultInstance(properties);

	      try{
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(from));
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	         message.setSubject("site for renting appartments ");
	         message.setText("Someone wants to rent your appartment");
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}*/
}
