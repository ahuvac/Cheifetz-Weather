package cheifetz.openweathermap;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

public class OpenWeatherMapFeed {

    Main main;
    String name;
    long dt;

    static class Main {
        double temp;
    }


    public Date getTime() {
        return new Date(dt * 1000);
    }

}


