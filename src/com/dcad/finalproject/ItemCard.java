package com.dcad.finalproject;

// -------------------------------------------------------------------------
/**
 * Represents a specific {@link ActionCard} that acts as an inventory item
 * action. An item card when activated will signal that the inventory must be
 * opened to select an item to use.
 *
 * @author Cameron Rader (crader)
 * @version 2014.11.5
 */
public class ItemCard
    extends ActionCard
{
    // ~ Fields ----------------------------------------------------------------
    /**
     * The Item that is linked to this card
     */
    Item currentItem;


    // ~ Constructors ----------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Create a new ItemCards object.
     */
    public ItemCard()
    {
        currentItem = null;
    }


    // ~ Methods ---------------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Always returns -1, this will signal an inventory open prompt.
     */
    @Override
    public int activate()
    {
        return -1;
    }


    // ----------------------------------------------------------
    /**
     * Since there are no variants of an item card, always returns "Item".
     */
    @Override
    public String getName()
    {
        return "Item";
    }


    // ----------------------------------------------------------
    /**
     * Gets a general description of the card.
     *
     * @return card info
     */
    @Override
    public String toString()
    {
        return "Use a single item from your inventory.";
    }


    // ----------------------------------------------------------
    /**
     * Sets the item to be used in the item card.
     *
     * @param given
     *            The item to go in the Item card.
     * @return The item previously in the card, returns null if no item.
     */
    public Item setItem(Item given)
    {
        Item temp = currentItem;
        currentItem = given;

        return temp;
    }


    // ----------------------------------------------------------
    /**
     * @return Returns the current item in the card.
     */
    public Item getItem()
    {
        return currentItem;
    }

}
