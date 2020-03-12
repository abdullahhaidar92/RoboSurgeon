package views;
import components.NumberField;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Database;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import machine.HealthMonitor;

public class SeedControl extends VBox {
        OperationDashboard operationDashboard;
        NumberField stepX, stepY;
        public SeedControl(OperationDashboard dashboard) throws SQLException{
            setMinWidth(dashboard.getWidth()*0.42);
            setMinHeight(dashboard.getHeight()*0.12);
            operationDashboard=dashboard;
            getStyleClass().add("seed_control");

            HBox boxX=new HBox(),boxY=new HBox();
            Label label=new Label("Seed Control");
            label.getStyleClass().add("labelTitle");
            HBox title=new HBox(label);
            title.getStyleClass().add("title");
            HBox box2=new HBox(boxX,boxY);
            HBox box1=new HBox();
            getChildren().addAll(title,box1,box2);
            setSpacing(7);
            box1.setSpacing(20);
            box2.setSpacing(55);
            
            
            
            

            int[] maxValues = getMaxFields();


            Label maxRadiationLabel = new Label("Maximum Radiation");
            Label maxRadiationValueLabel = new Label(String.valueOf(maxValues[0]));
            box1.getChildren().add(maxRadiationLabel);
            box1.getChildren().add(maxRadiationValueLabel);

            Label maxTimerLabel = new Label("Maximum Emission Duration");
            Label maxTimerValueLabel = new Label(String.valueOf(maxValues[1]));
            box1.getChildren().add(maxTimerLabel);
            box1.getChildren().add(maxTimerValueLabel);
            maxRadiationValueLabel.getStyleClass().add("value");
            maxTimerValueLabel.getStyleClass().add("value");


            Label stepXLabel = new Label("Horizontal Step");
            stepX = new NumberField();
            stepX.setText("8.5");
            boxX.getChildren().add(stepXLabel);
            boxX.getChildren().add(stepX);

            Label stepYLabel = new Label("Vertical Step");
            stepY = new NumberField();
            stepY.setText("5");
            boxY.getChildren().add(stepYLabel);
            boxY.getChildren().add(stepY);

            stepX.setFocusTraversable(false);
            stepY.setFocusTraversable(false);

            stepX.setMaxWidth(100);
            stepY.setMaxWidth(100);
            boxX.setSpacing(15);
            boxY.setSpacing(15);

        }


        public int[] getMaxFields() throws SQLException {
            String query = "select * from Operation where doctorid = " + operationDashboard.getDoctorId()+
                    " and patientid = " + operationDashboard.getPatientId()
                    +" and APPOINTMENTDATE = ?"; // timestamp missing


            PreparedStatement p=Database.getConnection().prepareStatement(query);
            p.setTimestamp(1,operationDashboard.getAppointmentDate());
            ResultSet rs = p.executeQuery();
            int[] result = {12,12};
            if(rs != null) {
                if(rs.next()) {
                    result[0] = rs.getInt("MAXRADIATION");
                    result[1] = rs.getInt("MAXTIMER");

                }
            }

            query = "select * from PATIENT where  patientid = " + operationDashboard.getPatientId();
            p=Database.getConnection().prepareStatement(query);
             rs = p.executeQuery();
            if(rs != null) {
                if(rs.next()) {
                    HealthMonitor.MAX_PULSE = rs.getInt("NORMALPULSE")+10;
                    HealthMonitor.MIN_PULSE = rs.getInt("NORMALPULSE")-10;

                    HealthMonitor.MAX_PRESSURE = Integer.parseInt(rs.getString("NORMALPRESSURE").trim())+20;
                    HealthMonitor.MIN_PRESSURE = Integer.parseInt(rs.getString("NORMALPRESSURE").trim())-20;


                    HealthMonitor.MAX_OXYGEN = rs.getInt("NORMALOXYGEN")+15;
                    HealthMonitor.MIN_OXYGEN = rs.getInt("NORMALOXYGEN")-15;

                    HealthMonitor.MAX_TEMP = rs.getFloat("NORMALTEMPERATURE")+2;
                    HealthMonitor.MIN_TEMP = rs.getFloat("NORMALTEMPERATURE")-2;

                    HealthMonitor.prevPressure=rs.getInt("NORMALPULSE");
                    HealthMonitor.prevPressure=Integer.parseInt(rs.getString("NORMALPRESSURE").trim());
                    HealthMonitor.prevOxygen=rs.getInt("NORMALOXYGEN");
                    HealthMonitor.prevTemp=rs.getFloat("NORMALTEMPERATURE");

                    HealthMonitor.NORMAL_PULSE=rs.getInt("NORMALPULSE");
                    HealthMonitor.NORMAL_PRESSURE=Integer.parseInt(rs.getString("NORMALPRESSURE").trim());
                    HealthMonitor.NORMAL_OXYGEN=rs.getInt("NORMALOXYGEN");
                    HealthMonitor.NORMAL_TEMP=rs.getFloat("NORMALTEMPERATURE");
                }
            }
            return result;
        }


        public double getStepX() {
            if(stepX.getText().isEmpty())
                return 10;
            return Double.parseDouble(stepX.getText());
        }

        public double getStepY() {
            if(stepY.getText().isEmpty())
                return 10;
            return Double.parseDouble(stepY.getText());
        }

        public void setError(String message){

        }

    }

