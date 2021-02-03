package math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.MarshalException;

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
    void testDot() {
        assertEquals(10.0, vector.dot(secondVector));
    }

    @Test
    void testDisplacementTo() {
        Vector2D expected = new Vector2D(1.0, 2.0);
        assertEquals(expected, vector.displacementTo(secondVector));
    }

    @Test
    void testAdd() {
        Vector2D expected = new Vector2D(3.0, 6.0);
        vector.add(secondVector);
        assertEquals(expected, vector);
    }

    @Test
    void testGetLength() {
        assertEquals(Math.sqrt(5), vector.getLength());
    }
    
    @Test
    void testNormalize() {
        Vector2D expected = new Vector2D(1.0D/vector.getLength(), vector.getY()/vector.getLength());
        vector.normalize();
        assertEquals(expected, vector);
    }
}