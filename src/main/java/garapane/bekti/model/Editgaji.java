/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.model;

import garapane.bekti.beans.GajiGuru;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import javax.faces.context.FacesContext;

/**
 *
 * @author ashura
 */
@javax.faces.bean.ManagedBean
@javax.faces.bean.ApplicationScoped
public class Editgaji {
    private GajiGuru g;
    private Date tgl;
    private String guru, kode;
    private BigDecimal jum;

    public GajiGuru getG() {
        return g;
    }

    public void setG(GajiGuru g) {
        this.g = g;
        if(g != null) fillData();
        else init();
    }

    public String simpen() {
        try {
            ubahGaji();
            akutansi();
        } catch (SQLException ex) {
            garapane.bekti.util.Db.hindar(ex, FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        } return User.ADMIN;
    }

    private void fillData() {
        guru = "" + g.getG().getKode();
        tgl = new Date(g.getK().getTgl().getTime());
        jum = BigDecimal.ZERO.add(g.getK().getKredit().getAmount());
        muatKode();
    }

    @javax.annotation.PostConstruct
    public void init() {
        tgl = null;
        guru = "";
        jum = new BigDecimal(0);
        g = null;
        kode = null;
    }

    public Date getTgl() {
        return tgl;
    }

    public String getGuru() {
        return guru;
    }

    public BigDecimal getJum() {
        return jum;
    }

    public void setJum(BigDecimal jum) {
        this.jum = jum;
    }

    private void muatKode() {
        try {
            garapane.bekti.util.Db d = new garapane.bekti.util.Db();
            java.sql.PreparedStatement p = d.getPS("select trans from gaji where guru=? and bulan=? and t=?");
            p.setString(1, g.getG().getKode());
            p.setString(2, "" + g.getBulan());
            p.setString(3, g.getTp());
            java.sql.ResultSet r = p.executeQuery();
            if(r.next()) kode = r.getString("trans");
            r.close();
            p.close();
            d.close();
        } catch (SQLException ex) {
            garapane.bekti.util.Db.hindar(ex, FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        }
    }

    public String getKode() {
        return kode;
    }

    public void setTgl(Date tgl) {
        this.tgl = tgl;
    }

    private void ubahGaji() throws SQLException {
        garapane.bekti.util.Db d = new garapane.bekti.util.Db();
        java.time.LocalDate ld=tgl.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        java.sql.PreparedStatement p = d.getPS("update gaji set bulan=?,t=? where guru=? and trans=?");
        p.setString(1, "" + ld.getMonth());
        p.setString(2, tpne());
        p.setString(3, guru);
        p.setString(4, kode);
        p.execute();
        p.close();
        d.close();
    }

    private String tpne() {
        int i=tgl.getMonth()-1;String kode;
        if(i<6)kode=""+(tgl.getYear()+1900-1)+"/"+(1900+tgl.getYear());
        else kode=""+(tgl.getYear()+1900)+"/"+(tgl.getYear()+1900+1);
        return kode;
    }

    private void akutansi() throws SQLException {
        garapane.bekti.util.Db d = new garapane.bekti.util.Db();
        java.sql.PreparedStatement p = d.getPS("update akutansi set tgl=?,duwik=? where kode=?");
        java.time.LocalDate ld=tgl.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        p.setDate(1, java.sql.Date.valueOf(ld));
        p.setBigDecimal(2, jum);
        p.setString(3, kode);
        p.execute();
        p.close();
        d.close();
    }
}
