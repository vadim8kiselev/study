package com.kiselev.library.dao.impl;

import com.kiselev.library.dao.DAO;
import com.kiselev.library.dao.exceptions.DAOException;
import com.kiselev.library.dao.exceptions.impl.MarshallException;
import com.kiselev.library.dao.exceptions.impl.UnmarshallException;
import com.kiselev.library.entity.Entity;
import com.kiselev.library.model.Model;

import javax.xml.bind.JAXBException;
import java.util.List;

public abstract class LibraryDAO<T extends Entity> extends DAO<T> {

    public LibraryDAO(Model<T> model) throws JAXBException {
        super(model);
    }

    public void addObject(T entity) throws DAOException {

        List<T> list = model.getList();

        int id = 0;
        for (T o : list) {
            id = Math.max(id, o.getId());
        }
        entity.setId(id + 1);

        list.add(entity);

        model.setList(list);

        try {
            marshal();
        } catch (JAXBException error) {
            throw new MarshallException("Incorrect db connection");
        }
    }

    public void deleteObject(int id) throws DAOException {
        List<T> list = model.getList();
        boolean deleted = false;

        for (int index = 0; index < list.size(); index++) {
            if (list.get(index).getId() == id) {
                list.remove(index);
                deleted = true;
                break;
            }
        }
        if (deleted) {
            model.setList(list);

            try {
                marshal();
            } catch (JAXBException error) {
                throw new MarshallException("Incorrect db connection");
            }
        } else {
            throw new MarshallException("This object already deleted");
        }
    }

    public T getObject(int id) throws DAOException {
        try {
            unmarshal();
            List<T> list = model.getList();

            for (T entity : list) {
                if (entity.getId() == id) {
                    return entity;
                }
            }

            return null;
        } catch (JAXBException e) {
            throw new MarshallException("Incorrect connection or source");
        }
    }

    public List<T> getObjects() throws DAOException {
        try {
            unmarshal();
            return model.getList();
        } catch (JAXBException e) {
            throw new UnmarshallException("Incorrect connection or source");
        }
    }

    protected abstract void marshal() throws JAXBException;

    protected abstract void unmarshal() throws JAXBException;
}
