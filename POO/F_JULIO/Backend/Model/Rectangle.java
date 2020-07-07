package POO.F_JULIO.Backend.Model;

import javafx.scene.paint.Color;

public class Rectangle extends Figure {

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight, Color linecolor, Color fillcolor, double linewidth) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        setBordercolor(linecolor);
        setFillercolor(fillcolor);
        setLinewidth(linewidth);
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }
    public double base() {
        return Math.abs(topLeft.getX() - bottomRight.getX());
    }
    public double height() {
        return Math.abs(topLeft.getY() - bottomRight.getY());
    }
    @Override
    public boolean figureBelongs(Point startpoint, Point endpoint) {
        boolean found;
        found=startpoint.getX()< topLeft.getX()&& bottomRight.getX()< endpoint.getX()&& startpoint.getY()<this.topLeft.getY()&& endpoint.getY()>bottomRight.getY();
        return found;
    }
    @Override
    public boolean figureBelongs(Point eventPoint) {
        boolean found;
        found = eventPoint.getX() > getTopLeft().getX() && eventPoint.getX() < getBottomRight().getX() && eventPoint.getY() > getTopLeft().getY() && eventPoint.getY() < getBottomRight().getY();
        return found;
    }
    @Override
    public void move(double deltax, double deltay) {
        topLeft.x+=deltax;
        bottomRight.x+=deltax;
        topLeft.y+=deltay;
        bottomRight.y+=deltay;
    }
}
