package com.ballclock;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import static com.ballclock.Utils.pushOrRelease;

public class MainTrack implements Track {

    public final Deque<Integer> track;
    private int capacity;
    private MainTrack mainTrack;
    private Track nextTrack;
    private final Object[] defaultValue;

    public MainTrack(int capacity, Track nextTrack) {
        this.capacity = capacity;
        this.nextTrack = nextTrack;
        track = new ArrayDeque<>(capacity);
        defaultValue = fillTrack(capacity, this);
    }

    public int getCapacity() {
        return capacity;
    }

    public void moveOneBall() {
        nextTrack.moveToNext(track.pollFirst());
    }

    @Override
    public void setNextTrack(Track nextTrack) {
        this.nextTrack = nextTrack;
    }

    @Override
    public boolean isEmpty() {
        return track.isEmpty();
    }

    @Override
    public boolean isFull() {
        return track.size() == capacity;
    }

    @Override
    public void moveToNext(Integer ball) {
        pushOrRelease(ball, this, mainTrack, nextTrack);
    }

    @Override
    public Deque<Integer> getTrack() {
        return this.track;
    }

    public Object[] fillTrack(int capacity, Track track) {
        for (int i = 1; i < capacity + 1; i++) {
            track.getTrack().add(i);
        }
        return track.getTrack().toArray();
    }

    public boolean checkOrder() {
        return track.size() == capacity
                && Arrays.equals(track.toArray(), defaultValue);
    }
}
