package com.pein.repositories;

import com.pein.entities.FeedcategoryEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class FeedCategoryRepository {
    EntityManager entityManager;
    public FeedCategoryRepository(){
        FactoryManager factoryManager = FactoryManager.getInstance();
        factoryManager.connect("default");
        entityManager = factoryManager.create();
    }
    public void create(FeedcategoryEntity relation) {
        entityManager.getTransaction().begin();
        entityManager.persist(relation);
        entityManager.getTransaction().commit();
    }
    public List<FeedcategoryEntity> findById2(Long id2){
        TypedQuery<FeedcategoryEntity> query = entityManager.createNamedQuery("FeedCategory.findId2",FeedcategoryEntity.class);
        return query.setParameter("id",id2).getResultList();
    }
}
