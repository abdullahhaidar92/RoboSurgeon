package views;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.Database;
import controllers.AssisstantDashboard;
import controllers.DoctorsDashboard;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Doctor;

public class DoctorDeleteView extends GridPane{
	private Stage parentStage;
	public DoctorDeleteView(Doctor d) {
		GridPane grid = this;
		
		grid.setPadding(new Insets(10, 10, 10, 10));
    	grid.setVgap(30);
		Label question = new Label("Are you sure you want to delete "+"\n"+"this doctor?");
		question.getStyleClass().add("question");
		Label firstname = new Label("First Name: ");
		firstname.getStyleClass().add("name");
		Label lastname = new Label("Last Name: ");
		lastname.getStyleClass().add("name");
		Label middlename = new Label("Middle Name: ");
		middlename.getStyleClass().add("name");
		Label fname = new Label(d.getFirstName());
		fname.getStyleClass().add("value");
		Label lname = new Label(d.getLastName());
		lname.getStyleClass().add("value");
		Label mname = new Label(d.getMiddleName());
		mname.getStyleClass().add("value");
		Button delete = new Button("Delete");
		delete.getStyleClass().add("delete");
		delete.setOnAction(e->{
			int id = d.getDoctorId();
			ResultSet resultSet = Database.getResults("select profileID from dbo.[User] where USERID = "
					+ "(select UserId from Doctor where DOCTORID = " + id +")");
			if(resultSet != null) {
				try {
					resultSet.next();
					int pid = resultSet.getInt("PROFILEID");
					Database.execute("UPDATE [dbo].[ASSISTANT] SET [DOCTORID] = null\r\n" + 
							" WHERE DOCTORID = "+ id );
					Database.execute("delete from operation where DOCTORID = "+ id);
					Database.execute("delete from DOCTOR where DOCTORID = " + id);
					Database.execute("delete from dbo.[User] where PROFILEID = "+ pid);
					Database.execute("delete from PROFILE where PROFILEID = "+ pid);
					Alert alert = new Alert(AlertType.INFORMATION);
	     			 alert.setTitle("Delete Doctor " );
	     			 alert.setHeaderText(null);
	     		
	     			 alert.setContentText("Doctor has been deleted successfully");
	     			 alert.showAndWait();
	        	     
	        	     Stage stage = (Stage) this.getScene().getWindow();
	                 stage.close();
					DoctorsDashboard.setFlag(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		grid.add(question, 0, 1);
		grid.add(firstname, 0, 2);
		grid.add(middlename, 0, 3);
		grid.add(lastname, 0, 4);
		
		grid.add(fname, 1, 2);
		grid.add(mname, 1, 3);
		grid.add(lname, 1, 4);
		
		grid.add(delete, 1, 5);
		getStylesheets().add(getClass().getResource("/css/details.css").toExternalForm());
		
	}
	
	 public void setParent(Stage parentStage) {
	        this.parentStage = parentStage;
	    }

}
