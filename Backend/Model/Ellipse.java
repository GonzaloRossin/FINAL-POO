package POO.F_JULIO.Backend.Model;

public class Ellipse extends Rectangle {
    public Ellipse(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);
    }
    @Override
    public String toString() {
        return String.format("elipse [ %s , %s ]",super.getTopLeft(),super.getBottomRight());
    }
}
