package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {
    private static final int RULE_0 = 0;
    private static final int NEG_RULE = -1;
    private static final String EMPY_STRING = "";
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
    void testGetRuleZero() {
        try {
            this.dn.getRule(RULE_0);
        } catch (IllegalArgumentException e) {
            assertNotEquals(e.getMessage(), EMPY_STRING);
        } 
    }

    /**
     * 
     */
    @Test
    void testGetNegativeRule() {
        try {
            this.dn.getRule(NEG_RULE);
        } catch (IllegalArgumentException e) {
            assertNotEquals(e.getMessage(), EMPY_STRING);
        }
    }

    /**
     * 
     */
    @Test
    void testNullRules() {
        for (int i = 1; i <= DeathNote.RULES.size(); i++) {
            String rule = dn.getRule(i);
            assertNotNull(rule);
            assertNotEquals(rule, EMPY_STRING);
        }
    }

    /*
     * 
     */
    
} 
