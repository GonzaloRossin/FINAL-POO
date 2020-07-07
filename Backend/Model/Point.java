package POO.F_JULIO.Backend.Model;

public class Point {

    public double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("{%.2f , %.2f}", x, y);
    }
    public double distanceTo(Point p2){return Math.sqrt(Math.pow(p2.x-this.x,2)+Math.pow(p2.y-this.y,2));}

}
