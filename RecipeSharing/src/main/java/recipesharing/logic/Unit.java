package recipesharing.logic;

import java.util.HashMap;
import java.util.Map;

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

    private Unit(String name, String symbol, boolean volume) {
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

    public Double getConversionQuotient(Unit to) {

        Double quotient = 0.0;

        if (this.equals(to)) {
            return 1.0;
        }

        if (this.isVolume() != to.isVolume()) {
            throw new RuntimeException("Attempting to convert incompatible units");
        }

        if (this.isVolume()) {
            quotient = volumeConversion(to);
        } else {
            quotient = massConversion(to);
        }

        return quotient;
    }

    private Double volumeConversion(Unit to) {
        HashMap<Map<Unit, Unit>, Double> conversionMap = getVolumeMap();
        Double quotient = conversionMap.get(Map.of(this, to));

        return quotient;
    }

    private Double massConversion(Unit to) {
        HashMap<Map<Unit, Unit>, Double> conversionMap = getMassMap();
        Double quotient = conversionMap.get(Map.of(this, to));

        return quotient;
    }

    private HashMap<Map<Unit, Unit>, Double> getVolumeMap() {
        HashMap<Map<Unit, Unit>, Double> unitToUnitMapping = new HashMap<>();

        Map<Unit, Unit> ml2l = Map.of(MILLILITRE, LITRE);
        Map<Unit, Unit> ml2floz = Map.of(MILLILITRE, FLUIDOUNCE);
        Map<Unit, Unit> ml2pint = Map.of(MILLILITRE, PINT);
        Map<Unit, Unit> l2ml = Map.of(LITRE, MILLILITRE);
        Map<Unit, Unit> l2floz = Map.of(LITRE, FLUIDOUNCE);
        Map<Unit, Unit> l2pint = Map.of(LITRE, PINT);
        Map<Unit, Unit> floz2ml = Map.of(FLUIDOUNCE, MILLILITRE);
        Map<Unit, Unit> floz2l = Map.of(FLUIDOUNCE, LITRE);
        Map<Unit, Unit> floz2pint = Map.of(FLUIDOUNCE, PINT);
        Map<Unit, Unit> pint2ml = Map.of(PINT, MILLILITRE);
        Map<Unit, Unit> pint2l = Map.of(PINT, LITRE);
        Map<Unit, Unit> pint2floz = Map.of(PINT, FLUIDOUNCE);

        unitToUnitMapping.put(ml2l, 0.001);
        unitToUnitMapping.put(ml2floz, 0.033814);
        unitToUnitMapping.put(ml2pint, 0.00175975);
        unitToUnitMapping.put(l2ml, 1000.0);
        unitToUnitMapping.put(l2floz, 33.814);
        unitToUnitMapping.put(l2pint, 2.11338);
        unitToUnitMapping.put(floz2ml, 29.5735);
        unitToUnitMapping.put(floz2l, 0.0295735);
        unitToUnitMapping.put(floz2pint, 0.0520421);
        unitToUnitMapping.put(pint2ml, 568.261);
        unitToUnitMapping.put(pint2l, 0.568261);
        unitToUnitMapping.put(pint2floz, 16.0);

        return unitToUnitMapping;
    }

    private HashMap<Map<Unit, Unit>, Double> getMassMap() {
        HashMap<Map<Unit, Unit>, Double> unitToUnitMapping = new HashMap<>();

        Map<Unit, Unit> g2kg = Map.of(GRAM, KILOGRAM);
        Map<Unit, Unit> g2oz = Map.of(GRAM, OUNCE);
        Map<Unit, Unit> g2lb = Map.of(GRAM, POUND);
        Map<Unit, Unit> kg2g = Map.of(KILOGRAM, GRAM);
        Map<Unit, Unit> kg2oz = Map.of(KILOGRAM, OUNCE);
        Map<Unit, Unit> kg2lb = Map.of(KILOGRAM, POUND);
        Map<Unit, Unit> oz2g = Map.of(OUNCE, GRAM);
        Map<Unit, Unit> oz2kg = Map.of(OUNCE, KILOGRAM);
        Map<Unit, Unit> oz2lb = Map.of(OUNCE, POUND);
        Map<Unit, Unit> lb2g = Map.of(POUND, GRAM);
        Map<Unit, Unit> lb2kg = Map.of(POUND, KILOGRAM);
        Map<Unit, Unit> lb2oz = Map.of(POUND, OUNCE);

        unitToUnitMapping.put(g2kg, 0.001);
        unitToUnitMapping.put(g2oz, 0.035274);
        unitToUnitMapping.put(g2lb, 0.0022);
        unitToUnitMapping.put(kg2g, 1000.0);
        unitToUnitMapping.put(kg2oz, 35.274);
        unitToUnitMapping.put(kg2lb, 2.20462);
        unitToUnitMapping.put(oz2g, 28.3495);
        unitToUnitMapping.put(oz2kg, 0.02835);
        unitToUnitMapping.put(oz2lb, 0.0625);
        unitToUnitMapping.put(lb2g, 453.592);
        unitToUnitMapping.put(lb2kg, 0.453592);
        unitToUnitMapping.put(lb2oz, 16.0);

        return unitToUnitMapping;
    }

    public static Unit unitConverter(String unitName) {
//        MILLILITRE("Millilitre", "ml", true),
//                LITRE("Litre", "l", true),
//                FLUIDOUNCE("Fluid Ounce", "fl oz", true),
//                PINT("Pint", "pt", true),
//                GRAM("Gram", "g", false),
//                KILOGRAM("Kilogram", "kg", false),
//                OUNCE("Ounce", "oz", false),
//                POUND("Pound", "lb", false);
        Unit converted = MILLILITRE;
        return converted;
    }
}
