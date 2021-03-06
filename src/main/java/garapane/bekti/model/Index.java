/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.model;

import garapane.bekti.beans.GajiGuru;
import garapane.bekti.beans.Guru;
import garapane.bekti.beans.Ulan1;
import garapane.bekti.beans.Ulan2;
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
public class Index {
    private List<GajiGuru>gajine;
    private List<Ulan1>bln;
    private List<Ulan2>bln2;
    private List<garapane.bekti.beans.JurnalUmum>ju;

    public List<GajiGuru> getGajine() {
        return gajine;
    }

    @javax.annotation.PostConstruct
    public void init(){
        bln=new java.util.LinkedList<Ulan1>();
        bln2=new java.util.LinkedList<Ulan2>();
        gajine=new java.util.LinkedList<GajiGuru>();
        ju=new java.util.LinkedList<>();
        dhewe();
    }

    public List<Ulan2> getBln2() {
        return bln2;
    }

    public List<Ulan1> getBln() {
        return bln;
    }

    private void wulane() throws SQLException {
        garapane.bekti.util.Db d=new garapane.bekti.util.Db();
        java.sql.ResultSet r=d.getRS("select max(tgl)as pol,min(tgl) as tek from akutansi");
        if(r.next())prosesBulan(r.getDate("pol"),r.getDate("tek"));
        r.close();
        d.close();
    }

    private void prosesBulan(Date pol, Date buri) throws SQLException {
        if(pol==null||buri==null)return;
        java.time.LocalDate l1=buri.toLocalDate(),l2=pol.toLocalDate(),i=java.time.LocalDate.of(l1.getYear(), l1.getMonth(), 1);
        while(i.equals(l2)||i.isBefore(l2)){
            java.time.Month m=i.getMonth();
            Ulan1 u=new Ulan1(Date.valueOf(i),Date.valueOf(i.plusMonths(1).minusDays(1)),""+m+"-"+i.getYear());
            Ulan2 u2=new Ulan2(""+m+"-"+i.getYear(),Date.valueOf(i),Date.valueOf(i.plusMonths(1).minusDays(1)));
            if(0<u.getL().size())bln.add(u);
            if(0<u2.getL().size())bln2.add(u2);
            i=i.plusMonths(1);
        }walekBln();
    }

    private void walekBln() {
        List<Ulan1>l=new java.util.LinkedList<Ulan1>();
        List<Ulan2>l2=new java.util.LinkedList<Ulan2>();
        for(int x=bln.size()-1;x>=0;x--)l.add(bln.get(x));
        bln=l;
        for(int x=bln2.size()-1;x>=0;x--)l2.add(bln2.get(x));
        bln2=l2;
    }

    private void gajine() throws SQLException {
        garapane.bekti.util.Db d=new garapane.bekti.util.Db();
        java.sql.ResultSet r=d.getRS("select distinct t from gaji order by t desc");
        while(r.next())prosesTahun(r.getString("t"));
        r.close();
        d.close();
    }

    private void prosesTahun(String t) throws SQLException {
        for(java.time.Month m:java.time.Month.values())for(Guru g:guruList())try{
            GajiGuru gg=new GajiGuru(m,g,t);
            if(null!=gg.getK())gajine.add(gg);
        }catch(SQLException e){
            garapane.bekti.util.Db.hindar(e, FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        }
    }

    private List<Guru> guruList() throws SQLException {
        List<Guru>l=new java.util.LinkedList<Guru>();
        garapane.bekti.util.Db d=new garapane.bekti.util.Db();
        java.sql.ResultSet r=d.getRS("select kode from guru");
        while(r.next())l.add(new Guru(r.getString("kode"),d));
        r.close();
        d.close();
        return l;
    }

    public List<garapane.bekti.beans.JurnalUmum> getJu() {
        return ju;
    }

    private void dhewe(){
        HttpServletRequest req=(HttpServletRequest) javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try{
            jurnalUmum();
            gajine();
            wulane();
        }catch(SQLException e){garapane.bekti.util.Db.hindar(e, req.getRemoteAddr());}
    }

    private void jurnalUmum()throws SQLException{
        garapane.bekti.util.Db d=new garapane.bekti.util.Db();
        java.sql.ResultSet r=d.getRS("select tgl,ket,hal,duwik,tipe from akutansi order by tgl");
        while(r.next()){
            garapane.bekti.beans.JurnalUmum j=new garapane.bekti.beans.JurnalUmum();
            if("kredit"==r.getString("tipe")){
                j.setDebit(org.joda.money.Money.zero(CurrencyUnit.of("IDR")));
                j.setKredit(org.joda.money.Money.of(CurrencyUnit.of("IDR"), r.getBigDecimal("duwik")));
            }else{
                j.setDebit(org.joda.money.Money.of(CurrencyUnit.of("IDR"), r.getBigDecimal("duwik")));
                j.setKredit(org.joda.money.Money.zero(CurrencyUnit.of("IDR")));
            }j.setKet(r.getString("ket"));
            j.setTgl(r.getDate("tgl"));
            j.setRef(r.getString("hal"));
            ju.add(j);
        }r.close();
        d.close();
    }

    public String reloadAdmin() {
        return User.ADMIN;
    }

    public String reloadIndex() {
        return User.INDEX;
    }
}