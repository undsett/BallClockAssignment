package com.ballclock;

import static com.ballclock.Utils.printResult;
import static com.ballclock.Utils.printJson;

public class BallClock {

    Track minuteTrack;
    Track fiveMinuteTrack;
    Track hourTrack;
    MainTrack mainTrack;
    int minutes;

    public BallClock(int balls, int minutes) {
        this.minutes = minutes;
        mainTrack = new MainTrack(balls, null);
        hourTrack = new CommonTrack(11, mainTrack);
        fiveMinuteTrack = new CommonTrack(11, hourTrack);
        minuteTrack = new CommonTrack(4, fiveMinuteTrack);
        mainTrack.setNextTrack(minuteTrack);
        CommonTrack.setMainTrack(mainTrack);
    }

    public void runClock() throws BallNumberOutOfRangeException {
        if (mainTrack.getCapacity() < 27 || mainTrack.getCapacity() > 127)
        {
            throw new BallNumberOutOfRangeException("Valid numbers of balls are in the range 27 to 127");
        }
        long time = System.currentTimeMillis();
        int cycles = 1;
        if (minutes == 0) {
            do {
                mainTrack.moveOneBall();
                if (mainTrack.checkOrder()) {
                    break;
                }
            } while (cycles++ > 0);
            long runTime = System.currentTimeMillis() - time;
            printResult(mainTrack, runTime, cycles);
        } else {
            while ( cycles < minutes+1 ) {
                mainTrack.moveOneBall();
                cycles++;
            }
            long runTime = System.currentTimeMillis() - time;
            printJson(this, runTime);
        }
    }
}
