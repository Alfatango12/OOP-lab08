package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

/**
 * 
 */
class TestDeathNote {
    private static final int[] WRONG_RULES = {0, -1, DeathNote.RULES.size() + 1}; 
    private static final long SHORT_SLEEP_TIME = 100;
    private static final long LONG_SLEEP_TIME = 6100;
    private static final String EMPTY_STRING = "";
    private static final String HUMAN_NAME = "Gianfranco Spiriponzi";
    private static final String HUMAN_NAME_B = "Gesualdo Stiripaldo";
    private static final String DEATH_CAUSE = "Karting accident";
    private static final String DEFAULT_DEATH_CAUSE = "Heart Attack";
    private static final String DEATH_DETAILS = "Ran for too long";
    private DeathNoteImpl dn;

    /**
     * Function that create a new object {@link DeathNoteImpl}
     * that will be used for all the tests
     */
    @BeforeEach
    public void setUp() {
        this.dn = new DeathNoteImpl();
    }

    /**
     * 
     */
    @Test
    void testGetIllegalRule() {
        for (final int i : WRONG_RULES) {
            try {
                dn.getRule(i);
                Assertions.fail("Expected IllegalArgumentException but none was thrown for " + i);
            } catch (final IllegalArgumentException e) {
                assertNotNull(e.getMessage());
                assertNotEquals(e.getMessage(), EMPTY_STRING);
            }
        }
    }

    /**
     * 
     */
    @Test
    void testNullRules() {
        for (int i = 1; i <= DeathNote.RULES.size(); i++) {
            final String rule = dn.getRule(i);
            assertNotNull(rule);
            assertNotEquals(rule, EMPTY_STRING);
        }
    }

    /**
     * 
     */
    @Test
    void testHumanDeath() {
        try {
            // Verify insertion of a valid name
            assertFalse(dn.isNameWritten(HUMAN_NAME));
            dn.writeName(HUMAN_NAME);
            assertTrue(dn.isNameWritten(HUMAN_NAME));
            // Verify that another name has been written
            assertFalse(dn.isNameWritten(HUMAN_NAME_B));
            // Verify insertion of empty string
            assertFalse(dn.isNameWritten(EMPTY_STRING));
            dn.writeName(EMPTY_STRING);
            assertFalse(dn.isNameWritten(EMPTY_STRING));

        } catch (final NullPointerException e) {
            System.out.println(this.exceptionMessages(e));
        }
    }

    /**
     * 
     * @throws InterruptedException for the {@link Thread.sleep} function
     */
    @Test
    void testDeathCauseTiming() throws InterruptedException {
        try {
            dn.writeDeathCause(DEATH_CAUSE);
            dn.writeName(HUMAN_NAME);
            assertEquals(dn.getDeathCause(HUMAN_NAME), DEFAULT_DEATH_CAUSE);
            dn.writeName(HUMAN_NAME_B);
            assertTrue(dn.writeDeathCause(DEATH_CAUSE));
            assertEquals(dn.getDeathCause(HUMAN_NAME_B), DEATH_CAUSE);
            Thread.sleep(SHORT_SLEEP_TIME);
            assertFalse(dn.writeDeathCause(DEFAULT_DEATH_CAUSE));
            assertEquals(dn.getDeathCause(HUMAN_NAME_B), DEATH_CAUSE);
        } catch (final IllegalStateException e) {
            System.out.println(this.exceptionMessages(e));
        } catch (final IllegalArgumentException e) {
            System.out.println(this.exceptionMessages(e));
        }
    }

    /**
     * 
     * @throws InterruptedException for the {@link Thread.sleep} function
     */
    @Test
    void testDeathDetails() throws InterruptedException{
        try {     
            dn.writeDetails(DEATH_DETAILS);
            // Test with human n1 (correct timing)
            dn.writeName(HUMAN_NAME);
            assertEquals(dn.getDeathDetails(HUMAN_NAME), EMPTY_STRING);
            assertTrue(dn.writeDetails(DEATH_DETAILS));
            assertEquals(dn.getDeathDetails(HUMAN_NAME), DEATH_DETAILS);
            // Test with human number 2 (incorrect timing)
            dn.writeName(HUMAN_NAME_B);
            Thread.sleep(LONG_SLEEP_TIME);
            assertFalse(dn.writeDetails(DEATH_DETAILS));
            assertEquals(dn.getDeathDetails(HUMAN_NAME_B), EMPTY_STRING);
        } catch (final IllegalStateException e) {
            System.out.println(this.exceptionMessages(e));
        }
    }

    private String exceptionMessages(final Throwable e) {
        return "Managed " + e.getClass().getSimpleName() // Stampa il nome dell'eccezione
           + " with message: " + e.getMessage() 
           + " With stack trace: " + Arrays.toString(e.getStackTrace()); // <-- Correzione qui
    }
} 
