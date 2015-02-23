package com.dcad.finalproject;

import java.util.ArrayList;
import sofia.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

// -------------------------------------------------------------------------
/**
 * Represents a monster, a subclass of an {@link Entity}, that the player will
 * fight. Most attributes of the class are randomly generated. Included in this
 * class is a list of items that represents the items dropped when the monster
 * dies.
 *
 * @author Cameron Rader (crader)
 * @version 2014.11.5
 */
public class Monster
    extends Entity
{
    // ~ Fields ----------------------------------------------------------------
    /**
     * Minimum health that a monster can have
     */
    private static final int MIN_RAND_HEALTH = 40;

    /**
     * Maximum health that a monster can have
     */
    private static final int MAX_RAND_HEALTH = 200;

    /**
     * Minimum armor that a monster can have
     */
    private static final int MIN_RAND_ARMOR  = 0;

    /**
     * Maximum armor that a monster can have
     */
    private static final int MAX_RAND_ARMOR  = 5;

    /**
     * Randomizer for this class
     */
    private static Random    rand            = new Random();

    /**
     * Items that this monster will drop when it dies.
     */
    private ArrayList<Item>  spoils;


    // ~ Constructors ----------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Create a new Monster, giving it randomized stats and loot spoils.
     */
    public Monster()
    {
        maxHealth = health = rand.nextInt(MIN_RAND_HEALTH, MAX_RAND_HEALTH);
        armor = rand.nextInt(MIN_RAND_ARMOR, MAX_RAND_ARMOR);

        actionDeck = new ActionDeck();

        actionQueue = new ArrayBlockingQueue<ActionCard>(3);

        spoils = new ArrayList<Item>();
        this.fillSpoils();
    }


    // ----------------------------------------------------------
    /**
     * Helper method for constructor that fills the monsters "spoils bag" with
     * the appropriate amount of loot, respective to the monster's armor and max
     * health.
     */
    private void fillSpoils()
    {
        int numItems = maxHealth / 40 + armor / 2;

        for (int i = 0; i < numItems; i++)
        {
            spoils.add(new Item(true));
        }
    }


    // ----------------------------------------------------------
    /**
     * <p>
     * Gets the spoils from a dead monster.
     * </p>
     * <p>
     * This method should never be called unless a monster is dead. Otherwise it
     * will throw an exception.
     * </p>
     *
     * @return the spoils of war!
     */
    public ArrayList<Item> dropLoot()
    {
        if (this.isDead())
        {
            return spoils;
        }
        else
        {
            throw new IllegalStateException(
                "Monster attempted to drop loot before it was dead.");
        }

    }
}
