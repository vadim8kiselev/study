package com.kiselev.library.dao.exceptions.impl;

import com.kiselev.library.dao.exceptions.DAOException;

public class UnmarshallException extends DAOException {

    public UnmarshallException() {
    }

    public UnmarshallException(String message) {
        super(message);
    }
}
