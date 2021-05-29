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
    public List<FeedEntity> findByCategory(String category) {
        TypedQuery<FeedEntity> query = entityManager.createNamedQuery("Feed.findCategory",FeedEntity.class);
        return query.setParameter("category", category).getResultList();
    }
}
