package com.dcad.finalproject;

import java.util.Stack;

// -------------------------------------------------------------------------
/**
 * Main model class that brings all elements of the model together. This is the
 * class that will interact with the controller for the app.
 *
 * @author Cameron Rader (crader)
 * @version 2014.11.5
 */
public class Dungeon
{
    // ~ Fields ----------------------------------------------------------------
    /**
     * Counter that tracks number of enemies slain in the game.
     */
    @SuppressWarnings("unused")
    private int               enemiesSlain;

    /**
     * Represents user's character
     */
    private Player            player;

    /**
     * Inventory of the user/player
     */
    private Bag               playerInventory;
    // TODO: move functionality of inventory into Player class??

    /**
     * Current monster the player is fighting. This will always be replaced by a
     * new Monster if the current one is dead. This should always be the
     * player's target.
     */
    private Monster           currentMonster;

    /**
     * Stores the actions already performed in the model by the player
     */
    private Stack<ActionCard> playerActionsPerformed;
    //FIXME: might not need this, but could be useful in view interaction


    // ~ Constructors ----------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Create a new Dungeon object.
     */
    public Dungeon()
    {
        enemiesSlain = 0;

        player = new Player();

        playerInventory = new Bag();

        currentMonster = new Monster();

    }


    // ~ Methods ---------------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Processes current turn once all actions are picked. FIXME: in-progress
     */
    public void processTurn()
    {
        while (player.hasActionsLeft())
        {
            playerActionsPerformed.push(player.performAction());
        }

        if (currentMonster.isDead())
        {
            // TODO: functionality changes?
            enemiesSlain++;

            playerInventory.addItems(currentMonster.dropLoot());

            currentMonster = new Monster();
        }

    }
}
