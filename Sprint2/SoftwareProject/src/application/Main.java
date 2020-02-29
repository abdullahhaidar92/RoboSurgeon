package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.CurrentUser;
import views.OperationDashboard;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {


    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws SQLException, IOException {
        new OperationDashboard().show();
        /*
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginView.fxml"));
         Parent root = loader.load();
        Scene scene = new Scene(root,670,512);
         primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
*/

    }
    public static Stage getPrimaryStage(){
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
