package application;

import models.CurrentUser;
import javafx.application.Application;
import javafx.stage.Stage;
import utility.FormValidation;

import java.sql.SQLException;

public class Main extends Application {


    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws SQLException {
        CurrentUser.getCurrentUser().setRole("admin");
         primaryStage = new UserProducer().getUserFactory("admin").getPortal();
        // primaryStage = new UserProducer().getUserFactory("admin").getDashboard("doctors").getView();
        // primaryStage = new UserProducer().getUserFactory("doctor").getPortal();
        // primaryStage = new UserProducer().getUserFactory("assistant").getPortal();
        primaryStage.show();
    }
    public static Stage getPrimaryStage(){
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
