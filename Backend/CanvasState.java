package POO.F_JULIO.Backend;


import POO.F_JULIO.Backend.Model.Figure;

import java.util.ArrayList;
import java.util.List;

public class CanvasState {

    private final List<Figure> list = new ArrayList<>();

    public void addFigure(Figure figure) {
        list.add(figure);
    }

    public Iterable<Figure> figures() {
        return new ArrayList<>(list);
    }

}