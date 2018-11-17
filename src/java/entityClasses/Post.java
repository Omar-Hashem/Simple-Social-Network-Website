/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClasses;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mohamed
 */
@Entity
@Table(name = "post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p"),
    @NamedQuery(name = "Post.findByMail", query = "SELECT p FROM Post p WHERE p.postPK.mail = :mail"),
    @NamedQuery(name = "Post.findByTime", query = "SELECT p FROM Post p WHERE p.postPK.time = :time"),
    @NamedQuery(name = "Post.findByCaption", query = "SELECT p FROM Post p WHERE p.caption = :caption"),
    @NamedQuery(name = "Post.findByIspublic", query = "SELECT p FROM Post p WHERE p.ispublic = :ispublic")})
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PostPK postPK;
    @Size(max = 256)
    @Column(name = "caption")
    private String caption;
    @Column(name = "ispublic")
    private Boolean ispublic;
    @Lob
    @Column(name = "image")
    private byte[] image;
    @ManyToMany(mappedBy = "postCollection")
    private Collection<User> userCollection;
    @JoinColumn(name = "mail", referencedColumnName = "mail", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Post() {
    }

    public Post(PostPK postPK) {
        this.postPK = postPK;
    }

    public Post(String mail, Date time) {
        this.postPK = new PostPK(mail, time);
    }

    public PostPK getPostPK() {
        return postPK;
    }

    public void setPostPK(PostPK postPK) {
        this.postPK = postPK;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Boolean getIspublic() {
        return ispublic;
    }

    public void setIspublic(Boolean ispublic) {
        this.ispublic = ispublic;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postPK != null ? postPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.postPK == null && other.postPK != null) || (this.postPK != null && !this.postPK.equals(other.postPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityPackage.Post[ postPK=" + postPK + " ]";
    }
    
}
