/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import entityClasses.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessionBeans.UserFacade;

/**
 *
 * @author Omar
 */
@ManagedBean
@SessionScoped
public class SignUp {

    /**
     * Creates a new instance of SignUp
     */
    String fName ; 
    String lName ;
    String email ; 
    String phoneNumber; 
    String password ; 
    Date birthDate ; 
    String gender ; //male female 
    String homeTown ;
    
    @Inject 
    UserFacade uf ; 
    
    public SignUp() {
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String creatAccount (){
        
        List<User> temp = new ArrayList<User>() ; 
        temp = uf.getUserByEmail(email); 
        if(temp.size()>0){
            FacesContext.getCurrentInstance().addMessage(null,
               new FacesMessage("This User is Already Registered Before"));
        return null;
        }
        User newUser = new User (email,fName,lName , password ,gender , birthDate);
        newUser.setProfilepicture("res/"+gender+".png");
        newUser.setHometown(homeTown);
        uf.create(newUser);
        return "successfulSignUp?faces-redirect=true"; 
    }
     public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }
    
}
