package model.motorsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MotorMySQL implements MotorSQL {
    private Properties properties;
    private Connection conn;
    private Statement st;
    private ResultSet rs;

    //private static final String URL = "jdbc:mysql://localhost/netflix";
    //private static final String URL = "jdbc:mysql://mysqls.cqmdxzbqyk6m.us-east-1.rds.amazonaws.com/mysqls";
  //  private static final String URL = "jdbc:mysql://mysqls.cqmdxzbqyk6m.us-east-1.rds.amazonaws.com/mysqls";
    private static final String URL = "jdbc:mysql://mysqls.cqmdxzbqyk6m.us-east-1.rds.amazonaws.com/netflix?useSSL=false&serverTimezone=UTC";


    // private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
    //jdbc:derby://localhost:1527/netflix
    //private static final String URL = "jdbc:derby://localhost:1527/netflix";
    private static final String USER = "mysqls";
    private static final String PASS = "LUCASLUCAS";

    public MotorMySQL(){
        properties = new Properties();
            properties.setProperty("user", MotorMySQL.USER);
            properties.setProperty("password", MotorMySQL.PASS);
            properties.setProperty("ssl", "true");
            properties.setProperty("verifyServerCertificate", "true");
            properties.setProperty("requireSSL", "true");
    }
    public void connect() {
        try {
            Class.forName(CONTROLADOR);
            conn = DriverManager.getConnection(URL, properties);
            st = conn.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public int execute(String sql) {
        int resp = 0;
        try {
            resp = st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resp;
    }
    public ResultSet executeQuery(String sql) {
        try {
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
    }
    public void disconnect() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
        }
    }

}
