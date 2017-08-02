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
public class JurnalUmum {
    private String ket,ref;
    private java.sql.Date tgl;
    private org.joda.money.Money debit,kredit;

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public java.sql.Date getTgl() {
        return tgl;
    }

    public void setTgl(java.sql.Date tgl) {
        this.tgl = tgl;
    }

    public org.joda.money.Money getDebit() {
        return debit;
    }

    public void setDebit(org.joda.money.Money debit) {
        this.debit = debit;
    }

    public org.joda.money.Money getKredit() {
        return kredit;
    }

    public void setKredit(org.joda.money.Money kredit) {
        this.kredit = kredit;
    }
}