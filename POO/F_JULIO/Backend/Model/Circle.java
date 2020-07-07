package POO.F_JULIO.Backend.Model;

import javafx.scene.paint.Color;

public class Circle extends Figure {

    protected final Point centerPoint;
    protected final double radius;

    public Circle(Point centerPoint, double radius, Color linecolor, Color fillcolor, double linewidth) {
        this.centerPoint = centerPoint;
        this.radius = radius;
        setBordercolor(linecolor);
        setFillercolor(fillcolor);
        setLinewidth(linewidth);
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, radius);
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getRadius() {
        return radius;
    }
    @Override
    public boolean figureBelongs(Point eventPoint) {
        return centerPoint.distanceTo(eventPoint)<=radius;
    }
    @Override
    public boolean figureBelongs(Point topleft, Point bottomright) {
        boolean found;
        found=topleft.getY()<centerPoint.getY()-getRadius() && bottomright.getY()>centerPoint.getY()+getRadius() && topleft.getX()<centerPoint.getX()-getRadius() && bottomright.getX()>centerPoint.getX()+getRadius();
        return found;
    }
    @Override
    public void move(double deltax, double deltay) {
        centerPoint.x+=deltax;
        centerPoint.y+=deltay;
    }
}
