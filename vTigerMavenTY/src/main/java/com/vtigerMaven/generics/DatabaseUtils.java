package com.vtigerMaven.generics;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import com.mysql.cj.jdbc.Driver;

/**
 * This class is used to handle database operations.
 * @author Baishali
 *
 */
public class DatabaseUtils {

	PropertyUtils pObj;
	Connection con;
	
	/**The below method connects to the database
	 * @throws SQLException 
	 * @throws IOException 
	 * 
	 */
	public void connectDB() throws SQLException, IOException
	{
		System.out.println("Connecting to database.");
		//Register the driver.
		/*Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		
		//Connect to database.
		con = DriverManager.getConnection(pObj.fetchDataFromFile("db_url"), pObj.fetchDataFromFile("db_un"),
				pObj.fetchDataFromFile("db_pwd"));
		
		System.out.println("Database connected.");*/		
	}
	
	/**
	 * The below method disconnects from database
	 * @throws SQLException
	 */
	public void disconnectDB() throws SQLException
	{
		//con.close();
		System.out.println("DB disconnect");	
	}
}
