package model.database;

import java.sql.Connection;
import java.sql.Statement;

import model.AbstractRoom;
import model.CityLodge;
import model.Suite;
import view.ErrorDialog;

public class AddRoomData extends DatabaseConnection
{

	public AddRoomData() 
	{
		// deleting existing data in database and reinserting data from arrays
		try (Connection con = DatabaseConnection.getConnection(DB_NAME); Statement stmt = con.createStatement();) 
		{
			stmt.executeUpdate("Delete from hiringrecords");
			stmt.executeUpdate("Delete from room");
			for (int i = 0; i <= CityLodge.getInstance().getRoomList().size() - 1; i++) 
			{
				String query = "insert into room values("
						+ CityLodge.getInstance().getRoomList().get(i).getRoomID() + ","
						+ CityLodge.getInstance().getRoomList().get(i).getNoOfBeds() + ","
						+ CityLodge.getInstance().getRoomList().get(i).getSummary() + ","
						+ CityLodge.getInstance().getRoomList().get(i).getClass().getSimpleName() + ", "
						+ CityLodge.getInstance().getRoomList().get(i).getRoomStatus() + ","
						+ CityLodge.getInstance().getRoomList().get(i).getImageName() + ",NULL)";
				stmt.executeUpdate(query);
 
				if (CityLodge.getInstance().getRoomList().get(i) instanceof Suite)
				{
					if (((Suite) CityLodge.getInstance().getRoomList().get(i)).getLastMaintenanceDate() != null)
					{
						String query1 = "UPDATE room SET lastmaintenancedate="
								+ ((Suite) CityLodge.getInstance().getRoomList().get(i)).getLastMaintenanceDate().toString()
								+ " where roomid="
								+ CityLodge.getInstance().getRoomList().get(i).getRoomID() + ";";
						stmt.executeUpdate(query1);
					}
				}
				for (int j = 0; j <= CityLodge.getInstance().getRoomList().get(i).getHiringRecordList().size() - 1; j++) 
				{
					AbstractRoom abstractroom = CityLodge.getInstance().getRoomList().get(i);
					if (abstractroom.getHiringRecordList().get(j) != null) 
					{
						if (abstractroom.getHiringRecordList().get(j).getActualReturnDate() != null)
						{
							String query1 = "insert into hiringrecords values("
									+ abstractroom.getHiringRecordList().get(j).getRecordID() + ","
									+ abstractroom.getHiringRecordList().get(j).getRentDate().toString() + ","
									+ abstractroom.getHiringRecordList().get(j).getEstimatedReturnDate().toString() + ","
									+ abstractroom.getHiringRecordList().get(j).getActualReturnDate().toString() + ","
									+ abstractroom.getHiringRecordList().get(j).getRentalFee() + ","
									+ abstractroom.getHiringRecordList().get(j).getLateFee() + "," 
									+ abstractroom.getRoomID() + ")";
							stmt.executeUpdate(query1);
						} 
						else 
						{
							String query1 = "insert into hiringrecords values("
									+ abstractroom.getHiringRecordList().get(j).getRecordID() + ","
									+ abstractroom.getHiringRecordList().get(j).getRentDate().toString() + ","
									+ abstractroom.getHiringRecordList().get(j).getEstimatedReturnDate().toString()
									+ ",null,null,null," 
									+ abstractroom.getRoomID() + ")";
							stmt.executeUpdate(query1);
						}
					}
				}
			}
			con.commit();
		} 
		catch (Exception e)
		{
			new ErrorDialog(e.getMessage());
		}
	}
}

