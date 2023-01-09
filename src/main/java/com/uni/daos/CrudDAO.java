package com.uni.daos;

import java.util.List;

public interface CrudDAO<Entity> {

    //Create
    Entity save(Entity entity);

    //Read
    List<Entity> findAll();

    //Update
    void update(Entity entity);

    //Delete



}
