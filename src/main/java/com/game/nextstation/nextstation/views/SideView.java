package com.game.nextstation.nextstation.views;

import com.game.nextstation.nextstation.controllers.GameController;
import com.game.nextstation.nextstation.tools.Constants;
import javafx.scene.layout.VBox;

public class SideView {
    private GameController gameController;
    private VBox vBox;
    private ScoreView scoreView;
    private ComingTilesView comingTilesView;

    public SideView(GameController gameController) {
        this.gameController = gameController;
        this.vBox = new VBox();

        this.vBox.setPrefWidth(Constants.SCORE_VIEW_WIDTH);
        this.vBox.setPrefHeight(Constants.WINDOW_HEIGHT);

        this.scoreView = new ScoreView(gameController);
        this.comingTilesView = new ComingTilesView(gameController);

        this.vBox.getChildren().add(this.scoreView.getPane());
        this.vBox.getChildren().add(this.comingTilesView.getPane());
    }

    public VBox getPane() {
        return this.vBox;
    }
}
