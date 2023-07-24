package com.game.nextstation.nextstation.controllers;

import com.game.nextstation.nextstation.models.Player;

public class PlayerController {
    private int nbPlayers;
    private Player[] players;

    public PlayerController() {
        this.nbPlayers = 1;

        this.players = new Player[this.nbPlayers];

        for (int i = 0; i < this.nbPlayers; i++) {
            this.players[i] = new Player(i);
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getPlayer(int i) {
        return this.players[i];
    }
}
