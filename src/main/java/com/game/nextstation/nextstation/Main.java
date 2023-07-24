package com.game.nextstation.nextstation;

import com.game.nextstation.nextstation.controllers.GameController;
import com.game.nextstation.nextstation.controllers.StationController;
import com.game.nextstation.nextstation.models.Game;
import com.game.nextstation.nextstation.tools.Constants;
import com.game.nextstation.nextstation.views.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();

        GameController gameController = new GameController(game);
        GameView gameView = new GameView(gameController, new StationController());
        gameController.setGameView(gameView);

        primaryStage.setScene(gameView.getScene());

        primaryStage.setTitle(Constants.WINDOW_TITLE);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
