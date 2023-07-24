package com.game.nextstation.nextstation.views;

import com.game.nextstation.nextstation.controllers.GameController;
import com.game.nextstation.nextstation.controllers.StationController;
import com.game.nextstation.nextstation.models.Game;
import com.game.nextstation.nextstation.models.Localisation;
import com.game.nextstation.nextstation.models.Station;
import com.game.nextstation.nextstation.tools.Constants;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameView {
    private GameController gameController;
    private StationController stationController;
    private Scene scene;
    private SideView sideView;
    private AnchorPane mainView = new AnchorPane();
    private Pane gameView = new Pane();

    private Circle[] circles = new Circle[Constants.STATION_NUMBER];


    public GameView(GameController gameController, StationController stationController) {
        this.stationController = stationController;
        this.gameController = gameController;

        this.sideView = new SideView(gameController);

        this.mainView.setStyle("-fx-background-color: #000000;");

        this.scene = new Scene(this.mainView, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        this.mainView.getChildren().add(this.sideView.getPane());
        AnchorPane.setRightAnchor(this.sideView.getPane(), 0.0);

        this.gameView.setPrefHeight(Constants.WINDOW_HEIGHT);
        this.gameView.setStyle("-fx-background-color: #ffffff;");
        this.gameView.setPrefWidth(Constants.WINDOW_WIDTH - Constants.SCORE_VIEW_WIDTH);
        mainView.getChildren().add(this.gameView);
        AnchorPane.setLeftAnchor(this.gameView, 0.0);

        for(int i = 0; i < Constants.STATION_NUMBER; i++) {
            circles[i] = new Circle();
        }

        drawSectors();
        drawConnections();
        drawStations();
    }

    private List<Node> createCrown(double x, double y, double r, double size, int n) {
        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            double angle = 2 * Math.PI * i / n;

            // Calculate the position of the triangle
            double triangleX = x + r * Math.cos(angle);
            double triangleY = y + r * Math.sin(angle);

            // Create the triangle
            Polygon triangle = createTriangle(triangleX, triangleY, size);

            // Rotate the triangle to point outwards
            triangle.setRotate(Math.toDegrees(angle) + 90);  // The +90 is to start from the top

            nodes.add(triangle);
        }

        return nodes;
    }

    private Polygon createTriangle(double x, double y, double size) {
        Polygon triangle = new Polygon();

        // Calculate the vertices of the triangle
        double[] points = new double[]{
                x, y - size / 2,  // Top vertex
                x - size / 2, y + size / 2,  // Bottom left vertex
                x + size / 2, y + size / 2   // Bottom right vertex
        };

        // Add each point to the polygon individually
        for (int i = 0; i < points.length; i += 2) {
            triangle.getPoints().add(points[i]);
            triangle.getPoints().add(points[i + 1]);
        }

        // Style the triangle
        triangle.setFill(Color.BLACK);
        triangle.setStroke(Color.BLACK);
        triangle.setStrokeWidth(0.5);

        return triangle;
    }

    public void drawSectors() {
        // Top left sector
        drawLine(0.5, -0.5, 0.5, 0.5);
        drawLine(0.5, 0.5, -0.5, 0.5);

        // Top right sector
        drawLine(8.5, 0.5, 9.45, 0.5);
        drawLine(8.5, -0.5, 8.5, 0.5);

        // Bottom left sector
        drawLine(0.5, 9.5, 0.5, 8.5);
        drawLine(0.5, 8.5, -0.5, 8.5);

        // Bottom right sector
        drawLine(8.5, 8.5, 9.45, 8.5);
        drawLine(8.5, 9.5, 8.5, 8.5);

        // First vertical line
        drawLine(2.5, -0.5, 2.5, 9.5);

        // Second vertical line
        drawLine(6.5, -0.5, 6.5, 9.5);

        // First horizontal line
        drawLine(-0.5, 2.5, 9.45, 2.5);

        // Second horizontal line
        drawLine(-0.5, 6.5, 9.45, 6.5);
    }

    private void drawConnections() {
        HashMap<String, Line> existingConnections = new HashMap<>();

        for (int i = 0; i < this.gameController.getStations().length; i++) {
            for (int j = i + 1; j < this.gameController.getStations().length; j++) {
                // Get the locations of the two stations
                Localisation loc1 = this.gameController.getStation(i).getLocalisation();
                Localisation loc2 = this.gameController.getStation(j).getLocalisation();

                // Calculate differences in x and y
                double xDiff = Math.abs(loc1.getX() - loc2.getX());
                double yDiff = Math.abs(loc1.getY() - loc2.getY());

                // Check for perfect horizontal, vertical or diagonal alignment
                if (xDiff == 0.0 || // vertical
                        yDiff == 0.0 || // horizontal
                        xDiff == yDiff) { // diagonal

                    // Ensure the key is always the same, regardless of the order in which we find the stations
                    String stationsKey = Math.min(i, j) + "-" + Math.max(i, j);

                    // Check if a line already exists between these stations
                    if (!existingConnections.containsKey(stationsKey)) {
                        Line line = drawConnectionBetweenTwoStation(i, j);
                        existingConnections.put(stationsKey, line);
                    }
                }
            }
        }
    }

    private Line drawConnectionBetweenTwoStation(Integer x, Integer y) {
        Station a = gameController.getStation(x);
        Station b = gameController.getStation(y);
        // Get the coordinates of the stations
        Localisation aLoc = a.getLocalisation();
        Localisation bLoc = b.getLocalisation();

        // Calculate the center of the stations
        double aX = aLoc.getX();
        double aY = aLoc.getY();
        double bX = bLoc.getX();
        double bY = bLoc.getY();

        double xPos1 = aX * ((double) (Constants.WINDOW_WIDTH - Constants.SCORE_VIEW_WIDTH) / Constants.UNIT) + Constants.GAME_MARGIN;
        double yPos1 = aY * ((double) (Constants.WINDOW_HEIGHT) / Constants.UNIT) + Constants.GAME_MARGIN;
        double xPos2 = bX * ((double) (Constants.WINDOW_WIDTH - Constants.SCORE_VIEW_WIDTH) / Constants.UNIT) + Constants.GAME_MARGIN;
        double yPos2 = bY * ((double) (Constants.WINDOW_HEIGHT) / Constants.UNIT) + Constants.GAME_MARGIN;

        Line line = new Line();
        line.setStartX(xPos1);
        line.setStartY(yPos1);
        line.setEndX(xPos2);
        line.setEndY(yPos2);
        line.setSmooth(true);

        line.setStrokeWidth(0.1);
        line.setStroke(Color.GRAY);

        gameView.getChildren().add(line);

        return line;
    }
    private void drawLine(double x1, double y1, double x2, double y2) {
        // Convert from 10x10 grid to window coordinates
        double xPos1 = x1 * ((double) (Constants.WINDOW_WIDTH - Constants.SCORE_VIEW_WIDTH) / Constants.UNIT) + Constants.GAME_MARGIN;
        double yPos1 = y1 * ((double) (Constants.WINDOW_HEIGHT) / Constants.UNIT) + Constants.GAME_MARGIN;
        double xPos2 = x2 * ((double) (Constants.WINDOW_WIDTH - Constants.SCORE_VIEW_WIDTH) / Constants.UNIT) + Constants.GAME_MARGIN;
        double yPos2 = y2 * ((double) (Constants.WINDOW_HEIGHT) / Constants.UNIT) + Constants.GAME_MARGIN;

        // Create the line
        Line line = new Line();
        line.setStartX(xPos1);
        line.setStartY(yPos1);
        line.setEndX(xPos2);
        line.setEndY(yPos2);

        // Set the line's properties
        line.setStroke(Color.YELLOW);
        line.setStrokeWidth(5);

        // Add the line to the game view
        this.gameView.getChildren().add(line);
    }

    private void shineStation(Station station) {
        Circle stationCircle = findStationCircle(station);
        if (stationCircle == null) {
            return;
        }

        Timeline timeline = new Timeline();

        Duration duration = Duration.millis(1000);  // 1 seconde

        KeyFrame grow = new KeyFrame(duration.divide(2),
                new KeyValue(stationCircle.radiusProperty(), stationCircle.getRadius() * 1.3),
                new KeyValue(stationCircle.strokeWidthProperty(), stationCircle.getStrokeWidth() * 10.5)
        );

        KeyFrame shrink = new KeyFrame(duration,
                new KeyValue(stationCircle.radiusProperty(), stationCircle.getRadius()),
                new KeyValue(stationCircle.strokeWidthProperty(), stationCircle.getStrokeWidth())
        );

        timeline.getKeyFrames().addAll(grow, shrink);

        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();
    }

    private Circle findStationCircle(Station station) {
        return this.circles[station.getId()];
    }


    public void drawStation(Station station) {
        this.circles[station.getId()].setRadius(Constants.STATION_WIDTH);

        double xPos = station.getLocalisation().getX() * ((double) (Constants.WINDOW_WIDTH - Constants.SCORE_VIEW_WIDTH) / Constants.UNIT) + Constants.GAME_MARGIN;
        double yPos = station.getLocalisation().getY() * ((double) (Constants.WINDOW_HEIGHT) / Constants.UNIT) + Constants.GAME_MARGIN;

        this.circles[station.getId()].setLayoutX(xPos);
        this.circles[station.getId()].setLayoutY(yPos);
        this.circles[station.getId()].setFill(Color.WHITE);
        this.circles[station.getId()].setStroke(Color.BLACK);
        this.circles[station.getId()].setStrokeWidth(0.5);

        if(station.getIsShining()) {
            shineStation(station);

            List<Station> stations = this.stationController.computeAccessibleStations(station);
            for (Station s : stations) {
                shineStation(s);
            }
        }

        if(station.getIsDepartStation()) {
            switch (station.getColor()) {
                case "blue" -> this.circles[station.getId()].setFill(Color.BLUE);
                case "red" -> this.circles[station.getId()].setFill(Color.RED);
                case "green" -> this.circles[station.getId()].setFill(Color.GREEN);
                case "purple" -> this.circles[station.getId()].setFill(Color.PURPLE);
            }
        }

        if(station.getIsTourist()) {
            if(station.getIsTourist()) {
                double crownRadius = Constants.STATION_WIDTH;  // Add some distance from the circle

                // Define the number of triangles to draw around the circle
                int numTriangles = 12;  // Adjust this number as needed

                // Create the crown of triangles
                List<Node> crown = createCrown(xPos, yPos, crownRadius, 15, numTriangles);  // Adjust size as needed

                // Add each triangle in the crown to the game view
                for (Node triangle : crown) {
                    this.gameView.getChildren().add(triangle);
                }
            }
        }

        this.gameView.getChildren().add(this.circles[station.getId()]);

        // If station.symbol is not null, draw it
        if(station.getSymbolType() != null) {
            switch (station.getSymbolType()) {
                case CIRCLE -> {
                    Circle symbol = new Circle();
                    symbol.setRadius(Constants.STATION_SYMBOL_WIDTH);
                    symbol.setLayoutX(xPos);
                    symbol.setLayoutY(yPos);
                    symbol.setStrokeWidth(2);
                    symbol.setStroke(Color.BLACK);
                    symbol.setFill(Color.WHITE);
                    this.gameView.getChildren().add(symbol);
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
                    this.gameView.getChildren().add(square);
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
                    this.gameView.getChildren().add(triangle);
                }
                case PENTAGON -> {
                    double radius = Constants.STATION_SYMBOL_WIDTH;
                    int numSides = 5; // Pentagone
                    double angleStep = 2 * Math.PI / numSides;

                    // Ajustement de -90 degrés (en radians) pour que la base soit horizontale
                    double angleAdjust = -Math.PI / 2;

                    Polygon pentagon = new Polygon();
                    for (int i = 0; i < numSides; i++) {
                        double angle1 = i * angleStep + angleAdjust;
                        pentagon.getPoints().add(xPos + radius * Math.cos(angle1));
                        pentagon.getPoints().add(yPos + radius * Math.sin(angle1));
                    }
                    pentagon.setStrokeWidth(2);
                    pentagon.setStroke(Color.BLACK);
                    pentagon.setFill(Color.WHITE);

                    // Ajouter toutes les lignes à la vue
                    this.gameView.getChildren().add(pentagon);
                }
                case JOKER -> {
                    // We will here put a ?
                    Text text = new Text("?");
                    text.setFont(Font.font("Verdana", FontWeight.BOLD, Constants.STATION_SYMBOL_WIDTH * 2));
                    text.setFill(Color.BLACK);

                    // Add the text to the game view to compute its width and height
                    this.gameView.getChildren().add(text);

                    // Calculate the width and height of the text
                    double textWidth = text.getLayoutBounds().getWidth();
                    double textHeight = text.getLayoutBounds().getHeight();

                    // Remove the text from the game view
                    this.gameView.getChildren().remove(text);

                    // Adjust the position of the text
                    text.setX(xPos - textWidth / 2);
                    text.setY(yPos + textHeight / 4);  // Dividing by 4 instead of 2 because text is anchored by baseline

                    // Add the text again to the game view
                    this.gameView.getChildren().add(text);
                }
            }

    }
    }

    public void drawStations() {
        for (Station station : this.gameController.getStations()) {
            this.drawStation(station);
        }
    }

    public Circle getCircle(int id) {
        return this.circles[id];
    }

    public Scene getScene() {
        return this.scene;
    }

}
