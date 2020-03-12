package views;
import java.util.Timer;
import java.util.TimerTask;

import controllers.OperationController;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import machine.Machine;
import machine.SafetyKernel;
import machine.Seed;


public class EmissionControl extends VBox {
    private int counter = 0;
    private Timer timer;
    private boolean flag = true;
    private  Label message;
    OperationDashboard operationDashboard;
    OperationController controller;
    public EmissionControl(OperationDashboard dashboard){
        int maxTimer = SafetyKernel.getMAX_Duration();
        setMinWidth(dashboard.getWidth()*0.58);
        setMinHeight(dashboard.getHeight()*0.12);
        operationDashboard=dashboard;

        setFocusTraversable(false);

        Label label=new Label("Emission Control");
        message=new Label(" ");
        message.getStyleClass().add("message");
        label.getStyleClass().add("labelTitle");
        HBox title=new HBox(label);
        title.getStyleClass().add("title");
        message.setMinWidth(600);
        title.setPadding(new Insets(5,5,5,5));
        HBox box2=new HBox();
        HBox box1=new HBox();
        getChildren().addAll(title,box1,box2);
        setSpacing(7);
        box2.setSpacing(30);
        box1.setSpacing(20);

        Label label1=new Label("Timer");

        box1.getChildren().add(label1);

        Label tfTimer = new Label("0");
        tfTimer.getStyleClass().add("value");

        box1.getChildren().add(tfTimer);

        box1.getChildren().add(message);

        //LableDuration
        Label labelDuration=new Label("Duration");

        box2.getChildren().add(labelDuration);
        //Duratin
        Spinner spinner = new Spinner(1, maxTimer, 1);
        spinner.setFocusTraversable(false);
        spinner.setPrefWidth(80);
        //spinner.setEditable(true);
        box2.getChildren().add(spinner);


        //LableDuration
        Label labelRadiation=new Label("Radiation");

        box2.getChildren().add(labelRadiation);
        //Radiation
        Spinner spinnerRadiation = new Spinner(1, 60, 10);
        spinnerRadiation.setPrefWidth(80);
        //spinnerRadiation.setEditable(true);
        box2.getChildren().add(spinnerRadiation);



        // Add Emit button
        Button emitBtn = new Button("Emit");
        GridPane.setHalignment(emitBtn, HPos.CENTER);

        emitBtn.getStyleClass().add("button1");

        box2.getChildren().add(emitBtn);


        // Add Stop button
        Button stopBtn = new Button("Stop");
        GridPane.setHalignment(stopBtn, HPos.CENTER);

        stopBtn.getStyleClass().add("button1");

        box2.getChildren().add(stopBtn);

        spinner.valueProperty().addListener((obs, oldValue, newValue) ->
        {  int value = Integer.parseInt(tfTimer.getText());
            if (value == 0)   flag= true;
            message.setText("");
            if(flag)
            {
                boolean success =controller.setEmissionDuration((Integer) newValue);
                tfTimer.setText(""+Machine.getMachine().getEmissionDuration());
                if(!success)
                    message.setText("Safety Kernel : Adusting value of emission duration ");
            }

        });

        spinnerRadiation.valueProperty().addListener((obs, oldValue, newValue) ->
        {

            message.setText("");
           boolean success= controller.setRadiation((Integer)newValue);
          if(!success) {
              spinnerRadiation.getValueFactory().setValue(oldValue);
              message.setText("Safety Kernel : Adusting value of radiation ");
          }

        });

        emitBtn.setOnAction(e -> {
            message.setText("");
            timer = new Timer();
            counter = Integer.parseInt(spinner.getEditor().getText());
            controller.startEmission();
            // disable spinner
            flag = false;
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {

                    if (counter > 0)
                    {
                        Platform.runLater(() -> tfTimer.setText(Integer.toString(counter)));
                        --counter;
                    }
                    else {
                        timer.cancel();
                        controller.stopEmission();
                    }
                }
            }, 0, 1000);

            Platform.setImplicitExit(false);

        });


        stopBtn.setOnAction(e -> {
            message.setText("");
            timer.cancel();
            tfTimer.setText("0");
            flag= true;
            controller.stopEmission();


        });


        getStyleClass().add("emission_control");


        spinner.setFocusTraversable(false);
        spinnerRadiation.setFocusTraversable(false);

    }

    public void setError(String msg){
        message.setText(msg);
    }

    public void setController(OperationController controller) {
        this.controller = controller;
    }


}