package POO.F_JULIO.Backend;

import POO.F_JULIO.Backend.Model.Figure;
import POO.F_JULIO.Backend.Model.Point;

import java.util.ArrayList;
import java.util.List;

public class SelectionBox {//CLASE QUE MANEJA LA SELECCION DE FIGURAS
    private Point startpoint;
    private Point endpoint;
    private Iterable<Figure> figures;
    private List<Figure> selection=new ArrayList<>();
    public SelectionBox(Iterable<Figure> figures){
        this.figures=figures;
    }
    public void selectFigures(){
        if(startpoint.equals(endpoint)){
            Figure finalfigure=null;
            for(Figure figure:figures){
                if(figure.figureBelongs(startpoint)) {
                    finalfigure=figure;
                }
            }
            if(finalfigure!=null)
                selection.add(finalfigure);
        }
        else{
            for(Figure figure:figures){
                if(figure.figureBelongs(startpoint,endpoint))
                    selection.add(figure);
            }
        }
    }
    public void setCoordinates(Point p1,Point p2){
        startpoint=p1;
        endpoint=p2;
    }
    public boolean figureIsSelected(Figure figure){return selection.contains(figure);}
    public boolean selectionIsEmpty(){return selection.isEmpty();}
    public void clearSelection(){selection.clear();}
    public Iterable<Figure> getSelection(){return new ArrayList<>(selection);}
}