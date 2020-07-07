package POO.F_JULIO.Backend;


import POO.F_JULIO.Backend.Model.Figure;
import POO.F_JULIO.Backend.Model.Point;

import java.util.ArrayList;
import java.util.List;

public class CanvasState {

    private final List<Figure> list = new ArrayList<>();

    public void addFigure(Figure figure) {
        list.add(figure);
    }
    public void removeFigure(Point eventPoint){
        int i=0;
        for(Figure aux : list){
            if(aux.figureBelongs(eventPoint)){
                list.remove(i);
                return;
            }
            i++;
        }
    }

    public Iterable<Figure> figures() {
        return new ArrayList<>(list);
    }

}