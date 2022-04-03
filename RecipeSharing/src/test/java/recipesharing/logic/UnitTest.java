package recipesharing.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Unit enum and its methods.
 */
class UnitTest {

    Unit grams;
    Unit kgs;
    Unit floz;
    Unit pints;

    /**
     * Set up fresh enums for each test.
     */
    @BeforeEach
    void setUp() {
        grams = Unit.GRAM;
        kgs = Unit.KILOGRAM;
        floz = Unit.FLUIDOUNCE;
        pints = Unit.PINT;
    }

    /**
     * Test the correct symbol is returned.
     */
    @Test
    void getSymbol() {
        assertEquals(grams.getSymbol(), "g");
        assertEquals(kgs.getSymbol(), "kg");
        assertEquals(floz.getSymbol(), "fl oz");
        assertEquals(pints.getSymbol(), "pt");
    }

    /**
     * Test that the correct name is returned.
     */
    @Test
    void getName() {
        assertEquals(grams.getName(), "Gram");
        assertEquals(kgs.getName(), "Kilogram");
        assertEquals(floz.getName(), "Fluid Ounce");
        assertEquals(pints.getName(), "Pint");
    }

    /**
     * Test that each unit is marked as a volume or mass unit.
     */
    @Test
    void isVolume() {
        assertFalse(grams.isVolume());
        assertFalse(kgs.isVolume());
        assertTrue(floz.isVolume());
        assertTrue(pints.isVolume());
    }

    /**
     * Test the units can be identified as equal or not.
     */
    @Test
    void testEquals() {
        Unit grams2 = Unit.GRAM;
        assertFalse(grams.equals(kgs));
        assertTrue(grams.equals(grams2));
    }

    /**
     * Test that the correct conversion quotient is returned and that exceptions are thrown when expected.
     */
    @Test
    void getConversionQuotient() {
        assertEquals(grams.getConversionQuotient(kgs), 0.001);
        assertEquals(pints.getConversionQuotient(floz), 16.0);

        assertThrows(RuntimeException.class, () -> pints.getConversionQuotient(kgs));
    }
}