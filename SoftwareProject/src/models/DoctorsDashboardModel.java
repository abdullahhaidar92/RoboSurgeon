package models;

import application.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DoctorsDashboardModel extends DashboardModel<Doctor> {

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





    @Override
    public void create(Doctor d)

    {    PreparedStatement preparedStatement, preparedStatement1, preparedStatement2 ;
        ResultSet resultSet, resultSet1, resultSet2;
        preparedStatement = null;
        preparedStatement1 = null;
        preparedStatement2 = null;
        resultSet = null; resultSet1 = null; resultSet2 = null;
        int profileId =0;
        int userId =0;
        int doctorId =0;

        String sql = ("insert into Profile(FIRSTNAME ,MIDDLENAME ,LASTNAME  ,EMAIL  ,PHONE  ,DATEOFBIRTH  ,ADDRESS  ,BLOODTYPE) values (?,?,?,?,?,?,?,?)");
        try {
            preparedStatement=Database.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, d.getFirstName());
            preparedStatement.setString(2, d.getMiddleName());
            preparedStatement.setString(3, d.getLastName());
            preparedStatement.setString(4, d.getEmail() );
            preparedStatement.setString(5, d.getPhone());
            preparedStatement.setDate(6, d.getBirthDate());
            preparedStatement.setString(7, d.getAddress());
            preparedStatement.setString(8, d.getBloodType());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            // resultSet = preparedStatement.executeQuery();

            if(resultSet != null && resultSet.next()){
                profileId = resultSet.getInt(1);
            }
            // System.out.println("A new Profile was inserted successfully!" + profileId);
            String  sql1 = ("insert into \"USER\"(PROFILEID ,USERNAME, PASSWORD ,ROLE) values (?,?,?,?)");
            try {
                preparedStatement1=Database.getConnection().prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);
                preparedStatement1.setInt(1, profileId);
                preparedStatement1.setString(2, d.getUserName());
                preparedStatement1.setString(3, d.getPassword());
                preparedStatement1.setString(4, d.getRole() );
                preparedStatement1.executeUpdate();
                resultSet1 = preparedStatement1.getGeneratedKeys();
                if(resultSet1 != null && resultSet1.next()){
                    userId = resultSet1.getInt(1);
                }
                // System.out.println("A new user was inserted successfully!" + userId);

                String   sql2 = ("insert into Doctor(USERID   ,SPECIALTY) values (?,?)");
                try {
                    preparedStatement2=Database.getConnection().prepareStatement(sql2,Statement.RETURN_GENERATED_KEYS);
                    preparedStatement2.setInt(1, userId);
                    preparedStatement2.setString(2, d.getSpeciality());
                    preparedStatement2.executeUpdate();
                    resultSet2 = preparedStatement2.getGeneratedKeys();
                    if(resultSet2 != null && resultSet2.next()){
                        doctorId = resultSet2.getInt(1);
                    }
                    // System.out.println("A new Doctor was inserted successfully!" + doctorId);


                } catch (Exception e) {

                    System.out.println("A new Doctor was insertion failed! " + e);
                }

            } catch (Exception e) {

                System.out.println("A new user was insertion failed!  " + e);
            }

        } catch (Exception e) {

            System.out.println("A new profile was insertion failed! " + e);
        }


    }


    @Override
    public void edit(Doctor d)

    {    PreparedStatement preparedStatement, preparedStatement1, preparedStatement2 ;
        ResultSet resultSet, resultSet1, resultSet2;
        preparedStatement = null;
        preparedStatement1 = null;
        preparedStatement2 = null;
        resultSet = null; resultSet1 = null; resultSet2 = null;
        int profileId =0;
        int userId =0;
        int doctorId =0;

        String sql = ("update Profile set FIRSTNAME = ? ,MIDDLENAME = ?,LASTNAME = ?  ,EMAIL = ?  ,PHONE  = ? ,DATEOFBIRTH = ? ,ADDRESS = ?  ,BLOODTYPE =?	where profileId = ? ");
        try {
            preparedStatement=Database.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, d.getFirstName());
            preparedStatement.setString(2, d.getMiddleName());
            preparedStatement.setString(3, d.getLastName());
            preparedStatement.setString(4, d.getEmail() );
            preparedStatement.setString(5, d.getPhone());
            preparedStatement.setDate(6, d.getBirthDate());
            preparedStatement.setString(7, d.getAddress());
            preparedStatement.setString(8, d.getBloodType());
            preparedStatement.setInt(9, d.getProfileId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            // System.out.println("A new Profile was inserted successfully!" + profileId);
            String  sql1 = ("update \"USER\" set USERNAME = ? , ROLE = ? where userId = ?");
            try {
                preparedStatement1=Database.getConnection().prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);
                preparedStatement1.setString(1, d.getUserName());
                preparedStatement1.setString(2, d.getRole() );
                preparedStatement1.setInt(3, d.getUserId() );
                preparedStatement1.executeUpdate();
                resultSet1 = preparedStatement1.getGeneratedKeys();

                String   sql2 = ("update  Doctor set SPECIALTY = ? where doctorId = ?");
                try {
                    preparedStatement2=Database.getConnection().prepareStatement(sql2,Statement.RETURN_GENERATED_KEYS);

                    preparedStatement2.setString(1, d.getSpeciality());
                    preparedStatement2.setInt(2, d.getDoctorId());
                    preparedStatement2.executeUpdate();
                    resultSet2 = preparedStatement2.getGeneratedKeys();


                } catch (Exception e) {

                    System.out.println("A Doctor was updated failed! " + e);
                }

            } catch (Exception e) {

                System.out.println("A user was updated failed!  " + e);
            }

        } catch (Exception e) {

            System.out.println("A profile was updated failed! " + e);
        }


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
                    d.setBirthDate(rs.getDate("DATEOFBIRTH"));
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
