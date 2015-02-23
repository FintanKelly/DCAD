package com.dcad.finalproject;

import sofia.util.Random;

// -------------------------------------------------------------------------
/**
 * Represents a base card model to be extended by other specific cards. Defines
 * the basic standardized functionality of a card that is held within an
 * {@link ActionDeck}.
 *
 * @author Cameron Rader (crader)
 * @version 2014.11.5
 */
public abstract class ActionCard
{
    // ~ Fields ----------------------------------------------------------------
    /**
     * Each subclass must have a Random that each individual class will decide
     * what it will do with.
     */
    protected static Random rand = new Random();


    // ~ Methods ---------------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Method to be called when a card is used.
     *
     * @return respective value to action performed, defined by the subclass.
     */
    public abstract int activate();


    // ----------------------------------------------------------
    /**
     * Gets the name of the card.
     *
     * @return card name
     */
    public abstract String getName();


    // ----------------------------------------------------------
    /**
     * Gets all general info of the card.
     *
     * @return card info
     */
    public abstract String toString();
}
