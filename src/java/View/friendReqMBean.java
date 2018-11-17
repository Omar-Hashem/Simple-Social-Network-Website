/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import static View.Login.currentUser;
import entityClasses.Friendrequest;
import entityClasses.Friends;
import entityClasses.User;
import java.util.ArrayList;
import java.util.Collection;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import sessionBeans.FriendrequestFacade;
import sessionBeans.FriendsFacade;
import sessionBeans.UserFacade;

/**
 *
 * @author Mohamed
 */
@ManagedBean
@Dependent
public class friendReqMBean {
    int indx = 0;

    /**
     * Creates a new instance of friendReqMBean
     */
    @Inject
    FriendrequestFacade frf;
    
    @Inject
    FriendsFacade ff;
    
    @Inject
    UserFacade uf;
    
    
    public friendReqMBean() {
        
    }
    public String setSelectedUser(User user){
        ProfileViewer.selectUser (user);
        return "profile";        
    }
    public int getCount(){
        return currentUser.getFriendrequestCollection().size();
    }
    
    public ArrayList<Friendrequest> getFrReqs()
    {
        return new ArrayList<>(currentUser.getFriendrequestCollection1());
    }
    public ArrayList<Friendrequest> getSentFrReqs()
    {
        return new ArrayList<>(currentUser.getFriendrequestCollection());
    }
    
    private <T> int getInd(T o, ArrayList<T> ar){
        for(int i = 0 ; i < ar.size() ; i++){
            if(o.equals(ar.get(i))){
                return i;
            }
        }
        return 0;
    }
    public int getSentIndx(Friendrequest fr){
        ArrayList<Friendrequest> ar =  new ArrayList<>(currentUser.getFriendrequestCollection());
        return getInd(fr,ar)+1;
    }
    public int getRecIndx(Friendrequest fr){
        ArrayList<Friendrequest> ar =  new ArrayList<>(currentUser.getFriendrequestCollection1());
        return getInd(fr,ar)+1;
    }
    public void clearInd(){
        indx = 0;
    }
    public String acceptRequest(Friendrequest fr)
    {
        Friends frnd = new Friends(fr.getUser().getMail(),fr.getUser1().getMail());
        ff.create(frnd);
        
        frf.remove(fr);
        Collection<Friends> frLst = currentUser.getFriendsCollection1();
        frLst.add(frnd);
        currentUser.setFriendsCollection1(frLst);
        
        Collection<Friendrequest> frRLst = currentUser.getFriendrequestCollection1();
        frRLst.remove(fr);
        currentUser.setFriendrequestCollection1(frRLst);
        
        uf.edit(currentUser);
        return "friendRequests?faces-redirect=true";
    }
    public String rejectRequest(Friendrequest fr)
    {
        frf.remove(fr);
        Collection<Friendrequest> frRLst = currentUser.getFriendrequestCollection1();
        frRLst.remove(fr);
        currentUser.setFriendrequestCollection1(frRLst);
        uf.edit(currentUser);
        return "friendRequests?faces-redirect=true";
    }
    
    public String cancelRequest(Friendrequest fr)
    {
        frf.remove(fr);
        Collection<Friendrequest> frRLst = currentUser.getFriendrequestCollection();
        frRLst.remove(fr);
        currentUser.setFriendrequestCollection(frRLst);
        uf.edit(currentUser);
        return "friendRequests?faces-redirect=true";
    }
    
}
