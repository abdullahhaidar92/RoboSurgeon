package models;

import application.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientDashboardModel extends DashboardModel<Patient> {
	
	int insertedProfileId = 0;

    @Override
    public ArrayList<Patient> loadProfiles() {
        ResultSet rs = Database.getResults("Select * from " +
                "Profile join [Patient] on Profile.PROFILEID = [Patient].PROFILEID ");
        return loadFromResultSet(rs);

    }

    @Override
    public ArrayList<Patient> filter(String query) {
        PreparedStatement P = null;
        ResultSet rs = null;
        String sql = "Select * from Profile join \"PATIENT\" on" +
                " Profile.PROFILEID = \"PATIENT\".PROFILEID ";
        try {
            try {
                int i=Integer.parseInt(query);
                sql += "Where PATIENTID=?";
                P = Database.getConnection().prepareStatement(sql);
                P.setInt(1,i );
            } catch (NumberFormatException e) {
                sql += "Where FIRSTNAME=? Or LASTNAME=?";
                P = Database.getConnection().prepareStatement(sql);
                P.setString(1, query);
                P.setString(2, query);
            }
            rs=P.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loadFromResultSet(rs);
    }


    private ArrayList<Patient> loadFromResultSet(ResultSet rs) {
        ArrayList<Patient> patients = new ArrayList();
        try {
            if (rs != null)
                while (rs.next()) {
                    Patient p = new Patient(rs.getInt("PROFILEID"),
                            rs.getString("FIRSTNAME").trim(), rs.getString("MIDDLENAME").trim(),
                            rs.getString("LASTNAME").trim(), rs.getString("EMAIL").trim(), rs.getString("PHONE").trim(), 
                            rs.getDate("DATEOFBIRTH"), rs.getString("ADDRESS").trim(), rs.getString("BLOODTYPE").trim());
                    p.setPatientId(rs.getInt("PATIENTID"));
                    p.setPulse(rs.getInt("PULSE"));
                    p.setNormalPulse(rs.getInt("NORMALPULSE"));
                    p.setOxygen(rs.getInt("OXYGEN"));
                    p.setNormalOxygen(rs.getInt("NORMALOXYGEN"));
                    p.setWeight(rs.getInt("WEIGHT"));
                    p.setHeight(rs.getInt("HEIGHT"));
                    p.setPressure(rs.getString("PRESSURE").trim());
                    p.setNormalPressure(rs.getString("NORMALPRESSURE").trim());
                    p.setTemperature(rs.getFloat("TEMPERATURE"));
                    p.setNormalTemperature(rs.getFloat("NORMALTEMPERATURE"));
                    patients.add(p);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }
    
    
    @Override
    public void create(Patient p) {
    	String insertProfileSql = "insert into Profile (FIRSTNAME, MIDDLENAME, LASTNAME, EMAIL,"
    			+ " PHONE, DATEOFBIRTH, ADDRESS, BLOODTYPE) values (";
    	insertProfileSql += "'" + p.getFirstName() + "',";
    	insertProfileSql += "'" + p.getMiddleName() + "',";
    	insertProfileSql += "'" + p.getLastName() + "',";
    	insertProfileSql += "'" + p.getEmail() + "',";
    	insertProfileSql += "'" + p.getPhone() + "',";
    	insertProfileSql += "'" + p.getBirthDate() + "',";
    	insertProfileSql += "'" + p.getAddress() + "',";
    	insertProfileSql += "'" + p.getBloodType() + "')";
    	
    	try {
    		Database.execute(insertProfileSql);
    		ResultSet rs = Database.getResults("Select MAX(PROFILEID) from Profile");
    		rs.next();
    		insertedProfileId = rs.getInt(1);
    	}
    	catch(Exception e1) {
    		e1.printStackTrace();
    	}
    	
    	
    	String insertPatientSql =  "insert into Patient (PROFILEID, PRESSURE, TEMPERATURE, PULSE, OXYGEN,"
    			+ " NORMALPRESSURE, NORMALTEMPERATURE, NORMALPULSE, NORMALOXYGEN, "
    			+ "WEIGHT, HEIGHT) values (";
    	insertPatientSql += insertedProfileId + ","; 
    	insertPatientSql += "'" + p.getPressure() + "',";
    	insertPatientSql += p.getTemperature() + ",";
    	insertPatientSql += p.getPulse() + ",";
    	insertPatientSql += p.getOxygen() + ",";
    	insertPatientSql += "'" + p.getNormalPressure() + "',";
    	insertPatientSql += p.getNormalTemperature() + ",";
    	insertPatientSql += p.getNormalPulse() + ",";
    	insertPatientSql += p.getNormalOxygen() + ",";
    	insertPatientSql += p.getWeight() + ",";
    	insertPatientSql += p.getHeight() + ")";
    	
    	try {
    		Database.execute(insertPatientSql);
    	}
    	catch(Exception e2) {
    		e2.printStackTrace();
    	}
    	
    }
    
    
    
    @Override
    public void edit(Patient p) {
    //	String insertProfileSql = "insert into Profile (FIRSTNAME, MIDDLENAME, LASTNAME, EMAIL,"
    	//		+ " PHONE, DATEOFBIRTH, ADDRESS, BLOODTYPE) values (";
    	
    	String updateProfileSql = "update Profile set ";
    	
    	updateProfileSql += "FirstName = " + "'" + p.getFirstName() + "',";
    	updateProfileSql += "middleName = " + "'" + p.getMiddleName() + "',";
    	updateProfileSql += "lastName = " + "'" + p.getLastName() + "',";
    	updateProfileSql += "email = " + "'" + p.getEmail() + "',";
    	updateProfileSql += "phone = " + "'" + p.getPhone() + "',";
    	updateProfileSql += "dateofbirth = " + "'" + p.getBirthDate() + "',";
    	updateProfileSql += "address = " + "'" + p.getAddress() + "',";
    	updateProfileSql += "bloodtype = " + "'" + p.getBloodType() + "' where profileId = " + p.getProfileId();
    	
    	try {
    		System.out.println(updateProfileSql);
    		Database.execute(updateProfileSql);
    		
    	}
    	catch(Exception e1) {
    		e1.printStackTrace();
    	}
    	
    	
    	//String insertPatientSql =  "insert into Patient (PROFILEID, PRESSURE, TEMPERATURE, PULSE, OXYGEN,"
    		//	+ " NORMALPRESSURE, NORMALTEMPERATURE, NORMALPULSE, NORMALOXYGEN, "
    		//	+ "WEIGHT, HEIGHT) values (";
    	String updatePatientSql = "update Patient set ";
    	
    	updatePatientSql +="profileId = " + p.getProfileId() + ","; 
    	updatePatientSql += "pressure = " + "'" + p.getPressure() + "',";
    	updatePatientSql += "temperature = " + p.getTemperature() + ",";
    	updatePatientSql += "pulse = " + p.getPulse() + ",";
    	updatePatientSql += "oxygen = " + p.getOxygen() + ",";
    	updatePatientSql += "normalpressure = " + "'" + p.getNormalPressure() + "',";
    	updatePatientSql += "normaltemperature = " + p.getNormalTemperature() + ",";
    	updatePatientSql += "normalpulse = " + p.getNormalPulse() + ",";
    	updatePatientSql += "normaloxygen = " + p.getNormalOxygen() + ",";
    	updatePatientSql += "weight = " + p.getWeight() + ",";
    	updatePatientSql += "height = " + p.getHeight() + " where patientId = " + p.getPatientId();
    	
    	try {
    		System.out.println(updatePatientSql);
    		Database.execute(updatePatientSql);
    	}
    	catch(Exception e2) {
    		e2.printStackTrace();
    	}
    	
    }
    
}
