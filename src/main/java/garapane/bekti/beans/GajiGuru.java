/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.beans;

import java.sql.SQLException;
import java.time.Month;

/**
 *
 * @author ai
 */
@javax.faces.bean.ApplicationScoped
@javax.faces.bean.ManagedBean
public class GajiGuru {
    private Month bulan;
    private Guru g;
    private Kas1 k;
    private String tp;

    public GajiGuru(Month bulan, Guru g, String tp) throws SQLException {
        this.bulan = bulan;
        this.g = g;
        this.tp = tp;
        init();
    }

    public Month getBulan() {
        return bulan;
    }

    public Guru getG() {
        return g;
    }

    public Kas1 getK() {
        return k;
    }

    private void init() throws SQLException {
        garapane.bekti.util.Db d=new garapane.bekti.util.Db();
        java.sql.PreparedStatement p=d.getPS("select trans from gaji where guru=? and bulan=? and t=?");
        p.setString(1, g.getKode());
        p.setString(2, bulan.name());
        p.setString(3, tp);
        java.sql.ResultSet r=p.executeQuery();
        if(r.next()){
            k=new Kas1(r.getString("trans"),d);
        }r.close();
        p.close();
        d.close();
    }

    public String getTp() {
        return tp;
    }
}