package model.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import view.ErrorDialog;

public class CreateTable extends DatabaseConnection
{
	public CreateTable() throws ClassNotFoundException
	{
		final String ROOM_TABLE = "CREATE TABLE room IF NOT EXISTS ("
				+ "roomid VARCHAR(80) NOT NULL,"
				+ "noofbeds int NOT NULL,"
				+ "roomfeature VARCHAR(100) NOT NULL,"
				+ "roomtype VARCHAR(80) NOT NULL,"
				+ "roomstatus VARCHAR(80) NOT NULL,"
				+ "imagename VARCHAR(80) NOT NULL,"
				+ "lastmaintenancedate VARCHAR(80) NULL,"
				+ "PRIMARY KEY (roomid))";
		final String HIRING_TABLE = "CREATE TABLE hiringrecords IF NOT EXISTS ("
				+ "hiringid VARCHAR(80) NOT NULL,"
				+ "rentdate VARCHAR(80) NOT NULL,"
				+ "estimatedreturndate VARCHAR(80) NOT NULL,"
				+ "actualreturndate VARCHAR(80) NULL,"
				+ "rentalfee float(10) NULL,"
				+ "latefee float(10) NULL,"
				+ "room_id VARCHAR(20) NOT NULL FOREIGN KEY REFERENCES room(roomid),"
				+ "PRIMARY KEY (hiringid))";
		
		try(Connection connection = DatabaseConnection.getConnection(DB_NAME); 
				Statement statement = connection.createStatement();)
		{
			statement.executeUpdate(ROOM_TABLE);
			int result = 0;
			result = statement.executeUpdate(HIRING_TABLE);
			if(result == 1)
				new ErrorDialog("Created");
			else
				new ErrorDialog("Not Created");
		}
		catch(SQLException e)
		{
			new ErrorDialog(e.getMessage());
		}
	}
}
