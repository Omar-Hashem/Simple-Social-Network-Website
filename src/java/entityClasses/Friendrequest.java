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
@Table(name = "friendrequest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Friendrequest.findAll", query = "SELECT f FROM Friendrequest f"),
    @NamedQuery(name = "Friendrequest.findBySender", query = "SELECT f FROM Friendrequest f WHERE f.friendrequestPK.sender = :sender"),
    @NamedQuery(name = "Friendrequest.findByReceiver", query = "SELECT f FROM Friendrequest f WHERE f.friendrequestPK.receiver = :receiver"),
    @NamedQuery(name = "Friendrequest.findByTime", query = "SELECT f FROM Friendrequest f WHERE f.time = :time")})
public class Friendrequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FriendrequestPK friendrequestPK;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @JoinColumn(name = "sender", referencedColumnName = "mail", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "receiver", referencedColumnName = "mail", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user1;

    public Friendrequest() {
    }

    public Friendrequest(FriendrequestPK friendrequestPK) {
        this.friendrequestPK = friendrequestPK;
    }

    public Friendrequest(String sender, String receiver) {
        this.friendrequestPK = new FriendrequestPK(sender, receiver);
    }

    public FriendrequestPK getFriendrequestPK() {
        return friendrequestPK;
    }

    public void setFriendrequestPK(FriendrequestPK friendrequestPK) {
        this.friendrequestPK = friendrequestPK;
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
        hash += (friendrequestPK != null ? friendrequestPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Friendrequest)) {
            return false;
        }
        Friendrequest other = (Friendrequest) object;
        if ((this.friendrequestPK == null && other.friendrequestPK != null) || (this.friendrequestPK != null && !this.friendrequestPK.equals(other.friendrequestPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityPackage.Friendrequest[ friendrequestPK=" + friendrequestPK + " ]";
    }
    
}
