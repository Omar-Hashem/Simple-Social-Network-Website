/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entityClasses.Friendrequest;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mohamed
 */
@Stateless
public class FriendrequestFacade extends AbstractFacade<Friendrequest> {

    @PersistenceContext(unitName = "WebProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FriendrequestFacade() {
        super(Friendrequest.class);
    }

//    @Override
//    public Friendrequest update(Friendrequest entity) {
//        flush();
//        em.getTransaction().begin();
//        entity = em.find(Friendrequest.class, entity.getFriendrequestPK());
//        em.getTransaction().commit();
//        return entity;
//    }
    
}
