package com.kiselev.library.dao.impl;

import com.kiselev.library.entity.impl.Book;
import com.kiselev.library.model.Model;
import com.kiselev.library.model.impl.LibraryXMLModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class XMLDAO extends LibraryDAO<Book> {

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;
    private File file;

    public XMLDAO(Model<Book> model, String path) throws JAXBException {
        super(model);

        JAXBContext jaxbContext = JAXBContext.newInstance(LibraryXMLModel.class);

        this.marshaller = jaxbContext.createMarshaller();
        this.unmarshaller = jaxbContext.createUnmarshaller();
        this.file = new File(path);

        try {
            unmarshal();
        } catch (JAXBException ignored) {
        }
    }

    @Override
    protected synchronized void marshal() throws JAXBException {
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        PrintWriter writer = null;

        try {
            writer = new PrintWriter(file);
            marshaller.marshal(model, writer);

        } catch (FileNotFoundException exception) {
            marshaller.marshal(model, file);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    @Override
    protected synchronized void unmarshal() throws JAXBException {
        model.setList(((LibraryXMLModel) unmarshaller.unmarshal(file)).getList());
    }
}
