/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClasses;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mohamed
 */
@Entity
@Table(name = "friends")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Friends.findAll", query = "SELECT f FROM Friends f"),
    @NamedQuery(name = "Friends.findByTime", query = "SELECT f FROM Friends f WHERE f.time = :time"),
    @NamedQuery(name = "Friends.findByMail1", query = "SELECT f FROM Friends f WHERE f.friendsPK.mail1 = :mail1"),
    @NamedQuery(name = "Friends.findByMail2", query = "SELECT f FROM Friends f WHERE f.friendsPK.mail2 = :mail2")})
public class Friends implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FriendsPK friendsPK;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @JoinColumn(name = "mail1", referencedColumnName = "mail", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "mail2", referencedColumnName = "mail", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user1;

    public Friends() {
    }

    public Friends(FriendsPK friendsPK) {
        this.friendsPK = friendsPK;
    }

    public Friends(String mail1, String mail2) {
        this.friendsPK = new FriendsPK(mail1, mail2);
    }

    public FriendsPK getFriendsPK() {
        return friendsPK;
    }

    public void setFriendsPK(FriendsPK friendsPK) {
        this.friendsPK = friendsPK;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (friendsPK != null ? friendsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Friends)) {
            return false;
        }
        Friends other = (Friends) object;
        if ((this.friendsPK == null && other.friendsPK != null) || (this.friendsPK != null && !this.friendsPK.equals(other.friendsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityPackage.Friends[ friendsPK=" + friendsPK + " ]";
    }
    
}
