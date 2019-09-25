/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jesus
 */
public class ConnectDB {
    private final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final String url = "jdbc:sqlserver://192.168.1.203:1433;database=db_app;user=sa;password=123;";

    public Connection getConnection() throws SQLException {
        Connection cn = null;
        try {
            Class.forName(driver).newInstance();
            cn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException e) {
            //No se encuentra esta clase
            //Accesos ilegales
            // instancias al driver del sql .jar
            throw new SQLException(e.getMessage());
        }
        return cn;
    }
    
}
