/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.beans;

import garapane.bekti.util.Db;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.joda.money.CurrencyUnit;

/**
 *
 * @author ai
 */
@javax.faces.bean.ManagedBean
public class Lap_bulan {
    private String kode;
    private Date mulai,henti;
    private List<Kas1>kas1;
    private List<Kas12>kas12;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
        if(kode!=null&&henti!=null&&mulai!=null)inisial();
    }

    public void setMulai(Date mulai) {
        this.mulai = mulai;
        if(kode!=null&&henti!=null&&mulai!=null)inisial();
    }

    public void setHenti(Date henti) {
        this.henti = henti;
        if(kode!=null&&henti!=null&&mulai!=null)inisial();
    }

    private void inisial() {
        HttpServletRequest req=(HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();try {
            Db d=new Db();
            kas1=new java.util.LinkedList<Kas1>();
            kas12=new java.util.LinkedList<Kas12>();
            kas1ne(d);
            kas12ne(d);
            d.close();
        } catch (SQLException ex) {
            Db.hindar(ex, req.getRemoteAddr());
        }
    }

    private void kas1ne(Db d) throws SQLException {
        java.sql.PreparedStatement p=d.getPS("select*from akutansi where tgl>=? and tgl<=? order by tgl");
        p.setDate(1, mulai);
        p.setDate(2, henti);
        java.sql.ResultSet r=p.executeQuery();
        int i=1;
        while(r.next()){
            Kas1 k=new Kas1();
            k.setTgl(r.getDate("tgl"));
            k.setNo(i);
            k.setHal(r.getString("hal"));
            k.setKet(r.getString("ket"));
            k.setSaldo(org.joda.money.Money.zero(CurrencyUnit.of("IDR")));
            k.setDebit("debit".equals(r.getString("tipe"))?org.joda.money.Money.of(CurrencyUnit.of("IDR"), r.getLong("duwek")):
                    org.joda.money.Money.zero(CurrencyUnit.of("IDR")));
            k.setKredit("kredit".equals(r.getString("tipe"))?org.joda.money.Money.of(CurrencyUnit.of("IDR"), r.getLong("duwek")):
                    org.joda.money.Money.zero(CurrencyUnit.of("IDR")));
            kas1.add(k);
            i++;
        }r.close();
        p.close();
    }

    private void kas12ne(Db d) throws SQLException {
        java.sql.PreparedStatement p=d.getPS("select*from akutansi where tgl>=? and tgl<=? order by tgl");
        p.setDate(1, mulai);
        p.setDate(2, henti);
        java.sql.ResultSet r=p.executeQuery();
        while(r.next()){
            Kas12 k=new Kas12();
            k.setKet(r.getString("ket"));
            k.setTgl(r.getDate("tgl"));
            k.setSaldo(org.joda.money.Money.zero(CurrencyUnit.of("IDR")));
            k.setSaldo(org.joda.money.Money.zero(CurrencyUnit.of("IDR")));
            k.setDebit("debit".equals(r.getString("tipe"))?org.joda.money.Money.of(CurrencyUnit.of("IDR"), r.getLong("duwek")):
                    org.joda.money.Money.zero(CurrencyUnit.of("IDR")));
            k.setKredit("kredit".equals(r.getString("tipe"))?org.joda.money.Money.of(CurrencyUnit.of("IDR"), r.getLong("duwek")):
                    org.joda.money.Money.zero(CurrencyUnit.of("IDR")));
            kas12.add(k);
        }r.close();
        p.close();
    }

    public List<Kas1> getKas1() {
        return kas1;
    }

    public List<Kas12> getKas12() {
        return kas12;
    }
}