package recipesharing.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests basic Recipe class logic.
 */
class RecipeTest {

    private Recipe testRecipe;

    /**
     * Set up a clean recipe object for each test.
     */
    @BeforeEach
    public void setUp() {
        testRecipe = new Recipe("test soup",
                "auth1",
                null,
                null,
                "testing soup",
                false,
                false,
                0,
                null,
                "cuis1",
                null,
                null);
    }

    /**
     * Tests that write access can be accessed.
     */
    @Test
    void isWriteAccess() {
        assertFalse(testRecipe.isWriteAccess());
    }

    /**
     * Tests that write access can be set.
     */
    @Test
    void setWriteAccess() {
        testRecipe.setWriteAccess(true);
        assertTrue(testRecipe.isWriteAccess());
    }

    /**
     * Tests that read access can be accessed.
     */
    @Test
    void isReadAccess() {
        assertFalse(testRecipe.isReadAccess());
    }

    /**
     * Tests that read access can be set.
     */
    @Test
    void setReadAccess() {
        testRecipe.setReadAccess(true);
        assertTrue(testRecipe.isReadAccess());
    }

    /**
     * Tests that recipe name can be retrieved.
     */
    @Test
    void getRecipeName() {
        assertEquals(testRecipe.getRecipeName(), "test soup");
    }

    /**
     * Tests that the author ID can be retrieved.
     */
    @Test
    void getAuthorId() {
        assertEquals(testRecipe.getAuthorId(), "auth1");
    }
}