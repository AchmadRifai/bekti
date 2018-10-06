/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.model;

import garapane.bekti.beans.Guru;
import java.sql.SQLException;
import javax.faces.context.FacesContext;

/**
 *
 * @author ashura
 */
@javax.faces.bean.ManagedBean
@javax.faces.bean.ApplicationScoped
public class Delguru {
    private Guru g;
    private String kode;

    public Guru getG() {
        return g;
    }

    public void setG(Guru g) {
        this.g = g;
        kode=g.getKode();
    }

    public String simpan() {
        try {
            garapane.bekti.util.Db d=new garapane.bekti.util.Db();
            java.sql.PreparedStatement p=d.getPS("update guru set hapus=? where kode=?");
            p.setBoolean(1, true);
            p.setString(2, kode);
            p.execute();
            d.close();
        } catch (SQLException ex) {
            garapane.bekti.util.Db.hindar(ex, FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        } return User.ADMIN;
    }
}
