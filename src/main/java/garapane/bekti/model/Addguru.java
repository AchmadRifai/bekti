/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.model;

import java.sql.SQLException;
import java.util.Date;
import javax.faces.context.FacesContext;

/**
 *
 * @author ai
 */
@javax.faces.bean.ManagedBean
public class Addguru {
    private String kode,nama,alamat;
    private Date masuk;
    private boolean cekal;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Date getMasuk() {
        return masuk;
    }

    public void setMasuk(Date masuk) {
        this.masuk = masuk;
    }

    public void validasikan() {
        cekal=kode==null;if(!cekal)try {
            if(alamat.isEmpty()||kode.isEmpty()||nama.isEmpty())cekal=true;
            garapane.bekti.util.Db d=new garapane.bekti.util.Db();
            java.sql.PreparedStatement p=d.getPS("select nama from guru where kode=?");
            p.setString(1, kode);
            java.sql.ResultSet r=p.executeQuery();
            cekal=r.next();
            r.close();
            p.close();
            d.close();
        } catch (SQLException ex) {
            cekal=true;
            garapane.bekti.util.Db.hindar(ex, FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        }
    }

    @javax.annotation.PostConstruct
    public void init(){
        validasikan();
    }

    public boolean isCekal() {
        return cekal;
    }

    public String save(){
        try {
            garapane.bekti.util.Db d=new garapane.bekti.util.Db();
            java.time.LocalDate ld=masuk.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            java.sql.PreparedStatement p=d.getPS("insert into guru values(?,?,?,?)");
            p.setString(1, kode);
            p.setString(2, nama);
            p.setString(3, alamat);
            p.setDate(4, java.sql.Date.valueOf(ld));
            p.execute();
            p.close();
            d.close();
        } catch (SQLException ex) {
            garapane.bekti.util.Db.hindar(ex, FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        }return User.ADMIN;
    }
}