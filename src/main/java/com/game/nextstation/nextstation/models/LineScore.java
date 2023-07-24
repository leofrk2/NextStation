package com.game.nextstation.nextstation.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class LineScore {


    private int lineNumber;
    private String lineColor;

    // Different scores values
    private IntegerProperty numberDistrictsProperty = new SimpleIntegerProperty(this, "numberDistricts", 0);

    private IntegerProperty maxInDistrictProperty = new SimpleIntegerProperty(this, "maxInDistrict", 0);

    private IntegerProperty riverCrossedProperty = new SimpleIntegerProperty(this, "riverCrossed", 0);

    private IntegerProperty totalLineScoreProperty = new SimpleIntegerProperty(this, "totalLineScore", 0);

    public LineScore(int lineNumber, String lineColor) {
        this.lineNumber = lineNumber;
        this.lineColor = lineColor;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getLineColor() {
        return lineColor;
    }

    public IntegerProperty numberDistrictsProperty() {
        return numberDistrictsProperty;
    }

    public IntegerProperty maxInDistrictProperty() {
        return maxInDistrictProperty;
    }

    public IntegerProperty riverCrossedProperty() {
        return riverCrossedProperty;
    }

    public IntegerProperty totalLineScoreProperty() {
        return totalLineScoreProperty;
    }



}
