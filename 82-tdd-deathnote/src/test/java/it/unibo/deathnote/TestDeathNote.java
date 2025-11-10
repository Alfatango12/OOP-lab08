package it.unibo.deathnote;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {
    private static final int RULE_0 = 0;
    private DeathNoteImpl dn;

    @BeforeAll
    /*
     * Function that create a new object {@link DeathNoteImpl}
     * that will be used for all the tests
     */
    public void setUp() {
        this.dn = new DeathNoteImpl();
    }

    @Test
    /*
     * 
     */
    public void testRuleZero() {
        try {
            dn.getRule(RULE_0);
        }
    }
}
