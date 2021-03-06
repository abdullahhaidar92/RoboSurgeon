package components;

import controllers.Dashboard;
import controllers.XraysController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import views.XraysView;

public class Window extends Stage {

    private VBox root =new VBox();
    private Scene scene;
    private Dashboard dashboard;


    Button close=new Button(" X ");
    public Window(String title, double width, double height){


        BorderPane header=new BorderPane();
        header.setCenter(new HBox(new Label(title)));
        header.getStyleClass().add("header");
        header.setPadding(new Insets(8));
        close.getStyleClass().add("close");
        close.setFocusTraversable(false);
        close.setOnAction(e->{
            this.close();
            if(dashboard!=null)
                dashboard.getView().show();
        });
        header.setRight(close);

       root.getChildren().add(header);
        scene = new Scene(root);
        setScene(scene);
        setWidth(width);
        setMaxHeight(height);
        initStyle(StageStyle.UNDECORATED);
        root.getStylesheets().add(getClass().getResource("/css/window.css").toExternalForm());

    }

    public void setContent(Node p){
        root.getChildren().add(p);
    }

    public void setParentStage(Dashboard parent) {
        this.dashboard = parent;
    }
    public Dashboard getDashboard() {
        return dashboard;
    }

    public Button getClose() {
        return close;
    }

    public void setupScene(XraysView view) {
        XraysController x_rayController = new XraysController(view);
        scene.setOnKeyPressed(x_rayController);
    }
}
