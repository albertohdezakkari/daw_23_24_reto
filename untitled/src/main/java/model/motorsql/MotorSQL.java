package model.motorsql;

import java.sql.ResultSet;

public interface MotorSQL {
    void connect();
    void disconnect();
    int execute(String sql) ;
    ResultSet executeQuery(String sql);
}
