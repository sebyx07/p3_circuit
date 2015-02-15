package com.sebastianbuza.p3_circuit.models.point;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GatewayTest {

    Point left;
    Point right;
    Point gateway;

    @Before
    public void setUp() throws Exception {
        left = new StartPoint(true);
        right = new StartPoint(false);
    }

    @Test
    public void testIsTrueOr() throws Exception {
        gateway = new Gateway(left, right, "||");
        assertTrue(gateway.isTrue());
    }

    @Test
    public void testIsTrueAnd() throws Exception {
        gateway = new Gateway(left, right, "&&");
        assertFalse(gateway.isTrue());
    }

    @Test
    public void testIsTrueRecursive() throws Exception{
        Point a = new StartPoint(true);
        Point b = new StartPoint(true);
        Point c = new StartPoint(false);

        Point g1 = new Gateway(a, b, "&&");
        Point g2 = new Gateway(a, c, "&&");
        Point g3 = new Gateway(g1, g2, "||");

        assertTrue(g3.isTrue());
    }
}