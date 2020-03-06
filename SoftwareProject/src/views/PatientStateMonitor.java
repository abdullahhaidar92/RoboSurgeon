package views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PatientStateMonitor extends StackPane {
    Signal heartBeatSignal=buildSignal();
    Signal bloodPressureSignal=buildSignal();
    Signal temperatureSignal=buildSignal();
    Signal oxygenLevelSignal=buildSignal();

    public PatientStateMonitor(OperationDashboard dashboard){
        VBox monitor=new VBox();
        VBox labels=new VBox();

        heartBeatSignal.setType("pulse");
        bloodPressureSignal.setType("pressure");
        temperatureSignal.setType("temperature");
        oxygenLevelSignal.setType("oxygen");


        heartBeatSignal.getLineChart().setPadding(new Insets(-5,-10,-34,-25));
        bloodPressureSignal.getLineChart().setPadding(new Insets(-24,-10,-34,-25));
        temperatureSignal.getLineChart().setPadding(new Insets(-24,-10,-34,-25));
        oxygenLevelSignal.getLineChart().setPadding(new Insets(-24,-10,-30,-25));
        heartBeatSignal.setColor("Yellow");
        bloodPressureSignal.setColor("Green");
        temperatureSignal.setColor("Red");
        monitor.setMaxWidth(400);
        monitor.setMaxHeight(dashboard.getHeight()*0.75);
        monitor.setSpacing(-10);
        monitor.getChildren().addAll(heartBeatSignal.getLineChart(),bloodPressureSignal.getLineChart(),temperatureSignal.getLineChart(),oxygenLevelSignal.getLineChart());
        final int spacing=87;
        Label label1=new Label("pulse");
        Label upper1=new Label(""+Sensor.MAX_PULSE);
        Label lower1=new Label(""+Sensor.MIN_PULSE);
        VBox limits1=new VBox(upper1,lower1);
        limits1.setSpacing(spacing);

        Label label2=new Label("pressure");
        Label upper2=new Label(""+Sensor.MAX_PRESSURE);
        Label lower2=new Label(""+Sensor.MIN_PRESSURE);
        VBox limits2=new VBox(upper2,lower2);
        limits2.setSpacing(spacing);

        Label label4=new Label("temperature");
        Label upper4=new Label(""+Sensor.MAX_TEMPERATURE);
        Label lower4=new Label(""+Sensor.MIN_TEMPERATURE);
        VBox limits4=new VBox(upper4,lower4);
        limits4.setSpacing(spacing);

        Label label3=new Label("oxygen");
        Label upper3=new Label(""+Sensor.MAX_OXYGEN);
        Label lower3=new Label(""+Sensor.MIN_OXYGEN);
        VBox limits3=new VBox(upper3,lower3);
        limits3.setSpacing(spacing);

        labels.getChildren().addAll(label1,limits1,label2,limits2,label3,limits3,label4,limits4);
        labels.setSpacing(5);
        labels.setPadding(new Insets(5,0,0,0));
        this.getChildren().addAll(monitor,labels);
        this.setMaxWidth(400);
        this.setMaxHeight(400);

        getStylesheets().add
                (getClass().getResource("/css/monitor.css").toExternalForm());
        label1.getStyleClass().add("signal_name");
        label2.getStyleClass().add("signal_name");
        label3.getStyleClass().add("signal_name");
        label4.getStyleClass().add("signal_name");
    }
    private Signal buildSignal(){
        SignalBuilder builder=new SignalBuilder();
        builder.buildXAxis(0,20 , 0.5f);
        builder.buildYAxis(-2.5f, 2.5f, 1);
        builder.buildLineChart();
        builder.initialData();
        return builder.getSignal();
    }
    public Signal getHeartBeatSignal() {
        return heartBeatSignal;
    }

    public void setHeartBeatSignal(Signal heartBeatSignal) {
        this.heartBeatSignal = heartBeatSignal;
    }

    public Signal getBloodPressureSignal() {
        return bloodPressureSignal;
    }

    public void setBloodPressureSignal(Signal bloodPressureSignal) {
        this.bloodPressureSignal = bloodPressureSignal;
    }

    public Signal getTemperatureSignal() {
        return temperatureSignal;
    }

    public void setTemperatureSignal(Signal temperatureSignal) {
        this.temperatureSignal = temperatureSignal;
    }

    public Signal getOxygenLevelSignal() {
        return oxygenLevelSignal;
    }

    public void setOxygenLevelSignal(Signal oxygenLevelSignal) {
        this.oxygenLevelSignal = oxygenLevelSignal;
    }


}
