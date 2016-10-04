package com.yash.vta.dbconnection;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * MyConnection class is to establish connection with the database by properties file
 * @author sarang.patil
 *
 */

public class MyConnection {

	/**
	 * getConnection() method returns the object of class Connection
	 * 
	 */
	
	public static Connection getConnection() {
		
		File file = null;
		FileInputStream fis = null;
		Connection connection = null;
		
		try {
			Properties props = new Properties();
			file = new File(
					"D://TrainerApplicationWorkspace//MyTrainerApplication//Database_Detail//mysql_db.properties");
			fis = new FileInputStream(file);
			props.load(fis);
			Class.forName(props.getProperty("DRIVER_CLASS"));
			
			connection = DriverManager.getConnection(
					props.getProperty("DB_URL"),
					props.getProperty("DB_USERNAME"),
					props.getProperty("DB_PASSWORD"));
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * closeConnection method returns the object of class Connection
	 * 
	 */
	public void closeConnection(Connection connection){
		
		try {
				connection.close();
				
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
