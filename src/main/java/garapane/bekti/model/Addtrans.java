/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.faces.context.FacesContext;

/**
 *
 * @author ai
 */
@javax.faces.bean.ManagedBean
public class Addtrans {
    private Date tgl;
    private String hal,ket,tipe;
    private BigDecimal duwek;

    public Date getTgl() {
        return tgl;
    }

    public void setTgl(Date tgl) {
        this.tgl = tgl;
    }

    public String getHal() {
        return hal;
    }

    public void setHal(String hal) {
        this.hal = hal;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public BigDecimal getDuwek() {
        return duwek;
    }

    public void setDuwek(BigDecimal duwek) {
        this.duwek = duwek;
    }

    public String[]kabehTipe(){
        return new String[]{"debit","kredit"};
    }

    public String save(){
        try {
            garapane.bekti.util.Db d=new garapane.bekti.util.Db();
            String kode=genKode();
            java.time.LocalDate ld=tgl.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            java.sql.PreparedStatement p=d.getPS("insert into akutansi values(?,?,?,?,?,?)");
            p.setString(1, kode);
            p.setDate(2, java.sql.Date.valueOf(ld));
            p.setString(3, hal);
            p.setString(4, ket);
            p.setBigDecimal(5, duwek);
            p.setString(6, tipe);
            p.execute();
            p.close();
            d.close();
        } catch (Exception ex) {
            garapane.bekti.util.Db.hindar(ex, FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        }return User.ADMIN;
    }

    private String genKode() {
        Date d=new Date();
        return tipe+d.getDate()+d.getMonth()+d.getYear()+d.getHours()+d.getMinutes()+d.getSeconds();
    }
}