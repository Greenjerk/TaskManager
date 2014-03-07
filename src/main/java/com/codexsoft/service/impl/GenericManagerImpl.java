package com.codexsoft.service.impl;

import com.codexsoft.dao.GenericDao;
import com.codexsoft.service.GenericManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
@Service
public class GenericManagerImpl<T, PK extends Serializable> implements GenericManager<T, PK> {

    protected final Log log = LogFactory.getLog(getClass());

    protected GenericDao<T, PK> dao;

    public GenericManagerImpl() {
    }

    public GenericManagerImpl(GenericDao<T, PK> genericDao) {
        this.dao = genericDao;
    }

    public List<T> getAll() {
        return dao.getAll();
    }

    public List<T> getAllDesc(String propertyName) {
        return dao.getAllDesc(propertyName);
    }

    public T get(PK id) {
        return dao.get(id);
    }

    public boolean exists(PK id) {
        return dao.exists(id);
    }

    public T save(T object) {
        return dao.save(object);
    }

    public void remove(T object) {
        dao.remove(object);
    }

    public void remove(PK id) {
        dao.remove(id);
    }

    @Override
    public List<T> search(String searchTerm) {
        if (searchTerm == null || "".equals(searchTerm.trim())) {
            return getAll();
        }

        return dao.search(searchTerm);
    }

    @Override
    public void reindex() {
        dao.reindex();
    }

    @Override
    public void reindexAll(boolean async) {
        dao.reindexAll(async);
    }

}
