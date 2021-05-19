package Utilidades;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Utilidades.variables;

/**
 * Created by hvelazquez on 04/04/2018.
 */

public class Connection_Oracle {
     @SuppressLint("NewaApi")
    public Connection Connections(){

       //  String user = "grupomaehara";
         String user = variables.userdb;
         String url = "jdbc:oracle:thin:@192.168.6.122:1521:XE";

       //  String passwd = "123456";
         String passwd = variables.passdb;
         String driver = "oracle.jdbc.driver.OracleDriver";
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection=null;

        try {
            Class.forName(driver);
            connection= DriverManager.getConnection(url, user, passwd);
        }
        catch (SQLException se) {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        return connection;
 }
 }

