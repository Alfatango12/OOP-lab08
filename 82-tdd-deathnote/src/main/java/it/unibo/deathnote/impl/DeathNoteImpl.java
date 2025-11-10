package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote {
    private static final int RULE_OFFSET = 1;
    @Override
    /*
     * {@inheritDoc}
     */
    public String getRule(int ruleNumber) {
        if (ruleNumber <= 0) {
            throw new IllegalArgumentException("The argument must be a number greater than 0");
        }

        else {
            return RULES.get(ruleNumber - RULE_OFFSET);
        }
    }

    @Override
    /*
     * {@inheritDoc}
     */
    public void writeName(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'writeName'");
    }

    @Override
    /*
     * {@inheritDoc}
     */
    public boolean writeDeathCause(String cause) {
        throw new UnsupportedOperationException("Unimplemented method 'writeDeathCause'");
    }

    @Override
    /*
     * {@inheritDoc}
     */
    public boolean writeDetails(String details) {
        throw new UnsupportedOperationException("Unimplemented method 'writeDetails'");
    }

    @Override
    /*
     * {@inheritDoc}
     */
    public String getDeathCause(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'getDeathCause'");
    }

    @Override
    /*
     * {@inheritDoc}
     */
    public String getDeathDetails(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    /*
     * {@inheritDoc}
     */
    public boolean isNameWritten(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'isNameWritten'");
    }
}
