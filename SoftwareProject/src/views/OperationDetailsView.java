package views;

import javafx.scene.control.Label;
import models.Operation;
import models.Patient;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OperationDetailsView extends GridPane {
	
    private Stage parentStage;

    public OperationDetailsView(Operation operation){
        //setMinWidth(400);
       int x=0,y=0;
       setHgap(20);
       setVgap(10);
       setPadding(new Insets(30,30,30,30));
       Label[] names=new Label[20],values=new Label[20];

        names[y]=new Label("Doctor Name");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ operation.getDoctorName());
        values[y].getStyleClass().add("value");
        add(names[y],x,y,1,1);
        add(values[y],++x,y,1,1);
        
        
        names[++y]=new Label("Patient Name");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ operation.getPatientName());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);

        
        names[++y]=new Label("Appointment Date");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ operation.getAppointmentDate());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);
        
        
        names[++y]=new Label("Machine Id");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ operation.getMachineId());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);
        
        
        names[++y]=new Label("Machine Room Number");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ operation.getMachineRoomNb());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);
        
        names[++y]=new Label("Machine State");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ operation.getMachineState());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);
        
        
        names[++y]=new Label("Surgery Name");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ operation.getName());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);
        
        names[++y]=new Label("Surgery Description");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ operation.getDescription());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);
        
        
        names[++y]=new Label("Surgery Duration");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ operation.getDuration());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);
        
        names[++y]=new Label("Registration Date");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ operation.getRegistrationDate());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);
        
        names[++y]=new Label("Start Time");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ operation.getStartTime());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);
        
        
        names[++y]=new Label("End Time");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ operation.getEndTime());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);
        
        
        getStylesheets().add(getClass().getResource("/css/details.css").toExternalForm());

    }
    public void setParent(Stage parentStage) {
        this.parentStage = parentStage;
    }

}
