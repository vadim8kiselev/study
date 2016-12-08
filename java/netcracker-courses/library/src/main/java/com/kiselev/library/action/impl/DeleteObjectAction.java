package com.kiselev.library.action.impl;

import com.kiselev.library.action.Action;
import com.kiselev.library.dao.DAO;
import com.kiselev.library.dao.exceptions.DAOException;
import com.kiselev.library.entity.impl.Book;
import com.kiselev.library.view.View;

public class DeleteObjectAction extends Action<Book> {

    public String execute(View view, DAO<Book> dao) {

        view.update("Enter the id from the list:");
        Integer id = Integer.parseInt(view.input());
        try {
            dao.deleteObject(id);
            return "Deleted";

        } catch (DAOException exception) {
            return exception.getMessage();
        }
    }
}
