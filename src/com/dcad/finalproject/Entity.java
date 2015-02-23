package com.dcad.finalproject;

import java.util.concurrent.ArrayBlockingQueue;

// -------------------------------------------------------------------------
/**
 * Represents the base class for all entities within the game including the
 * player and monsters. Provides a selection of standard methods that should be
 * accessible on all entities.
 *
 * @author Cameron Rader (crader)
 * @version 2014.11.5
 */
public abstract class Entity
{
    // ~ Fields ----------------------------------------------------------------
    /**
     * Health points. Zero or less = dead entity
     */
    protected int                            health;

    /**
     * Health points. Zero or less = dead entity
     */
    protected int                            maxHealth;
    /**
     * Armor points. One point of armor = one point of damage reduction
     */
    protected int                            armor;

    /**
     * Maximum amount of armor allowed.
     */
    protected int                            maxArmor;

    /**
     * The current target of this entity.
     */
    protected Entity                         currentTarget;

    /**
     * Deck of possible actions for entity
     */
    protected ActionDeck                     actionDeck;

    /**
     * Collection of queued actions for entity
     */
    protected ArrayBlockingQueue<ActionCard> actionQueue;


    // ~ Abstract Methods ------------------------------------------------------
    // TODO ? Might not need abstract methods.

    // ~ Defined Methods -------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Gets the health of the entity
     *
     * @return current health
     */
    public int getHealth()
    {
        return health;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     *
     * @return whether entity is dead
     */
    public boolean isDead()
    {
        return health <= 0;
    }


    // ----------------------------------------------------------
    /**
     * Gets the armor of the entity
     *
     * @return current armor
     */
    public int getArmor()
    {
        return armor;
    }


    // ----------------------------------------------------------
    /**
     * Restores health to the entity. If health restored is greater than health
     * missing, then return the entity to max health.
     *
     * @param healthRestore
     *            health restored
     */
    public void restoreHealth(int healthRestore)
    {
        health += healthRestore;
        if (health > maxHealth)
        {
            health = maxHealth;
        }
    }


    // ----------------------------------------------------------
    /**
     * Inflicts damage to the entity calling this method.
     *
     * @param damage
     *            raw damage before reductions
     * @return damage taken after reductions
     */
    public int takeDamage(int damage)
    {
        int netDamage = damage - armor;

        if (netDamage > 0)
        {
            health -= netDamage;

            if (health < 0)
                health = 0;

            return netDamage;
        }
        else
        {
            return 0;
        }

    }


    // ----------------------------------------------------------
    /**
     * Add armor points to this entity.
     *
     * @param arm
     *            armor points to add
     */
    public void addArmor(int arm)
    {
        armor += arm;
    }


    // ----------------------------------------------------------
    /**
     * Sets the current target of this entity to another specified entity.
     * Checks to make sure entity is not targeting itself.
     *
     * @param target
     *            new target of entity
     */
    public void setTarget(Entity target)
    {
        if (this != target)
        {
            currentTarget = target;
        }
    }


    // ----------------------------------------------------------
    /**
     * Gets current target of this entity.
     *
     * @return current target
     */
    public Entity getTarget()
    {
        return currentTarget;
    }


    // ----------------------------------------------------------
    /**
     * Queues a specified action to the entity's action queue. Note that the add
     * method used will throw an IllegalStateException if the queue size is
     * exceeded by the addition.
     *
     * @param act
     *            action to add
     */
    public void queueAction(ActionCard act)
    {
        actionQueue.add(act);
    }


    // ----------------------------------------------------------
    /**
     * Queues the next action deck card to the entity's action queue. Note that
     * the add method used will throw an IllegalStateException if the queue size
     * is exceeded by the addition.
     */
    public void queueAction()
    {
        actionQueue.add(actionDeck.draw());
    }


    // ----------------------------------------------------------
    /**
     * <p>
     * Performs the next action in the action queue and remove it from the
     * queue. If there is no next action, then throws an exception.
     * </p>
     * <p>
     * If the action is an attack, then apply it to the entity's target. Then,
     * no matter what type of card it is, return the card used.
     * </p>
     *
     * @return action performed
     */
    public ActionCard performAction()
    {
        ActionCard action = actionQueue.poll();

        if (action == null)
        {
            throw new IllegalStateException(
                "Tried to perfrom an action when there were no actions to be "
                    + "performed.");
        }
        else if (action.getName() == "Item")
        {
            this.useItem(((ItemCard)action).getItem());
        }
        else
        {
            int actVal = action.activate();
            if (actVal >= 0)
            {
                currentTarget.takeDamage(actVal);
            }
        }

        return action;
    }


    // ----------------------------------------------------------
    /**
     * Checks if there are actions left to perform for the entity.
     *
     * @return whether there are actions left
     */
    public boolean hasActionsLeft()
    {
        return actionQueue.size() > 0;
    }


    // ----------------------------------------------------------
    /**
     * Gets the number of remaining actions in the action queue of this entity.
     *
     * @return actions left
     */
    public int remainingActions()
    {
        return actionQueue.size();
    }


    // ----------------------------------------------------------
    /**
     * Uses the given item according to what type it is. Currently supports
     * damage, healing, and armor increases.
     *
     * @param selected
     *            The Item selected to be used.
     */
    public void useItem(Item selected)
    {
        if (selected != null)
        {
            String type = selected.getName();

            if (type == "DAMAGE")
            {
                currentTarget.takeDamage(selected.getItemPower()
                    * (currentTarget.getArmor() + 1));
            }

            else if (type == "POTION")
            {
                this.restoreHealth(selected.getItemPower());
            }

            else if (type == "ARMOR")
            {
                this.addArmor(selected.getItemPower());
            }
        }
    }
}
