package View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entityClasses.*;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessionBeans.PostFacade;
import sessionBeans.UserFacade;


/**
 *
 * @author nabil
 */
@ManagedBean
@SessionScoped
public class HomeScroller implements Serializable {
    
    @Inject
    PostFacade pf;
    
    @Inject
    UserFacade uf;

    private List<Home> posts;
    
    private boolean isPublic= true;
    private String caption;
    private File image;
    

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
    
    public boolean isIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
    
    public void submitPost(){
        Post newPost= new Post(Login.currentUser.getMail(), new Date());
        newPost.setCaption(caption);
        newPost.setIspublic(isPublic);
        
        pf.create(newPost);
    }
    
    public List<Home> getPosts() {
        return posts;
    }

    public void setPosts(List<Home> posts) {
        this.posts = posts;
    }
    
    @PostConstruct 
    public void init() {
        posts = new ArrayList<Home>();
        Collection<Friends> friends= Login.currentUser.getFriendsCollection();
        for(Friends f : friends){
            User temp_user= uf.getUserByEmail(f.getFriendsPK().getMail1()).get(0);
            if(temp_user.getMail().equals(Login.currentUser.getMail())){
                temp_user= uf.getUserByEmail(f.getFriendsPK().getMail2()).get(0);
            }
            Collection <Post> temp_posts= temp_user.getPostCollection1();
            for(Post p : temp_posts){
                posts.add(new Home(temp_user, p, (List<User>) p.getUserCollection()));
            }
        }
        Collection<Post> curr_user_posts= Login.currentUser.getPostCollection1();
        for(Post p : curr_user_posts){
            posts.add(new Home(Login.currentUser, p, (List<User>) p.getUserCollection()));
        }
    }
    
     public void upload() {
        if(image != null) {
            System.out.println(image.getPath());
            FacesMessage message = new FacesMessage("Succesful", image.getName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
