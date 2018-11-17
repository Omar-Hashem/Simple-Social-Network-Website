/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClasses;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Mohamed
 */
@Embeddable
public class PostPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 320)
    @Column(name = "mail")
    private String mail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public PostPK() {
    }

    public PostPK(String mail, Date time) {
        this.mail = mail;
        this.time = time;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mail != null ? mail.hashCode() : 0);
        hash += (time != null ? time.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PostPK)) {
            return false;
        }
        PostPK other = (PostPK) object;
        if ((this.mail == null && other.mail != null) || (this.mail != null && !this.mail.equals(other.mail))) {
            return false;
        }
        if ((this.time == null && other.time != null) || (this.time != null && !this.time.equals(other.time))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityPackage.PostPK[ mail=" + mail + ", time=" + time + " ]";
    }
    
}
