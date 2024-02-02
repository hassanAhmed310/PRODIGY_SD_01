package sample;

public class TemperatureValue extends Value <TemperatureUnit, Double>{
    public TemperatureValue(Double amount, TemperatureUnit unit) {
        super(amount, unit);
    }

    public TemperatureValue(Double amount, String unit) {
        super(amount, convertStringToEnumUnit(unit));
    }

    private static TemperatureUnit convertStringToEnumUnit(String unit){
        if(unit.equalsIgnoreCase("kelvin")){
            return TemperatureUnit.Kelvin;
        }
        if(unit.equalsIgnoreCase("Fahrenheit")){
            return TemperatureUnit.Fahrenheit;
        }
        return TemperatureUnit.Celsius;
    }
}
