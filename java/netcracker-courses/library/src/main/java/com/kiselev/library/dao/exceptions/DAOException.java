package com.kiselev.library.dao.exceptions;

public abstract class DAOException extends RuntimeException {

    public DAOException() {

    }

    public DAOException(String message) {
        super(message);
    }

}
