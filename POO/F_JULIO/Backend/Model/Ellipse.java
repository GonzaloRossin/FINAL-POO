package POO.F_JULIO.Backend.Model;

import javafx.scene.paint.Color;

public class Ellipse extends Rectangle {
    public Ellipse(Point p1, Point p2, Color linecolor, Color fillcolor, double linewidth) {
        super(p1, p2, linecolor, fillcolor,linewidth);
    }
    @Override
    public String toString() {
        return String.format("elipse [ %s , %s ]",super.getTopLeft(),super.getBottomRight());
    }
}
