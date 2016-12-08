package com.kiselev.library.action.impl;

import com.kiselev.library.action.Action;
import com.kiselev.library.dao.DAO;
import com.kiselev.library.dao.exceptions.DAOException;
import com.kiselev.library.entity.impl.Book;
import com.kiselev.library.view.View;

import java.util.List;

public class FindObjectAction extends Action<Book> {

    @Override
    public String execute(View view, DAO<Book> dao) {

        view.update("Enter the pattern of searching\n" +
                "(Use alphanumeric symbols and meta symbols like . and *): ");
        String pattern = view.input();

        pattern = pattern.replace("*", ".*");

        try {
            List<Book> list = dao.getObjects();

            StringBuilder foundedList = new StringBuilder();

            if (list.size() != 0) {

                for (Book entity : list) {
                    if (entity.getName().matches(pattern) || entity.getAuthor().matches(pattern)) {
                        foundedList.append("\n");
                        foundedList.append("ID:     ").append(entity.getId()).append("\n");
                        foundedList.append("Name:   ").append(entity.getName()).append("\n");
                        foundedList.append("Author: ").append(entity.getAuthor()).append("\n");
                    }
                }
                return foundedList.toString();

            } else {
                return "Match list is empty";
            }
        } catch (DAOException exception) {
            return exception.getMessage();
        }
    }
}
