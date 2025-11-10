package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;

/*
 * Implementation of the interface {@link DeathNote}
 */
public final class DeathNoteImpl implements DeathNote {
    private static final int RULE_OFFSET = 1;

    @Override
    /*
     * {@inheritDoc}
     */
    public String getRule(final int ruleNumber) {
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
    public void writeName(final String name) {
        throw new UnsupportedOperationException("Unimplemented method 'writeName'");
    }

    @Override
    /*
     * {@inheritDoc}
     */
    public boolean writeDeathCause(final String cause) {
        throw new UnsupportedOperationException("Unimplemented method 'writeDeathCause'");
    }

    @Override
    /*
     * {@inheritDoc}
     */
    public boolean writeDetails(final String details) {
        throw new UnsupportedOperationException("Unimplemented method 'writeDetails'");
    }

    @Override
    /*
     * {@inheritDoc}
     */
    public String getDeathCause(final String name) {
        throw new UnsupportedOperationException("Unimplemented method 'getDeathCause'");
    }

    @Override
    /*
     * {@inheritDoc}
     */
    public String getDeathDetails(final String name) {
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    /*
     * {@inheritDoc}
     */
    public boolean isNameWritten(final String name) {
        throw new UnsupportedOperationException("Unimplemented method 'isNameWritten'");
    }
}
