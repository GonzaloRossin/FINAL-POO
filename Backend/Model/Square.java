package POO.F_JULIO.Backend.Model;

public class Square extends Rectangle {
    public Square(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);
    }
    public double Side(){return getBottomRight().x-getTopLeft().x;}
    @Override
    public String toString() {
        Point aux= new Point(super.getTopLeft().x+super.getBottomRight().x,super.getTopLeft().y-super.getBottomRight().x);
        return String.format("Rectángulo [ %s , %s ]",super.getTopLeft(), aux);
    }
}
