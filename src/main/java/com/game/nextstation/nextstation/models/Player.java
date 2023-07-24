package com.game.nextstation.nextstation.models;

import com.game.nextstation.nextstation.tools.Constants;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Player {
    private ArrayList<String> colors = initColors();
    private String name;
    private int id;
    private boolean hasPlayerPlay;
    private LineScore[] lineScores;

    // Different scores values
    private IntegerProperty totalScoreProperty = new SimpleIntegerProperty(this, "totalScore", 0);
    private IntegerProperty numberConnectionTwoProperty = new SimpleIntegerProperty(this, "numberConnectionTwo", 0);
    private IntegerProperty numberConnectionThreeProperty = new SimpleIntegerProperty(this, "numberConnectionThree", 0);
    private IntegerProperty numberConnectionFourProperty = new SimpleIntegerProperty(this, "numberConnectionFour", 0);
    private IntegerProperty totalTouristScoreProperty = new SimpleIntegerProperty(this, "totalTouristScore", 0);


    private IntegerProperty numberConnectionTwoTotalProperty = new SimpleIntegerProperty(this, "numberConnectionTwoTotal", 0);
    private IntegerProperty numberConnectionThreeTotalProperty = new SimpleIntegerProperty(this, "numberConnectionThreeTotal", 0);

    private IntegerProperty numberConnectionFourTotalProperty = new SimpleIntegerProperty(this, "numberConnectionFourTotal", 0);
    private IntegerProperty numberConnectionTotalProperty = new SimpleIntegerProperty(this, "numberConnectionTotal", 0);
    private BooleanProperty bonus1Property = new SimpleBooleanProperty(this, "bonus1", false);
    private BooleanProperty bonus2Property = new SimpleBooleanProperty(this, "bonus2", false);

    public Player(int id) {
        this.name = "Player " + id;
        this.id = id;

        this.lineScores = new LineScore[4];

        this.hasPlayerPlay = false;

        initLines();
    }

    public void setHasPlayerPlay(boolean hasPlayerPlay) {
        this.hasPlayerPlay = hasPlayerPlay;
    }

    public boolean getHasPlayerPlay() {
        return this.hasPlayerPlay;
    }

    public LineScore getLineScore(int lineNumber) {
        return this.lineScores[lineNumber];
    }
    private void initLines() {
        for (int i = 0; i < 4; i++) {
            this.lineScores[i] = new LineScore(i, pickRandomColor());
        }
    }

    private ArrayList<String> initColors() {
        return new ArrayList<>(Arrays.asList(Constants.colors).subList(0, 4));
    }
    private String pickRandomColor() {
        Random rand = new Random();
        int index = rand.nextInt(this.colors.size());
        String color = this.colors.get(index);
        this.colors.remove(index);
        return color;
    }

    public IntegerProperty getTotalScoreProperty() {
        return totalScoreProperty;
    }

    public IntegerProperty getNumberConnectionTwoProperty() {
        return numberConnectionTwoProperty;
    }

    public IntegerProperty getNumberConnectionThreeProperty() {
        return numberConnectionThreeProperty;
    }


    public IntegerProperty getNumberConnectionFourProperty() {
        return numberConnectionFourProperty;
    }


    public IntegerProperty getTotalTouristScoreProperty() {
        return totalTouristScoreProperty;
    }


    public BooleanProperty getBonus1PropertyProperty() {
        return bonus1Property;
    }


    public BooleanProperty getBonus2PropertyProperty() {
        return bonus2Property;
    }



    public IntegerProperty getNumberConnectionThreeTotalProperty() {
        return numberConnectionThreeTotalProperty;
    }


    public IntegerProperty getNumberConnectionFourTotalProperty() {
        return numberConnectionFourTotalProperty;
    }

    public IntegerProperty getNumberConnectionTwoTotalProperty() {
        return numberConnectionTwoTotalProperty;
    }
    public IntegerProperty getNumberConnectionTotalProperty() {
        return numberConnectionTotalProperty;
    }

}
