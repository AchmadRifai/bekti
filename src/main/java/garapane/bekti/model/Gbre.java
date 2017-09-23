/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.model;

/**
 *
 * @author ai
 */
@javax.faces.bean.ManagedBean
public class Gbre {
    private java.util.List<String>g;

    @javax.annotation.PostConstruct
    public void init(){
        g=new java.util.LinkedList<>();
        g.add("1.jpg");
        g.add("2.jpg");
        g.add("3.jpg");
    }

    public java.util.List<String> getG() {
        return g;
    }
}