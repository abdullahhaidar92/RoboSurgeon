package controllers;

import machine.*;
import views.*;
import views.OperationDashboard;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class OperationController {
    public enum Danger{PULSE,PRESSURE,OXYGEN,TEMPERATURE};
    private CommandHandler commandHandler;
    private static EmissionControl emissionControl;
    private static SeedControl seedControl;
    private static PatientStateMonitor patientStateMonitor;
    private static XraysView xraysView;
    private OperationDashboard operationDashboard;
    private Seed seed=Seed.getSeed();
    private HealthMonitor healthMonitorThread;

    public void moveRight(double value){
        commandHandler.moveRight(value);
    }
    public void moveLeft(double value){
        commandHandler.moveLeft(value);
    }

    public void moveDown(double value){
        commandHandler.moveDown(value);
    }
    public void moveUp(double value){
        commandHandler.moveUp(value);
    }

    public static void setEmissionError(String message){
        emissionControl.setError(message);
    }
    public static void setMoveError(String message){
        seedControl.setError(message);
    }
    public static void setXrayError(String message){
        xraysView.setError(message);
    }
    public static void setDanger(Danger danger){

        switch (danger){
            case PULSE:patientStateMonitor.getHeartBeatSignal().setColor("Red");break;
            case PRESSURE:patientStateMonitor.getBloodPressureSignal().setColor("Red");break;
            case OXYGEN:patientStateMonitor.getOxygenLevelSignal().setColor("Red");break;
            case TEMPERATURE:patientStateMonitor.getTemperatureSignal().setColor("Red");break;
        }
    }
    public static void setSafe(Danger danger){
        String color="Blue";
        switch (danger){
            case PULSE:patientStateMonitor.getHeartBeatSignal().setColor(color);break;
            case PRESSURE:patientStateMonitor.getBloodPressureSignal().setColor(color);break;
            case OXYGEN:patientStateMonitor.getOxygenLevelSignal().setColor(color);break;
            case TEMPERATURE:patientStateMonitor.getTemperatureSignal().setColor(color);break;
        }
    }

    public boolean setRadiation(double value){
        return commandHandler.setRadiation(value);
    }
    public boolean setEmissionDuration(int value){
        return commandHandler.setEmissionDuration(value);
    }
    public boolean startEmission(){
        boolean success= commandHandler.startEmission();
        if(success)
            xraysView.startEmission();
        return success;
    }
    public boolean stopEmission(){
        boolean success= commandHandler.stopEmission();
        if(success)
            xraysView.stopEmission();
        return success;
    }

    public CommandHandler getCommandHandler() {
        MoveStrategy strategy = new RelativePositionsMoveStrategy();
        CommandHandler systemMonitor=new SystemMonitor();
        CommandHandler machine = Machine.getMachine();
        machine.setStrategy(strategy);
        HealthMonitor healthMonitor=new HealthMonitor();
        SafetyKernel safetyKernel=new SafetyKernel();

        systemMonitor.setSuccessor(safetyKernel);
        safetyKernel.setSuccessor(machine);
        //healthMonitor.setSuccessor(machine);
        machine.setSuccessor(null);

        healthMonitorThread=new HealthMonitor();
        healthMonitorThread.setOn();
        new Thread(healthMonitorThread).start();

        return systemMonitor;
    }

    public OperationController() {
        Machine.getMachine().start();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Timestamp timestamp = null;
        try {
            timestamp = new Timestamp(dateFormat.parse("2020-09-20 05:40:00.0").getTime());
            operationDashboard =   new OperationDashboard(2, 1,timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        emissionControl=operationDashboard.getEmissionControl();
        emissionControl.setController(this);
        seedControl=operationDashboard.getSeedControl();
        patientStateMonitor=operationDashboard.getStateMonitor();
        xraysView=operationDashboard.getXraysView();
        xraysView.setOperationController(this);

        Machine.getMachine().getRightCoil().setXPos(xraysView.getXRayWidth());
        Machine.getMachine().getBottomCoil().setYPos(xraysView.getXRayHeight());

        Sensor sensor = Sensor.getSensor(patientStateMonitor.getHeartBeatSignal(),
                patientStateMonitor.getBloodPressureSignal(),
                patientStateMonitor.getTemperatureSignal(),
                patientStateMonitor.getOxygenLevelSignal());
       // sensor.start();
        sensor.setON();


        operationDashboard.getClose().setOnAction(e->{
            sensor.setOFF();
            healthMonitorThread.setOff();
            operationDashboard.close();
            if(operationDashboard.getDashboard()!=null)
                operationDashboard.getDashboard().getView().show();

        });

        commandHandler=getCommandHandler();
        operationDashboard.show();


    }

}
