package com.kiselev.ejb.dao;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DAOLocal<T> {

    T add(T entity);

    void delete(Long id);

    T getObject(Long id);

    List<T> getObjects();

}
