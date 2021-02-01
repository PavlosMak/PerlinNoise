import images.Pixel;
import images.PpmImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PpmImageTest {

    PpmImage ppmImage;
    Pixel[][] pixels;


    @BeforeEach
    public void setup() {
        pixels = generatePixels(4,4);
        ppmImage = new PpmImage(pixels);
    }

    @Test
    public void testToString() throws IOException {
        String expected = "P3\n4 4\n255\n" +
                "255 255 255 0 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 255 255 255 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 255 255 255 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 0 255 255 255 \n";
        assertEquals(expected, ppmImage.toString());
    }


    /**
     * Generate a generic pixels array for testing.
     **/
    private Pixel[][] generatePixels(int width, int height) {
        Pixel[][] pixels = new Pixel[height][width];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if (i == j) {
                    pixels[i][j] = new Pixel(255, 255, 255);
                } else {
                    pixels[i][j] = new Pixel(0,0,0);
                }
            }
        }
        return pixels;
    }
}