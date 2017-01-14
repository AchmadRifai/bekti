/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.beans;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author ai
 */
@javax.faces.bean.ManagedBean
@javax.faces.bean.ApplicationScoped
public class Lap_tahun {
    private String kode;
    private Date mulai,henti;
    private List<GajiGuru>gaji;

    public Lap_tahun(String kode, Date mulai, Date henti) {
        this.kode = kode;
        this.mulai = mulai;
        this.henti = henti;
        gaji=new java.util.LinkedList<GajiGuru>();
        allGaji();
    }

    private void allGaji() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getKode() {
        return kode;
    }

    public List<GajiGuru> getGaji() {
        return gaji;
    }
}