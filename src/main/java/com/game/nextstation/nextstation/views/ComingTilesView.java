package com.game.nextstation.nextstation.views;

import com.game.nextstation.nextstation.controllers.GameController;
import com.game.nextstation.nextstation.models.Tiles;
import com.game.nextstation.nextstation.tools.Constants;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ComingTilesView {

    private Pane pane;
    private GameController gameController;

    private VBox tiles;

    private Button passButton;
    private Text playOrPass;
    private HBox passOrPlayPane;

    public ComingTilesView(GameController gameController) {
        this.gameController = gameController;
        this.pane = new Pane();
        this.pane.setPrefHeight(Constants.COMMINGTILES_VIEW_HEIGHT);
        this.pane.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px;");

        this.tiles = new VBox();
        this.tiles.setSpacing(5);
        this.tiles.setPadding(new Insets(10, 10, 10, 10));

        this.pane.getChildren().add(this.tiles);

        this.passOrPlayPane = new HBox();
        this.passOrPlayPane.setSpacing(5);
        this.passOrPlayPane.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 2px; --fx-padding: 10px;");
        this.passOrPlayPane.setLayoutX(Constants.COMING_TILES_SIZE + 40);
        this.passOrPlayPane.setLayoutY(10);

        this.passButton = new Button("Pass");
        this.passButton.setOnAction(e -> {
            System.out.println("UWU");
        });
        this.passButton.setStyle("-fx-background-color: #d23a3a; -fx-text-fill: #ffffff; -fx-border-color: #000000; -fx-border-width: 1px;");
        this.playOrPass = new Text("Play or");

        this.pane.getChildren().add(this.passOrPlayPane);
        this.passOrPlayPane.getChildren().add(this.playOrPass);
        this.passOrPlayPane.getChildren().add(this.passButton);

        drawTiles();
    }

    private void drawTiles() {
        for(int i = 0; i < 10; i++) {
            Pane pane = new Pane();
            pane.setPrefHeight(Constants.COMING_TILES_SIZE);
            pane.setPrefWidth(Constants.COMING_TILES_SIZE);

            Tiles tile = gameController.getTile(i);
            if(tile.getIsRed()) {
                pane.setStyle("-fx-background-color: #d23a3a; -fx-border-color: #000000; -fx-border-width: 2px;");
            } else {
                pane.setStyle("-fx-background-color: #3569b4; -fx-border-color: #000000; -fx-border-width: 2px;");
            }




            pane.setLayoutY(i * Constants.COMING_TILES_SIZE + 10);

            double xPos = pane.getLayoutX() + (double) (Constants.COMING_TILES_SIZE / 2) + 10;
            double yPos = pane.getLayoutY() + (double) (Constants.COMING_TILES_SIZE / 2) + (i * 5);

            switch (tile.getType()) {
                case CIRCLE -> {
                    Circle symbol = new Circle();
                    symbol.setRadius(Constants.STATION_SYMBOL_WIDTH);
                    symbol.setLayoutX(xPos);
                    symbol.setLayoutY(yPos);
                    symbol.setStrokeWidth(2);
                    symbol.setStroke(Color.BLACK);
                    symbol.setFill(Color.WHITE);
                    this.pane.getChildren().add(symbol);
                }
                case SQUARE -> {
                    Rectangle square = new Rectangle();
                    square.setX(xPos - Constants.STATION_SYMBOL_WIDTH);
                    square.setY(yPos - Constants.STATION_SYMBOL_WIDTH);
                    square.setWidth(2 * Constants.STATION_SYMBOL_WIDTH);
                    square.setHeight(2 * Constants.STATION_SYMBOL_WIDTH);
                    square.setStrokeWidth(2);
                    square.setStroke(Color.BLACK);
                    square.setFill(Color.WHITE);
                    this.pane.getChildren().add(square);
                }
                case TRIANGLE -> {
                    Polygon triangle = new Polygon();
                    triangle.getPoints().addAll(new Double[]{
                            xPos - Constants.STATION_SYMBOL_WIDTH, yPos + Constants.STATION_SYMBOL_WIDTH,
                            xPos, yPos - Constants.STATION_SYMBOL_WIDTH,
                            xPos + Constants.STATION_SYMBOL_WIDTH, yPos + Constants.STATION_SYMBOL_WIDTH
                    });
                    triangle.setStrokeWidth(2);
                    triangle.setStroke(Color.BLACK);
                    triangle.setFill(Color.WHITE);
                    this.pane.getChildren().add(triangle);
                }
                case PENTAGON -> {
                    double radius = Constants.STATION_SYMBOL_WIDTH;
                    int numSides = 5; // Pentagone
                    double angleStep = 2 * Math.PI / numSides;

                    // Ajustement de -90 degrés (en radians) pour que la base soit horizontale
                    double angleAdjust = -Math.PI / 2;

                    Polygon pentagon = new Polygon();
                    for (int j = 0; j < numSides; j++) {
                        double angle1 = j * angleStep + angleAdjust;
                        pentagon.getPoints().add(xPos + radius * Math.cos(angle1));
                        pentagon.getPoints().add(yPos + radius * Math.sin(angle1));
                    }
                    pentagon.setStrokeWidth(2);
                    pentagon.setStroke(Color.BLACK);
                    pentagon.setFill(Color.WHITE);

                    // Ajouter toutes les lignes à la vue
                    this.pane.getChildren().add(pentagon);
                }
                case JOKER -> {
                    // We will here put a ?
                    Text text = new Text("?");
                    text.setFont(Font.font("Verdana", FontWeight.BOLD, Constants.STATION_SYMBOL_WIDTH * 2));
                    text.setFill(Color.BLACK);

                    // Add the text to the game view to compute its width and height
                    this.pane.getChildren().add(text);

                    // Calculate the width and height of the text
                    double textWidth = text.getLayoutBounds().getWidth();
                    double textHeight = text.getLayoutBounds().getHeight();

                    // Remove the text from the game view
                    this.pane.getChildren().remove(text);

                    // Adjust the position of the text
                    text.setX(xPos - textWidth / 2);
                    text.setY(yPos + textHeight / 4);  // Dividing by 4 instead of 2 because text is anchored by baseline

                    // Add the text again to the game view
                    this.pane.getChildren().add(text);
                }
            }

            if(tile.getIsUsed()) {
                // Put an X on the tile using two lines
                Line line1 = new Line();
                line1.setStartX(0);
                line1.setStartY(0);
                line1.setEndX(Constants.COMING_TILES_SIZE);
                line1.setEndY(Constants.COMING_TILES_SIZE);
                line1.setStrokeWidth(2);
                line1.setStroke(Color.BLACK);
                pane.getChildren().add(line1);

                Line line2 = new Line();
                line2.setStartX(Constants.COMING_TILES_SIZE);
                line2.setStartY(0);
                line2.setEndX(0);
                line2.setEndY(Constants.COMING_TILES_SIZE);
                line2.setStrokeWidth(2);
                line2.setStroke(Color.BLACK);
                pane.getChildren().add(line2);
            }

            this.tiles.getChildren().add(pane);
        }
    }
    public Pane getPane() {
        return pane;
    }
}
