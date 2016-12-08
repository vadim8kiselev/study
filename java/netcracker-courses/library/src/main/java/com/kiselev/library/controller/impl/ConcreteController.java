package com.kiselev.library.controller.impl;

import com.kiselev.library.action.Action;
import com.kiselev.library.action.ActionEnum;
import com.kiselev.library.controller.Controller;
import com.kiselev.library.dao.DAO;
import com.kiselev.library.entity.impl.Book;
import com.kiselev.library.view.View;

public class ConcreteController extends Controller<Book> {

    public ConcreteController(View view, DAO<Book> dao) {
        super(view, dao);
    }

    public void updateView() {
        String page = "" +
                "\nMenu:\n" +
                "1. Add object\n" +
                "2. Delete object\n" +
                "3. Show object\n" +
                "4. Show all objects\n" +
                "5. Find object\n" +
                "6. Exit\n" +
                "Select one id from list: ";
        view.update(page);
    }

    public void performAction() {
        String method = view.input();

        Action<Book> action = ActionEnum.getAction(method);

        if (action != null) {
            view.update(action.execute(view, dao));
        } else {
            view.update("Invalid method's id");
        }
    }
}
