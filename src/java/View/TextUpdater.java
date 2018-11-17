package View;


import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Youssef Yossry
 */
@ManagedBean(name = "textUpdater")
public class TextUpdater {
    private String val, ret;
 
    public String getVal() {
        return val;
    }
 
    public void setVal(String val) {
        this.val = val;
    }
    
     public String getRet() {
        return ret;
    }
 
    public void setRet(String val) {
        this.ret = ret;
    }
 
    public void closeDialog() {
        //pass back to level 2
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        RequestContext.getCurrentInstance().closeDialog(val);
    }
}
