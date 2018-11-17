package View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import entityClasses.Friendrequest;
import entityClasses.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessionBeans.UserFacade;

@ManagedBean
@SessionScoped
public class Login {
    
    @Inject
    UserFacade uf;

    public static User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    private String email;

    private String password;

    private int userId = 1;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public static final String AUTH_KEY = "app.user.name";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void save() {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Welcome " + email + " " + password));
    }

    public String onSubmit() {

        List<User> temp = new ArrayList<User>();
        temp = uf.getUserByEmail(email);
        System.out.println(email);
//        Users x =  new Users(); 
//        x.setEmail("omar@gmail.com");
//        x.setName("DssD");
//        x.setPassword("omar");
//        users.add(x) ; 

        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getPassword().equals(password)) {
                currentUser = temp.get(i);
                return login();
            }
        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("enter a valid \"username\" and \"password\""));
        return null;

    }
    public boolean isLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get(AUTH_KEY) != null;
    }

    public String login() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
                AUTH_KEY, email);
        return "home?faces-redirect=true";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .remove(AUTH_KEY);
        this.email = "";
        this.password = "";
        return "login?faces-redirect=true";
    }
    
    public String profileGen(){
        ProfileViewer.selectUser(currentUser);
        return "profile";
    }
    
    public String friendsGen(){
        ProfileViewer.selectUser(currentUser);
        return "friends";
    }

}
