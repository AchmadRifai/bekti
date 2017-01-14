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
public class Ulan2 {
    private String kode;
    private Date mulai,henti;
    private List<Kas12>l;

    public Ulan2(String kode, Date mulai, Date henti) throws SQLException {
        this.kode = kode;
        this.mulai = mulai;
        this.henti = henti;
        l=new java.util.LinkedList<Kas12>();
        inisial();
    }

    public String getKode() {
        return kode;
    }

    public List<Kas12> getL() {
        return l;
    }

    private void inisial() throws SQLException {
        garapane.bekti.util.Db d=new garapane.bekti.util.Db();
        java.sql.PreparedStatement p=d.getPS("select kode from akutansi where tgl>=? and tgl<=? order by kode");
        p.setDate(1, mulai);
        p.setDate(2, henti);
        java.sql.ResultSet r=p.executeQuery();
        while(r.next())l.add(new Kas12(r.getString("kode"),d));
        r.close();
        p.close();
        d.close();
    }
}