package POO.F_JULIO.Backend.Model;

import javafx.scene.paint.Color;

public abstract class Figure{
    private Color color=Color.BLACK;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public abstract void move(double deltax,double deltay);
    public abstract boolean figureBelongs(Point eventPoint);
    public abstract boolean figureBelongs(Point startpoint,Point endpoint);
}
