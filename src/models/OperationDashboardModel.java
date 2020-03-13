package models;

import java.sql.*;
import java.util.ArrayList;

import application.Database;

public class OperationDashboardModel extends DashboardModel {



	public boolean create(Operation o)  {
		try {
			String sql = "insert into OPERATION(DOCTORID,PATIENTID,APPOINTMENTDATE,MACHINEID,SURGERYID,MAXRADIATION,MAXTIMER,REGISTERATIONDATE) values(?,?,?,?,?,?,?,?)";
			PreparedStatement P = Database.getConnection().prepareStatement(sql);
			P.setInt(1, getCurrentDocotorId());
			P.setInt(2, o.getPatientId());
			P.setTimestamp(3, Timestamp.valueOf(o.getAppointmentDate()));
			P.setInt(4, o.getMachineId());
			P.setInt(5, o.getSurgeryId());
			P.setInt(6, o.getMaxRadiationVal());
			P.setInt(7, o.getMaxTimerVal());
			P.setTimestamp(8, o.getRegistrationDate());
			P.execute();
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
			return  false;
		}
	}
	public boolean update(Operation o)  {
		try {
			String sql = "update OPERATION set MACHINEID=?,SURGERYID=? ,MAXRADIATION=?, MAXTIMER=?, REGISTERATIONDATE=?  where DOCTORID= ? and PATIENTID =? and APPOINTMENTDATE=? ";
			PreparedStatement P = Database.getConnection().prepareStatement(sql);
			P.setInt(1, o.getMachineId());
			P.setInt(2, o.getSurgeryId());
			P.setInt(3, o.getMaxRadiationVal());
			P.setInt(4, o.getMaxTimerVal());
			P.setTimestamp(5, o.getRegistrationDate());
			P.setInt(6, getCurrentDocotorId());
			P.setInt(7, o.getPatientId());
			P.setTimestamp(8, o.getAppointmentDate1());
			P.execute();
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
			return  false;
		}
	}
	public boolean delete(Operation o)  {
		try {
			String sql = "delete  OPERATION where DOCTORID= ? and PATIENTID =? and APPOINTMENTDATE=? ";
			PreparedStatement P = Database.getConnection().prepareStatement(sql);
			P.setInt(1, getCurrentDocotorId());
			P.setInt(2, o.getPatientId());
			P.setTimestamp(3, Timestamp.valueOf(o.getAppointmentDate()));
			P.execute();
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
			return  false;
		}
	}
	public ArrayList<Surgery> getSurgeries(){
		ArrayList<Surgery> surgeries=new ArrayList<>();
		ResultSet rs=Database.getResults("Select * from SURGERY");
		try {
			while (rs.next())
				surgeries.add(new Surgery(rs.getInt("SURGERYID"),rs.getString("NAME").trim(),
						rs.getString("DESCRIPTION"),rs.getString("DURATION")));

		}catch (Exception e){
			e.printStackTrace();
		}
		return surgeries;
	}
	public ArrayList<Machine> getMachines(){
		ArrayList<Machine> machines=new ArrayList<>();
		ResultSet rs=Database.getResults("Select * from MACHINE");
		try {
			while (rs.next())
				machines.add(new Machine(rs.getInt("MACHINEID"),rs.getInt("ROOMNUMBER"),rs.getString("MACHINESTATE"),
						rs.getDate("DATEBOUGHT"),rs.getInt("SERIALNUMBER")));
		}catch (Exception e){
			e.printStackTrace();
		}
		return machines;
	}
	public ArrayList<Patient> getPatients(){
		ArrayList<Patient> patients=new ArrayList<>();
		ResultSet rs = Database.getResults("Select * from " +
				"Profile join [Patient] on Profile.PROFILEID = [Patient].PROFILEID ");
		try {
			while (rs.next()){
				Patient p=new Patient(rs.getInt("PROFILEID"),
						rs.getString("FIRSTNAME").trim(), rs.getString("MIDDLENAME").trim(),
						rs.getString("LASTNAME").trim(), rs.getString("EMAIL").trim(), rs.getString("PHONE").trim(),
						rs.getDate("DATEOFBIRTH"), rs.getString("ADDRESS").trim(), rs.getString("BLOODTYPE").trim());
				p.setPatientId(rs.getInt("PATIENTID"));
				patients.add(p);
			}


		}catch (Exception e){
			e.printStackTrace();
		}
		return patients;
	}
	@Override
	public ArrayList<Operation> loadProfiles() {
		ResultSet rs = Database.getResults("Select * from " +
                "Operation join [Doctor] on Operation.DOCTORID = [Doctor].DOCTORID "
                + "join [Patient] on Operation.PATIENTID = [Patient].PATIENTID "
                + "join [Machine] on Operation.MACHINEID = [Machine].MACHINEID "
                + "join [Surgery] on Operation.SURGERYID = [Surgery].SURGERYID "
                + "where Operation.DOCTORID = " + getCurrentDocotorId());  //where doctorid=currentuser
		
        return loadFromResultSet(rs);
	}

	private ArrayList<Operation> loadFromResultSet(ResultSet rs) {
		ArrayList<Operation> operations = new ArrayList();
        try {
            if (rs != null) 
 
                while (rs.next()) {
                	
                	ResultSet rsDoctor = Database.getResults("Select * from "
                			+ "Doctor join [User] on Doctor.USERID = [User].USERID "
                			+ "join [Profile] on [User].PROFILEID = [Profile].PROFILEID where"
                			+ " Doctor.DOCTORID = " + rs.getInt("DOCTORID"));
                	rsDoctor.next();
                  	
                	
                	ResultSet rsPatient = Database.getResults("Select * from "
                			+ "Patient join [Profile] on Patient.PROFILEID = [Profile].PROFILEID "
                			+ "where Patient.PATIENTID = " + rs.getInt("PATIENTID"));
                	
                	rsPatient.next();
                    
                	Operation o = new Operation(rs.getInt("DOCTORID"), rsDoctor.getString("FIRSTNAME").trim() + 
                    		" " + rsDoctor.getString("LASTNAME").trim(), rs.getInt("PATIENTID"), 
                    		rsPatient.getString("FIRSTNAME").trim() + " " + rsPatient.getString("LASTNAME").trim(),
                    		rs.getInt("MACHINEID"), rs.getInt("ROOMNUMBER"), rs.getString("MACHINESTATE").trim(), 
                    		rs.getInt("SURGERYID"), rs.getTimestamp("APPOINTMENTDATE"),
                    		rs.getTimestamp("REGISTERATIONDATE"), rs.getTimestamp("STARTTIME"), 
                    		rs.getTimestamp("ENDTIME"), rs.getString("NAME").trim(), rs.getString("DESCRIPTION").trim(),
                    		rs.getString("DURATION").trim());
					o.setMaxRadiationVal(rs.getInt("MAXRADIATION"));
					o.setMaxTimerVal(rs.getInt("MAXTIMER"));
                    operations.add(o);
                }
            	
            	
            	
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operations;
	}

	@Override
	public ArrayList filter(String query) {
        PreparedStatement P = null;
        ResultSet rs = null;
        String sql = "Select * from " +
                "Operation join [Doctor] on Operation.DOCTORID = [Doctor].DOCTORID "
                + "join [Patient]  on Operation.PATIENTID = [Patient].PATIENTID "
                + "join [Profile] on [Patient].PROFILEID = [Profile].PROFILEID "
                + "join [Machine] on Operation.MACHINEID = [Machine].MACHINEID "
                + "join [Surgery] on Operation.SURGERYID = [Surgery].SURGERYID ";

        String[] keywords;
		if(query!=null) {
			keywords = query.split(" ");
			String arguments="( ?";
			for(int i=1;i<keywords.length;i++)
				arguments+=",?";
			arguments+=")";
		boolean flag=false;
			try {
				try {
					int i = Integer.parseInt(keywords[0]);
					sql += "Where PATIENTID=?";
					P = Database.getConnection().prepareStatement(sql);
					P.setInt(1, i);

				} catch (NumberFormatException e) {
					int N=0;
					switch (keywords.length) {
						case 1: sql += " Where  [PROFILE].FIRSTNAME in " + arguments + " or [PROFILE].LASTNAME in " + arguments +
								" or NAME in " + arguments ;N=3;break;
						case 2: sql += " Where ( [PROFILE].FIRSTNAME in " + arguments + " and [PROFILE].LASTNAME in " + arguments + ")" +
								" OR ( [PROFILE].FIRSTNAME in " + arguments + " and [Surgery].NAME in " + arguments + ")" +
								" OR ( [PROFILE].LASTNAME in " + arguments + " and [Surgery].NAME in " + arguments + ")"+
								" OR ( [Surgery].NAME =? )";N=6;flag=true;break;
						case 3: sql += " Where  ([PROFILE].FIRSTNAME in " + arguments + " and [PROFILE].LASTNAME in " + arguments
								+ " and [Surgery].NAME in " + arguments +
								") OR ( [Surgery].NAME = ?)";flag=true;N=3;break;
					}

					P = Database.getConnection().prepareStatement(sql);
					int counter=1;
					for(int i=0;i<N;i++) {
						for(int j=0;j<keywords.length;j++) {
							P.setString(counter, keywords[j]);
							counter++;
						}
					}
					if(flag)
						P.setString(counter, query.trim());
				}

				rs = P.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return loadFromResultSet(rs);
	}
	public int getCurrentDocotorId(){
		CurrentUser user = CurrentUser.getCurrentUser();
		int currentUserId = user.getId();
		int currentDoctorId = 0;
		if(user.getRole().equals("Doctor"))
			currentDoctorId = currentUserId;
		else {
			String query = "Select doctorId from Assistant where assistantId = " + currentUserId;
			ResultSet idRs = Database.getResults(query);
			try {
				idRs.next();
				currentDoctorId = idRs.getInt("DOCTORID");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  currentDoctorId;
	}
	
}
