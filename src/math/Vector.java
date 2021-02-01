package math;

public interface Vector<E extends Vector> {

    /**
     * Calculate the dot product between this vector and another one.
     * @param a The second vector.
     * @return A float equal to the dot product of this vector and a.
     */
    public double dot(E a);

    /**
     * Calculate a displacement vector from this vector to a.
     * @param a The vector to which to calculate the displacement.
     * @return A displacement Vector.
     */
    public E displacementTo(E a);

}
