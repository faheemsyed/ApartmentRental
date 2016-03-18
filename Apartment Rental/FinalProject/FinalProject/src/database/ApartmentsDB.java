package database;

import java.sql.*;
import java.util.*;

public class ApartmentsDB {

	private static ArrayList<String> Address = new ArrayList<String>();
	private static ArrayList<String> City = new ArrayList<String>();
	private static ArrayList<String> State = new ArrayList<String>();
	private static ArrayList<String> Zip = new ArrayList<String>();
	private static ArrayList<String> Price = new ArrayList<String>();
	private static ArrayList<String> Bed = new ArrayList<String>();
	private static ArrayList<String> Bath = new ArrayList<String>();
	private static ArrayList<String> Lister = new ArrayList<String>();
	private static ArrayList<String> Picture = new ArrayList<String>();
	private static ArrayList<String> Phone = new ArrayList<String>();

	private static int apartmentCount;
	
	public static void main(String[] args) throws Exception {
		//fill_Apartments_DB("Brooklyn", "", "", "", "");;
		//System.out.println(apartmentCount());
		//System.out.println((int) Math.ceil(ApartmentsDB.apartmentCount()/ 5.0));
		
	}
	
	
	public static void fill_Apartments_DB(String address, String bedrooms, String bathrooms, String min, String max) throws SQLException{
		apartmentCount = 0;
		erase();
		String MinQuery = null;
		String MaxQuery = null;
		String LocationQuery = address;
		String BedroomsQuery = bedrooms;
		String BathroomsQuery = bathrooms;
		
		try{
		//Location
		if(!LocationQuery.isEmpty())
			LocationQuery = (" City = " + "'" + LocationQuery + "' ");
		else
			LocationQuery = (" City IS NOT NULL ");
		
		//Bedrooms
		if(!BedroomsQuery.isEmpty())
			BedroomsQuery = (" Bed >= " + BedroomsQuery);
		else
			BedroomsQuery = (" Bed IS NOT NULL ");
		
		//Bathrooms
		if(!BathroomsQuery.isEmpty())
			BathroomsQuery = (" Bath >= " + BathroomsQuery); 
		else 
			BathroomsQuery = (" Bath Is NOT NULL ");
		
		//Max
		if(!max.isEmpty() && min.isEmpty())
			MaxQuery = (" Price <= " + max);
		else 
			MaxQuery = (" Price IS NOT NULL ");
		
		//Min
		if(!min.isEmpty() && max.isEmpty())
			MinQuery = (" Price >= " + min);
		else
			MinQuery = (" Price IS NOT NULL ");
		
			
		Connection c = DBConnector.getConnection();
		Statement myStmt = c.createStatement();
		ResultSet myRs = myStmt.executeQuery("SELECT * FROM apartments WHERE " + LocationQuery + 
				" AND " + BedroomsQuery + " AND " + BathroomsQuery + " AND " +
				MaxQuery + " AND " + MinQuery);
		
		
		while (myRs.next()){
			Address.add(myRs.getString("Address"));
			City.add(myRs.getString("City"));
			State.add(myRs.getString("State"));
			Zip.add(myRs.getString("Zip"));
			Price.add(myRs.getString("Price"));
			Bed.add(myRs.getString("Bed"));
			Bath.add(myRs.getString("Bath"));
			Lister.add(myRs.getString("Lister"));
			Phone.add(myRs.getString("ListerPhone"));
			Picture.add(myRs.getString("Picture"));
			apartmentCount++;
			}	
		}
		catch (Exception e){
			System.out.println(e);			
		}
		
		//Prnt();
	}
	
	public static int apartmentCount(){
		return apartmentCount;
	}
	
	public static String Address(int row){
		return Address.get(row);
	}
	
	public static String City(int row){
		return City.get(row);
	}
	
	public static String State(int row){
		return State.get(row);
	}
	
	public static String Zip(int row){
		return Zip.get(row);
	}
	
	public static String Price(int row){
		return Price.get(row);
	}
	
	public static String Bed(int row){
		return Bed.get(row);
	}
	
	public static String Bath(int row){
		return Bath.get(row);
	}
	
	public static String Lister(int row){
		return Lister.get(row);
	}
	
	public static String ListerPhone(int row){
		return Phone.get(row);
	}
	
	public static String Picture(int row){
		return Picture.get(row);
	}
	
	public static void Prnt(){
		int i = 0;
		
		System.out.println("Address "+ Address(i));
		System.out.println("City "+ City(i)); 
		System.out.println("State " + State(i));
		System.out.println("Zip "+ Zip(i)); 
		System.out.println("Price " +Price(i));
		System.out.println("Bed "+ Bed(i));
		System.out.println("Bath " + Bath(i));
		System.out.println("Lister " + Lister(i));
		System.out.println("Listers Phone " + ListerPhone(i));
		System.out.println("Picture " + Picture(i));
	}

	public static void erase(){
		Address.clear();
		City.clear();
		State.clear();
		Zip.clear();
		Price.clear();
		Bed.clear();
		Bath.clear();
		Lister.clear();
		Picture.clear();
		Phone.clear();
	}
}
