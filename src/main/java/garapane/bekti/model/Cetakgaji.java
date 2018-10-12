/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.model;

import garapane.bekti.beans.GajiGuru;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrintManager;

/**
 *
 * @author ashura
 */
@javax.faces.bean.ManagedBean
@javax.faces.bean.ApplicationScoped
public class Cetakgaji {
    private GajiGuru g;

    public GajiGuru getG() {
        return g;
    }

    public void setG(GajiGuru g) {
        this.g = g;
    }

    public String cetak() {
        try {
            garapane.bekti.util.Db d = new garapane.bekti.util.Db();
            java.util.Map<String, Object> m = new java.util.HashMap<>();
            m.put("guru", g.getG().getKode());
            m.put("bulan", "" + g.getBulan());
            m.put("tp", g.getTp());
            net.sf.jasperreports.engine.JasperPrint p = JasperFillManager.fillReport(JasperCompileManager.compileReport("report1.jrxml"), m, d.getC());
            java.io.File f = new java.io.File(System.getProperty("user.home") + "/Unduhan/s.pdf");
            if(!f.getParentFile().exists()) f.getParentFile().mkdirs();
            if(f.exists()) f.delete();
            JasperExportManager.exportReportToPdfFile(p, f.getAbsolutePath());
            d.close();
        } catch (SQLException | JRException ex) {
            garapane.bekti.util.Db.hindar(ex, FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        }return "pdfe";
    }

    public String print() {
        try {
            garapane.bekti.util.Db d = new garapane.bekti.util.Db();
            java.util.Map<String, Object> m = new java.util.HashMap<>();
            m.put("guru", g.getG().getKode());
            m.put("bulan", "" + g.getBulan());
            m.put("tp", g.getTp());
            net.sf.jasperreports.engine.JasperPrint p = JasperFillManager.fillReport(JasperCompileManager.compileReport("report1.jrxml"), m, d.getC());
            JasperPrintManager.printReport(p, false);
            d.close();
        } catch (SQLException | JRException ex) {
            garapane.bekti.util.Db.hindar(ex, FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        }return User.ADMIN;
    }
}
