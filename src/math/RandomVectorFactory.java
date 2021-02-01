package math;

import java.util.Random;

/**
 * This class is used to produce
 * random vectors of different dimensions.
 */
public class RandomVectorFactory {

    /**
     * Creates a random 2D vector.
     * @return A random Vector2D object.
     */
    public static Vector2D getRandom2DVector() {
        Random random = new Random();
        return new Vector2D(random.nextDouble(), random.nextDouble());
    }

}
