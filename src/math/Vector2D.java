package math;

/**
 *  A class representing a 2D Vector to be used
 *  for various mathematics operations.
 */
public class Vector2D implements Vector<Vector2D> {

    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double dot(Vector2D a) {
        return this.getX()*a.getX() + this.getY()*a.getY();
    }

    public Vector2D displacementTo(Vector2D a) {
        return new Vector2D(a.getX() - this.getX(), a.getY() - this.getY());
    }

    public void add(Vector2D a) {
        this.x = this.getX() + a.getX();
        this.y = this.getY() + a.getY();
    }

    public void normalize() {
        double length = getLength();
        this.x = this.getX() / length;
        this.y = this.getY() / length;
    }

    public double getLength() {
        return Math.sqrt(getX()*getX() + getY()*getY());
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;
        return Double.compare(vector2D.x, x) == 0 && Double.compare(vector2D.y, y) == 0;
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
