package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.CurrentUser;
import views.OperationDashboard;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main extends Application {


    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws SQLException, IOException, ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Timestamp timestamp = new java.sql.Timestamp(dateFormat.parse("2020-09-20 05:40:00.0").getTime());
        new OperationDashboard(2, 1,timestamp).show();  //provide pk when launching -- timestamp missing
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
