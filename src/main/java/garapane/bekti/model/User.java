/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.model;

import javax.faces.context.FacesContext;

/**
 *
 * @author ai
 */
@javax.faces.bean.ManagedBean
@javax.faces.bean.SessionScoped
public class User {
    public static String INDEX="index.xhtml?faces-redirect=true";
    public static String LOGIN="login.xhtml?faces-redirect=true";
    public static String ADMIN="admin.xhtml?faces-redirect=true";
    private String user,pass;
    private java.io.File f;

    @javax.annotation.PostConstruct
    public void init(){
        f=new java.io.File("masuk");
    }

    public String sedangMasuk(){
        if(f.exists()){
            if(logincok())return User.ADMIN;
            else return User.INDEX;
        }return null;
    }

    public String sudahMasuk(){
        if(!f.exists())return User.INDEX;
        return null;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String login(){
        if(logincok()){
            f.mkdir();
            return User.ADMIN;
        }return User.LOGIN;
    }

    public String logout(){
        f.delete();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return User.INDEX;
    }

    private boolean logincok() {
        return "admin".equals(user)&&"bekti".equals(pass);
    }
}