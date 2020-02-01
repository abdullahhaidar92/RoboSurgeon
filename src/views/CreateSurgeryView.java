package views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import models.Doctor;

import java.util.ArrayList;

public class CreateSurgeryView extends GridPane {

public CreateSurgeryView(Doctor doctor, ArrayList<String> patients,ArrayList<String> surgeries){
    int x=0,y=0,width=220;
    setHgap(20);
    setVgap(10);
    setPadding(new Insets(30,30,30,30));
    Label[] names=new Label[9],values=new Label[9];

    names[y]=new Label("Patient ");
    names[y].getStyleClass().add("name");
    ChoiceBox patChoice=new ChoiceBox();
    patChoice.getItems().setAll(patients);
    patChoice.getSelectionModel().selectFirst();
    patChoice.setPrefWidth(width);
    addRow(y,names[y],patChoice);

    names[++y]=new Label("Surgery Type ");
    names[y].getStyleClass().add("name");
    ChoiceBox surgChoice=new ChoiceBox();
    surgChoice.getItems().setAll(surgeries);
    surgChoice.getSelectionModel().selectFirst();
    surgChoice.setPrefWidth(width);
    addRow(y,names[y],surgChoice);

    names[++y]=new Label("Assigned Date");
    names[y].getStyleClass().add("name");
    DatePicker datePicker = new DatePicker();
    datePicker.setPrefWidth(width);
    addRow(y,names[y],datePicker);

    Button save =new Button("save");
    save.setPrefSize(100,30);
    addRow(++y,new Label(" "),save);
    save.setOnAction(e->{
        System.out.println(patChoice.getSelectionModel().getSelectedIndex());
        System.out.println(surgChoice.getSelectionModel().getSelectedIndex());
        System.out.println(datePicker.getValue());
    });
    getStylesheets().add(getClass().getResource("/css/createSurgery.css").toExternalForm());

}

}
