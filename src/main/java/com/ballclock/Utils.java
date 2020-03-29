package com.ballclock;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Utils {

    private Utils() {}

    public static void pushOrRelease(Integer ball, Track track, MainTrack mainTrack, Track nextTrack) {
        if (!track.isFull()) {
            track.getTrack().offer(ball);
        } else {
            releaseTrack(ball, track, mainTrack, nextTrack);
        }
    }

    public static void releaseTrack(Integer ball, Track track, MainTrack mainTrack, Track nextTrack) {
        while (!track.isEmpty()) {
            mainTrack.getTrack().offer(track.getTrack().pollLast());
        }
        nextTrack.moveToNext(ball);
    }

    public static void printResult(MainTrack mainTrack, long runTime, int days){
        System.out.println(mainTrack.getCapacity() + " balls cycle after " + days/1440 + " days.");
        System.out.println("Completed in " + runTime + " milliseconds("+ (double)runTime/1000 + " seconds)");
    }

    public static void printJson(BallClock clock, long runTime) {
        Map<String, Object[]> map = new LinkedHashMap<>();
        map.put("Min", clock.minuteTrack.getTrack().toArray());
        map.put("FiveMin", clock.fiveMinuteTrack.getTrack().toArray());
        map.put("Hour", clock.hourTrack.getTrack().toArray());
        map.put("Main", clock.mainTrack.getTrack().toArray());
        Gson gson = new Gson();
        System.out.println(gson.toJson(map));
        System.out.println("Completed in " + runTime + " milliseconds("+ (double)runTime/1000 + " seconds)");
    }
}
