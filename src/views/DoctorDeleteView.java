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
		Label[] names=new Label[4],values=new Label[4];
		int x=0,y=0;
		setHgap(20);
		setVgap(10);
		setPadding(new Insets(30,30,30,30));

		Label label=new Label(" Are you sure you want to delete this "+" \n "+"doctor?");
		add(label,x++,y,2,1);
		label.getStyleClass().add("name");

		names[++y]=new Label("First Name: ");
		names[y].getStyleClass().add("name");
		values[y]=new Label(d.getFirstName());
		values[y].getStyleClass().add("value");
		add(names[y],--x,y,1,1);
		add(values[y],++x,y,1,1);


		names[++y]=new Label("Middle Name: ");
		names[y].getStyleClass().add("name");
		values[y]=new Label(d.getMiddleName());
		values[y].getStyleClass().add("value");
		add(names[y],--x,y,1,1);
		add(values[y],++x,y,1,1);


		names[++y]=new Label("Last Name: ");
		names[y].getStyleClass().add("name");
		values[y]= new Label(d.getLastName());
		values[y].getStyleClass().add("value");
		add(names[y],--x,y,1,1);
		add(values[y],++x,y,1,1);


		addRow(++y,new Label(" "));
		Button confirmDelete =new Button(" Confirm  ");
		confirmDelete.setPrefSize(100,30);
		confirmDelete.getStyleClass().add("delete");
		addRow(++y,new Label(" "),confirmDelete);

		getStylesheets().add(getClass().getResource("/css/details.css").toExternalForm());

		confirmDelete.setOnAction(e->{
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

		getStylesheets().add(getClass().getResource("/css/details.css").toExternalForm());
		
	}
	
	 public void setParent(Stage parentStage) {
	        this.parentStage = parentStage;
	    }

}
