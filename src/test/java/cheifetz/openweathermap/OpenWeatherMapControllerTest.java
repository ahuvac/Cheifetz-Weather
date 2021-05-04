package cheifetz.openweathermap;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import org.junit.Before;
import org.junit.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OpenWeatherMapControllerTest {
    private Label today;
    private ImageView iconToday;
    private Label temoToday;
    private List<Label> dayLabels;
    private List<Label> tempLabels;
    private List<javafx.scene.image.ImageView> iconLabels;
    private TextField location;
    private RadioButton cel;
    private RadioButton far;

    private OpenWeatherMapController controller;


    @Test
    public void initialize() {
        // given
        givenOpenWeatherMapController();
        //doReturn();

        // when
        controller.initialize();

        // then
//        verify(controller.letterLabels.get(0)).setText("T");
//        verify(controller.letterLabels.get(1)).setText("G");
//        verify(controller.letterLabels.get(2)).setText("L");
//        verify(letterBag, times(3)).nextLetter();
    }

    public void givenOpenWeatherMapController() {
        today = mock(Label.class);
        iconToday = mock(ImageView.class);
        today = mock(Label.class);
        iconToday = mock(ImageView.class);
        temoToday = mock(Label.class);
        dayLabels = mock(List.class);
        tempLabels = mock(List.class);
        iconLabels = mock(List.class);
        location = mock(TextField.class);
        cel = mock(RadioButton.class);
        far= mock(RadioButton.class);

        controller = new OpenWeatherMapController();
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

        ToggleGroup group = mock(ToggleGroup.class);

    }

    @Test
    public void onOpenWeathermapForecast() {
        
    }

    @Test
    public void onError() {
    }

    @Test
    public void changeUnits() {
    }
}