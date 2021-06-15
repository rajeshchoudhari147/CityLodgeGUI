package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SuccessDialog 
{
	public SuccessDialog(String message) 
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}