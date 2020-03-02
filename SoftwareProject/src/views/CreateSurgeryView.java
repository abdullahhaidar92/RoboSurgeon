package views;

import application.Database;
import components.TimeField;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import java.util.ArrayList;

public class CreateSurgeryView extends GridPane {
    private Button save;
    private ChoiceBox patChoice;
    private ChoiceBox surgChoice;
    private ChoiceBox machChoice;
    private DatePicker datePicker;
    private TimeField timeField;
    private Stage parentStage;
    private TextField timerVal, radiationVal;
    
public CreateSurgeryView( ArrayList<String> patients,ArrayList<String> surgeries, ArrayList<String> machines){
    int x=0,y=0,width=220;
    setHgap(20);
    setVgap(10);
    setPadding(new Insets(30,30,30,30));
    Label[] names=new Label[15], values=new Label[15];

    names[y]=new Label("Patient ");
    names[y].getStyleClass().add("name");
    patChoice=new ChoiceBox();
    patChoice.getItems().setAll(patients);
    patChoice.getSelectionModel().selectFirst();
    patChoice.setPrefWidth(width);
    addRow(y,names[y],patChoice);

    names[++y]=new Label("Surgery Type ");
    names[y].getStyleClass().add("name");
     surgChoice=new ChoiceBox();
    surgChoice.getItems().setAll(surgeries);
    surgChoice.getSelectionModel().selectFirst();
    surgChoice.setPrefWidth(width);
    addRow(y,names[y],surgChoice);

    names[++y]=new Label("Machine ");
    names[y].getStyleClass().add("name");
     machChoice=new ChoiceBox();
    machChoice.getItems().setAll(machines);
    machChoice.getSelectionModel().selectFirst();
    machChoice.setPrefWidth(width);
    addRow(y,names[y],machChoice);

    names[++y]=new Label("Assigned Date");
    names[y].getStyleClass().add("name");
    datePicker = new DatePicker();
    datePicker.setPrefWidth(width);
    addRow(y,names[y],datePicker);

    names[++y]=new Label("Assigned Time");
    names[y].getStyleClass().add("name");
    timeField=new TimeField();
    timeField.setPrefWidth(200);
    addRow(y,names[y],timeField);
    
    
    names[++y]=new Label("Maximum Timer Value");
    names[y].getStyleClass().add("name");
    timerVal = new TextField();
    timerVal.setPrefWidth(200);
    addRow(y,names[y], timerVal);
    
    names[++y]=new Label("Maximum Radiation Value");
    names[y].getStyleClass().add("name");
    radiationVal = new TextField();
    radiationVal.setPrefWidth(200);
    addRow(y,names[y], radiationVal);

    
    addRow(++y,new Label(" "));
    save =new Button(" Save ");
    save.setPrefSize(100,30);
    addRow(++y,new Label(" "),save);
    getStylesheets().add(getClass().getResource("/css/createSurgery.css").toExternalForm());

}

    public Button getSave() {
        return save;
    }

    public ChoiceBox getPatChoice() {
        return patChoice;
    }

    public ChoiceBox getSurgChoice() {
        return surgChoice;
    }

    public ChoiceBox getMachChoice() {
        return machChoice;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public TimeField getTimeField() {
        return timeField;
    }
    
    
    public int getMaxTimerVal() {
    	return Integer.parseInt(timerVal.getText());
    } 
    
    public int getMaxRadiationVal() {
    	return Integer.parseInt(radiationVal.getText());
    }
    
    public void setMaxTimerVal(int maxTimerVal) {
    	timerVal.setText(String.valueOf(maxTimerVal));
    }
    
    public void setMaxRadiationVal(int maxRadiationVal) {
    	radiationVal.setText(String.valueOf(maxRadiationVal));
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

}
