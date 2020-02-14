package components;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Portal extends Stage {
    private Scene scene;
    private Label title = new Label();
    private Button close = new Button(" X ");
    private BorderPane root = new BorderPane();
    private HBox content = new HBox();

    public Portal() {
        BorderPane header = new BorderPane();
        header.getStyleClass().add("header");
        header.setCenter(new HBox(title));
        header.setRight(close);

        root.getStylesheets().add(getClass().getResource("/css/portal.css").toExternalForm());
        root.setCenter(content);
        root.setTop(header);

        content.setSpacing(50);
        content.setPadding(new Insets(40, 40, 40, 60));

        close.getStyleClass().add("close");
        close.setFocusTraversable(false);
        close.setOnAction(e -> {
            System.exit(0);
        });

        setScene(new Scene(root));
        setWidth(800);
        setHeight(500);
        initStyle(StageStyle.UNDECORATED);

    }

    public void addImageIcon(ImageIcon image) {
        content.getChildren().add(image);
    }


    public void setTitleBox(String title) {
        this.title.setText(title);
    }


}



