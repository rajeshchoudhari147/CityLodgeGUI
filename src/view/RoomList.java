package view;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.AbstractRoom;

public class RoomList implements Callback<ListView<AbstractRoom>, ListCell<AbstractRoom>> 
{
	@Override
	public ListCell<AbstractRoom> call(ListView<AbstractRoom> listview)
	{
		return new RoomInformation();
	}
}
