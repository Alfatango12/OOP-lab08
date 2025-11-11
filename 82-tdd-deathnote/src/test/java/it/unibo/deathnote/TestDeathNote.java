package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.EmptyStackException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {
    private static final int RULE_0 = 0;
    private static final int NEG_RULE = -1;
    private static final String EMPY_STRING = "";
    private static final String humanName = "Gianfranco";
    private DeathNoteImpl dn;

    @BeforeEach
    /*
     * Function that create a new object {@link DeathNoteImpl}
     * that will be used for all the tests
     */
    public void setUp() {
        this.dn = new DeathNoteImpl();
    }

    /**
     * 
     */
    @Test
    void testGetIllegalRule() {
        try {
            this.dn.getRule(RULE_0);
            this.dn.getRule(NEG_RULE);
        } catch (final IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertNotEquals(e.getMessage(), EMPY_STRING);
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
            assertNotEquals(rule, EMPY_STRING);
        }
    }

    /*
     * 
     */
    @Test
    void testHumanDeath() {
        try {
            // Verify insertion of a valid name
            assertEquals(dn.isNameWritten(humanName), false);
            dn.writeName(humanName);
            assertEquals(dn.isNameWritten(humanName), true);
            // Verify insertion of empty string
            assertEquals(dn.isNameWritten(EMPY_STRING), false);
            dn.writeName(EMPY_STRING);
            assertEquals(dn.isNameWritten(EMPY_STRING), false);

        } catch (final NullPointerException e) {

        } catch() {

        }
    }
} 
