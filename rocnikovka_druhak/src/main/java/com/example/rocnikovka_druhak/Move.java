package com.example.rocnikovka_druhak;

public class Move {
    private Square start;
    private Square end;

    public Move(Square start, Square end) {
        this.start = start;
        this.end = end;
    }

    public Square getStart() {
        return start;
    }

    public Square getEnd() {
        return end;
    }

    public boolean causesCheck() {
        // Check if this move would cause the player to be in check
        return false;
    }
}
