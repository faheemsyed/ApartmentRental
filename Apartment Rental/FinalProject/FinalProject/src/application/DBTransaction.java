package application;
import java.sql.*;

import application.User;

public class DBTransaction {

	private String query ="";
	private String db ="jdbc:mysql://localhost:3306/apartments1";
	private String user ="root";
	private String password = null;

	private Connection myConnection;
	private Statement myStatement ;
	private ResultSet rs;

	public void DbTransaction(){

	}

	public ResultSet getResultSet(String query)
	{
		this.query=query;
		try 
		{
			myConnection = DriverManager.getConnection(db,user,password);
			myStatement = myConnection.createStatement();
			rs = myStatement.executeQuery(query);
		}
		catch (SQLException e )
		{
			System.out.println("something went wrong with DB connection");
			e.printStackTrace();
		}



		return this.rs;
	}

	public void closeCon(){
		try
		{
			myConnection.close();

		}

		catch (SQLException e )
		{
			System.out.println("something went wrong with closing DB connection");
			e.printStackTrace();
		}
	}

	
	public boolean registerNew(User newuser){

		String query=" insert into user (email,fname,lname,phone,passord)"		
				+ " values (?,?,?,?,?)";
		try 
		{		
			myConnection = DriverManager.getConnection(db,user,password);
			PreparedStatement statement = myConnection.prepareStatement(query);

			statement.setString(1,newuser.getEmail());
			statement.setString(2,newuser.getfName());
			statement.setString(3,newuser.getlName());
			statement.setString(4,newuser.getPhone());
			statement.setString(5,newuser.getPass());
			statement.execute();
			return true;
		}
		catch (SQLException e )
		{
			return false;
		}

	}


}
