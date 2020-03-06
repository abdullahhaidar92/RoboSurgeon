package views;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.Database;
import controllers.AssisstantDashboard;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Assistant;

public class AssistantDeleteView extends GridPane{
	private Stage parentStage;
	public AssistantDeleteView(Assistant a) {
		GridPane grid = this;

		setPadding(new Insets(30,30,30,30));
        setVgap(10);
		setHgap(20);
		Label question = new Label(" Are you sure you want to delete this "+"\n"+"assistant?");
		question.getStyleClass().add("name");
		Label firstname = new Label("First Name: ");
		firstname.getStyleClass().add("name");
		Label lastname = new Label("Last Name: ");
		lastname.getStyleClass().add("name");
		Label middlename = new Label("Middle Name: ");
		middlename.getStyleClass().add("name");
		Label fname = new Label(a.getFirstName().trim());

		Label lname = new Label(a.getLastName().trim());

		Label mname = new Label(a.getMiddleName().trim());

		Button delete = new Button("Delete");
		delete.setPrefSize(100,30);
		delete.getStyleClass().add("delete");
		delete.setOnAction(e->{
			int id = a.getAssistantId();
			ResultSet resultSet = Database.getResults("select profileID from dbo.[User] where USERID = "
					+ "(select UserId from ASSISTANT where ASSISTANTID = " + id +")");
			if(resultSet != null) {
				try {
					resultSet.next();
					int pid = resultSet.getInt("PROFILEID");
					Database.execute("delete from ASSISTANT where ASSISTANTID = " + id);
					Database.execute("delete from dbo.[User] where PROFILEID = "+ pid);
					Database.execute("delete from PROFILE where PROFILEID = "+ pid);
					 Alert alert = new Alert(AlertType.INFORMATION);
	     			 alert.setTitle("Delete Assistant " );
	     			 alert.setHeaderText(null);
	     		
	     			 alert.setContentText("Assistant has been deleted successfully");
	     			 alert.showAndWait();
	        	     
	        	     Stage stage = (Stage) this.getScene().getWindow();
	                 stage.close();
					AssisstantDashboard.setFlag(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		grid.add(question, 0, 1,2,1);
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
