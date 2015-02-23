package com.dcad.finalproject;

import sofia.util.Random;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Test cases for the {@link ActionDeck} class.
 *
 * @author Cameron Rader (crader)
 * @version 2014.11.5
 */
public class ActionDeckTest
    extends TestCase
{
    // ~ Fields ----------------------------------------------------------------
    private ActionDeck deck;


    // ~ Constructors ----------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Create a new ActionDeckTest object.
     */
    public ActionDeckTest()
    {
        // Constructors in tests should not perform any actions.
    }


    // ~ Methods ---------------------------------------------------------------
    // ----------------------------------------------------------
    /**
     * <p>
     * Sets up the standard initial case. Note that the deck is set to a
     * specific configuration of cards for testing purposes.
     * </p>
     * <p>
     * We insert dummy values (3's) into the Random next ints so that our
     * desired random ints for the deck are not consumed in the randomization of
     * the cards.
     * </p>
     */
    public void setUp()
    {
        Random.setNextInts(0, 3, 1, 0, 3);
        deck = new ActionDeck(3);
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link ActionDeck#draw()}. Empties deck, checking
     * conditions as cards are removed and then ensures that the deck is
     * refilled correctly.
     */
    public void testDraw()
    {
        assertEquals(3, deck.cardsLeft());

        assertTrue(deck.draw() instanceof AttackCard);
        assertEquals(2, deck.cardsLeft());

        assertTrue(deck.draw() instanceof ItemCard);
        assertEquals(1, deck.cardsLeft());

        assertTrue(deck.draw() instanceof AttackCard);
        assertEquals(0, deck.cardsLeft());

        // Tests refresh case, when deck is out of cards
        Random.setNextInts(0, 3, 1, 1);
        assertTrue(deck.draw() instanceof ItemCard);
        assertEquals(2, deck.cardsLeft());
    }


    // ----------------------------------------------------------
    /**
     * Test method for{@link ActionDeck#discardTopCards(int)}. Cases include
     * negative, zero, and greater than deck size parameters.
     */
    public void testDiscardTopCards()
    {
        assertEquals(3, deck.cardsLeft());

        deck.discardTopCards(-1);
        assertEquals(3, deck.cardsLeft());

        deck.discardTopCards(0);
        assertEquals(3, deck.cardsLeft());

        deck.discardTopCards(2);
        assertEquals(1, deck.cardsLeft());

        deck.discardTopCards(1);
        assertEquals(3, deck.cardsLeft());

        deck.discardTopCards(4);
        assertEquals(3, deck.cardsLeft());
    }
}
