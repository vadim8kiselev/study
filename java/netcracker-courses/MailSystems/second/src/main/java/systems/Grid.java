package systems;

import interfaces.Office;
import main.PostOffice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 17.11.15.
 */
public class Grid {

    private static final Grid grid = new Grid();

    private List<Office> offices = new ArrayList<>();

    private Grid() {

    }

    public static Grid getInstance() {
        return grid;
    }

    public void addOffice() {
        offices.add(new PostOffice());
    }

    public void connectOffices(int baseId, int targetId) {
        getOffice(baseId).setConnection(targetId);
    }

    public List<Office> getListOfOffices() {
        return offices;
    }

    public int getCountOfOffices() {
        return offices.size();
    }

    public Office getOffice(int id) {
        if (id < 0 || id > offices.size() - 1)
            return null;
        return offices.get(id);
    }
}
