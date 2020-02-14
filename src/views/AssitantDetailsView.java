package views;

import javafx.scene.control.Label;
import models.Assistant;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AssitantDetailsView extends GridPane {

    private Stage parentStage;

    public AssitantDetailsView(Assistant assistant){
       int y=0;
       setHgap(20);
       setVgap(10);
       setPadding(new Insets(30,30,30,30));
        Label[] names=new Label[9],values=new Label[9];

        names[y]=new Label("Assistant Id");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ assistant.getAssistantId());
        values[y].getStyleClass().add("value");
        addRow(y,names[y],values[y]);

        names[++y]=new Label("First Name");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ assistant.getFirstName());
        values[y].getStyleClass().add("value");
        addRow(y,names[y],values[y]);

        names[++y]=new Label("Middle Name");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ assistant.getMiddleName());
        values[y].getStyleClass().add("value");
        addRow(y,names[y],values[y]);


        names[++y]=new Label("Last Name");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ assistant.getLastName());
        values[y].getStyleClass().add("value");
        addRow(y,names[y],values[y]);

        names[++y]=new Label("Contract Type");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ assistant.getContractType());
        values[y].getStyleClass().add("value");
        addRow(y,names[y],values[y]);



        names[++y]=new Label("Birthdate");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ assistant.getBirthDate());
        values[y].getStyleClass().add("value");
        addRow(y,names[y],values[y]);



        names[++y]=new Label("Email");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ assistant.getEmail());
        values[y].getStyleClass().add("value");
        addRow(y,names[y],values[y]);


        names[++y]=new Label("Phone");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ assistant.getPhone());
        values[y].getStyleClass().add("value");
        addRow(y,names[y],values[y]);


        names[++y]=new Label("Blood Type");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ assistant.getBloodType());
        values[y].getStyleClass().add("value");
        addRow(y,names[y],values[y]);


        getStylesheets().add(getClass().getResource("/css/details.css").toExternalForm());

    }
    public void setParent(Stage parentStage) {
        this.parentStage = parentStage;
    }

}
