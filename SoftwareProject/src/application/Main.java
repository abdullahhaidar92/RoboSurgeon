package application;


import controllers.LoginController;
import controllers.OperationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import machine.CommandHandler;
import machine.Machine;

import machine.MultiVersionEnvironment;
import machine.Version;
import models.CurrentUser;
import views.OperationDashboard;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {


    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws SQLException, IOException {
       // new OperationController();


/*
        Machine machine =Machine.getMachine();
        machine.start();
        OperationController controller=new OperationController();
        controller.moveRight(10);
        System.out.println(Machine.getMachine().getSeed().getxPos());

*/




         FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginView.fxml"));
         Parent root = loader.load();
        Scene scene = new Scene(root,670,512);
         primaryStage = new Stage(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();









    }
    public static Stage getPrimaryStage(){
        return primaryStage;
    }

    public static void main(String[] args) {
        try {
            launch(args);
        }
        catch (Exception e){
           // System.out.println("Error in main");
            e.printStackTrace();
        }
    }
}
