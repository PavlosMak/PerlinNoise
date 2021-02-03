import images.Pixel;
import images.PpmImage;
import math.Vector2D;

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

        double xStep = 0.01;
        double yStep = 0.01;

        PerlinNoise perlinNoise = new PerlinNoise();
        Pixel[][] pixels = new Pixel[height][width];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                double value = 1.0 + perlinNoise.getPerlinNoiseAtPoint(new Vector2D(i*xStep,j*yStep))/10;
                System.out.println(value);
                pixels[i][j] = new Pixel((int) (value*150), (int) (value * 150), (int) (value * 150));
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
