package math;

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
        this.x = this.x / getLength();
        this.y = this.y / getLength();
        this.z = this.z / getLength();
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
}
