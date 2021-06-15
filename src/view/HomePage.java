package view;

import java.io.FileNotFoundException;

import controller.HomePageController;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.CityLodge;
import model.AbstractRoom;

public class HomePage extends BorderPane
{
	private static HomePage single_instance = null;
	private FilteredList<AbstractRoom> filteredItems = new FilteredList<AbstractRoom>(CityLodge.getInstance().getRoomList()); // added
	private ListView<AbstractRoom> roomListView = new ListView<>();
	private HBox hbox = new HBox();
	private HomePageController homePage = new HomePageController(this);

	private ChoiceBox<String> statusChoiceBox = new ChoiceBox<String>();
	private ChoiceBox<String> typeChoiceBox = new ChoiceBox<String>();
	private ChoiceBox<String> bedsChoiceBox = new ChoiceBox<String>();

	private Label filterByStatusLabel = new Label("Room Status: ");
	private Label filterByTypeLabel = new Label("Room Type: ");
	private Label filterByBedsLabel = new Label("Number of Beds: ");

	private HomePage() throws FileNotFoundException 
	{
		roomListView.setCellFactory(new RoomList());
		roomListView.setItems(filteredItems);

		statusChoiceBox.setValue("All");
		typeChoiceBox.setValue("All");
		bedsChoiceBox.setValue("All");

		statusChoiceBox.getItems().add("All");
		statusChoiceBox.getItems().add("Available");
		statusChoiceBox.getItems().add("Rented");
		statusChoiceBox.getItems().add("Under Maintainance");

		bedsChoiceBox.getItems().add("All");
		bedsChoiceBox.getItems().add("1");
		bedsChoiceBox.getItems().add("2");
		bedsChoiceBox.getItems().add("4");
		bedsChoiceBox.getItems().add("6");

		typeChoiceBox.getItems().add("All");
		typeChoiceBox.getItems().add("Room");
		typeChoiceBox.getItems().add("Suite");

		hbox.getChildren().addAll(filterByStatusLabel, statusChoiceBox, filterByTypeLabel, typeChoiceBox, filterByBedsLabel, bedsChoiceBox);
		hbox.setSpacing(20);
		hbox.setPadding(new Insets(15, 12, 15, 12));

		ScrollPane scrollPane = new ScrollPane(roomListView);
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);
		this.setCenter(scrollPane);
		this.setTop(hbox);

		Insets insets = new Insets(20, 0, 90, 0);
		BorderPane.setMargin(hbox, insets);

		statusChoiceBox.setOnAction(homePage);
		typeChoiceBox.setOnAction(homePage);
		bedsChoiceBox.setOnAction(homePage);

	}

	public FilteredList<AbstractRoom> getFilteredItems() 
	{
		return filteredItems;
	}

	public ChoiceBox<String> getTypeChoiceBox() 
	{
		return typeChoiceBox;
	}

	public void setTypeChoiceBox(ChoiceBox<String> typeChoiceBox)
	{
		this.typeChoiceBox = typeChoiceBox;
	}

	public ChoiceBox<String> getBedsChoiceBox() 
	{
		return bedsChoiceBox;
	}

	public void setBedsChoiceBox(ChoiceBox<String> seatsChoiceBox)
	{
		this.bedsChoiceBox = seatsChoiceBox;
	}

	public ChoiceBox<String> getStatusChoiceBox() 
	{
		return statusChoiceBox;
	}

	public void setChoiceBox(ChoiceBox<String> statusChoiceBox) 
	{
		this.statusChoiceBox = statusChoiceBox;
	}

	// making home page singleton to save its state
	public static HomePage getInstance() throws FileNotFoundException
	{
		if (single_instance == null) 
		{
			single_instance = new HomePage();
		}
		return single_instance;
	}
}