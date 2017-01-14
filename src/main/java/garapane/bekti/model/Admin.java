/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garapane.bekti.model;

import garapane.bekti.beans.Guru;
import java.sql.SQLException;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;

/**
 *
 * @author ai
 */
@javax.faces.bean.ManagedBean
public class Admin {
    private List<Guru>gurune;

    public List<Guru> getGurune() {
        return gurune;
    }

    @javax.annotation.PostConstruct
    @SuppressWarnings("Convert2Diamond")
    public void init(){
        HttpServletRequest req=(HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            gurune=garapane.bekti.util.Util.kabehGuru();
        } catch (SQLException ex) {
            gurune=new java.util.LinkedList<Guru>();
            garapane.bekti.util.Db.hindar(ex, req.getRemoteAddr());
        }
    }

    public void postGuru(Object doc){
        HSSFWorkbook berkas=(HSSFWorkbook) doc;
        HSSFSheet s1=berkas.getSheetAt(0);
        HSSFRow r1=s1.getRow(0);
        HSSFCellStyle style=berkas.createCellStyle();
        style.setFillForegroundColor(HSSFColor.GREEN.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        for(int i=0;i<r1.getPhysicalNumberOfCells();i++){
            HSSFCell c=r1.getCell(i);
            c.setCellStyle(style);
        }
    }
}