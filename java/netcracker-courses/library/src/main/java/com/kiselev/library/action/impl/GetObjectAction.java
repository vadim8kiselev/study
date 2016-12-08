package com.kiselev.library.action.impl;

import com.kiselev.library.action.Action;
import com.kiselev.library.dao.DAO;
import com.kiselev.library.dao.exceptions.DAOException;
import com.kiselev.library.entity.impl.Book;
import com.kiselev.library.view.View;

public class GetObjectAction extends Action<Book> {

    public String execute(View view, DAO<Book> dao) {

        view.update("Enter the id:");

        try {
            Integer id = Integer.parseInt(view.input());

            Book object = dao.getObject(id);

            if (object != null) {
                return "\n" +
                        "ID:     " + object.getId() + "\n" +
                        "Name:   " + object.getName() + "\n" +
                        "Author: " + object.getAuthor();
            } else {
                return "Invalid id";
            }

        } catch (NumberFormatException exception) {
            return "Use numerical request only";
        } catch (DAOException exception) {
            return exception.getMessage();
        }
    }
}
