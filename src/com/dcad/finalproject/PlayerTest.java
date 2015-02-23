package com.dcad.finalproject;

import sofia.util.Random;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Test cases for both the {@link Entity} and {@link Player} classes.
 *
 * @author Cameron Rader (crader)
 * @version 2014.11.16
 */
public class PlayerTest
    extends TestCase
{
    // ~ Fields ----------------------------------------------------------------
    private Player player;


    // ~ Constructors ----------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Create a new PlayerTest object.
     */
    public PlayerTest()
    {
        // Constructors in tests should not perform any actions.
    }


    // ~ Methods ---------------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Sets up a new player.
     */
    public void setUp()
    {
        player = new Player();
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link Entity#restoreHealth(int)}.
     */
    public void testRestoreHealth()
    {
        // Normal heal case
        assertEquals(500, player.getHealth());
        player.takeDamage(100);
        assertEquals(400, player.getHealth());
        player.restoreHealth(70);
        // ---

        // Exceeding heal case
        assertEquals(470, player.getHealth());
        player.restoreHealth(50);
        assertEquals(500, player.getHealth());
        // ---
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link Entity#takeDamage(int)}.
     */
    public void testTakeDamage()
    {
        // Unarmored case
        assertEquals(500, player.getHealth());
        assertEquals(100, player.takeDamage(100));
        assertEquals(400, player.getHealth());
        // ---

        // Armored case
        player.addArmor(7);
        assertEquals(7, player.getArmor());
        assertEquals(93, player.takeDamage(100));
        assertEquals(307, player.getHealth());
        // ---

        // Armor-damage exact cancellation case
        assertEquals(0, player.takeDamage(7));
        assertEquals(307, player.getHealth());
        // ---

        // Armor-damage exceeding cancellation case
        assertEquals(0, player.takeDamage(5));
        assertEquals(307, player.getHealth());
        // ---

        // Lethal case
        assertEquals(307, player.takeDamage(314));
        assertEquals(0, player.getHealth());
        assertTrue(player.isDead());
        // ---
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link Entity#setTarget(Entity)}.
     */
    public void testSetTarget()
    {
        // Normal case
        Monster m = new Monster();
        player.setTarget(m);
        assertEquals(m, player.getTarget());
        // ---

        // Self-target case
        player.setTarget(player);
        assertEquals(m, player.getTarget());
        // ---
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link Entity#queueAction(ActionCard)}.
     */
    public void testQueueAction()
    {
        // Fill queue
        assertEquals(0, player.remainingActions());
        assertFalse(player.hasActionsLeft());
        player.queueAction(new AttackCard());
        assertTrue(player.hasActionsLeft());
        assertEquals(1, player.remainingActions());
        player.queueAction(new AttackCard());
        assertEquals(2, player.remainingActions());
        player.queueAction(new AttackCard());
        assertEquals(3, player.remainingActions());

        // Attempt to pass queue capacity
        Exception thrown = null;
        try
        {
            player.queueAction(new AttackCard());
        }
        catch (IllegalStateException e)
        {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof IllegalStateException);
    }


    // ----------------------------------------------------------
    /**
     * Test case for {@link Entity#performAction()}.
     */
    public void testPerformAction()
    {
        // Empty queue case
        Exception thrown = null;
        try
        {
            player.performAction();
        }
        catch (IllegalStateException e)
        {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof IllegalStateException);
        assertEquals(
            "Tried to perfrom an action when there were no actions to be performed.",
            thrown.getMessage());
        // ---

        Random.setNextInts(50, 0);
        player.setTarget(new Monster());

        // Fill queue, specifying card types for later use
        Random.setNextInts(3);
        player.queueAction(new AttackCard());
        assertEquals(1, player.remainingActions());

        player.queueAction(new ItemCard());
        assertEquals(2, player.remainingActions());

        Random.setNextInts(1);
        player.queueAction(new AttackCard());
        assertEquals(3, player.remainingActions());
        // ---

        // Hit attack card case
        Random.setNextDoubles(0.90);
        assertTrue(player.performAction() instanceof AttackCard);
        assertEquals(2, player.remainingActions());
        assertEquals(30, player.getTarget().getHealth());
        // ---

        // Item card case
        assertTrue(player.performAction() instanceof ItemCard);
        assertEquals(1, player.remainingActions());
        assertEquals(30, player.getTarget().getHealth());

        // Missed attack card case
        Random.setNextDoubles(0.30);
        assertTrue(player.performAction() instanceof AttackCard);
        assertEquals(0, player.remainingActions());
        assertEquals(30, player.getTarget().getHealth());
        // ---
    }
}
