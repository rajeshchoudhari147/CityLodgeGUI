package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import controller.RoomDetailController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.AbstractRoom;
import model.Suite;

public class RoomDetailWindow extends BorderPane 
{
	private AbstractRoom abstractRoom;
	private GridPane grid;
	private Button rentRoom = new Button("Rent Room");
	private Button returnRoom = new Button("Return Room");
	private Button maintainRoom = new Button("Maintain Room");
	private Button completeRoom = new Button("Complete Maintainance");

	public AbstractRoom getRoom() 
	{
		return abstractRoom;
	}

	public void setRoom(AbstractRoom abstractRoom) 
	{
		this.abstractRoom = abstractRoom;
	}

	public RoomDetailWindow(AbstractRoom abstractRoom) throws FileNotFoundException 
	{
		try
		{
		this.abstractRoom = abstractRoom;
		@SuppressWarnings("rawtypes")
		Dialog dialog = new Dialog();
		dialog.setResizable(false);
		ButtonType buttonTypeOk = new ButtonType("Close", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

		grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		grid.setHgap(5);
		grid.setVgap(20);
		grid.setMaxSize(2000, 2000);

		RoomDetailController handler = new RoomDetailController(this);

		rentRoom.setOnAction(handler);
		returnRoom.setOnAction(handler);
		maintainRoom.setOnAction(handler);
		completeRoom.setOnAction(handler);

		VBox vbox = new VBox();
		vbox.getChildren().addAll(rentRoom, returnRoom, maintainRoom, completeRoom);
		vbox.setSpacing(60);
		vbox.setPadding(new Insets(150, 0, 0, 40));

		this.setLeft(vbox);

		FileInputStream input;
		// if room does not have a image database and text file store "no image" in
		// image attribute.
		// if image attribute contains "no image" display a no image available logo
		if (abstractRoom.getImageName().equals("no image"))
		{
			input = new FileInputStream("images/no image.jpg");
		} 
		else 
		{
			input = new FileInputStream("images/" + abstractRoom.getImageName() + ".jpg");
		}

		Image image = new Image(input);
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(250);
		imageView.setFitWidth(350);
		grid.add(imageView, 20, 0);

		grid.add(new Label(abstractRoom.getRoomType()), 20, 1);
		grid.add(new Label("Number of Beds: " + abstractRoom.getNoOfBeds()), 20, 2);
		grid.add(new Label("Status: " + abstractRoom.getRoomStatus()), 20, 3);
		grid.add(new Label("Features: " + abstractRoom.getSummary()), 20, 4);

		// show last maintenance date on page only if not null
		if (abstractRoom instanceof Suite && ((Suite) abstractRoom).getLastMaintenanceDate() != null) 
		{
			grid.add(new Label("Last Maintenance Date: " + ((Suite) abstractRoom).getLastMaintenanceDate()), 20, 5);
			grid.add(new Label("Rental Records:"), 20, 6);
		}
		else
			grid.add(new Label("Rental Records:"), 20, 5);

		VBox v = new VBox();

		if (abstractRoom.getHiringRecordList().size() == 0)
		{
			v.getChildren().addAll(new Label("Empty!"));
		} 
		else
		{
			for (int i = abstractRoom.getHiringRecordList().size() - 1; i >= 0; i--) 
			{
				if (abstractRoom.getHiringRecordList().get(i) != null)
				{
					GridPane newGrid = new GridPane();
					newGrid.add(new Label("Record Id: " + abstractRoom.getHiringRecordList().get(i).getRecordID()), 20, 1);
					newGrid.add(new Label("Rent Date: " + abstractRoom.getHiringRecordList().get(i).getRentDate()), 20, 2);
					newGrid.add(new Label("Estimated Return Date:" + abstractRoom.getHiringRecordList().get(i).getEstimatedReturnDate()), 20, 3);
					newGrid.add(new Label(" "), 20, 4);
					// show complete rental record only if room is returned i.e. has a actual return date
					if (abstractRoom.getHiringRecordList().get(i).getActualReturnDate() != null)
					{
						newGrid.add(new Label("Actual Return Date:" + abstractRoom.getHiringRecordList().get(i).getActualReturnDate()),	20, 4);
						newGrid.add(new Label("Rent Fees:" + abstractRoom.getHiringRecordList().get(i).getRentalFee()), 20, 5);
						newGrid.add(new Label("Late Fees:" + abstractRoom.getHiringRecordList().get(i).getLateFee()), 20, 6);
						newGrid.add(new Label(""), 20, 7);
					}
					v.getChildren().addAll(newGrid);
				}
			}
		}

		ScrollPane scrollPane = new ScrollPane(v);
		scrollPane.setFitToWidth(true);
		scrollPane.setStyle("-fx-background-color:transparent;");
		grid.add(scrollPane, 20, 7);
		this.setCenter(grid);
		}
		catch(Exception e)
		{
			new ErrorDialog(e.getMessage());
		}
	}
}
