/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.model;

import garapane.bekti.beans.Guru;
import java.sql.SQLException;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author ashura
 */
@javax.faces.bean.ManagedBean
@javax.faces.bean.ApplicationScoped
public class Editguru {
    private String kode,nama,alamat;
    private Date masuk;
    private boolean hapus;
    private Guru g;

    public Guru getG() {
        return g;
    }

    public void setG(Guru g) {
        this.g = g;
        if(g!=null)fillThis();
        else init();
    }

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

    @javax.annotation.PostConstruct
    public void init() {
        kode="";
        nama="";
        alamat="";
        masuk=Date.from(java.time.Instant.now());
        hapus=false;
        g=null;
    }

    private void fillThis() {
        kode=""+g.getKode();
        nama=""+g.getNama();
        alamat=""+g.getAlamat();
        masuk=(Date) g.getMasuk().clone();
    }

    public String simpan() {
        try {
            garapane.bekti.util.Db d=new garapane.bekti.util.Db();
            java.sql.PreparedStatement p=d.getPS("update guru set nama=?,alamat=?,masuk=? where kode=?");
            p.setString(1, nama);
            p.setString(2, alamat);
            java.time.LocalDate ld=masuk.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            p.setDate(3, java.sql.Date.valueOf(ld));
            p.setString(4, kode);
            p.execute();
            d.close();
        } catch (SQLException ex) {
            garapane.bekti.util.Db.hindar(ex, FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        } return User.ADMIN;
    }

    public void aksi(ActionEvent e) {
        try {
            garapane.bekti.util.Db d=new garapane.bekti.util.Db();
            java.sql.PreparedStatement p=d.getPS("update guru set nama=?,alamat=?,masuk=? where kode=?");
            p.setString(1, nama);
            p.setString(2, alamat);
            java.time.LocalDate ld=masuk.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            p.setDate(3, java.sql.Date.valueOf(ld));
            p.setString(4, kode);
            p.execute();
            d.close();
        } catch (SQLException ex) {
            garapane.bekti.util.Db.hindar(ex, FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        }
    }
}
