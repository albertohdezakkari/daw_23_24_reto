package model.motorsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MotorOracle implements MotorSQL {
    private Properties properties;
    private Connection conn;
    private Statement st;
    private ResultSet rs;

    String RDS_INSTANCE_HOSTNAME = "oracles1.cqmdxzbqyk6m.us-east-1.rds.amazonaws.com";
    String RDS_INSTANCE_PORT = "1521";
    String JDBC_URL = "jdbc:oracle:thin:@" + RDS_INSTANCE_HOSTNAME + ":" + RDS_INSTANCE_PORT + ":ORCL";

    private static final String USER = "admin";
    private static final String PASS = "LUCASLUCAS";

    public MotorOracle() {
        /*properties = new Properties();
        properties.setProperty("user", USER);
        properties.setProperty("password", PASS);
        properties.setProperty("ssl", "false");*/
    }

    public void connect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(JDBC_URL,USER,PASS);
            System.out.println("Conectado a la base de datos Oracle exitosamente.");

            //          Statement stmt= connection.createStatement();

//            Class.forName(CONTROLADOR);
            // conn = DriverManager.getConnection(URL, properties);
            st = conn.createStatement();
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

