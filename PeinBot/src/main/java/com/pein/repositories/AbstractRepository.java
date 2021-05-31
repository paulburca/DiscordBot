package com.pein.repositories;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractRepository<T> {
    EntityManager entityManager;
    public AbstractRepository(){
        FactoryManager factoryManager = FactoryManager.getInstance();
        factoryManager.connect("default");
        entityManager = factoryManager.create();
    }
    public abstract void create(T object);
    public abstract T findById(Long id);
    public abstract List<T> findByName(String name);
    public abstract List<T> findAll();
}
