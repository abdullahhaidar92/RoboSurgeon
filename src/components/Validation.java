
package components;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Validation {
	
	public static boolean validateNumber(TextField text, String field)
	{
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(text.getText());
		if(m.find() && m.group().equals(text.getText()))
		{
			return true;
			
		} else
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Validate " +  field );
			alert.setHeaderText(null);
		
			alert.setContentText("Please Enter Valid " + field);
			alert.showAndWait();
			return false;	
		}
		
		

	}
	
	public static boolean validateString(TextField text, String field)
	{
		Pattern p = Pattern.compile("([A-Za-z]*)");
		Matcher m = p.matcher(text.getText());
		if(m.find() && m.group().equals(text.getText()))
		{
			return true;
			
		} else
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Validate " + field );
			alert.setHeaderText(null);
		
			alert.setContentText("Please Enter Valid " + field);
			alert.showAndWait();
			return false;	
		}
		
		

	}
	
	
	public static boolean validateEmail(TextField text)
	{
		Pattern p = Pattern.compile("([a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+)");
		Matcher m = p.matcher(text.getText());
		if(m.find() && m.group().equals(text.getText()))
		{
			return true;
			
		} else
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Validate Email");
			alert.setHeaderText(null);
		
			alert.setContentText("Please Enter Valid Email");
			alert.showAndWait();
			return false;	
		}
		
		

	}
	
	public static boolean validateConfirmPassword(TextField password, TextField confirmPassword)
	{
	
		if(password.getText().equals(confirmPassword.getText()))
		{
			return true;
			
		} else
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Validate Confirm Password");
			alert.setHeaderText(null);
		
			alert.setContentText("Please Enter Valid  Confirm Password");
			alert.showAndWait();
			return false;	
		}
		
		

	}
	
	
}
