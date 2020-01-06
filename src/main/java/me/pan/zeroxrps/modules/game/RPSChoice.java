package me.pan.zeroxrps.modules.game;

import java.util.Random;

public enum RPSChoice {
    ROCK, PAPER, SCISSOR;

    public static RPSChoice getRandomChoice() {
        return values()[new Random().nextInt(values().length)];
    }
}
