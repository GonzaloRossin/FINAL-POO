package POO.F_JULIO.Backend.Model;

public class Rectangle extends Figure {

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
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
    public boolean figureBelongs(Point topleft, Point bottomright) {
        boolean found;
        found=topleft.getX()<this.topLeft.getX()&& this.bottomRight.getX()<bottomright.getX()&&topLeft.getY()>this.topLeft.getY()&& this.bottomRight.getY()>bottomRight.getY();
        return found;
    }
    @Override
    public boolean figureBelongs(Point eventPoint) {
        boolean found;
        found = eventPoint.getX() > getTopLeft().getX() && eventPoint.getX() < getBottomRight().getX() && eventPoint.getY() > getTopLeft().getY() && eventPoint.getY() < getBottomRight().getY();
        return found;
    }

}
