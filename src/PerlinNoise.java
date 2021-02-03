import math.Vector2D;

import java.util.Random;

public class PerlinNoise {
    /**
     * The algorithm
     * 1) Create n-dimensional grid (cell) with random vectors
     * 2) For every point:
     *      find the cell (2^n) corners where the point belongs
     *      for each corner:
     *          find displacement vector from point to corner
     *          take dot product of corners gradient (random) vector with displacement vector
     *      interpolate the dot products
     */

    private double xMultiplier;
    private double yMultiplier;

    /**
     * Creates a new PerlinNoise object.
     * @param seed The seed to be used to introduce randomness.
     */
    public PerlinNoise(long seed) {
        Random random = new Random(seed);
        this.xMultiplier = random.nextDouble() * 2000;
        this.yMultiplier = random.nextDouble() * 2000;
    }

    /**
     * Creates a new PerlinNoise object.
     */
    public PerlinNoise() {
        Random random = new Random();
        this.xMultiplier = random.nextDouble() * 2000;
        this.yMultiplier = random.nextDouble() * 2000;
    }

    /**
     * Get the randomly generated gradient at the cell corner coordinates defined by the vector.
     * @param vector A vector holding the x and y coordinates of the cell corner we are interested in.
     * @return A Vector2D object holding the gradient at that point.
     */
    private Vector2D getRandomGradientVector(Vector2D vector) {
//        double x = vector.getX()*this.xMultiplier;
//        double y = vector.getY()*this.yMultiplier;
//        return new Vector2D(Math.cos(x), Math.sin(y));
        double random = 2920.0*Math.sin(vector.getX()*21942.0 + vector.getY() * 171324.0 + 8912.0) * Math.cos(vector.getX()*23157.0*vector.getY()*217832.0 + 9758.0);
        Vector2D randVector = new Vector2D(Math.cos(random), Math.sin(random));
        randVector.normalize();
        return randVector;
    }


    /**
     * Interpolate between start and end by weight using the smoothstep function.
     * @param start The starting value of the interpolation.
     * @param end The ending value of the interpolation.
     * @param weight The weight of the interpolation.
     * @return An interpolated value between the 2 inputs.
     */
    private double lerp(double start, double end, double weight) {
        //fade function as defined by Ken Perlin
        return start*(1-smootherStep(weight)) + end * smootherStep(weight);
    }

    private double smootherStep(double x) {
        if (x <= 0) {
            return 0.0;
        } else if (x >= 1) {
            return 1.0;
        }
        return  x*x*x*(6*x*x - 15*x + 10);
    }


    /**
     * Return the Perlin Noise value at the given point.
     * @param point A point.
     * @return A double between -1 and 1.
     */
    public double getPerlinNoiseAtPoint(Vector2D point) {
        //Find the grid coordinates where the point belongs
        int gridXLower = (int) point.getX();
        int gridXUpper = gridXLower + 1;
        int gridYLower = (int) point.getY();
        int gridYUpper = gridYLower + 1;

        //Get grid corner vectors
        Vector2D northEastCornerGridVector = new Vector2D(gridXUpper, gridYUpper);
        Vector2D northWestCornerGridVector = new Vector2D(gridXUpper, gridYLower);
        Vector2D southEastCornerGridVector = new Vector2D(gridXLower, gridYUpper);
        Vector2D southWestCornerGridVector = new Vector2D(gridXLower, gridYLower);

        //Get the gradients that "reside" in the corners of the grid
        Vector2D northEastCornerGradient = getRandomGradientVector(northEastCornerGridVector);
        Vector2D northWestCornerGradient = getRandomGradientVector(northWestCornerGridVector);
        Vector2D southEastCornerGradient = getRandomGradientVector(southEastCornerGridVector);
        Vector2D southWestCornerGradient = getRandomGradientVector(southWestCornerGridVector);

        //Find displacement vectors
        Vector2D displacementFromNorthEast = northEastCornerGradient.displacementTo(point);
        Vector2D displacementFromNorthWest = northWestCornerGradient.displacementTo(point);
        Vector2D displacementFromSouthEast = southEastCornerGradient.displacementTo(point);
        Vector2D displacementFromSouthWest = southWestCornerGradient.displacementTo(point);

        //Find dot products of gradients and displacements
        double dotNorthEast = northEastCornerGradient.dot(displacementFromNorthEast);
        double dotNorthWest = northWestCornerGradient.dot(displacementFromNorthWest);
        double dotSouthEast = northEastCornerGradient.dot(displacementFromSouthEast);
        double dotSouthWest = southWestCornerGradient.dot(displacementFromSouthWest);

        //Interpolate
        double interpolationWeightInX = point.getX() - gridXLower;
        double interpolationWeightInY = point.getY() - gridYLower;

        double horizontalInterpolation = lerp(dotSouthWest, dotSouthEast, interpolationWeightInX);
        double verticalInterpolation = lerp(dotNorthWest, dotNorthEast, interpolationWeightInY);

        double interpolation2D = lerp(horizontalInterpolation, verticalInterpolation, interpolationWeightInY);

        return interpolation2D;
    }

}
