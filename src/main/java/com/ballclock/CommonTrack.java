package com.ballclock;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.ballclock.Utils.pushOrRelease;

public class CommonTrack implements Track {

    public final Deque<Integer> track;
    private int capacity;
    private static MainTrack mainTrack;
    private Track nextTrack;

    public static void setMainTrack(MainTrack main) {
        mainTrack = main;
    }

    public CommonTrack(int capacity, Track nextTrack) {
        this.capacity = capacity;
        this.nextTrack = nextTrack;
        track = new ArrayDeque<>(capacity);
    }

    @Override
    public void setNextTrack(Track nextTrack) {
        this.nextTrack = nextTrack;
    }

    @Override
    public boolean isEmpty() {
        return this.track.isEmpty();
    }

    @Override
    public boolean isFull() {
        return this.track.size() == capacity;
    }

    @Override
    public void moveToNext(Integer ball) {
        pushOrRelease(ball, this, mainTrack, nextTrack);
    }

    @Override
    public Deque<Integer> getTrack() {
        return this.track;
    }
}
