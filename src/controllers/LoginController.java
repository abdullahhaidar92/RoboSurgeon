package controllers;
import java.sql.ResultSet;

import application.Database;
import application.UserProducer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.CurrentUser;

public class LoginController{
	private UserProducer producer;

    @FXML
    private Button loginButton;

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
    void close() {
    	System.exit(0);
    }
    

    @FXML
    void login() {
    	producer = new UserProducer();
    	String user = username.getText().trim();
    	String pass = password.getText().trim();
    	boolean flag = true;
    	if(user.isEmpty()) {wrongPane .setVisible(false);emptyUsernamePane.setVisible(true); flag = false ;}
    	else emptyUsernamePane.setVisible(false);
    	if(pass.isEmpty()) {wrongPane.setVisible(false);emptyPasswordPane.setVisible(true); flag = false ;}
    	else emptyPasswordPane.setVisible(false);
    	try {
    	if(!flag) return;
    	ResultSet rs = Database.getResults("select * from dbo.[USER]\r\n" + 
    			"where Username ='"
    			+ user +"'"
    			+ " and PASSWORD ='"
    			+ pass.toString().trim() 
    			+"'");
    	if (rs != null) {
    		wrongPane.setVisible(false);
    		rs.next();
    		int currentUserId = rs.getInt("USERID");
    		String role = rs.getString("ROLE").trim().toLowerCase();

    		System.out.println(role);
    		if(role.equals("doctor")) {
    			ResultSet currentUserRs = Database.getResults("select * from [USER] join [DOCTOR] on "
    					+ "[USER].USERID = [DOCTOR].USERID where [DOCTOR].USERID = " + currentUserId);
    			currentUserRs.next();
    			CurrentUser currentUser = CurrentUser.getCurrentUser();
    			currentUser.setId(currentUserRs.getInt("DOCTORID"));
    			currentUser.setRole("doctor");

    			
    		}
    		
    		else if(role.equals("assistant")) {
    			String query = "select * from [USER] join [ASSISTANT] on "
    					+ "[USER].USERID = [ASSISTANT].USERID where [ASSISTANT].USERID = " + currentUserId;
    			ResultSet currentUserRs = Database.getResults(query);
    			currentUserRs.next();
    			CurrentUser currentUser = CurrentUser.getCurrentUser();
    			currentUser.setId(currentUserRs.getInt("ASSISTANTID"));
    			currentUser.setRole("assistant");
    		}

			producer.getUserFactory(role).getPortal().show();
    		
		}
    	}
    	catch (Exception e1) {
			e1.printStackTrace();
    		wrongPane.setVisible(true);
		}
    }

}
