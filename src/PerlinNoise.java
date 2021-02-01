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
}
