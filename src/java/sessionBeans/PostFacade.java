/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entityClasses.Post;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mohamed
 */
@Stateless
public class PostFacade extends AbstractFacade<Post> {

    @PersistenceContext(unitName = "WebProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostFacade() {
        super(Post.class);
    }

//    @Override
//    public Post update(Post entity) {
//        flush();
//        em.getTransaction().begin();
//        entity = em.find(Post.class, entity.getPostPK());
//        em.getTransaction().commit();
//        return entity;
//    }
    
}
