package views;
import java.sql.SQLException;
import java.sql.Timestamp;

import components.TestKey;
import components.Window;
import controllers.XraysController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.xml.stream.EventFilter;

public class OperationDashboard extends Window {
 private XraysView xraysView;
 private EmissionControl emissionControl;
 private SeedControl seedControl;
 private PatientStateMonitor monitor;
 private int doctorId, patientId;
 private Timestamp appointmentDate;



    private EventHandler<KeyEvent> keyEventHandler;

    public OperationDashboard(int doctorId, int patientId,Timestamp appointmentDate) throws SQLException {  //operation primary key -- timestamp missing
        super("Operation", 1300,800);

        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentDate=appointmentDate;
        xraysView = new XraysView(this);
        monitor = new PatientStateMonitor(this);
        emissionControl = new EmissionControl(this);
        seedControl = new SeedControl(this);

        HBox box1 = new HBox(xraysView,monitor);
        HBox box2 = new HBox(emissionControl,seedControl);
        VBox pane = new VBox(box1,box2);

        xraysView.setFocusTraversable(true);
        XraysController x_rayController = new XraysController(xraysView);
        pane.setOnKeyPressed(x_rayController);
        pane.setMinWidth(getWidth());
        pane.setPadding(new Insets(0,2,10,0));

        Sensor sensor = new Sensor(monitor.getHeartBeatSignal(), monitor.getBloodPressureSignal(), monitor.getTemperatureSignal(), monitor.getOxygenLevelSignal());
        sensor.start();
        pane.getStylesheets().add(getClass().getResource("/css/operation.css").toExternalForm());
        setContent(pane);
        pane.getStyleClass().add("root");
    }

    public XraysView getXraysView() {
        return xraysView;
    }

    public EmissionControl getEmissionControl() {
        return emissionControl;
    }

    public SeedControl getSeedControl() {
        return seedControl;
    }

    public PatientStateMonitor getMonitor() {
        return monitor;
    }
    
    public int getDoctorId() {
    	return doctorId;
    }
    
    public int getPatientId() {
    	return patientId;
    }
    
    public Timestamp getAppointmentDate() {
    	return appointmentDate;
    }

    public EventHandler<KeyEvent> getKeyEventHandler() {
        return keyEventHandler;
    }
}
