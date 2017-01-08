/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.beans;

/**
 *
 * @author ai
 */
@javax.faces.bean.ManagedBean
public class Kas12 {
    private String ket;
    private org.joda.money.Money kredit,debit,saldo;
    private java.sql.Date tgl;

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

    public java.sql.Date getTgl() {
        return tgl;
    }

    public void setTgl(java.sql.Date tgl) {
        this.tgl = tgl;
    }
}