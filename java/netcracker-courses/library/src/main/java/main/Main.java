package main;

import com.kiselev.library.controller.Controller;
import com.kiselev.library.controller.impl.ConcreteController;
import com.kiselev.library.dao.DAO;
import com.kiselev.library.dao.impl.XMLDAO;
import com.kiselev.library.entity.impl.Book;
import com.kiselev.library.model.Model;
import com.kiselev.library.model.impl.LibraryXMLModel;
import com.kiselev.library.view.View;
import com.kiselev.library.view.impl.ConsoleView;

import javax.xml.bind.JAXBException;

public class Main {

    public static void main(String[] args) {

        Model<Book> model = LibraryXMLModel.getInstance();

        DAO<Book> dao = null;

        try {
            dao = new XMLDAO(model, "src/main/resources/library.xml");
        } catch (JAXBException e) {
            System.out.println("Incorrect file settings");
            System.exit(1);
        }

        View view = new ConsoleView();
        Controller controller = new ConcreteController(view, dao);

        while (true) {
            controller.updateView();
            controller.performAction();
        }
    }
}
