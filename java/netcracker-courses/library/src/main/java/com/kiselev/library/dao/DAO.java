package com.kiselev.library.dao;

import com.kiselev.library.dao.exceptions.DAOException;
import com.kiselev.library.model.Model;

import java.util.List;

public abstract class DAO<T> {

    protected Model<T> model;

    public DAO(Model<T> model) {
        this.model = model;
    }

    public abstract void addObject(T entity) throws DAOException;

    public abstract void deleteObject(int id) throws DAOException;

    public abstract T getObject(int id) throws DAOException;

    public abstract List<T> getObjects() throws DAOException;
}
