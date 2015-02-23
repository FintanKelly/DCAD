package com.dcad.finalproject;

import sofia.util.Random;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Test cases for only the {@link Monster} class.
 *
 * @author Cameron Rader (crader)
 * @version 2014.11.16
 */
public class MonsterTest
    extends TestCase
{
    // ~ Fields ----------------------------------------------------------------
    private Monster monster;


    // ~ Constructors ----------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Create a new MonsterTest object.
     */
    public MonsterTest()
    {
        // Constructors in tests should not perform any actions.
    }


    // ~ Methods ---------------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Creates a new standard monster with preset stats
     */
    public void setUp()
    {
        Random.setNextInts(80, 3);
        monster = new Monster();
    }


    // ----------------------------------------------------------
    /**
     * Tests the randomization of monster construction.
     */
    public void testMonster()
    {
        assertEquals(80, monster.getHealth());
        assertEquals(3, monster.getArmor());

        // TODO: !! test spoils generation when spoils are implemented.
    }


    /**
     * Tests the dropLoot method
     */
    public void testDropLoot()
    {
        // Monster not dead case
        Exception thrown = null;
        try
        {
            monster.dropLoot();
        }
        catch (IllegalStateException e)
        {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof IllegalStateException);
        assertEquals(
            "Monster attempted to drop loot before it was dead.",
            thrown.getMessage());
        // ---

        // Dead monster case
        // TODO: !! test spoils drop when spoils are implemented.
        // ---
    }
}
