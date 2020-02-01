package views;

import javafx.scene.control.Label;
import models.Doctor;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DoctorDetailsView extends GridPane {

    private Stage parentStage;

    public DoctorDetailsView(Doctor doctor){
        //setMinWidth(400);
       int y=0;
       setHgap(20);
       setVgap(10);
       setPadding(new Insets(30,30,30,30));
        Label[] names=new Label[9],values=new Label[9];

        names[y]=new Label("Doctor Id");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ doctor.getDoctorId());
        values[y].getStyleClass().add("value");
        addRow(y,names[y],values[y]);

        names[++y]=new Label("First Name");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ doctor.getFirstName());
        values[y].getStyleClass().add("value");
        addRow(y,names[y],values[y]);

        names[++y]=new Label("Middle Name");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ doctor.getMiddleName());
        values[y].getStyleClass().add("value");
        addRow(y,names[y],values[y]);


        names[++y]=new Label("Last Name");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ doctor.getLastName());
        values[y].getStyleClass().add("value");
        addRow(y,names[y],values[y]);

        names[++y]=new Label("Speciality");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ doctor.getSpeciality());
        values[y].getStyleClass().add("value");
        addRow(y,names[y],values[y]);



        names[++y]=new Label("Birthdate");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ doctor.getBirthDate());
        values[y].getStyleClass().add("value");
        addRow(y,names[y],values[y]);



        names[++y]=new Label("Email");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ doctor.getEmail());
        values[y].getStyleClass().add("value");
        addRow(y,names[y],values[y]);


        names[++y]=new Label("Phone");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ doctor.getPhone());
        values[y].getStyleClass().add("value");
        addRow(y,names[y],values[y]);


        names[++y]=new Label("Blood Type");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ doctor.getBloodType());
        values[y].getStyleClass().add("value");
        addRow(y,names[y],values[y]);


        getStylesheets().add(getClass().getResource("/css/details.css").toExternalForm());

    }
    public void setParent(Stage parentStage) {
        this.parentStage = parentStage;
    }

}
