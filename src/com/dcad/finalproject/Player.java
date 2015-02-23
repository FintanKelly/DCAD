package com.dcad.finalproject;

import java.util.concurrent.ArrayBlockingQueue;

// -------------------------------------------------------------------------
/**
 * Represents the user's character, a subclass of an {@link Entity}. This class
 * is mostly assigned default attributes and is very similar to its parent
 * class.
 *
 * @author Cameron Rader (crader)
 * @version 2014.11.5
 */
public class Player
    extends Entity
{
    // ~ Constructors ----------------------------------------------------------
    // ----------------------------------------------------------
    /**
     * Create a new Player object which represents the user's hero.
     */
    public Player()
    {
        maxHealth = health = 500;
        armor = 0;
        maxArmor = 50;

        actionDeck = new ActionDeck();

        actionQueue = new ArrayBlockingQueue<ActionCard>(3);
    }


    // ----------------------------------------------------------
    /**
     * @return The top card of the player's deck.
     */
    public ActionCard getTopCard()
    {
        return actionDeck.draw();
    }
}
