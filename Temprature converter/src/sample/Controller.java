package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button convert;

    @FXML
    private TextField degree;

    @FXML
    private ChoiceBox<String> unitsMenu;

    @FXML
    private Text result1;

    @FXML
    private Text result2;

    @FXML
    void convert(MouseEvent event) {
        if(!isNumeric(degree.getText())){
            alert();
        }
        TemperatureConverter converter = TemperatureConverter.getInstance();
        List<TemperatureValue> result = converter.convert(
                new TemperatureValue(
                        Double.parseDouble(degree.getText()), unitsMenu.getValue()
                ));
        result1.setText(formatDouble(result.get(0).getAmount()) + " " + result.get(0).getUnit().toString());
        result2.setText(formatDouble(result.get(1).getAmount()) + " " + result.get(1).getUnit().toString());
    }

    public static String formatDouble(Double number) {
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println(number);
        System.out.println(df.format(number));
        return df.format(number);
    }

    private String[] menu = {
            "Celsius",
            "Kelvin",
            "Fahrenheit",
    };

    public static void alert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Non Numeric Input");
        Optional<ButtonType> result = alert.showAndWait();
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Locale.setDefault(new Locale("en"));
        unitsMenu.getItems().addAll(menu);
        unitsMenu.setValue(menu[0]);
    }
}