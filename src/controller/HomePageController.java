package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.HomePage;

public class HomePageController implements EventHandler<ActionEvent>
{	
	private HomePage theView;

	public HomePageController(HomePage theView)
	{
		this.theView = theView;
	}

	@Override
	public void handle(ActionEvent event) 
	{
		theView.getFilteredItems().setPredicate(item -> {
			// logic to filter room,should satisfy all filter logic and then display the rooms
			if (((item.getRoomStatus().equals(theView.getStatusChoiceBox().getValue().toLowerCase()))
					|| ("all").equals(theView.getStatusChoiceBox().getValue().toLowerCase()))
					&& (((item.getRoomType().toLowerCase().equals(theView.getTypeChoiceBox().getValue().toLowerCase()))
							|| ("all").equals(theView.getTypeChoiceBox().getValue().toLowerCase())))
					&& (((Integer.toString(item.getNoOfBeds()).toLowerCase().equals(theView.getBedsChoiceBox().getValue().toLowerCase()))
							|| ("all").equals(theView.getBedsChoiceBox().getValue().toLowerCase()))))
			{
				return true;
			} 
			else 
			{
				return false;
			}
		});
	}
}
