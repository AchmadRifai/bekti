/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.beans;

import garapane.bekti.util.Db;
import java.sql.SQLException;
import java.time.Month;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ai
 */
@javax.faces.bean.ManagedBean
public class Lap_tahun {
    private String kode;
    private java.sql.Date mulai,henti;
    private java.util.List<GajiGuru>gaji;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
        if(kode!=null&&mulai!=null&&henti!=null)gajine();
    }

    public java.sql.Date getMulai() {
        return mulai;
    }

    public void setMulai(java.sql.Date mulai) {
        this.mulai = mulai;
        if(kode!=null&&mulai!=null&&henti!=null)gajine();
    }

    public java.sql.Date getHenti() {
        return henti;
    }

    public void setHenti(java.sql.Date henti) {
        this.henti = henti;
        if(kode!=null&&mulai!=null&&henti!=null)gajine();
    }

    private void gajine() {
        HttpServletRequest req=(HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();try {
            Db d=new Db();
            gaji=new java.util.LinkedList<GajiGuru>();
            ulane(d,Month.JUNE);
            d.close();
        } catch (SQLException ex) {
            Db.hindar(ex, req.getRemoteAddr());
        }
    }

    private void ulane(Db d, Month month) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}