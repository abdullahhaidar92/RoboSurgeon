package views;

import components.Window;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OperationDashboard extends Window {
 private XraysView xraysView;
 private EmissionControl emissionControl;
 private SeedControl seedControl;
 private PatientStateMonitor monitor;

    public OperationDashboard() {
        super("Operation", 1300,800);
        xraysView=new XraysView(this);
        monitor=new PatientStateMonitor(this);
        emissionControl=new EmissionControl(this);
        seedControl=new SeedControl(this);

        HBox box1=new HBox(xraysView,monitor);
        HBox box2=new HBox(emissionControl,seedControl);
        VBox pane = new VBox(box1,box2);
        pane.setMinWidth(getWidth());
        pane.setPadding(new Insets(0,2,10,0));




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

    public PatientStateMonitor getStateMonitor() {
        return monitor;
    }



}
