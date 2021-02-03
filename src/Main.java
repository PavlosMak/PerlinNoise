import images.Pixel;
import images.PpmImage;
import math.Vector2D;

import java.io.IOException;

/**
 * Driver class. It's meant mostly as an example of how to use the provided classes.
 */
public class Main {

    //=======Some constants for the texture generation======

    //texture dimensions
    public static int HEIGHT = 1000;
    public static int WIDTH = 1000;

    //Color strengths - set them slightly higher to the max color of your image
    //to get brighter results.
    public static int RED = 0;
    public static int GREEN = 250;
    public static int BLUE = 300;

    //As this values grow the resulting image will look more and more like white noise.
    public static double X_STEP = 0.02;
    public static double Y_STEP = 0.02;
    public static double Z_STEP = 0.04;

    /**
     * Driver method.
     * @param args Command line arguments to be passed.
     */
    public static void main(String[] args) {

        //=======================This Code Generates A single texture==================
        PerlinNoise perlinNoise = new PerlinNoise(42342494924824L);
        Pixel[][] pixels = new Pixel[HEIGHT][WIDTH];
        generateImage(perlinNoise, "generatedTexture", 0.0);
        //=======================This code generates the frames of an animated texture==================
        int depth = 20;
        for (int z = 0; z < depth; z++) {
            generateImage(perlinNoise,"animatedTextures/frame"+z, z*Z_STEP);
        }
    }

    /**
     * An example method to generate a 2D texture.
     * @param perlinNoise A PerlinNoise object to generate the noise.
     * @param filename The filename to use to store the image.
     * @param zOffset The zOffset in the 3D noise space at which to "take a 2D snapshot".
     */
    public static void generateImage(PerlinNoise perlinNoise, String filename, double zOffset) {
        Pixel[][] pixels = new Pixel[HEIGHT][WIDTH];
        for(int i = 0; i < HEIGHT; i++) {
            for(int j = 0; j < WIDTH; j++) {
                //we add 1 and divide by 2 to map from [-1,1] to [0,1]
                double value = (perlinNoise.getPerlinNoiseAtPoint(new Vector2D(i* X_STEP,j* Y_STEP)) + 1)/2;
                pixels[i][j] = new Pixel((int) (value* RED), (int) (value * GREEN), (int) (value * BLUE));
            }
        }
        //write texture to image
        PpmImage ppmImage = new PpmImage(pixels);
        try {
            ppmImage.writeToFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
