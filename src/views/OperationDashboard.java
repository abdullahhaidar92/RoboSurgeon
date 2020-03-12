package views;
import java.sql.SQLException;
import java.sql.Timestamp;


import components.Window;
import controllers.XraysController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
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

    public OperationDashboard(int doctorId, int patientId,Timestamp appointmentDate) throws SQLException{
        super("Operation", 1300,800);

        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentDate=appointmentDate;
        xraysView = new XraysView(this);
        super.setupScene(xraysView);
        monitor = new PatientStateMonitor(this);
        emissionControl = new EmissionControl(this);
        seedControl = new SeedControl(this);

        monitor=new PatientStateMonitor(this);
        emissionControl=new EmissionControl(this);
        seedControl=new SeedControl(this);
        HBox box1=new HBox(xraysView,monitor);
        Separator separator=new Separator(Orientation.VERTICAL);
        HBox box2=new HBox(emissionControl,seedControl);
        box2.setSpacing(5);
        VBox pane = new VBox(box1,box2);



        XraysController x_rayController = new XraysController(xraysView);
        pane.setOnKeyPressed(x_rayController);
        pane.setMinWidth(getWidth());
        pane.setPadding(new Insets(0,2,10,0));


        pane.getStylesheets().add(getClass().getResource("/css/operation.css").toExternalForm());
        setContent(pane);
        pane.getStyleClass().add("root");
        xraysView.requestFocus();





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

    public PatientStateMonitor getStateMonitor() {
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
}
