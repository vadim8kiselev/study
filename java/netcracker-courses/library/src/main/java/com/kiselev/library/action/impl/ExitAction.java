package com.kiselev.library.action.impl;

import com.kiselev.library.action.Action;
import com.kiselev.library.dao.DAO;
import com.kiselev.library.view.View;

public class ExitAction extends Action {

    @Override
    public String execute(View view, DAO dao) {
        view.update("Bye");
        System.exit(0);
        return null;
    }
}
