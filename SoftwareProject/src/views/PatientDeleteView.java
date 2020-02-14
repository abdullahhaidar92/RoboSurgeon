package views;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.Database;
import controllers.PatientDashboard;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Patient;

public class PatientDeleteView extends GridPane{
	private Stage parentStage;
	public PatientDeleteView(Patient p) {
		GridPane grid = this;
		
		grid.setPadding(new Insets(10, 10, 10, 10));
    	grid.setVgap(30);
		Label question = new Label("Are you sure you want to delete "+"\n"+"this patient?");
		question.getStyleClass().add("question");
		Label firstname = new Label("First Name: ");
		firstname.getStyleClass().add("name");
		Label lastname = new Label("Last Name: ");
		lastname.getStyleClass().add("name");
		Label middlename = new Label("Middle Name: ");
		middlename.getStyleClass().add("name");
		Label fname = new Label(p.getFirstName());
		fname.getStyleClass().add("value");
		Label lname = new Label(p.getLastName());
		lname.getStyleClass().add("value");
		Label mname = new Label(p.getMiddleName());
		mname.getStyleClass().add("value");
		Button delete = new Button("Delete");
		delete.getStyleClass().add("delete");
		delete.setOnAction(e->{
			int id = p.getPatientId();
			ResultSet resultSet = Database.getResults("select profileID from Patient"
					+ " where PATIENTID = " + id);
			if(resultSet != null) {
				try {
					resultSet.next();
					int pid = resultSet.getInt("PROFILEID");
					Database.execute("delete from OPERATION where PATIENTID = " + id);
					Database.execute("delete from PATIENT where PATIENTID = " + id);
					Database.execute("delete from PROFILE where PROFILEID = " + pid);
					
					 Alert alert = new Alert(AlertType.INFORMATION);
	     			 alert.setTitle("Delete Patient " );
	     			 alert.setHeaderText(null);
	     		
	     			 alert.setContentText("Patient has been deleted successfully");
	     			 alert.showAndWait();
	        	     
	        	     Stage stage = (Stage) this.getScene().getWindow();
	                 stage.close();
					 PatientDashboard.setFlag(true);
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
