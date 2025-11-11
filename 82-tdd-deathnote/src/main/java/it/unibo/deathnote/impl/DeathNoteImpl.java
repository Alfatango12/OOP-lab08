package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

/**
 * Implementation of the interface {@link DeathNote}.
 */
public final class DeathNoteImpl implements DeathNote {
    private static final int RULE_OFFSET = 1;
    private static final long DEATH_MILLIS_TIME = 40;
    private static final long DETAILS_MILLIS_TIME = 6000 + DEATH_MILLIS_TIME;
    private static final String EMPTY_STRING = "";

    private Map<String, Death> deathNote;
    private String lastNameInserted;
    private long timeInMillis;

    /**
     * 
     */
    public DeathNoteImpl() {
        this.deathNote = new HashMap<String, Death>();
        this.lastNameInserted = "";
        this.timeInMillis = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRule(final int ruleNumber) {
        if (ruleNumber <= 0 || ruleNumber >= DeathNote.RULES.size() + RULE_OFFSET) {
            final String message = "The argument must be a number greater than 0 and lower than the list size + " + RULE_OFFSET;
            throw new IllegalArgumentException(message);
        } else {
            return RULES.get(ruleNumber - RULE_OFFSET);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeName(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("No name present in the deathNote, or the name in input is NULL");
        } else if (name.equals(EMPTY_STRING)) {
            System.err.println("Cannot insert an empty string, non a valid name");
        } else {
            this.deathNote.put(name, new Death());
            this.lastNameInserted = name;
            this.timeInMillis = System.currentTimeMillis();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean writeDeathCause(final String cause) {
        if (this.lastNameInserted == null || cause == null) {
            throw new IllegalStateException("The are no names in deathNote, or the cause parameter is NULL");
        }

        if (checkTime(DEATH_MILLIS_TIME)) {
            deathNote.get(this.lastNameInserted).setDeathCause(cause);
            this.timeInMillis = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean writeDetails(final String details) {
        if (this.lastNameInserted == null || details == null) {
            final String message = "There are no names in deathNote, or the cause parameter is NULL";
            throw new IllegalArgumentException(message); //Note: this can be transformed in a function
        }

        if (checkTime(DETAILS_MILLIS_TIME)) {
            deathNote.get(this.lastNameInserted).setDeathDetails(details);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathCause(final String name) {
        if (!isNameWritten(name)) {
            throw new IllegalArgumentException("The name is not present in the deathNote");
        }

        return deathNote.get(name).getDeathCause();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathDetails(final String name) {
        if (!isNameWritten(name)) {
            throw new IllegalArgumentException("The name is not present in the deathNote");
        }

        return deathNote.get(name).getDetails();
    }

    /**
     * {@inheritDoc}
     */
    @Override
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

    /**
     * 
     */
    public class Death {
        private static final String DEFAULT_DEATH_CAUSE = "Heart Attack";

        private String deathCause;
        private String deathDetails;

        /**
         * 
         */
        public Death() {
            this.deathCause = DEFAULT_DEATH_CAUSE;
            this.deathDetails = DeathNoteImpl.EMPTY_STRING;
        }

        /**
         * @return d
         */
        public String getDeathCause() {
            return this.deathCause;
        }

        /**
         * @return d
         */
        public String getDetails() {
            return this.deathDetails;
        }

        /**
         * @param d
         */
        public void setDeathCause(final String cause) {
            this.deathCause = cause;
        }

        /**
         * @param d
         */
        public void setDeathDetails(final String details) {
            this.deathDetails = details;
        }
    }
}
