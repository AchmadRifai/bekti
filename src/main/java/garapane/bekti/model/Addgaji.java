/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.model;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import javax.faces.context.FacesContext;

/**
 *
 * @author ai
 */
@javax.faces.bean.ManagedBean
public class Addgaji {
    public java.util.List<String>allGuru(){
        java.util.List<String>l=new java.util.LinkedList<String>();try {
            garapane.bekti.util.Db d=new garapane.bekti.util.Db();
            java.sql.ResultSet r=d.getRS("select kode from guru");
            while(r.next())l.add(r.getString("kode"));
            r.close();
            d.close();
        } catch (SQLException ex) {
            garapane.bekti.util.Db.hindar(ex, FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        }return l;
    }

    private String guru;
    private Date tgl;
    private BigDecimal jum;

    public String getGuru() {
        return guru;
    }

    public void setGuru(String guru) {
        this.guru = guru;
    }

    public Date getTgl() {
        return tgl;
    }

    public void setTgl(Date tgl) {
        this.tgl = tgl;
    }

    public BigDecimal getJum() {
        return jum;
    }

    public void setJum(BigDecimal jum) {
        this.jum = jum;
    }

    public String simpen(){
        try {
            garapane.bekti.util.Db d=new garapane.bekti.util.Db();
            java.time.LocalDate ld=tgl.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            java.sql.PreparedStatement p=d.getPS("insert into gaji values(?,?,?,?)");
            p.setString(1, guru);
            p.setString(2, akutansi(d));
            p.setString(3, ""+ld.getMonth());
            p.setString(4, tpne());
            p.execute();
            d.close();
        } catch (SQLException ex) {
            garapane.bekti.util.Db.hindar(ex, FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        }return User.ADMIN;
    }

    private String akutansi(garapane.bekti.util.Db d) throws SQLException {
        Date dt=new Date();
        java.time.LocalDate ld=tgl.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        String kode="kredit"+dt.getDate()+dt.getMonth()+dt.getYear()+dt.getHours()+dt.getMinutes()+dt.getSeconds();
        java.sql.PreparedStatement p=d.getPS("insert into akutansi values(?,?,?,?,?,?)");
        p.setString(1, kode);
        p.setDate(2, java.sql.Date.valueOf(ld));
        p.setString(3, "");
        p.setString(4, "Gaji untuk "+guru);
        p.setBigDecimal(5, jum);
        p.setString(6, "kredit");
        p.execute();
        p.close();
        return kode;
    }

    private String tpne() {
        int i=tgl.getMonth()-1;String kode;
        if(i<6)kode=""+tgl.getYear()+"/"+(tgl.getYear()-1);
        else kode=""+(1+tgl.getYear())+"/"+tgl.getYear();
        return kode;
    }
}