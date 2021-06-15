package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import controller.RoomInfoController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.AbstractRoom;

public class RoomInformation extends ListCell<AbstractRoom> 
{
	private BorderPane borderPane = new BorderPane();
	private AbstractRoom abstractRoom;

	public BorderPane getBorderPane() 
	{
		return borderPane;
	}

	public void setBorderPane(BorderPane borderPane) 
	{
		this.borderPane = borderPane;
	}

	public AbstractRoom getRoom()
	{
		return abstractRoom;
	}

	public void setRoom(AbstractRoom abstractRoom) 
	{
		this.abstractRoom = abstractRoom;
	}

	// logic to convert image to image view to display
	private ImageView imageToImageView(String imageName) 
	{
		FileInputStream file = null;
		try
		{
			file = new FileInputStream(imageName);
		} 
		catch (FileNotFoundException e) 
		{
			new ErrorDialog(e.getMessage());
		}
		Image iconImage = new Image(file);
		return new ImageView(iconImage);
	}

	@Override
	public void updateItem(AbstractRoom abstractRoom, boolean empty)
	{
		super.updateItem(abstractRoom, empty);
		if (abstractRoom == null || empty)
		{
			setGraphic(null);
		}
		else
		{
			this.abstractRoom = abstractRoom;
			RoomInfoController handler = new RoomInfoController(abstractRoom);

			borderPane.setMaxSize(900, 300);
			borderPane.setStyle("-fx-border-color: black");

			ImageView imageView;
			if (abstractRoom.getImageName().equals("no image"))
			{
				imageView = imageToImageView("images/no image.jpg");
			} 
			else 
			{
				imageView = imageToImageView("images/" + abstractRoom.getImageName() + ".jpg");
			}
			imageView.setFitHeight(200);
			imageView.setFitWidth(300);
			borderPane.setLeft(imageView);

			Button bookButton = new Button("View");
			bookButton.setOnAction(handler);
			borderPane.setRight(bookButton);
			BorderPane.setAlignment(bookButton, Pos.CENTER);

			VBox vBox = new VBox();

			Label l = new Label(abstractRoom.getRoomType() + ":" + abstractRoom.getRoomStatus());
			l.setStyle("-fx-font: 20 arial;");

			HBox h = new HBox();

			String[] imageNames = new String[3];
			// names of icons displayed in list view on home page
			imageNames[0] = "images/bed.png";
			imageNames[1] = "images/feature.png";
			imageNames[2] = "images/dollar.png";

			ImageView[] images = new ImageView[3];

			// converting to image views
			for (int i = 0; i <= 2; i++)
			{
				images[i] = imageToImageView(imageNames[i]);
				images[i].setFitHeight(40);
				images[i].setFitWidth(40);
			}

			String beds = Integer.toString(abstractRoom.getNoOfBeds());
			Label bedLabel = new Label((":" + beds));
			bedLabel.setStyle("-fx-font: 15 arial;");
			bedLabel.setPadding(new Insets(15, 0, 00, 00));

			String feature = abstractRoom.getSummary();
			Label featureLabel = new Label((":" + feature));
			featureLabel.setStyle("-fx-font: 15 arial;");
			featureLabel.setPadding(new Insets(15, 0, 00, 00));
			
			int rent = getRentAmount();
			Label rentLabel = new Label((":" + rent));
			rentLabel.setStyle("-fx-font: 20 arial;");
			rentLabel.setPadding(new Insets(15, 0, 00, 00));
			
			h.getChildren().addAll(images[0], bedLabel, images[1], featureLabel, images[2], rentLabel);
			h.setSpacing(20);

			vBox.getChildren().addAll(l, h);
			vBox.setSpacing(40);
			vBox.setPadding(new Insets(10, 0, 10, 10));

			borderPane.setCenter(vBox);
			setGraphic(borderPane);
		}
	}
	
	public int getRentAmount()
	{
		int rent = 0;
		int beds = abstractRoom.getNoOfBeds();
		if(beds == 1)
		{
			rent = 59;
		}
		if(beds == 2)
		{
			rent = 99;
		}
		if(beds == 4)
		{
			rent = 199;
		}
		if(beds == 6)
		{
			rent = 999;
		}
		return rent;
	}
}