package POO.F_JULIO.Backend;


import POO.F_JULIO.Backend.Model.Figure;
import POO.F_JULIO.Backend.Model.Point;

import java.util.ArrayList;
import java.util.LinkedList;
public class CanvasState {

    private final LinkedList<Figure> list=new LinkedList<>();
    private SelectionBox selection=new SelectionBox(list);

    public void addFigure(Figure figure) {list.add(figure);}
    public Iterable<Figure> getselectedfigures(){return selection.getSelection();}
    public Iterable<Figure> figures() { return new ArrayList<>(list); }
    public void setSelection(Point p1, Point p2) {
        selection.setCoordinates(p1, p2);
        selection.selectFigures();
    }
    public boolean containsselection(Figure figure){return selection.figureIsSelected(figure);}
    public boolean selectionIsEmpty(){return selection.selectionIsEmpty();}
    public void clearSelection(){selection.clearSelection();}
    public void removeSelection(){
        if(selection.selectionIsEmpty())
            return;
        for(Figure figure:selection.getSelection()){
            list.remove(figure);
        }
    }
    public void moveToFront(Figure selectedfigure){
        list.remove(selectedfigure);
        list.addLast(selectedfigure);
    }
    public void moveToLast(Figure selectedfigure){
        list.remove(selectedfigure);
        list.addFirst(selectedfigure);
    }
}