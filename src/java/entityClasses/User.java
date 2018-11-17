/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClasses;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mohamed
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByFname", query = "SELECT u FROM User u WHERE u.fname = :fname"),
    @NamedQuery(name = "User.findByLname", query = "SELECT u FROM User u WHERE u.lname = :lname"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByMail", query = "SELECT u FROM User u WHERE u.mail = :mail"),
    @NamedQuery(name = "User.findByPhonenumber", query = "SELECT u FROM User u WHERE u.phonenumber = :phonenumber"),
    @NamedQuery(name = "User.findByGender", query = "SELECT u FROM User u WHERE u.gender = :gender"),
    @NamedQuery(name = "User.findByBirthdate", query = "SELECT u FROM User u WHERE u.birthdate = :birthdate"),
    @NamedQuery(name = "User.findByProfilepicture", query = "SELECT u FROM User u WHERE u.profilepicture = :profilepicture"),
    @NamedQuery(name = "User.findByHometown", query = "SELECT u FROM User u WHERE u.hometown = :hometown"),
    @NamedQuery(name = "User.findByMaritalstatus", query = "SELECT u FROM User u WHERE u.maritalstatus = :maritalstatus"),
    @NamedQuery(name = "User.findByAboutme", query = "SELECT u FROM User u WHERE u.aboutme = :aboutme"),
    @NamedQuery(name = "User.findByFullName", query = "SELECT u FROM User u WHERE u.fname = :fname AND u.lname = :lname")
})

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "fname")
    private String fname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "lname")
    private String lname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "password")
    private String password;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 320)
    @Column(name = "mail")
    private String mail;
    @Size(max = 30)
    @Column(name = "phonenumber")
    private String phonenumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Size(max = 200)
    @Column(name = "profilepicture")
    private String profilepicture;
    @Size(max = 32)
    @Column(name = "hometown")
    private String hometown;
    @Size(max = 8)
    @Column(name = "maritalstatus")
    private String maritalstatus;
    @Size(max = 256)
    @Column(name = "aboutme")
    private String aboutme;
    @JoinTable(name = "liked", joinColumns = {
        @JoinColumn(name = "likermail", referencedColumnName = "mail")}, inverseJoinColumns = {
        @JoinColumn(name = "mail", referencedColumnName = "mail"),
        @JoinColumn(name = "time", referencedColumnName = "time")})
    @ManyToMany
    private Collection<Post> postCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Friendrequest> friendrequestCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user1")
    private Collection<Friendrequest> friendrequestCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Post> postCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Friends> friendsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user1")
    private Collection<Friends> friendsCollection1;

    public User() {
    }

    public User(String mail) {
        this.mail = mail;
    }

    public User(String mail, String fname, String lname, String password, String gender, Date birthdate) {
        this.mail = mail;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public String getAboutme() {
        return aboutme;
    }

    public void setAboutme(String aboutme) {
        this.aboutme = aboutme;
    }

    @XmlTransient
    public Collection<Post> getPostCollection() {
        return postCollection;
    }

    public void setPostCollection(Collection<Post> postCollection) {
        this.postCollection = postCollection;
    }

    @XmlTransient
    public Collection<Friendrequest> getFriendrequestCollection() {
        return friendrequestCollection;
    }

    public void setFriendrequestCollection(Collection<Friendrequest> friendrequestCollection) {
        this.friendrequestCollection = friendrequestCollection;
    }

    @XmlTransient
    public Collection<Friendrequest> getFriendrequestCollection1() {
        return friendrequestCollection1;
    }

    public void setFriendrequestCollection1(Collection<Friendrequest> friendrequestCollection1) {
        this.friendrequestCollection1 = friendrequestCollection1;
    }

    @XmlTransient
    public Collection<Post> getPostCollection1() {
        return postCollection1;
    }

    public void setPostCollection1(Collection<Post> postCollection1) {
        this.postCollection1 = postCollection1;
    }

    @XmlTransient
    public Collection<Friends> getFriendsCollection() {
        return friendsCollection;
    }

    public void setFriendsCollection(Collection<Friends> friendsCollection) {
        this.friendsCollection = friendsCollection;
    }

    @XmlTransient
    public Collection<Friends> getFriendsCollection1() {
        return friendsCollection1;
    }

    public void setFriendsCollection1(Collection<Friends> friendsCollection1) {
        this.friendsCollection1 = friendsCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mail != null ? mail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.mail == null && other.mail != null) || (this.mail != null && !this.mail.equals(other.mail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityPackage.User[ mail=" + mail + " ]";
    }
    
}
