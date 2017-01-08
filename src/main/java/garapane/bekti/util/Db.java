/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.util;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author ai
 */
public class Db {
    public static void hindar(Exception e,String r){
        java.sql.Timestamp t=java.sql.Timestamp.valueOf(LocalDateTime.now());
        java.io.File f=new java.io.File("error/"+r.replaceAll(":"," ").replaceAll("."," ")+"/"+t.getDate()+t.getMonth()+t.getYear()+"_"+t.getHours()+
        t.getMinutes()+t.getSeconds()+t.getNanos()+".log");
        if(!f.getParentFile().exists())f.getParentFile().mkdirs();try {
            java.io.PrintWriter o=new java.io.PrintWriter(f);
            e.printStackTrace(o);
            o.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private java.sql.Connection c;
    private java.sql.Statement s;

    public Db() throws SQLException {
        try {
            com.mysql.jdbc.Driver.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Db.hindar(ex, "localhost");
        }c=java.sql.DriverManager.getConnection("jdbc:mysql://localhost/bekti", "root", "");
        s=c.createStatement();
    }

    public void close() throws SQLException{
        s.close();
        c.close();
    }

    public java.sql.PreparedStatement getPS(String sql) throws SQLException{
        return c.prepareStatement(sql);
    }

    public java.sql.ResultSet getRS(String sql) throws SQLException{
        return s.executeQuery(sql);
    }
}