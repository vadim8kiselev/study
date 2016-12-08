package com.kiselev.library.controller;

import com.kiselev.library.dao.DAO;
import com.kiselev.library.view.View;

public abstract class Controller<T> {

    protected View view;
    protected DAO<T> dao;

    public Controller(View view, DAO<T> dao) {
        this.view = view;
        this.dao = dao;
    }

    abstract public void updateView();

    abstract public void performAction();
}
