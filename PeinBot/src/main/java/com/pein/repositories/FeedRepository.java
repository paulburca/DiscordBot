package com.pein.repositories;

import com.pein.Entities.FeedEntity;

import javax.persistence.TypedQuery;
import java.util.List;

public class FeedRepository extends AbstractRepository<FeedEntity> {

    @Override
    public void create(FeedEntity feed) {
        entityManager.getTransaction().begin();
        entityManager.persist(feed);
        entityManager.getTransaction().commit();
    }

    @Override
    public FeedEntity findById(Long id) {
        TypedQuery<FeedEntity> query = entityManager.createNamedQuery("Feed.findId",FeedEntity.class);
        return query.setParameter("id",id).getSingleResult();
    }

    @Override
    public List<FeedEntity> findByName(String name) {
        TypedQuery<FeedEntity> query = entityManager.createNamedQuery("Feed.findName",FeedEntity.class);
        return query.setParameter("name", name).getResultList();
    }

    @Override
    public List<FeedEntity> findAll() {
        TypedQuery<FeedEntity> query = entityManager.createNamedQuery("Feed.findAll",FeedEntity.class);
        return query.getResultList();
    }
}
