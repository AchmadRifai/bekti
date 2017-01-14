/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.beans;

import java.sql.SQLException;
import org.joda.money.CurrencyUnit;

/**
 *
 * @author ai
 */
@javax.faces.bean.ManagedBean
@javax.faces.bean.ApplicationScoped
public class Kas1 {
    private String hal,ket;
    private org.joda.money.Money kredit,debit,saldo;
    private int no;
    private java.sql.Date tgl;

    public Kas1(String kode,garapane.bekti.util.Db d) throws SQLException {
        java.sql.PreparedStatement p=d.getPS("select*from akutansi where kode=?");
        p.setString(1, kode);
        java.sql.ResultSet r=p.executeQuery();
        if(r.next()){
            kredit="kredit".equals(r.getString("tipe"))?org.joda.money.Money.of(CurrencyUnit.of("IDR"), r.getLong("duwik")):
                    org.joda.money.Money.zero(CurrencyUnit.of("IDR"));
            debit="debit".equals(r.getString("tipe"))?org.joda.money.Money.of(CurrencyUnit.of("IDR"), r.getLong("duwik")):
                    org.joda.money.Money.zero(CurrencyUnit.of("IDR"));
            saldo=org.joda.money.Money.zero(CurrencyUnit.of("IDR"));
            tgl=r.getDate("tgl");
            hal=r.getString("hal");
            ket=r.getString("ket");
        }r.close();
        p.close();
    }

    public Kas1() {
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

    public org.joda.money.Money getKredit() {
        return kredit;
    }

    public void setKredit(org.joda.money.Money kredit) {
        this.kredit = kredit;
    }

    public org.joda.money.Money getDebit() {
        return debit;
    }

    public void setDebit(org.joda.money.Money debit) {
        this.debit = debit;
    }

    public org.joda.money.Money getSaldo() {
        return saldo;
    }

    public void setSaldo(org.joda.money.Money saldo) {
        this.saldo = saldo;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public java.sql.Date getTgl() {
        return tgl;
    }

    public void setTgl(java.sql.Date tgl) {
        this.tgl = tgl;
    }
}
