package com.kiselev.library.action.impl;

import com.kiselev.library.action.Action;
import com.kiselev.library.dao.DAO;
import com.kiselev.library.dao.exceptions.DAOException;
import com.kiselev.library.entity.impl.Book;
import com.kiselev.library.view.View;

import java.util.List;

public class GetObjectsAction extends Action<Book> {

    public String execute(View view, DAO<Book> dao) {
        try {
            List<Book> objects = dao.getObjects();
            StringBuilder resultList = new StringBuilder();

            if (objects != null && objects.size() != 0) {

                for (Book object : objects) {
                    resultList.append("\n");
                    resultList.append("ID:     ").append(object.getId()).append("\n");
                    resultList.append("Name:   ").append(object.getName()).append("\n");
                    resultList.append("Author: ").append(object.getAuthor()).append("\n");
                }

                return resultList.toString();

            } else {
                return "List is empty";
            }
        } catch (DAOException exception) {
            return exception.getMessage();
        }
    }
}
