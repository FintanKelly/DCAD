package com.dcad.finalproject;

import sofia.util.Observable;
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
    extends Observable
{
    // ~ Fields ----------------------------------------------------------------
    /**
     * Counter that tracks number of enemies slain in the game.
     */
    private int               enemiesSlain;

    /**
     * Represents user's character
     */
    private Player            player;

    /**
     * Inventory of the user/player
     */
    private Bag               playerInventory;

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
    // FIXME: might not need this, but could be useful in view interaction

    /**
     * Stores the actions already performed in the model by the current monster.
     */
    private Stack<ActionCard> monsterActionsPerformed;

    /**
     * Amount of enemies needed to kill before winning.
     */
    private int               difficultyAmount;

    /**
     * This is multiplied by the difficultyLevel to give the number of enemies
     * you need to kill to win.
     */
    private final int         difficultyModifier = 5;

    private ActionCard        hand[];

    private ActionCard        currentCard;


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
        currentMonster.setTarget(player);
        player.setTarget(currentMonster);

        playerActionsPerformed = new Stack<ActionCard>();
        monsterActionsPerformed = new Stack<ActionCard>();

        setDifficulty(1);

        hand = new ActionCard[3];

        dealHand();
    }


    // ~ Methods ---------------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Puts the given item into the ItemCard.
     *
     * @param selected
     */
    public void selectItem(Item selected)
    {
        if (currentCard instanceof ItemCard)
        {
            ((ItemCard)currentCard).setItem(selected);
        }

        notifyObservers();
    }


    // ----------------------------------------------------------
    /**
     * Sets the current card to an input index.
     *
     * @param selected
     *            The index of the card in the hand array to become the new
     *            selected card.
     */
    public void selectCard(int selected)
    {
        if (selected < hand.length)
        {
            currentCard = hand[selected];

            notifyObservers();
        }
    }


    // ----------------------------------------------------------
    /**
     * Gets currently selected card
     *
     * @return current card
     */
    public ActionCard getSelectedCard()
    {
        return currentCard;
    }


    // ----------------------------------------------------------
    /**
     * Plays the currently selected card and fights a turn with the monster.
     */
    public void playCard()
    {
        player.queueAction(currentCard);

        processTurn();

        dealHand();
    }


    // ----------------------------------------------------------
    /**
     * Processes current turn once all actions are picked.
     */
    private void processTurn()
    {

        if (player.hasActionsLeft() && !(gameLost() || gameWon()))
        {
            ActionCard use = player.performAction();

            /*if (use.getName() == "Item")
            {
                ItemCard itemSelect = (ItemCard)use;
                player.useItem(itemSelect.getItem());
            }
            else
            {
                AttackCard playerAttack = new AttackCard();
                player.queueAction(playerAttack);
                player.performAction();
            }*/

            playerActionsPerformed.push(use);

            if (!currentMonster.isDead())
            {
                AttackCard monsterAttack = new AttackCard();
                currentMonster.queueAction(monsterAttack);
                currentMonster.performAction();

                monsterActionsPerformed.push(monsterAttack);
            }

            else
            {
                newMonster();
            }
        }

        notifyObservers();
    }


    // ----------------------------------------------------------
    /**
     * Creates a new monster if the current one is dead.
     */
    private void newMonster()
    {
        if (currentMonster.isDead())
        {
            enemiesSlain++;

            playerInventory.addItems(currentMonster.dropLoot());

            monsterActionsPerformed.clear();

            currentMonster = new Monster();
            currentMonster.setTarget(player);
            player.setTarget(currentMonster);
        }
    }


    // ----------------------------------------------------------
    /**
     * Returns current card hand
     *
     * @return current hand
     */
    public ActionCard[] getHand()
    {
        return hand;
    }


    // ----------------------------------------------------------
    /**
     * Deals a new hand of actionCards and sets the current card to the first
     * card.
     */
    private void dealHand()
    {
        for (int i = 0; i < hand.length; i++)
        {
            hand[i] = player.getTopCard();
        }

        currentCard = hand[0];

        notifyObservers();
    }


    // ----------------------------------------------------------
    /**
     * @return The last action the player performed.
     */
    public ActionCard getLastPlayerAction()
    {
        if (playerActionsPerformed.isEmpty())
        {
            return null;
        }
        else
            return playerActionsPerformed.peek();
    }


    // ----------------------------------------------------------
    /**
     * @return The last action the monster performed.
     */
    public ActionCard getLastMonsterAction()
    {
        if (monsterActionsPerformed.isEmpty())
        {
            return null;
        }
        else
            return monsterActionsPerformed.peek();
    }


    // ----------------------------------------------------------
    /**
     * @return True if the monster is dead
     */
    public boolean monsterKilled()
    {
        return currentMonster.isDead();
    }


    // ----------------------------------------------------------
    /**
     * @return True if the player is dead, false otherwise.
     */
    public boolean gameLost()
    {
        return player.isDead();
    }


    // ----------------------------------------------------------
    /**
     * @return True if game is won, false if otherwise.
     */
    public boolean gameWon()
    {
        return (enemiesSlain >= difficultyAmount);
    }


    // ----------------------------------------------------------
    /**
     * Sets the difficulty of the game, directly related to how many enemies
     * needed to kill to win.
     *
     * @param level
     *            Number between 1 and 10
     */
    public void setDifficulty(int level)
    {
        if (level >= 1 && level <= 10)
        {
            difficultyAmount = level * difficultyModifier;
        }
    }


    // ----------------------------------------------------------
    /**
     * Gets number of remaining monsters to slay in order to win.
     *
     * @return number of monsters to slay
     */
    public int getMonstersRemaining()
    {
        return difficultyAmount - enemiesSlain;
    }


    // ----------------------------------------------------------
    /**
     * @return The player's inventory.
     */
    public Bag getInventory()
    {
        return playerInventory;
    }


    // ----------------------------------------------------------
    /**
     * @return The player's health
     */
    public int getPlayerHealth()
    {
        return player.getHealth();
    }


    // ----------------------------------------------------------
    /**
     * @return How much health the monster has.
     */
    public int getMonsterHealth()
    {
        return currentMonster.getHealth();
    }


    // ----------------------------------------------------------
    /**
     * @return The player's armor
     */
    public int getPlayerArmor()
    {
        return player.getArmor();
    }


    // ----------------------------------------------------------
    /**
     * @return How much armor the monster has.
     */
    public int getMonsterArmor()
    {
        return currentMonster.getArmor();
    }


    // ----------------------------------------------------------
    /**
     * @return The difficulty level of the game.
     */
    public int getDifficulty()
    {
        return difficultyAmount / difficultyModifier;
    }


    // ----------------------------------------------------------
    /**
     * Method used for debugging and testing.
     */
    protected void killCurrentMonster()
    {
        currentMonster.takeDamage(getMonsterHealth() * (1 + getMonsterArmor()));

        enemiesSlain++;
    }
}
