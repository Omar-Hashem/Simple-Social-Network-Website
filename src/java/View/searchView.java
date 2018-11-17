/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import entityClasses.Friends;
import entityClasses.Post;
import entityClasses.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import sessionBeans.PostFacade;
import sessionBeans.UserFacade;

/**
 *
 * @author Ramadan
 */
@ManagedBean
@SessionScoped
public class searchView {

    /**
     * Creates a new instance of searchView
     */
    String searchValue;
    String searchOption;

    public static User  selectedUser ; 
    List<User> userResult;
    List<Post> postResult;

    @Inject
    UserFacade uf;

    @Inject
    PostFacade pf;

    @PostConstruct
    public void init() {
        userResult = new ArrayList<User>();
        postResult = new ArrayList<Post>();

    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getSearchOption() {
        return searchOption;
    }

    public void setSearchOption(String searchOption) {
        this.searchOption = searchOption;
    }

    public String loadData() {
        if (searchOption.equals("email")) {
            userResult = uf.getUserByEmail(searchValue);
            return "showUserResult";
        } else if (searchOption.equals("phoneNumber")) {
            userResult = uf.getUserByPhoneNumber(searchValue);
            return "showUserResult";
        } else if (searchOption.equals("name")) {
            String[] arr = searchValue.split(" ");
            if (arr.length == 2) {
                userResult = uf.getUserByFullName(arr[0], arr[1]);
                return "showUserResult";
            } else {
                return null;
            }

        } else if (searchOption.equals("homeTown")) {
//            searchValue = searchValue.toLowerCase() ;
            userResult = uf.getUserByHomeTown(searchValue);
            return "showUserResult";

        } else if (searchOption.equals("postCaption")) {

//            postResult = new ArrayList<Home>();
//            Collection<Friends> friends = Login.currentUser.getFriendsCollection();
//            System.out.println("Friends size: " + friends.size());
//            for (Friends f : friends) {
//                User temp_user = uf.getUserByEmail(f.getFriendsPK().getMail1()).get(0);
//                if (temp_user.getMail().equals(Login.currentUser.getMail())) {
//                    temp_user = uf.getUserByEmail(f.getFriendsPK().getMail2()).get(0);
//                }
//                Collection<Post> temp_posts = temp_user.getPostCollection1();
//                for (Post p : temp_posts) {
//                    postResult.add(new Home(temp_user, p));
//                }
//            }
//            Collection<Post> curr_user_posts = Login.currentUser.getPostCollection1();
//            for (Post p : curr_user_posts) {
//                postResult.add(new Home(Login.currentUser, p));
//            }
            return "showPostResult";
        }

        return null;
    }

    public List<User> getUserResult() {
        return userResult;
    }

    public void setUserResult(List<User> userResult) {
        this.userResult = userResult;
    }

    public List<Post> getPostResult() {
        return postResult;
    }

    public void setPostResult(List<Post> postResult) {
        this.postResult = postResult;
    }
    
    public String setSelectedUser(User selected){
            selectedUser = selected ; 
            ProfileViewer.selectUser (selected);
            return "profile" ;  
    }

}
