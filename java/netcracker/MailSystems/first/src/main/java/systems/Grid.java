package systems;

import entities.office.PostOffice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 09.11.15.
 */
public class Grid {

    private static final Grid grid = new Grid();

    private List<PostOffice> listOfOffices = new ArrayList<>();

    private Grid() {

    }

    public void addPostOffice(PostOffice office) {
        listOfOffices.add(office);
    }

    public static Grid getInstance() {
        return grid;
    }

    public PostOffice getPostOffice(int index) throws ArrayIndexOutOfBoundsException {

        if (index < 0 || index > listOfOffices.size() - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return listOfOffices.get(index);
    }

    public int getGridSize() {
        return listOfOffices.size();
    }

}
