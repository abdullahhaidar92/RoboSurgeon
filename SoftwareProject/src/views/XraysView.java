package views;

import javafx.scene.layout.VBox;

public class XraysView extends VBox {
    OperationDashboard operationDashboard;
    public XraysView(OperationDashboard dashboard){
        setMinWidth(dashboard.getWidth()*0.7);
        setMinHeight(dashboard.getHeight()*0.7);
        operationDashboard=dashboard;
        getStyleClass().add("xrays");
    }
    public void setError(String message){

    }
}
