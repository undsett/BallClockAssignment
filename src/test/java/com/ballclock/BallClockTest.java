package com.ballclock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class BallClockTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void modeOneThirtyBallsPositive() throws Exception {
        new BallClock(30, 0).runClock();
        assertTrue(outContent.toString().contains("30 balls cycle after 15 days."));
    }

    @Test
    public void modeOneFortyFiveBallsPositive() throws Exception {
        new BallClock(45, 0).runClock();
        assertTrue(outContent.toString().contains("45 balls cycle after 378 days."));
    }

    @Test
    public void modeTwoPositive() throws Exception {
        new BallClock(30, 325).runClock();
        assertTrue(outContent.toString().contains("{\"Min\":[],\"FiveMin\":[22,13,25,3,7],\"Hour\":[6,12,17,4,15]" +
                ",\"Main\":[11,5,26,18,2,30,19,8,24,10,29,20,16,21,28,1,23,14,27,9]}"));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
