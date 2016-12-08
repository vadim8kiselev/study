package com.kiselev.library.action.impl;

import com.kiselev.library.action.Action;
import com.kiselev.library.dao.DAO;
import com.kiselev.library.dao.exceptions.DAOException;
import com.kiselev.library.entity.impl.Book;
import com.kiselev.library.view.View;

public class AddObjectAction extends Action<Book> {

    public String execute(View view, DAO<Book> dao) {

        view.update("Enter the name:");
        String name = view.input();

        view.update("Enter the name of author:");
        String author = view.input();

        Book entity = new Book();

        entity.setName(name);
        entity.setAuthor(author);

        try {
            dao.addObject(entity);
            return "Added";

        } catch (DAOException exception) {
            return exception.getMessage();
        }
    }
}
