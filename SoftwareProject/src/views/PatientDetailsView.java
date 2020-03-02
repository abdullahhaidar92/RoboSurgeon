package views;

import javafx.scene.control.Label;
import models.Patient;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PatientDetailsView extends GridPane {
	
    private Stage parentStage;

    public PatientDetailsView(Patient patient){
        //setMinWidth(400);
       int x=0,y=0;
       setHgap(20);
       setVgap(10);
       setPadding(new Insets(30,30,30,30));
       Label[] names=new Label[20],values=new Label[20];

        names[y]=new Label("Patient Id");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ patient.getPatientId());
        values[y].getStyleClass().add("value");
        add(names[y],x,y,1,1);
        add(values[y],++x,y,1,1);

        names[++y]=new Label("First Name");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ patient.getFirstName());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);

        names[++y]=new Label("Middle Name");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ patient.getMiddleName());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);


        names[++y]=new Label("Last Name");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ patient.getLastName());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);


        names[++y]=new Label("Birthdate");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ patient.getBirthDate());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);


        names[++y]=new Label("Email");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ patient.getEmail());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);

        names[++y]=new Label("Phone");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ patient.getPhone());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);

        names[++y]=new Label("Blood Type");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ patient.getBloodType());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);
        
        names[++y]=new Label("Pulse");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ patient.getPulse());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);
        
        names[++y]=new Label("Normal Pulse");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ patient.getNormalPulse());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);
        
        names[++y]=new Label("Oxygen");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ patient.getOxygen());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);
        
        names[++y]=new Label("Normal Oxygen");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ patient.getNormalOxygen());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);

        names[++y]=new Label("Pressure");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ patient.getPressure());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);
        
        names[++y]=new Label("Normal Pressure");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ patient.getNormalPressure());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);
        
        names[++y]=new Label("Temperature");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ patient.getTemperature());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);
        
        names[++y]=new Label("Normal Temperature");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ patient.getNormalTemperature());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);
        
        names[++y]=new Label("Weight");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ patient.getWeight());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);
        
        names[++y]=new Label("Height");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ patient.getHeight());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
        add(values[y],++x,y,1,1);
        
        
        
        getStylesheets().add(getClass().getResource("/css/details.css").toExternalForm());

    }
    public void setParent(Stage parentStage) {
        this.parentStage = parentStage;
    }

}
