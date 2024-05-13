package model.factory;

import model.motorsql.MotorMySQL;
import model.motorsql.MotorOracle;
import model.motorsql.MotorPostgre;
import model.motorsql.MotorSQL;

public class DatabaseFactory {
    public static final String MYSQL = "MYSQL";
    public static final String ORACLE = "ORACLE";
    public static final String POSTGRE = "POSTGRE";

    public static MotorSQL getDatabase(String tipo) {
        switch (tipo) {
            case MYSQL:
                return new MotorMySQL();
            case ORACLE:
                return new MotorOracle();
            case POSTGRE:
                return new MotorPostgre();
            default:
                throw new IllegalArgumentException("Tipo de base de datos no soportado");
        }
    }
}

