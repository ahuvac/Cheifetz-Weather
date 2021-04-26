package cheifetz.openweathermap;

import java.util.List;

public class OpenWeatherMapFeed {

        Main main;

        public double getTemp() {
            return main.temp;
        }

    public static class Main {
        double temp;
    }


}