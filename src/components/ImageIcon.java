package components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageIcon extends Button {
    private VBox box=new VBox();
    public ImageIcon(String title,String imagePath){
        try {
            ImageView image=new ImageView(new Image( new FileInputStream(imagePath)));
            box.getChildren().addAll(image,new Label(title));
            image.setFitHeight(120);
            image.setFitWidth(120);
            image.setPreserveRatio(true);
            box.setSpacing(20);
            box.setAlignment(Pos.CENTER);
            box.setPadding(new Insets(10));

        } catch (FileNotFoundException e) {
            System.out.println("Icon Image Not Found");
        }
        setGraphic(box);
        getStylesheets().add(getClass().getResource("/css/imageIcon.css").toExternalForm());
        getStyleClass().add("imageIcon");
    }
}
