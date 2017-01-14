/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.beans;

import java.sql.SQLException;

/**
 *
 * @author ai
 */
@javax.faces.bean.ManagedBean
@javax.faces.bean.ApplicationScoped
public class Guru {
    private String kode,nama,alamat;
    private java.sql.Date masuk;

    public Guru(String kode,garapane.bekti.util.Db d) throws SQLException {
        this.kode = kode;
        java.sql.PreparedStatement p=d.getPS("select*from guru where kode=?");
        p.setString(1, kode);
        java.sql.ResultSet r=p.executeQuery();
        if(r.next()){
            nama=r.getString("nama");
            alamat=r.getString("alamat");
            masuk=r.getDate("masuk");
        }r.close();
        p.close();
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

    public java.sql.Date getMasuk() {
        return masuk;
    }

    public void setMasuk(java.sql.Date masuk) {
        this.masuk = masuk;
    }
}