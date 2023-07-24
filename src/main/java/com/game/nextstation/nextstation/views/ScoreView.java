package com.game.nextstation.nextstation.views;

import com.game.nextstation.nextstation.controllers.GameController;
import com.game.nextstation.nextstation.models.Game;
import com.game.nextstation.nextstation.models.Player;
import com.game.nextstation.nextstation.tools.Constants;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ScoreView {
    private GameController gameController;
    private Pane pane;

    private HBox linesHBox = new HBox();
    private VBox imagesVBox = new VBox();
    private List<VBox> lineBoxes = new ArrayList<>();
    private List<Label> numberDistrictsLabels = new ArrayList<>();
    private List<Label> maxInDistrictsLabels = new ArrayList<>();
    private List<Label> riverCrossedLabels = new ArrayList<>();
    private List<Label> totalLineScoreLabels = new ArrayList<>();

    public ScoreView(GameController gameController) {
        this.gameController = gameController;
        this.pane = new Pane();
        this.pane.setPrefHeight(Constants.SCORE_VIEW_HEIGHT);
        this.pane.setStyle("-fx-background-color: #FDEB17;");

        // Add the hbox to the pane
        this.pane.getChildren().add(this.linesHBox);

        Image maxInDistrictsImage = new Image(new File("/com/game/nextstation/nextstation/images/maxInDistricts.png").toURI().toString());
        ImageView maxInDistricts = new ImageView(maxInDistrictsImage);

        Image numberDistrictsImage = new Image(new File("/com/game/nextstation/nextstation/images/numberDistricts.png").toURI().toString());
        ImageView numberDistricts = new ImageView(numberDistrictsImage);

        Image riverCrossedImage = new Image(new File("/com/game/nextstation/nextstation/images/riverCrossed.png").toURI().toString());
        ImageView riverCrossed = new ImageView(riverCrossedImage);

        // Add the images to the vbox
        this.imagesVBox.getChildren().add(maxInDistricts);
        this.imagesVBox.getChildren().add(numberDistricts);
        this.imagesVBox.getChildren().add(riverCrossed);

        // Add the vbox to the pane
        this.linesHBox.getChildren().add(this.imagesVBox);

        Player player = gameController.getPlayer(0);

        String style = "-fx-font-size: 20px; -fx-padding: 5 5 5 5;";
        String styleBis = "-fx-padding: 0 0 0 5;";



        for(int i = 0; i < 4; i++) {
            VBox lineBox = new VBox();
            this.linesHBox.getChildren().add(lineBox);
            this.lineBoxes.add(lineBox);

            Label numberDistrictsLabel = new Label();
            numberDistrictsLabel.setStyle(style);
            numberDistrictsLabel.textProperty().bind(Bindings.convert(player.getLineScore(i).numberDistrictsProperty()));
            lineBox.getChildren().add(numberDistrictsLabel);
            this.numberDistrictsLabels.add(numberDistrictsLabel);

            Label labelMultiply = new Label("x");
            labelMultiply.setStyle(styleBis);
            lineBox.getChildren().add(labelMultiply);

            Label maxInDistrictsLabel = new Label();
            maxInDistrictsLabel.setStyle(style);
            maxInDistrictsLabel.textProperty().bind(Bindings.convert(player.getLineScore(i).maxInDistrictProperty()));
            lineBox.getChildren().add(maxInDistrictsLabel);
            this.maxInDistrictsLabels.add(maxInDistrictsLabel);

            Label labelPlus = new Label("+");
            labelPlus.setStyle(styleBis);
            lineBox.getChildren().add(labelPlus);

            Label riverCrossedLabel = new Label();
            riverCrossedLabel.setStyle(style);
            riverCrossedLabel.textProperty().bind(Bindings.convert(player.getLineScore(i).riverCrossedProperty()));
            lineBox.getChildren().add(riverCrossedLabel);
            this.riverCrossedLabels.add(riverCrossedLabel);

            Label labelEqual = new Label("=");
            labelEqual.setStyle(styleBis);
            lineBox.getChildren().add(labelEqual);

            Label totalLineScoreLabel = new Label();
            totalLineScoreLabel.setStyle(style);
            totalLineScoreLabel.textProperty().bind(Bindings.convert(player.getLineScore(i).totalLineScoreProperty()));
            lineBox.getChildren().add(totalLineScoreLabel);
            this.totalLineScoreLabels.add(totalLineScoreLabel);
        }

        // VBox for the connections count
        VBox connectionsCountVBox = new VBox();

        Label numerConnectionTwoLabel = new Label();
        numerConnectionTwoLabel.setStyle(style);
        numerConnectionTwoLabel.textProperty().bind(Bindings.convert(player.getNumberConnectionTwoProperty()));
        connectionsCountVBox.getChildren().add(numerConnectionTwoLabel);

        Label space = new Label(" ");
        space.setStyle(styleBis);
        connectionsCountVBox.getChildren().add(space);

        Label numerConnectionThreeLabel = new Label();
        numerConnectionThreeLabel.setStyle(style);
        numerConnectionThreeLabel.textProperty().bind(Bindings.convert(player.getNumberConnectionThreeProperty()));
        connectionsCountVBox.getChildren().add(numerConnectionThreeLabel);

        Label spaceBis = new Label(" ");
        spaceBis.setStyle(styleBis);
        connectionsCountVBox.getChildren().add(spaceBis);

        Label numerConnectionFourLabel = new Label();
        numerConnectionFourLabel.setStyle(style);
        numerConnectionFourLabel.textProperty().bind(Bindings.convert(player.getNumberConnectionFourProperty()));
        connectionsCountVBox.getChildren().add(numerConnectionFourLabel);

        this.linesHBox.getChildren().add(connectionsCountVBox);

        // VBox for the connections total
        VBox connectionsTotalVBox = new VBox();

        Label labelPlus = new Label("+");
        labelPlus.setStyle(styleBis);

        Label labelPlusBis = new Label("+");
        labelPlusBis.setStyle(styleBis);

        Label labelEqual = new Label("=");
        labelEqual.setStyle(styleBis);

        Label numberConnectionTwoTotalLabel = new Label();
        numberConnectionTwoTotalLabel.setStyle(style);
        numberConnectionTwoTotalLabel.textProperty().bind(Bindings.convert(player.getNumberConnectionTwoTotalProperty()));
        connectionsTotalVBox.getChildren().add(numberConnectionTwoTotalLabel);

        connectionsTotalVBox.getChildren().add(labelPlus);

        Label numberConnectionThreeTotalLabel = new Label();
        numberConnectionThreeTotalLabel.setStyle(style);
        numberConnectionThreeTotalLabel.textProperty().bind(Bindings.convert(player.getNumberConnectionThreeTotalProperty()));
        connectionsTotalVBox.getChildren().add(numberConnectionThreeTotalLabel);

        connectionsTotalVBox.getChildren().add(labelPlusBis);

        Label numberConnectionFourTotalLabel = new Label();
        numberConnectionFourTotalLabel.setStyle(style);
        numberConnectionFourTotalLabel.textProperty().bind(Bindings.convert(player.getNumberConnectionFourTotalProperty()));
        connectionsTotalVBox.getChildren().add(numberConnectionFourTotalLabel);

        connectionsTotalVBox.getChildren().add(labelEqual);

        Label numberConnectionTotalLabel = new Label();
        numberConnectionTotalLabel.setStyle(style);
        numberConnectionTotalLabel.textProperty().bind(Bindings.convert(player.getNumberConnectionTotalProperty()));
        connectionsTotalVBox.getChildren().add(numberConnectionTotalLabel);

        this.linesHBox.getChildren().add(connectionsTotalVBox);

    }

    public Pane getPane() {
        return pane;
    }
}
