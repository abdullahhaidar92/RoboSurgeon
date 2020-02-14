package components;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Window extends Stage {

    private VBox root =new VBox();

    private Dashboard dashboard;

    public Window(String title, double width, double height){


        BorderPane header=new BorderPane();
        header.setCenter(new HBox(new Label(title)));
        header.getStyleClass().add("header");
        header.setPadding(new Insets(8));
        Button close=new Button(" X ");
        close.getStyleClass().add("close");
        close.setFocusTraversable(false);
        close.setOnAction(e->{
            this.close();
            if(dashboard!=null)
                dashboard.getView().show();
        });
        header.setRight(close);

       root.getChildren().add(header);
        Scene scene = new Scene(root);
        setScene(scene);
        setWidth(width);
        setHeight(height);
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
}
