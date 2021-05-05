package cheifetz.openweathermap;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpenWeatherMapApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        OpenWeatherMapService service = new OpenWeatherMapServiceFactory().newInstance();
        OpenWeatherMapController controller = new OpenWeatherMapController(service);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/weather_application.fxml"));
        loader.setController(controller);

        Parent root = loader.load();

        Scene scene = new Scene(root, 300, 620);

        stage.setTitle("5 Day Weather");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}


