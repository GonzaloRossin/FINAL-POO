package POO.F_JULIO.Backend;

import POO.F_JULIO.Backend.Model.Figure;
import POO.F_JULIO.Backend.Model.Point;
import javafx.scene.paint.Color;

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
    public void changeLineWidth(double value){
        for(Figure figure : selection){
            figure.setLinewidth(value);
        }
    }
    public void changeLineColor(Color color){//CAMBIO DE COLOR DE LINEA DE SELECCIONES
        for(Figure figure : selection){
            figure.setBordercolor(color);
        }
    }
    public void changeFillColor(Color color){// CAMBIO DE RELLENO DE SELECCIONES
        for(Figure figure : selection){
            figure.setFillercolor(color);
        }
    }
    public void moveSelection(double diffX,double diffY){//CAMBIO DE TAMAÑO DE BORDE DE SELECCIONES
        for(Figure figure:selection){
            figure.move(diffX,diffY);
        }
    }
}