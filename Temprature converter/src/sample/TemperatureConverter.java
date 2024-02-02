package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Singleton Converter
public final class TemperatureConverter implements IConverter <TemperatureValue, TemperatureUnit> {
    private static volatile TemperatureConverter instance;

    private TemperatureConverter() {}

    public static TemperatureConverter getInstance(){
        TemperatureConverter result = instance;
        if(result != null){
            return result;
        }
        synchronized (TemperatureConverter.class) {
            if (instance == null) {
                instance = new TemperatureConverter();
            }
            return instance;
        }
    }

    @Override
    public TemperatureValue convertTo(TemperatureValue from, TemperatureUnit to) {
        if(from.getUnit() == TemperatureUnit.Celsius){
            return ConvertFromCelsius(from, to);
        }
        if(from.getUnit() == TemperatureUnit.Kelvin){
            return ConvertFromKelvin(from, to);
        }
        if(from.getUnit() == TemperatureUnit.Fahrenheit){
            return ConvertFromFahrenheit(from, to);
        }
        return from;
    }

    private TemperatureValue ConvertFromCelsius(TemperatureValue from, TemperatureUnit to) {
        if(to == TemperatureUnit.Celsius){
            return new TemperatureValue(from.getAmount(), to);
        }
        if(to == TemperatureUnit.Kelvin){
            return new TemperatureValue(from.getAmount() + 273.15, to);
        }
        if(to == TemperatureUnit.Fahrenheit){
            return new TemperatureValue(from.getAmount() * 1.8 + 32, to);
        }
        return from;
    }

    private TemperatureValue ConvertFromKelvin(TemperatureValue from, TemperatureUnit to) {
        if(to == TemperatureUnit.Celsius){
            return new TemperatureValue(from.getAmount() - 273.15, to);
        }
        if(to == TemperatureUnit.Kelvin){
            return new TemperatureValue(from.getAmount(), to);
        }
        if(to == TemperatureUnit.Fahrenheit){
            return new TemperatureValue((from.getAmount() - 273.15) * 1.8 + 32, to);
        }
        return from;
    }

    private TemperatureValue ConvertFromFahrenheit(TemperatureValue from, TemperatureUnit to) {
        if(to == TemperatureUnit.Celsius){
            return new TemperatureValue((from.getAmount() - 32) * 5 / 9, to);
        }
        if(to == TemperatureUnit.Kelvin){
            return new TemperatureValue((from.getAmount() - 32) * 5 / 9 + 273.15, to);
        }
        if(to == TemperatureUnit.Fahrenheit){
            return new TemperatureValue(from.getAmount(), to);
        }
        return from;
    }

    @Override
    public List<TemperatureValue> convert(TemperatureValue value) {
        List<TemperatureValue> result = new ArrayList<>();
        if(value.getUnit() == TemperatureUnit.Celsius){
            result.add(convertTo(value, TemperatureUnit.Kelvin));
            result.add(convertTo(value, TemperatureUnit.Fahrenheit));
        }
        if(value.getUnit() == TemperatureUnit.Kelvin){
            result.add(convertTo(value, TemperatureUnit.Celsius));
            result.add(convertTo(value, TemperatureUnit.Fahrenheit));
        }
        if(value.getUnit() == TemperatureUnit.Fahrenheit){
            result.add(convertTo(value, TemperatureUnit.Celsius));
            result.add(convertTo(value, TemperatureUnit.Kelvin));
        }
        return result;
    }
}
