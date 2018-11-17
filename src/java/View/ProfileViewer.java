package View;


import View.Login;
import static View.Login.currentUser;
import entityClasses.Friendrequest;
import entityClasses.FriendrequestPK;
import entityClasses.Friends;
import entityClasses.FriendsPK;
import entityClasses.Post;
import entityClasses.User;
import java.awt.image.BufferedImage;
import javax.faces.bean.*;
import java.io.Serializable;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;
import sessionBeans.FriendrequestFacade;
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
public class ProfileViewer implements Serializable{

    public boolean flag = true;
    static User selected;
    static ArrayList<Post> pl = new ArrayList<Post>();
    @Inject
    FriendrequestFacade frf;
    @Inject
    UserFacade uf;

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean f) {
        flag = f;
    }

    public User getSelected() {
        return selected;
    }

    public void setSelected(User u) {
        selected = u;
    }
    public void setSelectedCurUser(){
        selected = currentUser;
    }
    public ArrayList<Post> getPl() {
        return pl;
    }

    public void setPl(ArrayList<Post> p) {
        pl = p;
    }

    public static boolean areFriends(User u1, User u2) {
        FriendsPK fpk = new FriendsPK(u1.getMail(), u2.getMail());
        FriendsPK fpk1 = new FriendsPK(u2.getMail(), u1.getMail());

        for (Friends fs : u1.getFriendsCollection()) {
            if (fpk.equals(fs.getFriendsPK()) || fpk1.equals(fs.getFriendsPK())) {
                return true;
            }
        }

        for (Friends fs : u1.getFriendsCollection1()) {
            if (fpk.equals(fs.getFriendsPK()) || fpk1.equals(fs.getFriendsPK())) {
                return true;
            }
        }
        return false;
    }

    public static boolean areFriendRequest(User u1, User u2) {
        FriendrequestPK fpk = new FriendrequestPK(u1.getMail(), u2.getMail());
        System.out.println(u1.getMail()+" "+u2.getMail());
        for (Friendrequest fs : u1.getFriendrequestCollection()) {
            System.out.println(fs.getFriendrequestPK().getSender()+" 1 "+fs.getFriendrequestPK().getReceiver());
            if (fpk.equals(fs.getFriendrequestPK())) {
                return true;
            }
        }

        for (Friendrequest fs : u1.getFriendrequestCollection1()) {
            System.out.println(fs.getFriendrequestPK().getSender()+" 2 "+fs.getFriendrequestPK().getReceiver());
            if (fpk.equals(fs.getFriendrequestPK())) {
                return true;
            }
        }
        return false;
    }

    public static void selectUser(User su) {
        selected = su;
        Collection<Post> pcoll = su.getPostCollection1();
        pl.clear();
        boolean flag = areFriends(su, Login.currentUser);
        if (su.equals(Login.currentUser) || flag) {
            pl.addAll(pcoll);
        } else {
            for (Post p : pcoll) {
                if (p.getIspublic()) {
                    pl.add(p);
                }
            }
        }
//        System.out.print(pl.get(0).getCaption());
    }

    public String getFriendsButtonName() {
        if (selected.getMail().equals(Login.currentUser.getMail())) {
            return "your";
        }
        return selected.getFname() + "'s";
    }

    public boolean addFriendCond() {
        
        System.out.println(":(  "+!selected.getMail().equals(Login.currentUser.getMail()));
        System.out.println(!areFriends(selected, Login.currentUser));
        System.out.println(!areFriendRequest(Login.currentUser,selected));
        return !selected.getMail().equals(Login.currentUser.getMail()) && !areFriends(selected, Login.currentUser) && !areFriendRequest(Login.currentUser,selected);

    }

    public String gotoFriends() {
        return "friends?faces-redirect=true";
    }

    public String addFriend() {
        User u1 = Login.currentUser;
        User u2 = selected;
        Friendrequest fr = new Friendrequest(new FriendrequestPK(u1.getMail(), u2.getMail()));
        fr.setTime(new Date());
        frf.create(fr);
        u1.getFriendrequestCollection().add(fr);
        u2.getFriendrequestCollection1().add(fr);
        uf.edit(u2);
        uf.edit(u1);
        flag = false;
        return "profile?faces-redirect=true";
    }
    public void openUpdate() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("contentHeight", "'100%'");
        options.put("contentWidth", "'100%'");
        options.put("draggable", true);
        options.put("resizable", true);
        options.put("appendToBody",true);
        RequestContext.getCurrentInstance().openDialog("updated", options, null);
    }
     
    public void onReturnUpdate(SelectEvent event) {
        //pass back to level 1
        ArrayList<String> as = (ArrayList<String>) event.getObject();
        if(as.get(0).equals("Home Town")){
            selected.setHometown(as.get(1));
        }else if(as.get(0).equals("Marital Status")){
            selected.setMaritalstatus(as.get(1));
        }else if(as.get(0).equals("About me")){
            selected.setAboutme(as.get(1));
        }else if(as.get(0).equals("Phone Number")){
            selected.setPhonenumber(as.get(1));
        }
        uf.edit(selected);
    }
    
    public boolean update(){
        return selected.getMail().equals(Login.currentUser.getMail());
    }
    
    private UploadedFile file;
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() {
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            try{
                BufferedImage image = ImageIO.read(file.getInputstream());
            }catch(Exception e){
                
            }
            
            
        }
    }

}
