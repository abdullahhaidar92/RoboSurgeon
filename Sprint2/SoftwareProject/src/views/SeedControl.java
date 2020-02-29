package views;

import javafx.scene.layout.VBox;

public class SeedControl extends VBox {
    OperationDashboard operationDashboard;
    public SeedControl(OperationDashboard dashboard){
        setMinWidth(dashboard.getWidth()*0.4);
        setMinHeight(dashboard.getHeight()*0.2);
        operationDashboard=dashboard;
        getStyleClass().add("seed_control");
    }
}
