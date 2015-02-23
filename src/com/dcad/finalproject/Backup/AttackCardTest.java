package com.dcad.finalproject;

import sofia.util.Random;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Test cases for {@link AttackCard}.
 *
 * @author Cameron Rader (crader)
 * @version 2014.11.5
 */
public class AttackCardTest
    extends TestCase
{
    // ~ Fields ----------------------------------------------------------------
    private AttackCard card;


    // ~ Constructors ----------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Create a new ActionDeckTest object.
     */
    public AttackCardTest()
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
        Random.setNextInts(0);
        card = new AttackCard();
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link AttackCard} constructor to ensure that cards are
     * properly generated.
     */
    public void testAttackCard()
    {
        assertEquals("SLASH", card.getName());
        assertEquals(10, card.getDamage());
        assertEquals(0.80, card.getAccuracy(), 0.001);

        Random.setNextInts(1);
        card = new AttackCard();
        assertEquals("MAUL", card.getName());
        assertEquals(15, card.getDamage());
        assertEquals(0.75, card.getAccuracy(), 0.001);

        Random.setNextInts(2);
        card = new AttackCard();
        assertEquals("STAB", card.getName());
        assertEquals(5, card.getDamage());
        assertEquals(0.90, card.getAccuracy(), 0.001);

        Random.setNextInts(3);
        card = new AttackCard();
        assertEquals("LUNGE", card.getName());
        assertEquals(20, card.getDamage());
        assertEquals(0.65, card.getAccuracy(), 0.001);
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link AttackCard#activate()}. Checks for accuracy roll
     * failures and successes.
     */
    public void testActivate()
    {
        Random.setNextDoubles(0.82);
        assertEquals(10, card.activate());

        Random.setNextDoubles(0.78);
        assertEquals(0, card.activate());

        Random.setNextDoubles(0.80);
        assertEquals(10, card.activate());
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link AttackCard#toString()}
     */
    public void testToString()
    {
        String expected = "SLASH type attack/nDamage: 10/nAccuracy: 80%";

        assertEquals(expected, card.toString());
    }
}
