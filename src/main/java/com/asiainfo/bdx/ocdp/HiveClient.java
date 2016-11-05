package com.asiainfo.bdx.ocdp;

import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.conf.Configuration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Created by baikai on 9/1/16.
 */
public class HiveClient {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    private Connection conn;

    private String hiveJDBCUrl;

    private String superUser;

    private Configuration conf;

    public HiveClient(String hiveHost, String hivePort, String dbName, String userPrinc){
        this.hiveJDBCUrl = "jdbc:hive2://" + hiveHost + ":" + hivePort + "/" + dbName + ";principal=" + userPrinc;
        this.superUser = userPrinc;
        this.conf = new Configuration();
        conf.set("hadoop.security.authentication", "Kerberos");
        this.authentication(conf);
    }

    public void execute(String sqlStatement) throws Exception{
        try{
            Class.forName(this.driverName);
            this.conn = DriverManager.getConnection(this.hiveJDBCUrl);
            Statement stmt = conn.createStatement();
            stmt.execute(sqlStatement);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            throw e;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            conn.close();
            System.out.println("SQL statement " + sqlStatement + " execute success.");
        }
    }

    public void queryExecute(String sqlStatement) throws Exception{
        try{
            Class.forName(this.driverName);
            this.conn = DriverManager.getConnection(this.hiveJDBCUrl);
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sqlStatement);
            System.out.println("Hive SQL query output: ");
            while (res.next()){
                System.out.println(res.getString(1));
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            throw e;
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            conn.close();
            System.out.println("SQL statement " + sqlStatement + " execute success.");
        }
    }

    private void authentication(Configuration conf){
        System.out.println("Authentication with KDC.");
        UserGroupInformation.setConfiguration(conf);
        try{
            UserGroupInformation.getLoginUser().reloginFromTicketCache();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
