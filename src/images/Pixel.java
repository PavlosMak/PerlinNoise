package images;

public class Pixel {

    int red;
    int green;
    int blue;

    /**
     * Constructs a new images.Pixel object.
     * @param red The red value of the pixel.
     * @param green The green value of the pixel.
     * @param blue The blue value of the pixel.
     */
    public Pixel(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Returns a String representation of the pixel,
     * suitable to be written to a PPM image file.
     * @return A String representation of the pixel.
     */
    public String toString() {
        return this.red + " " + this.green + " " + this.blue;
    }


}
