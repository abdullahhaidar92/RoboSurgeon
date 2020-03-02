package views;


import java.util.Timer;
import java.util.TimerTask;

import components.TestKey;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;


public class EmissionControl extends GridPane {
    private int counter = 0;

    OperationDashboard operationDashboard;

    public EmissionControl(OperationDashboard dashboard){
        setMinWidth(dashboard.getWidth()*0.6);
        setMinHeight(dashboard.getHeight()*0.2);
        operationDashboard=dashboard;
        setFocusTraversable(false);
        GridPane grid = this;

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label label1=new Label("Timer");
        label1.setStyle("-fx-font-size: 2em;");
        grid.add(label1, 0,1);

        Label tfTimer = new Label("3");
        tfTimer.setStyle("-fx-font-size: 2em;");

        grid.add(tfTimer, 1,1);

        //LableDuration
        Label labelDuration=new Label("Duration");
        labelDuration.setStyle("-fx-font-size: 2em;");
        grid.add(labelDuration, 0,2);
        //Duratin
        Spinner spinner = new Spinner(1, 60, 3);
        spinner.setFocusTraversable(false);
        spinner.setPrefWidth(80);
        //spinner.setEditable(true);
        grid.add(spinner, 1,2);


        //LableDuration
        Label labelRadiation=new Label("Radiation");
        labelRadiation.setStyle("-fx-font-size: 2em;");
        grid.add(labelRadiation, 8,2);
        //Radiation
        Spinner spinnerRadiation = new Spinner(1, 60, 10);
        spinnerRadiation.setPrefWidth(80);
        //spinnerRadiation.setEditable(true);
        grid.add(spinnerRadiation, 9,2);



        // Add Emit button
        Button emitBtn = new Button("Emit");
        GridPane.setHalignment(emitBtn, HPos.CENTER);
        emitBtn.setStyle("-fx-font-size: 2em;");

        grid.add(emitBtn, 0,4);


        // Add Stop button
        Button stopBtn = new Button("Stop");
        GridPane.setHalignment(stopBtn, HPos.CENTER);
        stopBtn.setStyle("-fx-font-size: 2em;");

        grid.add(stopBtn, 9,4);

        spinner.valueProperty().addListener((obs, oldValue, newValue) ->
        {
            tfTimer.setText(newValue.toString());
            // Call SetDuration

        });

        spinnerRadiation.valueProperty().addListener((obs, oldValue, newValue) ->
        {

            // Call setEmissionDuration

        });

        emitBtn.setOnAction(e -> {
            Timer timer = new Timer();
            counter = Integer.parseInt(spinner.getEditor().getText());

            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (counter > 0)
                    {
                        Platform.runLater(() -> tfTimer.setText(Integer.toString(counter)));
                        --counter;
                    }
                    else
                        timer.cancel();
                }
            }, 0, 1000);

            Platform.setImplicitExit(false);

            // call startEmission

        });


        stopBtn.setOnAction(e -> {

            // call StopEmission
        });


        getStyleClass().add("emission_control");




        spinner.setFocusTraversable(false);
        spinnerRadiation.setFocusTraversable(false);

    }




}