package model.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import view.ErrorDialog;

public class DeleteTable extends DatabaseConnection
{
	public DeleteTable() throws SQLException 
	{
		try (Connection con = DatabaseConnection.getConnection(DB_NAME); Statement statement = con.createStatement();)
		{
			statement.executeUpdate("DROP TABLE hiringrecords");
			statement.executeUpdate("DROP TABLE room");
		}
		catch (Exception e) 
		{
			new ErrorDialog(e.getMessage());
		}
	}
}