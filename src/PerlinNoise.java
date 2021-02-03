import math.Vector2D;
import math.Vector3D;

import java.util.Random;

/**
 * The main class responsible for generating Perlin Noise.
 */
public class PerlinNoise {

    //this will store our random gradients.
    Vector3D[] gradients = new Vector3D[256];

    //Permutation table used by Ken Perlin in the enhanced version of his original algorithm.
    int[] permutations = {151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36,
            103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0,
            26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56,
            87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166,
            77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55,
            46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132,
            187, 208, 89, 18, 169, 200, 196, 135, 130, 116, 188, 159, 86, 164, 100, 109,
            198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118, 126,
            255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183,
            170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43,
            172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112,
            104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162,
            241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106,
            157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205,
            93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180,
            151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36,
            103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0,
            26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56,
            87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166,
            77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55,
            46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132,
            187, 208, 89, 18, 169, 200, 196, 135, 130, 116, 188, 159, 86, 164, 100, 109,
            198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118, 126,
            255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183,
            170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43,
            172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112,
            104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162,
            241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106,
            157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205,
            93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180};

    /**
     * Creates a new PerlinNoise object.
     * @param seed The seed to be used to introduce randomness.
     */
    public PerlinNoise(long seed) {
        Random random = new Random(seed);
        initialiseGradients(random);
    }

    /**
     * Creates a new PerlinNoise object.
     */
    public PerlinNoise() {
        Random random = new Random();
        initialiseGradients(random);
    }

    /**
     * Initialises the gradients array with random gradients (normalized 3D vectors).
     * @param random A Random object to be used for the random value generation.
     */
    private void initialiseGradients(Random random) {
        for(int i = 0; i < gradients.length; i++) {
            gradients[i] = getRandomNormalizedVector3D(random);
        }
    }

    /**
     * Pick a random, normalized gradient vector from a uniform distribution.
     * @param random The random object to be used for random number generation.
     * @return A normalized 3D vector.
     */
    private Vector3D getRandomNormalizedVector3D(Random random) {
        //the 2*random - 1 maps the random value from [0,1] to [-1,1]
        double theta = Math.acos(2.0*random.nextDouble() - 1);
        double phi = 2.0 * random.nextDouble() * Math.PI;

        double x = Math.cos(phi) * Math.sin(theta);
        double y = Math.sin(phi) * Math.sin(theta);
        double z = Math.cos(theta);

        Vector3D vector3D = new Vector3D(x,y,z);
        vector3D.normalize();
        return vector3D;
    }


    /**
     * Interpolate between start and end points (using the smootherstep function).
     * @param start The starting value of the interpolation.
     * @param end The ending value of the interpolation.
     * @param weight The weight of the interpolation.
     * @return An interpolated value between the 2 inputs.
     */
    private double lerp(double start, double end, double weight) {
        //fade function as defined by Ken Perlin
        return start*(1-smootherStep(weight)) + end * smootherStep(weight);
    }

    /**
     * The smootherstep function to be used for a smoother interpolation.
     * @param x The input of the function.
     * @return A double corresponding to the value of the function at x.
     */
    private double smootherStep(double x) {
        if (x <= 0) {
            return 0.0;
        } else if (x >= 1) {
            return 1.0;
        }
        return  x*x*x*(6*x*x - 15*x + 10);
    }

    /**
     * Get the index of the gradient corresponding to the given cell corner.
     * @param x The x coordinate of the cell corner.
     * @param y The y coordinate of the cell corner.
     * @param z The z coordinate of the cell corner.
     * @return An integer corresponding to an index in the gradients array.
     */
    private int hash(int x, int y, int z) {
        return permutations[permutations[permutations[x] + y] + z];
    }


    /**
     * Get the Perlin Noise value at a certain point.
     * @param point A 3D point at which we want to know the noise value.
     * @return A noise value between -1.0 and 1.0
     */
    public double getPerlinNoiseAtPoint(Vector3D point) {
        //calculate the corners of the cell
        int xi0 = (int) point.getX() % 256;
        int yi0 = (int) point.getY() % 256;
        int zi0 = (int) point.getZ() % 256;

        int xi1 = (xi0 + 1) % 256;
        int yi1 = (yi0 + 1) % 256;
        int zi1 = (zi0 + 1) % 256;

        Vector3D corner000 = new Vector3D(xi0, yi0, zi0);
        Vector3D corner001 = new Vector3D(xi0, yi0, zi1);
        Vector3D corner010 = new Vector3D(xi0, yi1, zi0);
        Vector3D corner011 = new Vector3D(xi0, yi1, zi1);
        Vector3D corner100 = new Vector3D(xi1, yi0, zi0);
        Vector3D corner101 = new Vector3D(xi1, yi0, zi1);
        Vector3D corner110 = new Vector3D(xi1, yi1, zi0);
        Vector3D corner111 = new Vector3D(xi1, yi1, zi1);

        //calculate the weights for the interpolation
        double weightX = point.getX() - xi0;
        double weightY = point.getY() - yi0;
        double weightZ = point.getZ() - zi0;

        //get the gradients at the corner of the cell.
        Vector3D gradientAtCorner000 = gradients[hash(xi0, yi0, zi0)];
        Vector3D gradientAtCorner001 = gradients[hash(xi0, yi0, zi1)];
        Vector3D gradientAtCorner010 = gradients[hash(xi0, yi1, zi0)];
        Vector3D gradientAtCorner011 = gradients[hash(xi0, yi1, zi1)];
        Vector3D gradientAtCorner100 = gradients[hash(xi1, yi0, zi0)];
        Vector3D gradientAtCorner101 = gradients[hash(xi1, yi0, zi1)];
        Vector3D gradientAtCorner110 = gradients[hash(xi1, yi1, zi0)];
        Vector3D gradientAtCorner111 = gradients[hash(xi1, yi1, zi1)];

        //get the vectors from the corners of the cell to the point
        Vector3D c000toP = corner000.displacementTo(point);
        Vector3D c001toP = corner001.displacementTo(point);
        Vector3D c010toP = corner010.displacementTo(point);
        Vector3D c011toP = corner011.displacementTo(point);
        Vector3D c100toP = corner100.displacementTo(point);
        Vector3D c101toP = corner101.displacementTo(point);
        Vector3D c110toP = corner110.displacementTo(point);
        Vector3D c111toP = corner111.displacementTo(point);

        //Take the dot products of displacement and gradient vectors and interpolate

        //interpolate linearly (along x axis)
        double a = lerp(gradientAtCorner000.dot(c000toP), gradientAtCorner100.dot(c100toP), weightX);
        double b = lerp(gradientAtCorner010.dot(c010toP), gradientAtCorner110.dot(c110toP), weightX);
        double c = lerp(gradientAtCorner001.dot(c001toP), gradientAtCorner101.dot(c101toP), weightX);
        double d = lerp(gradientAtCorner011.dot(c011toP), gradientAtCorner111.dot(c111toP), weightX);

        //interpolate bi-linearly (along y axis)
        double e = lerp(a,b, weightY);
        double f = lerp(c,d, weightY);

        //interpolate tri-linearly (along z axis)
        double result = lerp(e,f,weightZ);

        //the absolute is needed here because due to floating point errors
        //sometimes the result will be a very small negative number (-0.001...)
        return result;
    }

    /**
     * Get the Perlin Noise Value at a certain point.
     * @param point A 2D point at which we want to know the noise value.
     * @return A noise value between -1 and 1.
     */
    public double getPerlinNoiseAtPoint(Vector2D point) {
        return getPerlinNoiseAtPoint(new Vector3D(point.getX(), point.getY(), 0.0));
    }

}
