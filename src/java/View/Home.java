package View;


import static View.Login.currentUser;
import entityClasses.Post;
import entityClasses.User;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import sessionBeans.UserFacade;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nabil
 */

public class Home implements Serializable {
    
    private Post post;
    private User poster;
    private List<User> likers;
    private boolean likeValue;
    
    public Collection<User> getLikers() {
        return likers;
    }

    public void setLikers(List<User> likers) {
        this.likers = likers;
    }
            
    @Inject
    UserFacade uf;

    @PostConstruct 
    public void init() {
        
    }
    
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getPoster() {
        return poster;
    }
    
    public String likeString(){
        if(likeValue) return "Liked";
        else return "Like";
    }
    
    
    public void setPoster(User poster) {
        this.poster = poster;
    }

    public Home(User poster, Post post, List<User> likers) {
        this.post = post;
        this.poster = poster;
        this.likers= likers;
    }
    
    public String onUser(){
        ProfileViewer.selectUser(poster);
        return "profile";
    }
    
    public boolean isLiked(Home h){
        for(User u : h.getLikers()){
            System.out.println(u.getMail());
            if(u.getMail().equals(Login.currentUser.getMail())){
                likeValue= true;
                return true;
            }
        }
        System.out.println("HERE");
        likeValue= false;
        return false;
    }
    
    public void onLike(Home h){
        Collection<Post> temp= currentUser.getPostCollection();
        temp.add(h.getPost());
        System.out.println(temp.size());
        currentUser.setPostCollection(temp);
        uf.edit(currentUser);
    }
	
	 public void onLike(Post h){
        Collection<Post> temp= Login.currentUser.getPostCollection();
        temp.add(h);
        System.out.println(temp.size());
        Login.currentUser.setPostCollection(temp);
        uf.update(Login.currentUser);
    }
	
	public boolean isLiked(Post p){
        for(User u : p.getUserCollection()){
            if(u.getMail().equals(Login.currentUser.getMail())){
                likeValue= true;
                return true;
            }
        }
        likeValue= false;
        return false;
    }
	
	
}
