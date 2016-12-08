package com.kiselev.library.action;

import com.kiselev.library.dao.DAO;
import com.kiselev.library.entity.Entity;
import com.kiselev.library.view.View;

public abstract class Action<T extends Entity> {

    public abstract String execute(View view, DAO<T> dao);
}
