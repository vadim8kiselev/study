package com.kiselev.rest;

import javax.ejb.Local;
import javax.ws.rs.core.Response;
import java.util.List;

@Local
public interface RESTServiceLocal<T> {

    T getById(Long id);

    List<T> get();

    Response post(T entity);
}
