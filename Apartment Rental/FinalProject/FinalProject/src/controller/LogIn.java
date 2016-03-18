package controller;



import java.sql.*;
import java.util.Properties;

import application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import application.User;
import java.util.*;
import javax.activation.*;

public class LogIn {
	
 private User user;
 private DBTransaction dbt = new DBTransaction();	
 private ResultSet rs;
 private boolean error = false;
	
	
	@FXML
	private Button LogInButton, SingUpButton;
	@FXML
	private TextField Username ;
	@FXML
	private PasswordField Password ;
	@FXML
	private TextField FirstName ;
	@FXML
	private TextField SecondName ;
	@FXML
	private TextField Email ;
	@FXML
	private TextField Phone ;
	@FXML
	private PasswordField newPassword ;
	
	
	@FXML
	public void SignUp(ActionEvent event){
		
		user = new User(Email.getText(), FirstName.getText()
				,SecondName.getText(), Phone.getText(), newPassword.getText());		
		if(dbt.registerNew(user))
			changScene();
		else
		{
			error = true;
			Email.setText("Email already exists");
			Email.setStyle("-fx-text-inner-color: red;");
		}
		dbt.closeCon();
		
	}
	
	
	public void LogInClick(ActionEvent event){
		
      
		
		if (checkLogin( Username.getText(),Password.getText())){
		
			changScene();
		}
		
		else{
			Username.setText("Incorrect username or password");
			Username.setStyle("-fx-text-inner-color: red;");
			Password.setText("Error");
			Password.setStyle("-fx-text-inner-color: red;");
			error = true;
		}

		
		
		
	}
	
	private boolean checkLogin(String email,String password)
	{
		
		String query = ("SELECT * FROM apartments1.user where email=" +"'" +email+"'" + " and passord="+ "'" + password+"'"  );
		
		
		rs=dbt.getResultSet(query);
	
		
		try {
			if (!rs.next()){
				dbt.closeCon();
				return false;
			}
		} catch (SQLException e) {
			System.out.print("something oops");
			e.printStackTrace();
		}
		dbt.closeCon();
		return true;
	}

	private void changScene(){
		try {			
			
			Parent root = FXMLLoader.load(getClass().getResource("/application/Home.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) LogInButton.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void reset(){
		if(error) {
			error = false;
			Username.setText("");
			Username.setStyle("-fx-text-inner-color: black;");
			Password.setText("");
			Password.setStyle("-fx-text-inner-color: black;");
			Email.setText("");
			Email.setStyle("-fx-text-inner-color: black;");
		}
	}
}
	
	
	

