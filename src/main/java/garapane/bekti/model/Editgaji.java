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
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

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
            garapane.bekti.util.Db d = new garapane.bekti.util.Db();
            d.close();
        } catch (SQLException ex) {
            garapane.bekti.util.Db.hindar(ex, FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        } return User.ADMIN;
    }

    private void fillData() {
        guru = "" + g.getG().getKode();
        tgl = Date.from(g.getK().getTgl().toInstant());
        if(Money.zero(CurrencyUnit.of("IDR")).isEqual(g.getK().getDebit()))
            jum = BigDecimal.ZERO.add(g.getK().getKredit().getAmount());
        else jum = BigDecimal.ZERO.add(g.getK().getDebit().getAmount());
        muatKode();
    }

    @javax.annotation.PostConstruct
    public void init() {
        tgl = new java.util.Date();
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
}
