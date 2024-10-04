package final_project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField inputField;

    @FXML
    private ComboBox<String> comboBoxFrom;

    @FXML
    private ComboBox<String> comboBoxTo;

    @FXML
    private Button convertButton;

    @FXML
    private Label resultLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Populate ComboBox with temperature options
        comboBoxFrom.setItems(FXCollections.observableArrayList("Celsius", "Fahrenheit", "Kelvin"));
        comboBoxTo.setItems(FXCollections.observableArrayList("Celsius", "Fahrenheit", "Kelvin"));

        // Set default values
        comboBoxFrom.setValue("Celsius");
        comboBoxTo.setValue("Fahrenheit");
    }

    @FXML
    private void convertTemperature(ActionEvent event) {
        try {
            // Retrieve input temperature and selected units
            double inputTemp = Double.parseDouble(inputField.getText());
            String fromUnit = comboBoxFrom.getValue();
            String toUnit = comboBoxTo.getValue();

            // Perform conversion
            double convertedTemp = convertTemperature(inputTemp, fromUnit, toUnit);

            // Display the result
            resultLabel.setText("Result: " + String.format("%.2f", convertedTemp) + " " + toUnit);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter a valid number.");
        }
    }

    // Conversion logic
    private double convertTemperature(double temperature, String from, String to) {
        if (from.equals(to)) {
            return temperature;
        }

        // Convert from Celsius to others
        if (from.equals("Celsius")) {
            if (to.equals("Fahrenheit")) {
                return (temperature * 9 / 5) + 32;
            } else if (to.equals("Kelvin")) {
                return temperature + 273.15;
            }
        }

        // Convert from Fahrenheit to others
        if (from.equals("Fahrenheit")) {
            if (to.equals("Celsius")) {
                return (temperature - 32) * 5 / 9;
            } else if (to.equals("Kelvin")) {
                return ((temperature - 32) * 5 / 9) + 273.15;
            }
        }

        // Convert from Kelvin to others
        if (from.equals("Kelvin")) {
            if (to.equals("Celsius")) {
                return temperature - 273.15;
            } else if (to.equals("Fahrenheit")) {
                return ((temperature - 273.15) * 9 / 5) + 32;
            }
        }

        return temperature;
    }
}
