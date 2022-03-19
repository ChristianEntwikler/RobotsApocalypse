/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robots.apocalypse.db;

import java.sql.Connection;
import java.util.ResourceBundle;

/**
 *
 * @author cenebeli
 */
public class DatabaseResource {
    
    public static Connection getLocalConnection(ResourceBundle rb) {

        Connection localCon = null;
        String localServerName = rb.getString("localdbserver");
        String localDatabasePort = rb.getString("localdbport");
        String sInstance = rb.getString("sqlinstance");      
        String localDatabaseName = rb.getString("localdbname");
        String localUserName = rb.getString("localdbuser");
        String localUserpwd = rb.getString("localdbpwd");
        
        boolean isInstance = true;
        if (sInstance.equalsIgnoreCase("default")) {
            isInstance = false;
        }
        try {
            java.lang.Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            if (!isInstance) {
                localCon = java.sql.DriverManager.getConnection("jdbc:sqlserver://" + localServerName + ":" + localDatabasePort + ";Database=" + localDatabaseName + ";User=" + localUserName + "; Password=" + localUserpwd);
            } else {
                String str = "jdbc:sqlserver://" + localServerName + "\\" + sInstance + ";databaseName=" + localDatabaseName + ";";
                localCon = java.sql.DriverManager.getConnection(str, localUserName, localUserpwd);
            }


           if(localCon!=null) {
                System.out.println("Connection Local Database is Successful!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Trace in getConnection() : " + e.getMessage());
        }

        return localCon;
    }
    
    private static ResourceBundle resBundle = ResourceBundle.getBundle("config");
}
