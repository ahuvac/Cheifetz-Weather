package cheifetz.openweathermap;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.*;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OpenWeatherMapControllerTest {

    private List<Label> dayLabels;
    private List<Label> tempLabels;
    private List<ImageView> iconLabels;
    private TextField location;

    private OpenWeatherMapController controller;

    @BeforeClass
    public static void beforeClass() {
        com.sun.javafx.application.PlatformImpl.startup(() -> {
        });
    }

    @Test
    public void initialize() {
        // given
        givenOpenWeatherMapController();
        // when
        controller.initialize();
        // then
        verify(controller.far).setSelected(true);
    }

    @Test
    public void setTexts() {
        //given
        Date date = java.util.Calendar.getInstance().getTime();

        givenOpenWeatherMapController();

        OpenWeathermapForecast forecast = mock(OpenWeathermapForecast.class);
        OpenWeathermapForecast.HourlyForecast hourlyForecast = mock(OpenWeathermapForecast.HourlyForecast.class);
        OpenWeathermapForecast.HourlyForecast.Main main = mock(OpenWeathermapForecast.HourlyForecast.Main.class);
        List<OpenWeathermapForecast.HourlyForecast.Weather> weather = Arrays.asList(
                mock(OpenWeathermapForecast.HourlyForecast.Weather.class),
                mock(OpenWeathermapForecast.HourlyForecast.Weather.class),
                mock(OpenWeathermapForecast.HourlyForecast.Weather.class));

        hourlyForecast.main = main;
        main.temp = 70.0;
        hourlyForecast.weather = weather;

        doReturn(hourlyForecast).when(forecast).getForcastFor(anyInt());
        doReturn(date).when(hourlyForecast).getDate();
        doReturn("http://openweathermap.org/img/wn/105@2x.png").
                when(hourlyForecast.weather.get(0)).getIconUrl();

        //when
        controller.setTexts(forecast);

        //then
        for (int i = 0; i < controller.dayLabels.size(); i++) {
            String fullDate = date + "";
            verify(dayLabels.get(i), times(1)).setText(fullDate.substring(0, fullDate.indexOf(" ")));
            verify(tempLabels.get(i), times(1)).setText("70" + (char) 186);
            verify(iconLabels.get(i), times(1)).setImage(any(Image.class));
        }
    }

    @Test
    public void doService() {
        //given
        givenOpenWeatherMapController();
        OpenWeatherMapServiceFactory factory = mock(OpenWeatherMapServiceFactory.class);
        OpenWeatherMapService service = factory.newInstance();

        //when
        controller.doService();

        //then
        verify(controller.far).isSelected();
    }

    @Test
    public void refresh() {
        //given
        controller = mock(OpenWeatherMapController.class);
        //when
        controller.refresh(mock(ActionEvent.class));
        //then
        verify(controller, never()).doService();
    }

    public void givenOpenWeatherMapController() {
        controller = new OpenWeatherMapController();
        dayLabels = mock(List.class);
        tempLabels = mock(List.class);
        iconLabels = mock(List.class);
        location = mock(TextField.class);
        controller.far = mock(RadioButton.class);
        controller.cel = mock(RadioButton.class);

        dayLabels = Arrays.asList(
                mock(Label.class),
                mock(Label.class),
                mock(Label.class)
        );
        controller.dayLabels = dayLabels;

        tempLabels = Arrays.asList(
                mock(Label.class),
                mock(Label.class),
                mock(Label.class)
        );
        controller.tempLabels = tempLabels;

        iconLabels = Arrays.asList(
                mock(ImageView.class),
                mock(ImageView.class),
                mock(ImageView.class)
        );
        controller.iconLabels = iconLabels;
    }

}