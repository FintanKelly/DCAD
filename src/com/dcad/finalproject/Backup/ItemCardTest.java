package com.dcad.finalproject;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Test cases for {@link ItemCard}.
 *
 * @author Cameron Rader (crader)
 * @version 2014.11.5
 */
public class ItemCardTest
    extends TestCase
{
    // ~ Fields ----------------------------------------------------------------
    private ItemCard card;


    // ~ Constructors ----------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Create a new ActionDeckTest object.
     */
    public ItemCardTest()
    {
        // Constructors in tests should not perform any actions.
    }


    // ~ Methods ---------------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Sets up a new card with a pre-defined variant.
     */
    public void setUp()
    {
        card = new ItemCard();
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link ItemCard#activate()}.
     */
    public void testActivate()
    {
        assertEquals(-1, card.activate());
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link ItemCard#getName()}.
     */
    public void testGetName()
    {
        assertEquals("Item", card.getName());
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link ItemCard#toString()}
     */
    public void testToString()
    {
        assertEquals("Use a single item from your inventory.", card.toString());
    }
}
