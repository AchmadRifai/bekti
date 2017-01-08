/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.beans;

import java.time.Month;

/**
 *
 * @author ai
 */
@javax.faces.bean.ManagedBean
public class GajiGuru {
    private Month bulan;
    private Guru g;
    private Kas1 k;

    public void setBulan(Month bulan) {
        this.bulan = bulan;
    }

    public void setG(Guru g) {
        this.g = g;
    }

    public void setK(Kas1 k) {
        this.k = k;
    }

    public Month getBulan() {
        return bulan;
    }

    public Guru getG() {
        return g;
    }

    public Kas1 getK() {
        return k;
    }
}