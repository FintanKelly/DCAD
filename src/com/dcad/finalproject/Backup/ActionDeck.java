package com.dcad.finalproject;

import sofia.util.Random;
import java.util.Stack;

// -------------------------------------------------------------------------
/**
 * Represents a deck of {@link ActionCard}s and provides standard methods of
 * accessing and manipulating the deck. The deck of cards is represented by a
 * stack implementation.
 *
 * @author Cameron Rader (crader)
 * @version 2014.11.5
 */
public class ActionDeck
{
    // ~ Fields ----------------------------------------------------------------
    /**
     * Stores the collection of cards to be wrapped by this class
     */
    private Stack<ActionCard> deck;

    /**
     * Random number generator to generate random cards in the deck
     */
    private Random            rand;

    /**
     * Maximum size of the deck
     */
    private int               maxSize;


    // ~ Constructors ----------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Create a new ActionDeck object by initializing the deck and filling it.
     */
    public ActionDeck()
    {
        deck = new Stack<ActionCard>();
        maxSize = 64;

        rand = new Random();

        this.refreshDeck();
    }


    // ----------------------------------------------------------
    /**
     * Create a new ActionDeck object of a specified max size by initializing
     * the deck and filling it.
     *
     * @param size
     *            maximum size of the deck
     */
    public ActionDeck(int size)
    {
        deck = new Stack<ActionCard>();
        maxSize = size;

        rand = new Random();

        this.refreshDeck();
    }


    // ~ Private Methods -------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Randomly generates a new deck of cards.
     */
    private void refreshDeck()
    {
        deck.clear();

        for (int i = 0; i < maxSize; i++)
        {
            if (rand.nextInt(2) == 0)
            {
                deck.push(new AttackCard());
            }
            else
            {
                deck.push(new ItemCard());
            }

        }
    }


    // ~ Public Methods --------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Get the top card from the deck. If the deck is empty, then first refresh
     * the deck and then get the top card.
     *
     * @return the top card in the deck
     */
    public ActionCard draw()
    {
        if (deck.isEmpty())
        {
            this.refreshDeck();
        }

        return deck.pop();
    }


    // ----------------------------------------------------------
    /**
     * <p>
     * Removes the specified number of cards from the top of the deck.
     * </p>
     * <p>
     * If the number specified is greater than or equal to the number of
     * remaining cards then just remove the remaining cards and refresh the deck
     * without removing any more.
     * </p>
     *
     * @param numCards
     *            number of card to withdraw
     */
    public void discardTopCards(int numCards)
    {
        if (numCards >= deck.size())
        {
            this.refreshDeck();
        }
        else
        {
            for (int i = 0; i < numCards; i++)
            {
                deck.pop();
            }
        }

    }


    // ----------------------------------------------------------
    /**
     * Gets the number of cards left the deck.
     *
     * @return cards left
     */
    public int cardsLeft()
    {
        return deck.size();
    }
}
