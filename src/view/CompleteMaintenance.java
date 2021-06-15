package view;

import controller.CompleteMaintenanceController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.AbstractRoom;

public class CompleteMaintenance extends GridPane 
{
	private TextField maintainanceCompletionDate = new TextField();
	private AbstractRoom abstractRoom;

	public CompleteMaintenance(AbstractRoom abstractRoom) 
	{
		this.abstractRoom = abstractRoom;

		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		this.setHgap(5.5);
		this.setVgap(35.5);

		// Place nodes in the pane

		this.add(new Label("Enter the maintainance completion date:"), 6, 1);
		this.add(maintainanceCompletionDate, 9, 1);

		Button maintainanceCompletion = new Button("Complete Maintenance");
		this.add(maintainanceCompletion, 8, 3);

		CompleteMaintenanceController c = new CompleteMaintenanceController(this);
		maintainanceCompletion.setOnAction(c);

	}

	public String getMaintenanceCompletionDate()
	{
		return this.maintainanceCompletionDate.getText();
	}

	public void setMaintenanceCompletionDate(String maintainanceCompletionDate) 
	{
		this.maintainanceCompletionDate.setText(maintainanceCompletionDate);
	}

	public AbstractRoom getRoom() 
	{
		return abstractRoom;
	}

	public void setRoom(AbstractRoom abstractRoom) 
	{
		this.abstractRoom = abstractRoom;
	}

}
