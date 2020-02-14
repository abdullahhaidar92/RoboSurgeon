package views;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Operation;

import java.util.GregorianCalendar;

public class DeleteOperationView extends GridPane {
    private Stage parentStage;
    private Button confirmDelete;
    public DeleteOperationView(Operation operation){
        Label[] names=new Label[4],values=new Label[4];
        int x=0,y=0;
        setHgap(20);
        setVgap(10);
        setPadding(new Insets(30,30,30,30));
        Label label=new Label("Are you sure you want to delete this operation ?");
        add(label,x++,y,2,1);
        label.getStyleClass().add("name");

        names[++y]=new Label("Doctor Name");
        names[y].getStyleClass().add("name");
        values[y]=new Label(""+ operation.getDoctorName());
        values[y].getStyleClass().add("value");
        add(names[y],--x,y,1,1);
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


        addRow(++y,new Label(" "));
        confirmDelete =new Button(" Confirm  ");
        confirmDelete.setPrefSize(100,30);
        addRow(++y,new Label(" "),confirmDelete);
        getStylesheets().add(getClass().getResource("/css/details.css").toExternalForm());
    }
    public void showAlert(boolean success,String type){
        if(success){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(type+" Operation " );
            alert.setHeaderText(type+" Success");
            alert.setContentText(type+" operation has been successfully executed");
            alert.showAndWait();
            getParentStage().close();

        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(type+" Operation " );
            alert.setHeaderText(type+" Failure");
            alert.setContentText(type+" operation failed");
            alert.showAndWait();
        }

    }
    public void setParent(Stage parentStage) {
        this.parentStage = parentStage;
    }

    public Stage getParentStage() {
        return parentStage;
    }

    public Button getConfirmDelete() {
        return confirmDelete;
    }
}
