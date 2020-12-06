package ir.artaateam.android.braingame.Forms;

import ir.artaateam.android.braingame.Enums.Color;
import ir.artaateam.android.braingame.Enums.Shape;

public class ShapeAndColor {
    private Color color;
    private Shape shape;
    private int id;

    public ShapeAndColor(Shape shape, Color color, int id) {
        this.color = color;
        this.shape = shape;
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
