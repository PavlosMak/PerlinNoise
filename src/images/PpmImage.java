package images;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The image class is used to
 * store, manipulate and export images of the PPM format.
 */
public class PpmImage {

    private int width;
    private int height;
    private Pixel[][] pixels;

    //The readability format for how the file is saved.
    //Can be either P6 (binary) or P3 (human readable).
    private String readability = "P3";
    //The maximum value that colors can have.
    private int maxColorValue = 255;

    /**
     * Constructs a new Image object.
     * @param width The width of the image.
     * @param height The height of the image.
     */
    public PpmImage(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new Pixel[height][width];
    }


    /**
     * Constructs a new Image object.
     * @param pixels A 2D array holding the Pixels of the image.
     */
    public PpmImage(Pixel[][] pixels) {
        this.pixels = pixels;
        this.height = pixels.length;
        this.width = pixels[0].length;
    }

    /**
     * Write this image to a file.
     * @param filename The filename to write.
     * @throws IOException Iff an exception got encountered while writing the file.
     */
    public void writeToFile(String filename) throws IOException {
        if (!filename.endsWith(".ppm")) {
            filename += ".ppm";
        }
        FileWriter writer = new FileWriter(filename);
        writer.write(this.toString());
        writer.close();
    }

    /**
     * Returns a String representation of the image according to the
     * PPM format.
     * @return A String representation of the image.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(getReadability()).append("\n")
                .append(getWidth()).append(" ").append(getHeight()).append("\n")
                .append(maxColorValue).append("\n");
        for(int i = 0; i < getHeight(); i++) {
            for(Pixel pixel : pixels[i]) {
                result.append(pixel.toString()).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }


    //Getters and setters.

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Pixel[][] getPixels() {
        return pixels;
    }

    public String getReadability() {
        return readability;
    }


    public void setReadability(String readability) throws IllegalArgumentException {
        if(!(readability.equals("P6") || readability.equals("P3"))) {
            throw new IllegalArgumentException("Readability can be either P6 (binary) or P3 (human readable)");
        }
        this.readability = readability;
    }

    public int getMaxColorValue() {
        return maxColorValue;
    }

    public void setMaxColorValue(int maxColorValue) throws IllegalArgumentException {
        if (maxColorValue <= 0 || maxColorValue > 65536) {
            throw new IllegalArgumentException("The maximum color value must be between 1 and 65536 (included)");
        }
        this.maxColorValue = maxColorValue;
    }
}
