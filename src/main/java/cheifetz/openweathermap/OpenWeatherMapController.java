package cheifetz.openweathermap;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class OpenWeatherMapController {

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
    TextField degree;

    @FXML
    public void initialize() {
    /*    String loc = location.getText();
        loc = "New York";
        String units = degree.getText();
        units = "fahrenheit";

        OpenWeatherMapServiceFactory factory = new OpenWeatherMapServiceFactory();
        OpenWeatherMapService service = factory.newInstance();


           Disposable disposable = service.getWeatherForecast(loc, units)
                    // request the data in the background
                    .subscribeOn(Schedulers.io())
                    // work with the data in the foreground
                    .observeOn(Schedulers.trampoline())
                    // work with the feed whenever it gets downloaded
                    .subscribe(this::onOpenWeathermapForecast, this::onError);`

*/

    }


    public void onOpenWeathermapForecast(OpenWeathermapForecast forecast) {
        tempToday.setText(forecast.list.get(0).main.temp + "");
        iconToday.setImage(new Image(forecast.list.get(0).weather.get(0).getIconUrl()));
        int day = 1;
        for (Label label : tempLabels) {
            label.setText(forecast.list.get(day).main.temp + "");
            day++;
        }
        for (Label label : dayLabels) {
            label.setText(forecast.list.get(day).getDate() + "");
        }
    }

    public void onError(Throwable throwable) {
        System.out.println("error occurred");
    }

}

