package com.dcad.finalproject;

import java.util.ArrayList;
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
        Random.setNextInts(80, 20);
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
     * Tests the dropLoot method for a still alive monster
     */
    public void testDropLootMonsterAlive()
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
    }

    // ----------------------------------------------------------
    /**
     * Tests the dropLoot method for a dead monster.
     */
    public void testDropLoot()
    {
        assertFalse(monster.isDead());

        monster.takeDamage(monster.getHealth() * (1 * monster.getArmor()));

        assertTrue(monster.isDead());

        ArrayList<Item> loot = monster.dropLoot();

        assertEquals(3, loot.size());
    }
}
