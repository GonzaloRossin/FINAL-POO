package POO.F_JULIO.Backend.Model;

import javafx.scene.paint.Color;

public abstract class Figure{
    private Color bordercolor;
    private Color fillercolor;
    private double linewidth;

    public double getLinewidth() {
        return linewidth;
    }
    public void setLinewidth(double linewidth) {
        this.linewidth = linewidth;
    }

    public Color getBordercolor() {
        return bordercolor;
    }

    public void setBordercolor(Color bordercolor) {
        this.bordercolor = bordercolor;
    }
    public Color getFillercolor() {
        return fillercolor;
    }

    public void setFillercolor(Color fillercolor) {
        this.fillercolor = fillercolor;
    }
    public abstract void move(double deltax,double deltay);
    public abstract boolean figureBelongs(Point eventPoint);
    public abstract boolean figureBelongs(Point startpoint,Point endpoint);
}
