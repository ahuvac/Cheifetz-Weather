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
    Label today;
    @FXML
    ImageView iconToday;
    @FXML
    Label tempToday;

    @FXML
    List<Label> dayLabels;
    @FXML
    List<Label> tempLabels;
    @FXML
    List<ImageView> iconLabels;

    @FXML
    TextField location;
    @FXML
    RadioButton cel;
    @FXML
    RadioButton far;

    private final char DEGREE = (char) 186;

    @FXML
    public void initialize() {
        ToggleGroup group = new ToggleGroup();
        far.setToggleGroup(group);
        cel.setToggleGroup(group);
        far.setSelected(true);
        doService();
    }

    private void doService() {
        String units = far.isSelected() ? "imperial" : "metric";
        String loc = location.getText()+"";

        OpenWeatherMapServiceFactory factory = new OpenWeatherMapServiceFactory();
        OpenWeatherMapService service = factory.newInstance();

        Disposable disposable = service.getWeatherForecast(loc, units)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.trampoline())
                .subscribe(this::onOpenWeathermapForecast, this::onError);
    }

    public void onOpenWeathermapForecast(OpenWeathermapForecast forecast) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 6; i++) {
                    String fullForecast = forecast.getForcastFor(i).getDate().toString();
                    dayLabels.get(i).setText(fullForecast.substring(0, fullForecast.indexOf(" ")));
                    tempLabels.get(i).setText(Math.round(Double.parseDouble(forecast.getForcastFor(i).main.temp + "")) + "" + DEGREE);
                    iconLabels.get(i).setImage(new Image(forecast.getForcastFor(i).weather.get(0).getIconUrl()));
                    iconLabels.get(i).setFitHeight(50);
                    iconLabels.get(i).setFitWidth(50);
                }
            }
        });
    }

    public void onError(Throwable throwable) {
        System.out.println("error occurred");
    }

    public void changeUnits(ActionEvent actionEvent) {
        doService();
    }
}

