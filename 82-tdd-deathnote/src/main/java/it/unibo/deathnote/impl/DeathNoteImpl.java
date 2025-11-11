package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

/*
 * Implementation of the interface {@link DeathNote}
 */
public final class DeathNoteImpl implements DeathNote {
    private static final int RULE_OFFSET = 1;
    private static float DEATH_MILLIS_TIME = 40;
    private static float DETAILS_MILLIS_TIME = 6000 + DEATH_MILLIS_TIME;

    private Map<String, Death> deathNote;
    private String lastNameInserted;
    private int timeInMillis;
    
    public DeathNoteImpl() {
        this.deathNote = new HashMap<String, Death>();
        this.lastNameInserted = "";
        this.timeInMillis = 0;
    }

    @Override
    /*
     * {@inheritDoc}
     */
    public String getRule(final int ruleNumber) {
        if (ruleNumber <= 0 || ruleNumber >= DeathNote.RULES.size() + RULE_OFFSET) {
            throw new IllegalArgumentException("The argument must be a number greater than 0 and lower than the list size + " + RULE_OFFSET);
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
        if (this.deathNote.keySet().size() == 0 || name.equals(null)) {
            throw new IllegalStateException("No name present in the deathNote, or the name in input is NULL");
        }

        this.deathNote.put(name, new Death());
        this.lastNameInserted = name;
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
        return deathNote.containsKey(name);
    }

    public class Death {
        private static String DEFAULT_DEATH_CAUSE = "Heart Attack";
        private static String EMPTY_STRING = "";

        private String deathCause;
        private String deathDetails;

        public Death() {
            deathCause = DEFAULT_DEATH_CAUSE;
            deathDetails = EMPTY_STRING;
        }

        public String getDeathCause() {
            return this.deathCause;
        }

        public String getDetails() {
            return this.deathDetails;
        }

        public void setDeathCause(final String cause) {
            this.deathCause = cause;
        }

        public void setDeathDetails(final String details) {
            this.deathDetails = details;
        }
    }
}
