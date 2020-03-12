package application;

import java.sql.*;

public class Database {
    private static ResultSet rs = null;
    private static PreparedStatement P;
    private String server = "localhost";
    private int port = 1433;
    private String user = "sa";
    private String password = "Aa123456789$";
    private String database = "RoboDB";
    private String jdbcurl;
    private static Connection con = null;


    private Database() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        jdbcurl = "jdbc:sqlserver://" + server + ":" + port + ";user=" + user + ";password=" + password
                + ";databasename=" + database + "";
        try {

            con = DriverManager.getConnection(jdbcurl);
            //System.out.println("Worked");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (con == null)
            new Database();
        return con;
    }

    public static ResultSet getResults(String sql) {

        try {
            P = getConnection().prepareStatement(sql);
            rs = P.executeQuery();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rs;

    }

    public static void execute(String sql) {

        try {
            P = getConnection().prepareStatement(sql);
            P.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}