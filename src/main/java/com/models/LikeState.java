package com.models;

/**
 * Created by LihaiMac on 5/15/17.
 */
public enum LikeState {
    DISLIKE(-1), NONE(0) , LIKE(1);

    private final int id;

    LikeState(int id) {
        this.id = id;
    }

    public int getValue() {
        return id;
    }
}