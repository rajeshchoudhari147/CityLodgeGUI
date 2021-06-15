package view;

import controller.ReturnRoomController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.AbstractRoom;

public class ReturnRoom extends GridPane 
{
	private AbstractRoom abstractRoom;
	private TextField returnDate = new TextField();

	public AbstractRoom getRoom() 
	{
		return abstractRoom;
	}

	public void setRoom(AbstractRoom abstractRoom) 
	{
		this.abstractRoom = abstractRoom;
	}

	public ReturnRoom(AbstractRoom abstractRoom)
	{
		this.setAlignment(Pos.CENTER);
		this.abstractRoom = abstractRoom;
		this.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		this.setHgap(5.5);
		this.setVgap(35.5);

		this.add(new Label("Enter the return date:"), 7, 1);
		this.add(returnDate, 10, 1);

		Button returnRoom = new Button("Return Room");
		this.add(returnRoom, 8, 3);

		ReturnRoomController r = new ReturnRoomController(this);
		returnRoom.setOnAction(r);
	}

	public void setReturnDate(String returnDate)
	{
		this.returnDate.setText(returnDate);
	}

	public String getReturnDate() 
	{
		return this.returnDate.getText();
	}
}
