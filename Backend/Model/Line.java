package POO.F_JULIO.Backend.Model;

import javafx.scene.paint.Color;

public class Line extends Figure {
    private Point p1,p2;

    public Line(Point p1, Point p2, Color linecolor, double linewidth) {
        this.p1 = p1;
        this.p2 = p2;
        setBordercolor(linecolor);
        setLinewidth(linewidth);
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    @Override
    public boolean figureBelongs(Point eventPoint) {
        return false;
    }
    @Override
    public boolean figureBelongs(Point startpoint, Point endpoint) {
        boolean p1contained,p2contained;
        p1contained=p1.getX()>startpoint.getX()&&p1.getX()<endpoint.getX()&&p1.getY()>startpoint.getY()&&p1.getY()<endpoint.getY();
        p2contained=p2.getX()>startpoint.getX()&&p2.getX()<endpoint.getX()&&p2.getY()>startpoint.getY()&&p2.getY()<endpoint.getY();
        return p1contained && p2contained;
    }
    @Override
    public void move(double deltax, double deltay) {
        p1.x+=deltax;
        p1.y+=deltay;
        p2.x+=deltax;
        p2.y+=deltay;
    }
    @Override
    public String toString() {
        return String.format("Linea [ %s , %s ]", p1, p2);
    }
}
