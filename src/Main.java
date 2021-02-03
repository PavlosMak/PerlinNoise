import images.Pixel;
import images.PpmImage;
import math.Vector2D;
import math.Vector3D;

import java.io.IOException;

/**
 * Driver class.
 */
public class Main {

    /**
     * Driver method.
     * @param args Command line arguments to be passed.
     */
    public static void main(String[] args) {
        //generate texture
        int height = 400;
        int width = 400;

        double xStep = 0.02;
        double yStep = 0.01;

        PerlinNoise perlinNoise = new PerlinNoise(7829320483401L);
        Pixel[][] pixels = new Pixel[height][width];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                //we add 1 and divide by 2 to map from [-1,1] to [0,1]
                double value = (perlinNoise.getPerlinNoiseAtPoint(new Vector3D(i*xStep,j*yStep, 0.0)) + 1)/2;
                pixels[i][j] = new Pixel((int) (value*255), (int) (value * 255), (int) (value * 255));
            }
        }
        //write texture to image
        PpmImage ppmImage = new PpmImage(pixels);
        try {
            ppmImage.writeToFile("test");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
