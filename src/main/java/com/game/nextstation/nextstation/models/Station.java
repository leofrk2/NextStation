package com.game.nextstation.nextstation.models;

public class Station {
    private Symbol.Type symbolType;

    private Localisation localisation;
    private Boolean isTourist;
    private Integer id;
    private boolean isDepartStation;
    private boolean isShining;
    private String color;
    public Station(Integer x, Integer y) {
        this.localisation = new Localisation(x, y);
        this.isTourist = false;
        this.isDepartStation = false;
        this.isShining = false;
    }
    public boolean getIsDepartStation() {
        return isDepartStation;
    }

    public void setIsShining(boolean isShining) {
        this.isShining = isShining;
    }

    public boolean getIsShining() {
        return isShining;
    }

    public String getColor() {
        return color;
    }
    public void setDepartStation(String color) {
        this.isDepartStation = true;
        this.color = color;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public void setIsTourist(Boolean isTourist) {
        this.isTourist = isTourist;
    }

    public Boolean getIsTourist() {
        return isTourist;
    }

    public Symbol.Type getSymbolType() {
        return symbolType;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public void setLocalisation(Localisation localisation, Symbol.Type symbolType) {
        this.localisation = localisation;
        this.symbolType = symbolType;
    }

    public void setSymbolType(Symbol.Type symbolType) {
        this.symbolType = symbolType;
    }
}
