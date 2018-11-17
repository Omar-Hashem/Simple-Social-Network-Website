package View;


import java.util.*;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Youssef Yossry
 */
@ManagedBean(name = "updatedVal")
public class UpdatedVal {
    private String val, att;
    public String getVal(){
        return val;
    }
    
    public void setVal(String v){
        val = v;
    }
    
    public String getAtt(){
        return att;
    }
    
    public void setAtt(String a){
        att = a;
    }
    
    public void openUpdate() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("contentHeight", "'100%'");
        options.put("contentWidth", "'100%'");
        options.put("draggable", true);
        options.put("resizable", true);
        options.put("appendToBody",true);
        RequestContext.getCurrentInstance().openDialog("updateText", options, null);
    }
     
   public void closeDialog() {
        //pass back to level 2
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        ArrayList<String> as = new ArrayList<>();
        as.add(att);
        as.add(val);
        RequestContext.getCurrentInstance().closeDialog(as);
    }
}
