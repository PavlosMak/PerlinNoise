package math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector3DTest {

    Vector3D vector;
    Vector3D secondVector;

    @BeforeEach
    void setup() {
        vector = new Vector3D(1.0, 2.0, 3.0);
        secondVector = new Vector3D(2.0, 3.0, 4.0);
    }

    @Test
    void testDot() {
        assertEquals(20.0, vector.dot(secondVector));
    }

    @Test
    void testDisplacementTo() {
        Vector3D expected = new Vector3D(1.0, 1.0, 1.0);
        assertEquals(expected, vector.displacementTo(secondVector));
    }

    @Test
    void testAdd() {
        Vector3D expected = new Vector3D(3.0, 5.0, 7.0);
        vector.add(secondVector);
        assertEquals(expected, vector);
    }

    @Test
    void testGetLength() {
        assertEquals(Math.sqrt(14), vector.getLength());
    }

    @Test
    void testNormalize() {
        Vector3D expected = new Vector3D(1.0/vector.getLength(), 2.0/vector.getLength(), 3.0/vector.getLength());
        vector.normalize();
        assertEquals(expected, vector);
    }

}