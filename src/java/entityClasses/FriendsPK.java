/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityClasses;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Mohamed
 */
@Embeddable
public class FriendsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 320)
    @Column(name = "mail1")
    private String mail1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 320)
    @Column(name = "mail2")
    private String mail2;

    public FriendsPK() {
    }

    public FriendsPK(String mail1, String mail2) {
        this.mail1 = mail1;
        this.mail2 = mail2;
    }

    public String getMail1() {
        return mail1;
    }

    public void setMail1(String mail1) {
        this.mail1 = mail1;
    }

    public String getMail2() {
        return mail2;
    }

    public void setMail2(String mail2) {
        this.mail2 = mail2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mail1 != null ? mail1.hashCode() : 0);
        hash += (mail2 != null ? mail2.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FriendsPK)) {
            return false;
        }
        FriendsPK other = (FriendsPK) object;
        if ((this.mail1 == null && other.mail1 != null) || (this.mail1 != null && !this.mail1.equals(other.mail1))) {
            return false;
        }
        if ((this.mail2 == null && other.mail2 != null) || (this.mail2 != null && !this.mail2.equals(other.mail2))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityPackage.FriendsPK[ mail1=" + mail1 + ", mail2=" + mail2 + " ]";
    }
    
}
