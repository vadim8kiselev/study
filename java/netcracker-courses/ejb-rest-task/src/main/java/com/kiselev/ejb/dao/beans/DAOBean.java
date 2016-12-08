package com.kiselev.ejb.dao.beans;

import com.kiselev.ejb.dao.DAOLocal;
import com.kiselev.ejb.model.entities.EntityBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.ws.rs.Path;
import java.util.List;

@Stateless
public class DAOBean implements DAOLocal<EntityBean> {

    private EntityManager manager = Persistence
            .createEntityManagerFactory("users")
            .createEntityManager();

    @Override
    public EntityBean add(EntityBean entity) {
        try {
            manager.getTransaction().begin();
            manager.merge(entity);
            manager.getTransaction().commit();
            return entity;
        } catch (PersistenceException error) {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        manager.getTransaction().begin();
        manager.createQuery("" +
                "DELETE FROM EntityBean user" +
                " WHERE user.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        manager.getTransaction().commit();
    }

    @Override
    public EntityBean getObject(Long id) {
        try {
            return (EntityBean) manager
                    .createQuery("" +
                            "SELECT user " +
                            "FROM EntityBean user " +
                            "WHERE id = :id")
                    .setParameter("id", id)
                    .getSingleResult();

        } catch (NoResultException error) {
            return null;
        }
    }

    @Override
    public List<EntityBean> getObjects() {
        return manager.createQuery("" +
                "SELECT user " +
                "FROM EntityBean user", EntityBean.class).getResultList();
    }
}
