/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entityClasses.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Omar
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "WebProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public List<User> getUserByEmail (String email){
        
        List<User> temp =new ArrayList<User> () ; 
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mail",email);
       
        try {
            temp = executeNamedQuery("User.findByMail", param) ;
        } catch (Exception ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp; 
    }
    public List<User> getUserByFullName (String firstName, String lastName){
        
        List<User> temp =new ArrayList<User> () ; 
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("fname",firstName);
        param.put("lname", lastName); 
       
        try {
            temp = executeNamedQuery("User.findByFullName", param) ;
        } catch (Exception ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp; 
    }
    public List<User> getUserByHomeTown (String homeTown){
        
        List<User> temp =new ArrayList<User> () ; 
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("hometown",homeTown);
       
        try {
            temp = executeNamedQuery("User.findByHometown", param) ;
        } catch (Exception ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp; 
    }
    public List<User> getUserByPhoneNumber(String phoneNumber){
        
        List<User> temp =new ArrayList<User> () ; 
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("phonenumber",phoneNumber);
       
        try {
            temp = executeNamedQuery("User.findByPhonenumber", param) ;
        } catch (Exception ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp; 
    }

}
