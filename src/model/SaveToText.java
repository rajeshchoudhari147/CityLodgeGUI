package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;

import util.DateTime;

public class SaveToText
{
	public static void readFromFile(String fileName) throws FileNotFoundException
	{
		CityLodge.getInstance().getRoomList().clear();
		String dataArray[] = new String[10];
		AbstractRoom abstractRoom = null;
		File file = new File(fileName);

		try (Scanner input = new Scanner(file))
		{
			while (input.hasNextLine()) 
			{
				String data = input.nextLine();
				dataArray = data.split(":");
				// rental records line in text file consists of 6 strings separated by
				// delimiter. vehicle
				// record consists of 7 or 8 strings separated by delimiter. if length is not 6
				// make a room object
				if (dataArray.length != 5) 
				{
					if (abstractRoom != null)
						Collections.reverse(abstractRoom.getHiringRecordList());
					if (dataArray[0].contains("S")) {
						abstractRoom = new Suite(dataArray[0], Integer.parseInt(dataArray[1]), dataArray[5], dataArray[2], dataArray[3], dataArray[6]);
						if (!dataArray[4].equals("null"))
						{
							((Suite) abstractRoom).setLastMaintenanceDate(DateTime.stringToDateTime(dataArray[4]));
						}
					}
					else 
					{
						abstractRoom = new StandardRoom(dataArray[0], Integer.parseInt(dataArray[1]), dataArray[4], dataArray[2], dataArray[3], dataArray[5]);

					}
					CityLodge.getInstance().getRoomList().add(abstractRoom);
				}
				else
				{

                    HiringRecord hiringRecord = null;
                    if (!dataArray[3].equals("none"))
                    {
                    	hiringRecord = new HiringRecord(
                    			dataArray[0],
                    			DateTime.stringToDateTime(dataArray[1]),
                    			DateTime.stringToDateTime(dataArray[2]), 
                    			DateTime.stringToDateTime(dataArray[3]),
                                Float.parseFloat(dataArray[4]),
                                Float.parseFloat(dataArray[5]));
                    } 
                    else
                    {
                    	hiringRecord = new HiringRecord(
                    			dataArray[0],
                    			DateTime.stringToDateTime(dataArray[1]),
                    			DateTime.stringToDateTime(dataArray[2]));
                    }
                    abstractRoom.getHiringRecordList().add(hiringRecord);
				}
			}

		} catch (Exception e) 
		{
			throw new FileNotFoundException();
		}
	}

	public static void writeToFile(String directoryName) throws FileNotFoundException 
	{
		File file = new File(directoryName + "/export_data.txt");
		try 
		{
			PrintWriter output = new PrintWriter(file);
			for (int i = 0; i <= CityLodge.getInstance().getRoomList().size() - 1; i++) 
			{
				output.write(CityLodge.getInstance().getRoomList().get(i).toString() + "\n");
				for (int j = CityLodge.getInstance().getRoomList().get(i).getHiringRecordList().size() - 1; j >= 0; j--) 
				{
					if (CityLodge.getInstance().getRoomList().get(i).getHiringRecordList().get(j) != null)
						output.write(CityLodge.getInstance().getRoomList().get(i).getHiringRecordList().get(j).toString() + "\n");
				}
			}
			output.close();
		}
		catch (FileNotFoundException e) 
		{
			throw new FileNotFoundException();
		}
	}
}
