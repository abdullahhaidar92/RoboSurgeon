package views;

import javafx.scene.layout.VBox;

public class EmissionControl extends VBox {
        OperationDashboard operationDashboard;
        public EmissionControl(OperationDashboard dashboard){
            setMinWidth(dashboard.getWidth()*0.6);
            setMinHeight(dashboard.getHeight()*0.2);
            operationDashboard=dashboard;
            getStyleClass().add("emission_control");
        }

}
