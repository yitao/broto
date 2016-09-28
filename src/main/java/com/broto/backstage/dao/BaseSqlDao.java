package com.broto.backstage.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by yitao on 2016/5/19.
 */
public interface BaseSqlDao<T, PK extends Serializable> {

    void insert(T entity);

    void delete(PK id, boolean isHardDelete);

    void hardDelete(PK id);

    void update(T entity);

    void save(T entity);

    T findById(PK id);

    List<T> findAllByMap(Map<String, Object> query);

    long countAllByMap(Map<String,Object> query);


}
