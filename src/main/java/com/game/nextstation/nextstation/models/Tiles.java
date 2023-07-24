package com.game.nextstation.nextstation.models;

public class Tiles {
    private Symbol.Type type;
    private boolean isRed;
    private boolean isUsed;

    public Tiles(Symbol.Type type) {
        this.type = type;
        this.isRed = false;
        this.isUsed = false;
    }

    public Tiles(Symbol.Type type, boolean isRed) {
        this.type = type;
        this.isRed = true;
        this.isUsed = false;
    }

    public Symbol.Type getType() {
        return this.type;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public boolean getIsUsed() {
        return this.isUsed;
    }

    public boolean getIsRed() {
        return this.isRed;
    }
}
