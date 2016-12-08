package com.kiselev.library.entity.impl;

import com.kiselev.library.entity.Entity;

import javax.xml.bind.annotation.XmlElement;

public class Book extends Entity {

    private String author;

    public String getAuthor() {
        return author;
    }

    @XmlElement
    public void setAuthor(String author) {
        this.author = author;
    }
}
