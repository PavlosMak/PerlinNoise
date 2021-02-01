package math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2DTest {

    Vector2D vector;
    Vector2D secondVector;

    @BeforeEach
    public void setup() {
        vector = new Vector2D(1.0, 2.0);
        secondVector = new Vector2D(2.0, 4.0);
    }

    @Test
    void dot() {
        assertEquals(10.0, vector.dot(secondVector));
    }

    @Test
    void displacementTo() {
        Vector2D expected = new Vector2D(1.0, 2.0);
        assertEquals(expected, vector.displacementTo(secondVector));
    }
}