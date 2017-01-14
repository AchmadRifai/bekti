/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.beans;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ai
 */
@javax.faces.bean.ManagedBean
@javax.faces.bean.ApplicationScoped
public class Ulan1 {
    private Date mulai,henti;
    private String kode;
    private List<Kas1>l;

    public Ulan1(Date mulai, Date henti, String kode) throws SQLException {
        this.mulai = mulai;
        this.henti = henti;
        this.kode = kode;
        l=new java.util.LinkedList<Kas1>();
        inisial();
    }

    private void inisial() throws SQLException {
        garapane.bekti.util.Db d=new garapane.bekti.util.Db();
        java.sql.PreparedStatement p=d.getPS("select kode from akutansi where tgl>=? and tgl<=? order by kode");
        p.setDate(1, mulai);
        p.setDate(2, henti);
        java.sql.ResultSet r=p.executeQuery();
        int x=1;
        while(r.next()){
            Kas1 k=new garapane.bekti.beans.Kas1(r.getString("kode"), d);
            k.setNo(x);
            x++;
            l.add(k);
        }r.close();
        p.close();
        d.close();
    }

    public String getKode() {
        return kode;
    }

    public List<Kas1> getL() {
        return l;
    }
}