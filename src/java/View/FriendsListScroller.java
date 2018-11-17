package View;


import entityClasses.Friends;
import entityClasses.FriendsPK;
import entityClasses.User;
import java.io.*;
import javax.inject.Inject;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessionBeans.FriendsFacade;
import sessionBeans.UserFacade;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Youssef Yossry
 */
@ManagedBean
@SessionScoped
public class FriendsListScroller implements Serializable{
    @Inject
    UserFacade uf ;
    @Inject
    FriendsFacade ff;
    ArrayList<User> fl;
    User su;
    public ArrayList<User> maintainFriends(){
        su = ProfileViewer.selected;
        fl = new ArrayList<>();
        FriendsPK fpk;
        String mail;
        for(Friends fs : su.getFriendsCollection()){
            fpk= fs.getFriendsPK();
            if(!(mail = fpk.getMail1()).equals(su.getMail())){
                fl.addAll(uf.getUserByEmail(mail));
            }
            if(!(mail = fpk.getMail2()).equals(su.getMail())){
                fl.addAll(uf.getUserByEmail(mail));
            }
        }
        
        for(Friends fs : su.getFriendsCollection1()){
            fpk = fs.getFriendsPK();
            if(!(mail = fpk.getMail1()).equals(su.getMail())){
                fl.addAll(uf.getUserByEmail(mail));
            }
            if(!(mail = fpk.getMail2()).equals(su.getMail())){
                fl.addAll(uf.getUserByEmail(mail));
            }
        }
        return fl;
    }
    
    public String getGraphic(String pic, String gender)throws IOException {
        if(pic != null){
            return pic;
        }
        return "res\\"+gender+".png";
    }
    public String linkAction(User friend){
        ProfileViewer.selectUser(friend);
        return "profile?faces-redirect=true";
    }
}
