package models;

import application.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorsDashboardModel extends DashboardModel {

    @Override
    public ArrayList<Doctor> loadProfiles() {
        ResultSet rs = Database.getResults("Select * from " +
                "Profile join [User] on Profile.PROFILEID = [USER].PROFILEID " +
                "join DOCTOR on DOCTOR.USERID= [USER].USERID");
        return loadFromResultSet(rs);

    }

    @Override
    public ArrayList<Doctor> filter(String query) {
        PreparedStatement P = null;
        ResultSet rs = null;
        String sql = "Select * from Profile join \"USER\" on" +
                " Profile.PROFILEID = \"USER\".PROFILEID join DOCTOR on DOCTOR.USERID= \"USER\".USERID ";
        try {
            try {
                int i=Integer.parseInt(query);
                sql += "Where DOCTORID=?";
                P = Database.getConnection().prepareStatement(sql);
                P.setInt(1,i );
            } catch (NumberFormatException e) {
                sql += "Where FIRSTNAME=? Or LASTNAME=? Or SPECIALTY=? ";
                P = Database.getConnection().prepareStatement(sql);
                P.setString(1, query);
                P.setString(2, query);
                P.setString(3, query);
            }
            rs=P.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loadFromResultSet(rs);
    }


    private ArrayList<Doctor> loadFromResultSet(ResultSet rs) {
        ArrayList<Doctor> doctors = new ArrayList();
        try {
            if (rs != null)
                while (rs.next()) {
                    Doctor d = new Doctor(rs.getInt("PROFILEID"),
                            rs.getString("FIRSTNAME").trim(), rs.getString("MIDDLENAME").trim(),
                            rs.getString("LASTNAME").trim());
                    d.setDoctorId(rs.getInt("DOCTORID"));
                    d.setSpeciality(rs.getString("SPECIALTY").trim());
                    d.setAddress(rs.getString("ADDRESS").trim());
                    d.setPhone(rs.getString("PHONE").trim());
                    d.setBirthDate(rs.getString("DATEOFBIRTH").trim());
                    d.setBloodType(rs.getString("BLOODTYPE").trim());
                    d.setEmail(rs.getString("EMAIL").trim());
                    doctors.add(d);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }
}
