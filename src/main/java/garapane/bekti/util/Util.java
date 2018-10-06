/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.util;

import java.sql.SQLException;

/**
 *
 * @author ai
 */
public class Util {
    public static java.util.List<garapane.bekti.beans.Guru>kabehGuru() throws SQLException{
        java.util.List<garapane.bekti.beans.Guru>l=new java.util.LinkedList<>();
        Db d=new Db();
        java.sql.ResultSet r=d.getRS("select kode from guru where not hapus");
        while(r.next())l.add(new garapane.bekti.beans.Guru(r.getString("kode"), d));
        r.close();
        d.close();
        return l;
    }
}