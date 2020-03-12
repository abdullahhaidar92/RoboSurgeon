package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;

import application.Database;
import application.UserProducer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import models.CurrentUser;

public class LoginController {
    private UserProducer producer;

    @FXML
    private Button loginButton;

    @FXML
    private ImageView userImage;
    @FXML
    private ImageView lockImage;
    @FXML
    private ImageView loginImage;
    @FXML
    private ImageView errorImage;
    @FXML
    private ImageView emptyImage;
    @FXML
    private ImageView empty2Image;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button closeLogin;

    @FXML
    private AnchorPane wrongPane;

    @FXML
    private AnchorPane emptyUsernamePane;

    @FXML
    private AnchorPane emptyPasswordPane;

    @FXML
    public void initialize() throws FileNotFoundException {
        userImage.setImage(new Image(new FileInputStream("images/user.jpg")));
        lockImage.setImage(new Image(new FileInputStream("images/lock.jpg")));
        loginImage.setImage(new Image(new FileInputStream("images/Login.png")));
        errorImage.setImage(new Image(new FileInputStream("images/wrong.png")));
        emptyImage.setImage(new Image(new FileInputStream("images/Empty.png")));
        empty2Image.setImage(new Image(new FileInputStream("images/Empty.png")));
        username.setText("RizkJad");
        password.setText("123");

    }

    @FXML
    void close() {
        System.exit(0);
    }

    public ImageView getUserImage() {
        return userImage;
    }

    @FXML
    void login() {
        producer = new UserProducer();
        String user = username.getText().trim();
        String pass = password.getText().trim();
        boolean flag = true;
        if (user.isEmpty()) {
            wrongPane.setVisible(false);
            emptyUsernamePane.setVisible(true);
            flag = false;
        } else emptyUsernamePane.setVisible(false);
        if (pass.isEmpty()) {
            wrongPane.setVisible(false);
            emptyPasswordPane.setVisible(true);
            flag = false;
        } else emptyPasswordPane.setVisible(false);
        try {
            if (!flag) return;
            ResultSet rs = Database.getResults("select * from dbo.[USER]\r\n" +
                    "where Username ='"
                    + user + "'"
                    + " and PASSWORD ='"
                    + pass.toString().trim()
                    + "'");
            if (rs != null) {
                wrongPane.setVisible(false);
                rs.next();
                int currentUserId = rs.getInt("USERID");
                String role = rs.getString("ROLE").trim().toLowerCase();
                CurrentUser.getCurrentUser().setRole("admin");

                if (role.equals("doctor")) {
                    ResultSet currentUserRs = Database.getResults("select * from [USER] join [DOCTOR] on "
                            + "[USER].USERID = [DOCTOR].USERID inner join Profile p  on p.profileId = [USER].profileId where [DOCTOR].USERID = " + currentUserId);
                    currentUserRs.next();
                    CurrentUser currentUser = CurrentUser.getCurrentUser();
                    currentUser.setId(currentUserRs.getInt("DOCTORID"));
                    currentUser.setName(currentUserRs.getString("FIRSTNAME"));
                    currentUser.setRole("doctor");


                } else if (role.equals("assistant")) {
                    String query = "select * from  [USER] join [ASSISTANT] on "
                            + "[USER].USERID = [ASSISTANT].USERID  inner join Profile p  on p.profileId = [USER].profileId  where [ASSISTANT].USERID = " + currentUserId;
                    ResultSet currentUserRs = Database.getResults(query);
                    currentUserRs.next();
                    CurrentUser currentUser = CurrentUser.getCurrentUser();
                    currentUser.setId(currentUserRs.getInt("ASSISTANTID"));
                    currentUser.setName(currentUserRs.getString("FIRSTNAME"));
                    currentUser.setRole("assistant");
                }

                producer.getUserFactory(role).getPortal().show();
            }

        } catch (Exception e1) {
            // e1.printStackTrace();
            wrongPane.setVisible(true);
        }
    }

}
