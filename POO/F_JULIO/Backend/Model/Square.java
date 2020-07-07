package POO.F_JULIO.Backend.Model;

import javafx.scene.paint.Color;

public class Square extends Rectangle {
    public Square(Point p1, Point p2, Color linecolor, Color fillcolor, double linewidth) {
        super(p1, p2, linecolor, fillcolor,linewidth);
    }
    public double Side(){return getBottomRight().x-getTopLeft().x;}
    @Override
    public String toString() {
        Point aux= new Point(super.getTopLeft().x+super.getBottomRight().x,super.getTopLeft().y-super.getBottomRight().x);
        return String.format("Cuadrado [ %s , %s ]",super.getTopLeft(), aux);
    }
}
