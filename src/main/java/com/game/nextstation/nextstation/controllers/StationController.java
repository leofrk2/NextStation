package com.game.nextstation.nextstation.controllers;

import com.game.nextstation.nextstation.models.Localisation;
import com.game.nextstation.nextstation.models.Station;
import com.game.nextstation.nextstation.models.Symbol;
import com.game.nextstation.nextstation.tools.Constants;
import com.game.nextstation.nextstation.views.GameView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StationController {

    private Station[] stations;
    private List<Station> departStations;
    private GameView gameView;

    public StationController() {
        this.stations = new Station[Constants.STATION_NUMBER];

        for (int i = 0; i < Constants.STATION_NUMBER; i++) {
            this.stations[i] = new Station(0, 0);
            this.stations[i].setId(i);
        }

        this.departStations = new ArrayList<>(4);

        this.initStations();
        test();
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void test() {
        Station station = getRandomDepartStation();
        station.setIsShining(true);
        this.gameView.getCircle(station.getId()).setOnMouseClicked(mouseEvent -> {
            System.out.println("Station clicked");
            List<Station> stations = computeAccessibleStations(station);

            for (Station stationTmp : stations) {
                stationTmp.setIsShining(true);
            }

        });

    }
    public Station[] getStations() {
        return stations;
    }

    public Station getRandomDepartStation() {
        int random = (int) (Math.random() * 4);
        Station departStation = this.departStations.get(random);

        this.departStations.remove(random);

        return departStation;
    }

    public Station getStation(int id) {
        return this.stations[id];
    }

    private void initStations() {
        this.stations[0].setLocalisation(new Localisation(0, 0), Symbol.Type.PENTAGON);
        this.stations[1].setLocalisation(new Localisation(1, 0), Symbol.Type.TRIANGLE);
        this.stations[2].setLocalisation(new Localisation(2, 0), Symbol.Type.SQUARE);
        this.stations[3].setLocalisation(new Localisation(4, 0), Symbol.Type.TRIANGLE);
        this.stations[4].setLocalisation(new Localisation(5, 0), Symbol.Type.CIRCLE);
        this.stations[5].setLocalisation(new Localisation(7, 0), Symbol.Type.TRIANGLE);
        this.stations[6].setLocalisation(new Localisation(9, 0), Symbol.Type.CIRCLE);

        this.stations[7].setLocalisation(new Localisation(1, 1), Symbol.Type.PENTAGON);
        this.stations[8].setLocalisation(new Localisation(3, 1), Symbol.Type.SQUARE);
        this.stations[9].setLocalisation(new Localisation(6, 1), Symbol.Type.PENTAGON);
        this.stations[9].setIsTourist(true);
        this.stations[10].setLocalisation(new Localisation(8, 1), Symbol.Type.SQUARE);
        this.stations[11].setLocalisation(new Localisation(9, 1), Symbol.Type.PENTAGON);

        this.stations[12].setLocalisation(new Localisation(0, 2), Symbol.Type.CIRCLE);
        this.stations[13].setLocalisation(new Localisation(3, 2), Symbol.Type.TRIANGLE);
        this.stations[13].setDepartStation("green");
        this.departStations.add(this.stations[13]);
        this.stations[14].setLocalisation(new Localisation(6, 2), Symbol.Type.SQUARE);
        this.stations[15].setLocalisation(new Localisation(9, 2), Symbol.Type.TRIANGLE);

        this.stations[16].setLocalisation(new Localisation(0, 3), Symbol.Type.SQUARE);
        this.stations[16].setIsTourist(true);
        this.stations[17].setLocalisation(new Localisation(2, 3), Symbol.Type.PENTAGON);
        this.stations[19].setLocalisation(new Localisation(4, 3), Symbol.Type.TRIANGLE);
        this.stations[20].setLocalisation(new Localisation(5, 3), Symbol.Type.JOKER);
        this.stations[20].setIsTourist(true);
        this.stations[21].setLocalisation(new Localisation(6, 3), Symbol.Type.CIRCLE);
        this.stations[22].setLocalisation(new Localisation(7, 3), Symbol.Type.CIRCLE);
        this.stations[22].setDepartStation("red");
        this.departStations.add(this.stations[22]);
        this.stations[23].setLocalisation(new Localisation(9, 3), Symbol.Type.SQUARE);

        this.stations[24].setLocalisation(new Localisation(1, 4), Symbol.Type.TRIANGLE);
        this.stations[25].setLocalisation(new Localisation(2, 4), Symbol.Type.SQUARE);
        this.stations[26].setLocalisation(new Localisation(4, 4), Symbol.Type.PENTAGON);
        this.stations[27].setLocalisation(new Localisation(5, 4), Symbol.Type.SQUARE);
        this.stations[28].setLocalisation(new Localisation(8, 4), Symbol.Type.PENTAGON);

        this.stations[29].setLocalisation(new Localisation(0, 5), Symbol.Type.PENTAGON);
        this.stations[30].setLocalisation(new Localisation(2, 5), Symbol.Type.SQUARE);
        this.stations[30].setDepartStation("purple");
        this.departStations.add(this.stations[30]);
        this.stations[31].setLocalisation(new Localisation(4, 5), Symbol.Type.CIRCLE);
        this.stations[32].setLocalisation(new Localisation(7, 5), Symbol.Type.CIRCLE);

        this.stations[33].setLocalisation(new Localisation(3, 6), Symbol.Type.PENTAGON);
        this.stations[34].setLocalisation(new Localisation(4, 6), Symbol.Type.TRIANGLE);
        this.stations[35].setLocalisation(new Localisation(6, 6), Symbol.Type.SQUARE);
        this.stations[36].setLocalisation(new Localisation(7, 6), Symbol.Type.TRIANGLE);
        this.stations[37].setLocalisation(new Localisation(9, 6), Symbol.Type.TRIANGLE);
        this.stations[37].setIsTourist(true);

        this.stations[38].setLocalisation(new Localisation(0, 7), Symbol.Type.CIRCLE);
        this.stations[39].setLocalisation(new Localisation(2, 7), Symbol.Type.SQUARE);
        this.stations[40].setLocalisation(new Localisation(3, 7), Symbol.Type.CIRCLE);
        this.stations[41].setLocalisation(new Localisation(5, 7), Symbol.Type.PENTAGON);
        this.stations[41].setDepartStation("blue");
        this.departStations.add(this.stations[41]);
        this.stations[42].setLocalisation(new Localisation(8, 7), Symbol.Type.CIRCLE);
        this.stations[43].setLocalisation(new Localisation(9, 7), Symbol.Type.PENTAGON);

        this.stations[44].setLocalisation(new Localisation(1, 8), Symbol.Type.CIRCLE);
        this.stations[45].setLocalisation(new Localisation(6, 8), Symbol.Type.PENTAGON);
        this.stations[46].setLocalisation(new Localisation(8, 8), Symbol.Type.TRIANGLE);

        this.stations[47].setLocalisation(new Localisation(0, 9), Symbol.Type.TRIANGLE);
        this.stations[48].setLocalisation(new Localisation(1, 9), Symbol.Type.SQUARE);
        this.stations[49].setLocalisation(new Localisation(3, 9), Symbol.Type.PENTAGON);
        this.stations[50].setLocalisation(new Localisation(4, 9), Symbol.Type.CIRCLE);
        this.stations[50].setIsTourist(true);
        this.stations[51].setLocalisation(new Localisation(5, 9), Symbol.Type.TRIANGLE);
        this.stations[52].setLocalisation(new Localisation(7, 9), Symbol.Type.CIRCLE);
        this.stations[18].setLocalisation(new Localisation(9, 9), Symbol.Type.SQUARE);
    }

    public List<Station> computeAccessibleStations(Station station) {
        List<Station> accessibleStations = new ArrayList<>();

        List<Station> allStations = Arrays.asList(this.stations);

        for (Station otherStation : allStations) {
            if (otherStation == station) {
                // Ignore the station itself
                continue;
            }

            // Calculate the differences in x and y coordinates
            double dx = otherStation.getLocalisation().getX() - station.getLocalisation().getX();
            double dy = otherStation.getLocalisation().getY() - station.getLocalisation().getY();

            // Check if the stations are horizontally, vertically, or diagonally aligned
            if (dx == 0 || dy == 0 || Math.abs(dx) == Math.abs(dy)) {
                // Now check if there's no station on the way
                if (!isStationInTheWay(station, otherStation, allStations)) {
                    accessibleStations.add(otherStation);
                }
            }
        }

        return accessibleStations;
    }

    private boolean isStationInTheWay(Station station1, Station station2, List<Station> allStations) {
        double x1 = station1.getLocalisation().getX();
        double y1 = station1.getLocalisation().getY();
        double x2 = station2.getLocalisation().getX();
        double y2 = station2.getLocalisation().getY();

        for (Station otherStation : allStations) {
            if (otherStation == station1 || otherStation == station2) {
                // Ignore the two stations we are checking
                continue;
            }

            double x0 = otherStation.getLocalisation().getX();
            double y0 = otherStation.getLocalisation().getY();

            // Check if the other station is on the line between the two stations
            // Make sure to only check the segment of line, not the extended line
            if (Math.min(x1, x2) <= x0 && x0 <= Math.max(x1, x2) && Math.min(y1, y2) <= y0 && y0 <= Math.max(y1, y2)) {
                // The other station is between the two stations
                // Now check if it's on the exact line or only the extended line
                if (x1 == x2) {
                    // The stations are vertically aligned
                    return true;
                } else {
                    // Check the slope
                    double m = (y2 - y1) / (x2 - x1);
                    if (Math.abs(y0 - m * x0 - y1 + m * x1) < 1e-6) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
