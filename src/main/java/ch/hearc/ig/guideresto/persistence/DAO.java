package ch.hearc.ig.guideresto.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    private String DBURL = "jdbc:oracle:thin@db.ig.he-arc.ch";
    private String DBUSER = "virginie_valentin1";
    private String DBPWD = "virginie_valentin1";
    private Connection cnn = null;

    //public static final ThreadLocal<Connection> connection; version a plusieurs thread si besoin?!
    //Connection connection = DriverManager.getConnection()
    public Connection openConnection() {
        try {
            cnn = DriverManager.getConnection(DBURL, DBUSER, DBPWD);
            cnn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cnn;
    }
}
