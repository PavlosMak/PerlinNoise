package math;

/**
 * A class representing a 3D Vector to be used
 * for various mathematics operations.
 */
public class Vector3D implements Vector<Vector3D> {

    private double x;
    private double y;
    private double z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double dot(Vector3D a) {
        return this.x * a.getX() + this.y * a.getY() + this.z * a.getZ();
    }

    public Vector3D displacementTo(Vector3D a) {
        return new Vector3D(
                a.getX() - this.x,
                a.getY() - this.y,
                a.getZ() - this.z
        );
    }

    public void add(Vector3D a) {
        this.x += a.getX();
        this.y += a.getY();
        this.z += a.getZ();
    }

    public void normalize() {
        double length = getLength();
        this.x = this.x / length;
        this.y = this.y / length;
        this.z = this.z / length;
    }

    public double getLength() {
        return Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
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

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3D vector3D = (Vector3D) o;
        return Double.compare(vector3D.x, x) == 0 && Double.compare(vector3D.y, y) == 0 && Double.compare(vector3D.z, z) == 0;
    }

    @Override
    public String toString() {
        return "Vector3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
