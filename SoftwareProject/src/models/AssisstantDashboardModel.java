package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import application.Database;

public class AssisstantDashboardModel extends DashboardModel<Assistant> {

    @Override
    public ArrayList<Assistant> loadProfiles() {
    	ResultSet rs = Database.getResults("Select * from " +
                "Profile join [User] on Profile.PROFILEID = [USER].PROFILEID " +
                "join ASSISTANT on ASSISTANT.USERID= [USER].USERID");
        return loadFromResultSet(rs);
    }
    @Override
    public ArrayList<Assistant> filter(String query) {
    	if(query.isEmpty())  return loadProfiles();
   	 PreparedStatement P = null;
        ResultSet rs = null;
        String sql = "Select * from Profile join \"USER\" on" +
                " Profile.PROFILEID = \"USER\".PROFILEID join ASSISTANT on ASSISTANT.USERID= \"USER\".USERID ";
        try {
            try {
                int i=Integer.parseInt(query);
                sql += "Where ASSISTANTID=?";
                P = Database.getConnection().prepareStatement(sql);
                P.setInt(1,i );
            } catch (NumberFormatException e) {
                sql += "Where FIRSTNAME=? Or LASTNAME=? Or CONTRACTTYPE=? ";
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

    private ArrayList<Assistant> loadFromResultSet(ResultSet rs) {
    	ArrayList<Assistant> assistants = new ArrayList<>();
        try {
            if (rs != null )
                while (rs.next()) {
                    Assistant a = new Assistant(rs.getInt("PROFILEID"),
                            rs.getString("FIRSTNAME"),
                            rs.getString("MIDDLENAME"),
                            rs.getString("LASTNAME"));
                    ResultSet rSet = Database.getResults("select DOCTORID from ASSISTANT "
                    		+ "where ASSISTANTID =" 
                    		+rs.getInt("ASSISTANTID"));

                    if(rSet != null) {
                    	rSet.next();
                    	a.setDoctorId(rSet.getInt("DOCTORID"));
                    }
                    a.setAssistantId(rs.getInt("ASSISTANTID"));
                    a.setAddress(rs.getString("ADDRESS"));
                    a.setPhone(rs.getString("PHONE"));
                    a.setBirthDate(rs.getDate("DATEOFBIRTH"));
                    a.setBloodType(rs.getString("BLOODTYPE"));
                    a.setEmail(rs.getString("EMAIL"));
                    a.setContractType(rs.getString("CONTRACTTYPE"));
                    assistants.add(a);
                }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return assistants;
    }

    @Override
    public void create(Assistant a)

    {    PreparedStatement preparedStatement, preparedStatement1, preparedStatement2 ;
        ResultSet resultSet, resultSet1, resultSet2;
        preparedStatement = null;
        preparedStatement1 = null;
        preparedStatement2 = null;
        resultSet = null; resultSet1 = null; resultSet2 = null;
        int profileId =0;
        int userId =0;
        int assistantId =0;

        String sql = ("insert into Profile(FIRSTNAME ,MIDDLENAME ,LASTNAME  ,EMAIL  ,PHONE  ,DATEOFBIRTH  ,ADDRESS  ,BLOODTYPE) values (?,?,?,?,?,?,?,?)");
        try {
            preparedStatement=Database.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, a.getFirstName());
            preparedStatement.setString(2, a.getMiddleName());
            preparedStatement.setString(3, a.getLastName());
            preparedStatement.setString(4, a.getEmail() );
            preparedStatement.setString(5, a.getPhone());
            preparedStatement.setDate(6, a.getBirthDate());
            preparedStatement.setString(7, a.getAddress());
            preparedStatement.setString(8, a.getBloodType());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet != null && resultSet.next()){
                profileId = resultSet.getInt(1);
            }
            // System.out.println("A new Profile was inserted successfully!" + profileId);
            String  sql1 = ("insert into \"USER\"(PROFILEID ,USERNAME, PASSWORD ,ROLE) values (?,?,?,?)");
            try {
                preparedStatement1=Database.getConnection().prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);
                preparedStatement1.setInt(1, profileId);
                preparedStatement1.setString(2, a.getUserName());
                preparedStatement1.setString(3, a.getPassword());
                preparedStatement1.setString(4, a.getRole() );
                preparedStatement1.executeUpdate();
                resultSet1 = preparedStatement1.getGeneratedKeys();
                if(resultSet1 != null && resultSet1.next()){
                    userId = resultSet1.getInt(1);
                }
                // System.out.println("A new user was inserted successfully!" + userId);

                String   sql2 = ("insert into Assistant(USERID ,DoctorId  ,CONTRACTTYPE) values (?,?,?)");
                try {
                    preparedStatement2=Database.getConnection().prepareStatement(sql2,Statement.RETURN_GENERATED_KEYS);
                    preparedStatement2.setInt(1, userId);
                    preparedStatement2.setInt(2, a.getDoctorId());
                    preparedStatement2.setString(3, a.getContractType());
                    preparedStatement2.executeUpdate();
                    resultSet2 = preparedStatement2.getGeneratedKeys();
                    if(resultSet2 != null && resultSet2.next()){
                        assistantId = resultSet2.getInt(1);
                    }
                    // System.out.println("A new Doctor was inserted successfully!" + doctorId);


                } catch (Exception e) {

                    System.out.println("A new Assistant was insertion failed! " + e);
                }

            } catch (Exception e) {

                System.out.println("A new user was insertion failed!  " + e);
            }

        } catch (Exception e) {

            System.out.println("A new profile was insertion failed! " + e);
        }


    }


    @Override
    public void edit(Assistant a)

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
            preparedStatement.setString(1, a.getFirstName());
            preparedStatement.setString(2, a.getMiddleName());
            preparedStatement.setString(3, a.getLastName());
            preparedStatement.setString(4, a.getEmail() );
            preparedStatement.setString(5, a.getPhone());
            preparedStatement.setDate(6, a.getBirthDate());
            preparedStatement.setString(7, a.getAddress());
            preparedStatement.setString(8, a.getBloodType());
            preparedStatement.setInt(9, a.getProfileId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            // System.out.println("A new Profile was inserted successfully!" + profileId);
            String  sql1 = ("update \"USER\" set USERNAME = ? , ROLE = ? where userId = ?");
            try {
                preparedStatement1=Database.getConnection().prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);
                preparedStatement1.setString(1, a.getUserName());
                preparedStatement1.setString(2, a.getRole() );
                preparedStatement1.setInt(3, a.getUserId() );
                preparedStatement1.executeUpdate();
                resultSet1 = preparedStatement1.getGeneratedKeys();

                String   sql2 = ("update  ASSISTANT set CONTRACTTYPE = ? , DOCTORID = ? where ASSISTANTID = ?");
                try {
                    preparedStatement2=Database.getConnection().prepareStatement(sql2,Statement.RETURN_GENERATED_KEYS);

                    preparedStatement2.setString(1, a.getContractType());
                    preparedStatement2.setInt(2, a.getDoctorId());
                    preparedStatement2.setInt(3, a.getAssistantId());
                    preparedStatement2.executeUpdate();
                    resultSet2 = preparedStatement2.getGeneratedKeys();


                } catch (Exception e) {

                    System.out.println("A Assistant was updated failed! " + e);
                }

            } catch (Exception e) {

                System.out.println("A user was updated failed!  " + e);
            }

        } catch (Exception e) {

            System.out.println("A profile was updated failed! " + e);
        }


    }


}
