package model.database;

import java.sql.Connection;
import java.sql.Statement;

public class AddDefaultValues extends DatabaseConnection
{
	public AddDefaultValues()
	{
		try (Connection con = DatabaseConnection.getConnection(DB_NAME); Statement stmt = con.createStatement();) 
		{
			// inserting room into database
			stmt.executeUpdate("insert into room values"
					+ "('R_159',1,'Wifi','Room','Available','bedroom1',NULL)");
			stmt.executeUpdate("insert into room values"
					+ "('R_145',2,'Wifi','Room','Available','bedroom2',NULL)");
			stmt.executeUpdate("insert into room values"
					+ "('R_965',4,'Wifi','Room','Available','bedroom3',NULL)");
			stmt.executeUpdate("insert into room values"
					+ "('S_157',6,'Wifi','Suite','Available','bedroom4',NULL)");
			stmt.executeUpdate("insert into room values"
					+ "('S_365',6,'Wifi','Suite','Available','bedroom5',NULL)");
			stmt.executeUpdate("insert into room values"
					+ "('S_954',6,'Wifi','Suite','Available','bedroom6',NULL)");
			
			//inserting hiring records into database
			stmt.executeUpdate("insert into hiringrecords values"
					+ "('R_159_C_154_02102019','02/10/2019','07/10/2019','07/10/2019',295,0,'R_159')");
			stmt.executeUpdate("insert into hiringrecords values"
					+ "('R_159_C_459_10102019','10/10/2019','13/10/2019','13/10/2019',177,0,'R_159')");
			stmt.executeUpdate("insert into hiringrecords values"
					+ "('R_145_C_154_09102019','09/10/2019','14/10/2019','14/10/2019',295,0,'R_145')");
			stmt.executeUpdate("insert into hiringrecords values"
					+ "('R_145_C_459_15102019','15/10/2019','18/10/2019','18/10/2019',177,0,'R_145')");
			stmt.executeUpdate("insert into hiringrecords values"
					+ "('R_965_C_154_15102019','15/10/2019','20/10/2019','20/10/2019',295,0,'R_965')");
			stmt.executeUpdate("insert into hiringrecords values"
					+ "('R_965_C_459_19102019','19/10/2019','22/10/2019','22/10/2019',177,0,'R_965')");
			stmt.executeUpdate("insert into hiringrecords values"
					+ "('S_157_C_753_15102019','15/10/2019','20/10/2019','20/10/2019',4995,0,'S_157')");
			stmt.executeUpdate("insert into hiringrecords values"
					+ "('S_157_C_994_02102019','02/10/2019','06/10/2019','06/10/2019',3996,0,'S_157')");
			stmt.executeUpdate("insert into hiringrecords values"
					+ "('S_365_C_753_21102019','21/10/2019','26/10/2019','26/10/2019',4995,0,'S_365')");
			stmt.executeUpdate("insert into hiringrecords values"
					+ "('S_365_C_994_07102019','07/10/2019','11/10/2019','11/10/2019',3996,0,'S_365')");
			stmt.executeUpdate("insert into hiringrecords values"
					+ "('S_954_C_753_27102019','27/10/2019','01/11/2019','01/11/2019',4995,0,'S_954')");
			stmt.executeUpdate("insert into hiringrecords values"
					+ "('S_954_C_994_12102019','12/10/2019','16/10/2019','16/10/2019',3996,0,'S_954')");
		
			con.commit();
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
}
