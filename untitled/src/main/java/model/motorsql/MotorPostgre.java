package model.motorsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MotorPostgre implements MotorSQL {
    private Properties properties;
    private Connection conn;
    private Statement st;
    private ResultSet rs;

    private static final String URL = "jdbc:postgresql://postgres.cqmdxzbqyk6m.us-east-1.rds.amazonaws.com/postgres";
    private static final String CONTROLADOR = "org.postgresql.Driver";
    private static final String USER = "postgres";
    private static final String PASS = "LUCASLUCAS";

    public MotorPostgre() {
        properties = new Properties();
        properties.setProperty("user", USER);
        properties.setProperty("password", PASS);
        properties.setProperty("ssl", "false");
    }

    public void connect() {
        try {
            Class.forName(CONTROLADOR);
            conn = DriverManager.getConnection(URL, properties);
            st = conn.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    public int execute(String sql) {
        int resp = 0;
        try {
            resp = st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        }
        return resp;
    }

    public ResultSet executeQuery(String sql) {
        try {
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
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
            System.out.println("SQL Exception during disconnect: " + ex.getMessage());
        }
    }
}
