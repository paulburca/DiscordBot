package com.pein.repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryManager {
    public static FactoryManager instance;
    private EntityManagerFactory factory;
    private FactoryManager(){}
    public static FactoryManager getInstance(){
        if(instance == null){
            instance = new FactoryManager();
        }
        return instance;
    }
    public void connect(String unit){
        factory = Persistence.createEntityManagerFactory(unit);
    }
    public EntityManager create(){
        return factory.createEntityManager();
    }

    public  void close(){
        factory.close();
    }

}
