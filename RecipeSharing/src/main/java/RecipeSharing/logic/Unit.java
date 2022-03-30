package RecipeSharing.logic;


import java.util.HashMap;
import java.util.HashSet;

public enum Unit {

    MILLILITRE("Millilitre", "ml", true),
    LITRE("Litre", "l", true),
    FLUIDOUNCE("Fluid Ounce", "fl oz", true),
    PINT("Pint", "pt", true),
    GRAM("Gram", "g", false),
    KILOGRAM("Kilogram", "kg", false),
    OUNCE("Ounce", "oz", false),
    POUND("Pound", "lb", false);

    private final String name;
    private final String symbol;
    private final boolean volume;

    private Unit(String name, String symbol, boolean volume){
        this.name = name;
        this.symbol = symbol;
        this.volume = volume;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getName() {
        return this.name;
    }

    public boolean isVolume() {
        return this.volume;
    }

    public boolean equals(Unit check) {
        return (this.getName().equals(check.getName()));
    }

    public Double getConversionQuotient(Unit from, Unit to) {

        Double quotient = 0.0;

        if (from.equals(to)) {
            return 1.0;
        }

        if (from.isVolume() != to.isVolume()) {
            throw new RuntimeException("Attempting to convert incompatible units");
        }

        if (from.isVolume()) {
            quotient = 0.0;
        } else {
            quotient = 0.0;
        }

        return quotient;
    }

    private Double volumeConversion(Unit from, Unit to) {

        return 0.0;
    }

    private Double massConversion(Unit from, Unit to) {

        return 0.0;
    }

    private HashMap<HashSet<Unit>, Double> getConversionMap() {

        return null;
    }
}
