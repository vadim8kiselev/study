package com.kiselev.library.dao.exceptions.impl;

import com.kiselev.library.dao.exceptions.DAOException;

public class MarshallException extends DAOException {

    public MarshallException() {
    }

    public MarshallException(String message) {
        super(message);
    }
}
