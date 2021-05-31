package com.pein.repositories;

import com.pein.Entities.CategoryEntity;
import com.pein.Entities.FeedEntity;

import javax.persistence.TypedQuery;
import java.util.List;

public class CategoryRepository extends AbstractRepository<CategoryEntity>{
    @Override
    public void create(CategoryEntity category) {
        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();
    }

    @Override
    public CategoryEntity findById(Long id) {
        TypedQuery<CategoryEntity> query = entityManager.createNamedQuery("Category.findId",CategoryEntity.class);
        return query.setParameter("id",id).getSingleResult();
    }

    @Override
    public List<CategoryEntity> findByName(String name) {
        TypedQuery<CategoryEntity> query = entityManager.createNamedQuery("Category.findName",CategoryEntity.class);
        return query.setParameter("name", name).getResultList();
    }

    @Override
    public List<CategoryEntity> findAll() {
        TypedQuery<CategoryEntity> query = entityManager.createNamedQuery("Category.findAll",CategoryEntity.class);
        return query.getResultList();
    }
}
