package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

/*
 * Implementation of the interface {@link DeathNote}
 */
public final class DeathNoteImpl implements DeathNote {
    private static final int RULE_OFFSET = 1;
    private static long DEATH_MILLIS_TIME = 40;
    private static long DETAILS_MILLIS_TIME = 6000 + DEATH_MILLIS_TIME;
    private static String EMPTY_STRING = "";

    private Map<String, Death> deathNote;
    private String lastNameInserted;
    private long timeInMillis;
    
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
        if (name.equals(null)) {
            throw new IllegalArgumentException("No name present in the deathNote, or the name in input is NULL");
        }

        else if (name.equals(EMPTY_STRING)) {
            System.err.println("Cannot insert an empty string, non a valid name");
        }

        else {
            this.deathNote.put(name, new Death());
            this.lastNameInserted = name;
            this.timeInMillis = System.currentTimeMillis();
        }
        
    }

    @Override
    /*
     * {@inheritDoc}
     */
    public boolean writeDeathCause(final String cause) {
        if (this.lastNameInserted.equals(null) || cause.equals(null)) {
            throw new IllegalStateException("The are no names in deathNote, or the cause parameter is NULL");
        }
        
        if (checkTime(DEATH_MILLIS_TIME)) {
            deathNote.get(this.lastNameInserted).setDeathCause(cause);
            this.timeInMillis = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    @Override
    /*
     * {@inheritDoc}
     */
    public boolean writeDetails(final String details) {
        if (this.lastNameInserted.equals(null) || details.equals(null)) {
            throw new IllegalArgumentException("There are no names in deathNote, or the cause parameter is NULL"); //Note: this can be transformed in a function
        }

        if (checkTime(DEATH_MILLIS_TIME)) {
            deathNote.get(this.lastNameInserted).setDeathDetails(details);
            return true;
        }
        return false;
    }

    @Override
    /*
     * {@inheritDoc}
     */
    public String getDeathCause(final String name) {
        if (!isNameWritten(name)) {
            throw new IllegalArgumentException("The name is not present in the deathNote");
        }

        return deathNote.get(name).getDeathCause();
    }

    @Override
    /*
     * {@inheritDoc}
     */
    public String getDeathDetails(final String name) {
        if (!isNameWritten(name)) {
            throw new IllegalArgumentException("The name is not present in the deathNote");
        }

        return deathNote.get(name).getDetails();
    }

    @Override
    /*
     * {@inheritDoc}
     */
    public boolean isNameWritten(final String name) {
        return deathNote.containsKey(name);
    }

    private boolean checkTime(final long timeLimit) {
        final long currentTimeMillis = System.currentTimeMillis();
        if ((currentTimeMillis - this.timeInMillis) <= timeLimit) {
            return true;
        }
        return false;
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
