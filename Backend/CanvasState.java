package POO.F_JULIO.Backend;


import POO.F_JULIO.Backend.Model.Figure;
import POO.F_JULIO.Backend.Model.Point;

import java.util.ArrayList;
import java.util.List;

public class CanvasState {

    private final List<Figure> list = new ArrayList<>();
    private  List<Figure> selectedfigures=new ArrayList<>();

    public void addFigure(Figure figure) {list.add(figure);}
    public Iterable<Figure> getselectedfigures(){return new ArrayList<>(selectedfigures);}
    public Iterable<Figure> figures() { return new ArrayList<>(list); }
    public void addSelection(Figure figure){ selectedfigures.add(figure);}
    public boolean containsselection(Figure figure){return selectedfigures.contains(figure);}
    public void clearSelection(){selectedfigures.clear();}
    public void removeSelection(){
        if(selectedfigures==null)
            return;
        for(Figure figure:selectedfigures){
            list.remove(figure);
        }
    }

}