package com.ballclock;

public interface Track {
    void setNextTrack(Track nextTrack);

//    void popToNext();

    boolean isEmpty();

    boolean isFull();

    void moveToNext(Integer ball);

    java.util.Deque<Integer> getTrack();

    @Override
    String toString();
}
