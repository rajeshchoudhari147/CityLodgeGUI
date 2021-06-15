package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import view.ErrorDialog;

public class DatabaseConnection
{
	protected String DB_NAME = "CityLodgeDB";
	
	public DatabaseConnection()
	{
		try(Connection con = getConnection(DB_NAME))
		{
			//System.out.println("Connection to database "+DB_NAME+" Successfully Created.");
		}
		catch(Exception e)
		{
			new ErrorDialog(e.getMessage());
		}
	}

	public static Connection getConnection(String DBName) throws SQLException, ClassNotFoundException
	{
		//Registering the HSQLDB JDBC Driver
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		
		//Database Files will be created in the database folder
		Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:database/" + DBName, "SA", "");
		return connection;
			
	}
}
