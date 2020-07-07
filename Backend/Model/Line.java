package POO.F_JULIO.Backend.Model;

public class Line extends Figure {
    private Point p1,p2;

    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
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
    public boolean figureBelongs(Point topleft, Point bottomright) {
        return false;
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
