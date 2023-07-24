package com.game.nextstation.nextstation.controllers;

import com.game.nextstation.nextstation.models.*;
import com.game.nextstation.nextstation.views.GameView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GameController {

    private ArrayList<Tiles> comingTiles;
    private GameView gameView;
    private Game game;
    private StationController stationController;
    private PlayerController playerController;

    public GameController(Game game) {
        this.game = game;

        this.comingTiles = new ArrayList<Tiles>(10);
        this.initTiles();

        this.stationController = new StationController();
        this.playerController = new PlayerController();


        this.gameView = new GameView(this, this.stationController);
        this.stationController.setGameView(this.gameView);
        this.runGame();
    }

    public GameView getGameView() {
        return this.gameView;
    }

    private void runGame() {

    }

    private void initTiles() {
        comingTiles.add(0, new Tiles(Symbol.Type.SQUARE));
        comingTiles.add(1, new Tiles(Symbol.Type.CIRCLE));
        comingTiles.add(2, new Tiles(Symbol.Type.TRIANGLE));
        comingTiles.add(3, new Tiles(Symbol.Type.PENTAGON));
        comingTiles.add(4, new Tiles(Symbol.Type.JOKER));
        comingTiles.add(5, new Tiles(Symbol.Type.SQUARE, true));
        comingTiles.add(6, new Tiles(Symbol.Type.CIRCLE, true));
        comingTiles.add(7, new Tiles(Symbol.Type.TRIANGLE, true));
        comingTiles.add(8, new Tiles(Symbol.Type.PENTAGON, true));
        comingTiles.add(9, new Tiles(Symbol.Type.JOKER, true));
    }

    public ArrayList<Tiles> getComingTiles() {
        return comingTiles;
    }

    public Tiles getTile(int i) {
        return this.comingTiles.get(i);
    }

    public Tiles getRandomTile() {
        if (comingTiles.size() > 0) {
            int random = (int) (Math.random() * 10);
            Tiles tile = comingTiles.get(random);

            while (tile.getIsUsed()) {
                random = (int) (Math.random() * 10);
                tile = comingTiles.get(random);
            }

            comingTiles.get(random).setIsUsed(true);
            return tile;
        }
        return null;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }
    public Station[] getStations() {
        return this.stationController.getStations();
    }

    public Station getStation(int i) {
        return this.stationController.getStation(i);
    }

    public Player getPlayer(int i) {
        return this.playerController.getPlayer(i);
    }


}
