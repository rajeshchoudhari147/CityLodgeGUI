package model.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.StandardRoom;
import util.DateTime;
import view.ErrorDialog;
import model.HiringRecord;
import model.CityLodge;
import model.Suite;

public class SelectRoom extends DatabaseConnection
{
	public SelectRoom()
	{
		int i = 0;

		// use try-with-resources Statement
		try (Connection con = DatabaseConnection.getConnection(DB_NAME); Statement stmt = con.createStatement();) 
		{
			String query = "SELECT * FROM room";

			try (ResultSet resultSet = stmt.executeQuery(query)) 
			{
				while (resultSet.next())
				{
					if (resultSet.getString("roomtype").equals("Suite")) 
					{
						Suite suite = new Suite(
								resultSet.getString("roomid"),
								resultSet.getInt("noofbeds"), 
								resultSet.getString("roomfeature"),
								resultSet.getString("roomtype"), 
								resultSet.getString("roomstatus"),
								resultSet.getString("imagename"));
						CityLodge.getInstance().getRoomList().add(suite);
						//System.out.println("Suite");
						if (resultSet.getString("lastmaintenancedate") != null)
						{
							suite.setLastMaintenanceDate(DateTime.stringToDateTime(resultSet.getString("lastmaintenancedate")));
							CityLodge.getInstance().getRoomList().add(suite);
						}
					}
					else
					{
						StandardRoom stdRoom = new StandardRoom(
								resultSet.getString("roomid"),
								resultSet.getInt("noofbeds"), 
								resultSet.getString("roomfeature"),
								resultSet.getString("roomtype"), 
								resultSet.getString("roomstatus"),
								resultSet.getString("imagename"));
						CityLodge.getInstance().getRoomList().add(stdRoom);
						//System.out.println("Room");
					}

					String rentalQuery = "SELECT * FROM hiringrecords where room_id = " + resultSet.getString("roomid");
					
					try (ResultSet resultSet1 = stmt.executeQuery(rentalQuery)) 
					{
						while (resultSet1.next()) 
						{
							if (resultSet1.getString("actualreturndate") == null) 
							{
								HiringRecord hiringRecord = new HiringRecord(
										resultSet1.getString("hiringid"),
										DateTime.stringToDateTime(resultSet1.getString("rentdate")),
										DateTime.stringToDateTime(resultSet1.getString("estimatedreturndate")));
								CityLodge.getInstance().getRoomList().get(i).getHiringRecordList().add(hiringRecord);
							}
							else 
							{
								HiringRecord hiringRecord = new HiringRecord(
										resultSet1.getString("hiringid"),
										DateTime.stringToDateTime(resultSet1.getString("rentdate")),
										DateTime.stringToDateTime(resultSet1.getString("estimatedreturndate")),
										DateTime.stringToDateTime(resultSet1.getString("actualreturndate")),
										resultSet1.getFloat("rentalfee"), 
										resultSet1.getFloat("latefee"));
								CityLodge.getInstance().getRoomList().get(i).getHiringRecordList().add(hiringRecord);
							}
						}
						i++;
					} 
					catch (SQLException e) 
					{
						new ErrorDialog(e.getMessage());
					}
				}
			} 
			catch (SQLException e) 
			{
				new ErrorDialog(e.getMessage());
			}
		} 
		catch (Exception e) 
		{
			new ErrorDialog(e.getMessage());
		}
	}
}