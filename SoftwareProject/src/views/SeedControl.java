package views;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Database;

    public class SeedControl extends GridPane {
        OperationDashboard operationDashboard;
        TextField stepX, stepY;
        public SeedControl(OperationDashboard dashboard) throws SQLException{
            setMinWidth(dashboard.getWidth()*0.4);
            setMinHeight(dashboard.getHeight()*0.2);
            operationDashboard=dashboard;
            getStyleClass().add("seed_control");


            this.setPadding(new Insets(10, 10, 10, 10));
            this.setVgap(5);
            this.setHgap(5);

            int[] maxValues = getMaxFields();


            Label maxRadiationLabel = new Label("Maximum Radiation");
            Label maxRadiationValueLabel = new Label(String.valueOf(maxValues[0]));
            this.add(maxRadiationLabel, 0, 0);
            this.add(maxRadiationValueLabel, 1, 0);

            Label maxTimerLabel = new Label("Maximum Timer Duration");
            Label maxTimerValueLabel = new Label(String.valueOf(maxValues[1]));
            this.add(maxTimerLabel, 0, 1);
            this.add(maxTimerValueLabel, 1, 1);


            Label stepXLabel = new Label("Step X");
            stepX = new TextField();
            this.add(stepXLabel, 0, 2);
            this.add(stepX, 1, 2);

            Label stepYLabel = new Label("Step Y");
            stepY = new TextField();
            this.add(stepYLabel, 0, 3);
            this.add(stepY, 1, 3);

            stepX.setFocusTraversable(false);
            stepY.setFocusTraversable(false);

        }


        public int[] getMaxFields() throws SQLException {
            String query = "select * from Operation where doctorid = " + operationDashboard.getDoctorId()+
                    " and patientid = " + operationDashboard.getPatientId()
                    +" and APPOINTMENTDATE = ?"; // timestamp missing
            PreparedStatement p=Database.getConnection().prepareStatement(query);
            p.setTimestamp(1,operationDashboard.getAppointmentDate());
            ResultSet rs = p.executeQuery();
            int[] result = {0,0};
            if(rs != null) {
                if(rs.next()) {
                    result[0] = rs.getInt("MAXRADIATION");
                    result[1] = rs.getInt("MAXTIMER");
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

