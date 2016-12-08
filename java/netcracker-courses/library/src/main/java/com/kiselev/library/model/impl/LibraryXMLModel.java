package com.kiselev.library.model.impl;

import com.kiselev.library.entity.impl.Book;
import com.kiselev.library.model.Model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "entities")
public class LibraryXMLModel implements Model<Book> {

    private static final LibraryXMLModel instance = new LibraryXMLModel();

    private LibraryXMLModel() {
    }

    public static LibraryXMLModel getInstance() {
        return instance;
    }

    protected volatile List<Book> list = new ArrayList<Book>();

    @XmlElement
    public synchronized List<Book> getList() {
        return list;
    }

    public synchronized void setList(List<Book> list) {
        this.list = list;
    }
}
