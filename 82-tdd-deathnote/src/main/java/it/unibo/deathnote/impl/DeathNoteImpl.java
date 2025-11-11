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
    private static final String WRITE_METHOD_ERROR = "There are no names in deathNote, or the parameter is NULL";

    private final Map<String, Death> deathNote;
    private String lastNameInserted;
    private long timeInMillis;

    /**
     * Constructor of {@link DeathNoteImpl}.
     * It doesn't take any input value, just initialize all fields to default values.
     */
    public DeathNoteImpl() {
        this.deathNote = new HashMap<>();
        this.lastNameInserted = "";
        this.timeInMillis = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRule(final int ruleNumber) {
        if (ruleNumber <= 0 || ruleNumber >= RULES.size() + RULE_OFFSET) {
            throw new IllegalArgumentException("The argument must be a number from 1 to " + RULES.size());
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
            throw new NullPointerException("The name in input is NULL");
        } else if (EMPTY_STRING.equals(name)) {
            System.err.println("Cannot insert an empty string, non a valid name"); //NOPMD
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
            throw new IllegalStateException(WRITE_METHOD_ERROR);
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
            throw new IllegalArgumentException(WRITE_METHOD_ERROR);
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
        return (currentTimeMillis - this.timeInMillis) <= timeLimit;
    }

    /**
     * This class manages all the values associated with death.
     * This way makes data more manageable in the {@link DeathNoteImpl} by using a map.
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
            this.deathDetails = EMPTY_STRING;
        }

        /**
         * @return the death cause associated to the human name.
         */
        public String getDeathCause() {
            return this.deathCause;
        }

        /**
         * @return the death details associated to the human name.
         */
        public String getDetails() {
            return this.deathDetails;
        }

        /**
         * @param cause The cause of the human's death.
         */
        public void setDeathCause(final String cause) {
            this.deathCause = cause;
        }

        /**
         * @param details The details associated to the human's cause of death.
         */
        public void setDeathDetails(final String details) {
            this.deathDetails = details;
        }
    }
}
