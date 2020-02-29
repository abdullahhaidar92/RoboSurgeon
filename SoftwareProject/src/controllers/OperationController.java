package controllers;

import machine.*;
import views.*;
import views.OperationDashboard;

public class OperationController {
    public enum Danger{PULSE,PRESSURE,OXYGEN,TEMPERATURE};
    private CommandHandler commandHandler;
    private static EmissionControl emissionControl;
    private static SeedControl seedControl;
    private static PatientStateMonitor patientStateMonitor;
    private static XraysView xraysView;
    private OperationDashboard operationDashboard;


    public void moveRight(double value){
        commandHandler.moveRight(value);
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
        System.out.println("Hello");
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


    private CommandHandler getCommandHandler() {
        MoveStrategy strategy = new RelativePositionsMoveStrategy();
        CommandHandler systemMonitor=new SystemMonitor();
        CommandHandler machine = Machine.getMachine();
        machine.setStrategy(strategy);
        HealthMonitor healthMonitor=new HealthMonitor();

        systemMonitor.setSuccessor(healthMonitor);
        healthMonitor.setSuccessor(machine);
        machine.setSuccessor(null);

        Thread healthMonitorThread=new Thread(new HealthMonitor());
        healthMonitorThread.start();

        return systemMonitor;
    }

    public OperationController(){
        operationDashboard=new OperationDashboard();
        emissionControl=operationDashboard.getEmissionControl();
        seedControl=operationDashboard.getSeedControl();
        patientStateMonitor=operationDashboard.getStateMonitor();
        xraysView=operationDashboard.getXraysView();

        Sensor sensor = new Sensor(patientStateMonitor.getHeartBeatSignal(),
                patientStateMonitor.getBloodPressureSignal(),
                patientStateMonitor.getTemperatureSignal(),
                patientStateMonitor.getOxygenLevelSignal());
        sensor.start();
        sensor.setON();


        operationDashboard.getClose().setOnAction(e->{
            sensor.setOFF();
            operationDashboard.close();
            if(operationDashboard.getDashboard()!=null)
                operationDashboard.getDashboard().getView().show();

        });

        commandHandler=getCommandHandler();
        operationDashboard.show();


    }

}
