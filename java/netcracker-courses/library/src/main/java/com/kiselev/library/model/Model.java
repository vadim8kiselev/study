package com.kiselev.library.model;

import java.util.List;

public interface Model<T> {

    List<T> getList();

    void setList(List<T> list);
}
