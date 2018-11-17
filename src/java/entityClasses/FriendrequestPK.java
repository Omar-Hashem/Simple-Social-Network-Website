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
public class FriendrequestPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 320)
    @Column(name = "sender")
    private String sender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 320)
    @Column(name = "receiver")
    private String receiver;

    public FriendrequestPK() {
    }

    public FriendrequestPK(String sender, String receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sender != null ? sender.hashCode() : 0);
        hash += (receiver != null ? receiver.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FriendrequestPK)) {
            return false;
        }
        FriendrequestPK other = (FriendrequestPK) object;
        if ((this.sender == null && other.sender != null) || (this.sender != null && !this.sender.equals(other.sender))) {
            return false;
        }
        if ((this.receiver == null && other.receiver != null) || (this.receiver != null && !this.receiver.equals(other.receiver))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityPackage.FriendrequestPK[ sender=" + sender + ", receiver=" + receiver + " ]";
    }
    
}
