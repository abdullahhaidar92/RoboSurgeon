package controllers;

import application.Database;
import models.CurrentUser;
import views.*;
import views.OperationDashboard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


public class OperationController {

    private static EmissionControl emissionControl;
    private static SeedControl seedControl;
    private static PatientStateMonitor patientStateMonitor;
    private static XraysView xraysView;
    private OperationDashboard operationDashboard;
    private controllers.OperationDashboard dashboardController;

    public void moveRight(double value) {

    }

    public void moveLeft(double value) {
    }

    public void moveDown(double value) {

    }

    public void moveUp(double value) {

    }


    public OperationController(OperationDashboard dashboard) {

        operationDashboard = dashboard;
        emissionControl = operationDashboard.getEmissionControl();
        emissionControl.setController(this);
        seedControl = operationDashboard.getSeedControl();
        patientStateMonitor = operationDashboard.getStateMonitor();
        xraysView = operationDashboard.getXraysView();
        xraysView.setOperationController(this);


        Sensor sensor = Sensor.getSensor(patientStateMonitor.getHeartBeatSignal(),
                patientStateMonitor.getBloodPressureSignal(),
                patientStateMonitor.getTemperatureSignal(),
                patientStateMonitor.getOxygenLevelSignal());
        sensor.start();
        sensor.setON();


        operationDashboard.getClose().setOnAction(e -> {
            sensor.setOFF();
            operationDashboard.close();
            setEndTime();
            System.exit(0);
        });

        operationDashboard.show();


    }


    private void setEndTime() {
        try {
            CurrentUser user = CurrentUser.getCurrentUser();
            int currentUserId = user.getId();
            int currentDoctorId = 0;
            if (user.getRole().equals("Doctor"))
                currentDoctorId = currentUserId;
            else {
                String query = "Select doctorId from Assistant where assistantId = " + currentUserId;
                ResultSet idRs = Database.getResults(query);
                try {
                    idRs.next();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            String sql = "update OPERATION set ENDTIME=? where DOCTORID= ? and PATIENTID =? and APPOINTMENTDATE=? ";
            PreparedStatement P = Database.getConnection().prepareStatement(sql);
            P.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            P.setInt(2, operationDashboard.getDoctorId());
            P.setInt(3, operationDashboard.getPatientId());
            P.setTimestamp(4, operationDashboard.getAppointmentDate());
            P.execute();
            dashboardController.reloadProfiles();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void setDashboardController(controllers.OperationDashboard dashboardController) {
        this.dashboardController = dashboardController;
    }
}
