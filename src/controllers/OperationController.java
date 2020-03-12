package controllers;

import application.Database;
import machine.*;
import models.CurrentUser;
import models.Operation;
import views.*;
import views.OperationDashboard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private controllers.OperationDashboard dashboardController;

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
            case PULSE:patientStateMonitor.getHeartBeatSignal().setDanger();break;
            case PRESSURE:patientStateMonitor.getBloodPressureSignal().setDanger();break;
            case OXYGEN:patientStateMonitor.getOxygenLevelSignal().setDanger();break;
            case TEMPERATURE:patientStateMonitor.getTemperatureSignal().setDanger();break;
        }
    }
    public static void setSafe(Danger danger){
        switch (danger){
            case PULSE:patientStateMonitor.getHeartBeatSignal().setSafe();break;
            case PRESSURE:patientStateMonitor.getBloodPressureSignal().setSafe();break;
            case OXYGEN:patientStateMonitor.getOxygenLevelSignal().setSafe();break;
            case TEMPERATURE:patientStateMonitor.getTemperatureSignal().setSafe();break;
        }
    }

    public boolean setRadiation(double value){
        return commandHandler.setRadiation(value);
    }
    public boolean setEmissionDuration(int value){
            xraysView.setEmissionDuration(value);
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
        Thread thread = new Thread(healthMonitorThread);
        thread.setName("Health Monitor Thread");
        thread.start();

        return systemMonitor;
    }

    public OperationController(OperationDashboard dashboard) {

            operationDashboard=dashboard;
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
        sensor.start();
        sensor.setON();


        operationDashboard.getClose().setOnAction(e->{
            sensor.setOFF();
            healthMonitorThread.setOff();
            operationDashboard.close();
            setEndTime();
            System.exit(0);
            // if(operationDashboard.getDashboard()!=null)
              //  operationDashboard.getDashboard().getView().show();

        });

        commandHandler=getCommandHandler();
        operationDashboard.show();


    }


    private void setEndTime(){
        try{
            CurrentUser user = CurrentUser.getCurrentUser();
            int currentUserId = user.getId();
            int currentDoctorId = 0;
            if(user.getRole().equals("Doctor"))
                currentDoctorId = currentUserId;
            else {
                String query = "Select doctorId from Assistant where assistantId = " + currentUserId;
                //System.out.println(query);
                ResultSet idRs = Database.getResults(query);
                try {
                    idRs.next();
                    currentDoctorId = idRs.getInt("DOCTORID");
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            String sql = "update OPERATION set ENDTIME=? where DOCTORID= ? and PATIENTID =? and APPOINTMENTDATE=? ";
            PreparedStatement P = Database.getConnection().prepareStatement(sql);
            P.setTimestamp(1,new Timestamp(System.currentTimeMillis()));
            P.setInt(2, operationDashboard.getDoctorId());
            P.setInt(3, operationDashboard.getPatientId());
            P.setTimestamp(4, operationDashboard.getAppointmentDate());
            P.execute();
            dashboardController.reloadProfiles();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void setDashboardController(controllers.OperationDashboard dashboardController) {
        this.dashboardController = dashboardController;
    }
}
