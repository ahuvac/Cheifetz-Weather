package cheifetz.openweathermap;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;

import java.util.List;

public class OpenWeatherMapController {

    @FXML
    List<Label> dayLabels;
    @FXML
    List<Label> tempLabels;
    @FXML
    List<ImageView> iconLabels;

    @FXML
    TextField loc;
    @FXML
    RadioButton cel;
    @FXML
    RadioButton far;

    private final char DEGREE = (char) 186;

    OpenWeatherMapService service;

    public OpenWeatherMapController(OpenWeatherMapService service) {
        this.service = service;
    }

    @FXML
    public void initialize() {
        ToggleGroup group = new ToggleGroup();
        cel.setToggleGroup(group);
        far.setToggleGroup(group);
        far.setSelected(true);
        doService();
    }

    public void doService() {
        String units = far.isSelected() ? "imperial" : "metric";
        String location = loc.getText();

        //OpenWeatherMapService service = new OpenWeatherMapServiceFactory().newInstance();
        Disposable disposable = service.getWeatherForecast(location, units)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.trampoline())
                .subscribe(this::onOpenWeathermapForecast, this::onError);
    }

    public void onOpenWeathermapForecast(OpenWeathermapForecast forecast) {
        Platform.runLater(() -> setTexts(forecast));

    }

    public void setTexts(OpenWeathermapForecast forecast) {
        for (int i = 0; i < dayLabels.size(); i++) {
            String fullForecast = forecast.getForcastFor(i).getDate().toString();
            dayLabels.get(i).setText(fullForecast.substring(0, fullForecast.indexOf(" ")));
            tempLabels.get(i).setText(Math.round(Double.parseDouble(forecast.getForcastFor(i).main.temp + "")) + "" + DEGREE);
            iconLabels.get(i).setImage(new Image(forecast.getForcastFor(i).weather.get(0).getIconUrl()));
            System.out.println(new Image(forecast.getForcastFor(i).weather.get(0).getIconUrl()));
            iconLabels.get(i).setFitHeight(50);
            iconLabels.get(i).setFitWidth(50);
        }
    }

    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    public void refresh(ActionEvent actionEvent) {
        doService();
    }

}

