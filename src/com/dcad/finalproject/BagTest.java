package com.dcad.finalproject;

import java.util.ArrayList;
import student.TestCase;

/**
 * This will test the functionality of the Bag class and make sure that it will,
 * in theory, work correctly in practice
 *
 * @author Fintan Kelly (fintank)
 * @version 2014.11.17
 */
public class BagTest
    extends TestCase
{
    private Bag             bag;
    private ArrayList<Item> itemList;
    /**
     * Constructor has no purpose in this test case
     */
    public BagTest()
    {
        // Doesn't perform any actions in this test case
    }



    /**
     * Initial setup of the test cases
     */
    public void setUp()
    {
        bag = new Bag();
        itemList = new ArrayList<Item>();
    }


    /**
     * Tests all of the constructors
     */
    public void testConstructors()
    {
        assertTrue(bag.isEmpty());
        assertFalse(bag.isFull());
        assertEquals(bag.getSize(), 3);
        assertNotNull(bag.getEmptySlot());
        assertNull(bag.getItem(1, 0));

        Bag bag2 = new Bag(5);
        assertEquals(bag2.getSize(), 5);

        Bag bag3 = new Bag(bag2);
        assertEquals(bag3.getSize(), 5);
    }


    /**
     * Tests the addItem method
     */
    public void testAddItem()
    {
        bag.addItem(new Item(), 0, 0);

        assertNotNull(bag.getItem(0, 0));
        assertFalse(bag.isEmpty());
        assertFalse(bag.isFull());

        bag.addItem(new Item(), 10, 10);

        itemList.add(new Item());
        itemList.add(new Item());

        bag.addItems(itemList);

        assertNotNull(bag.getItem(0, 2));

        bag.addItem(new Item(), 2, 2);
        bag.addItem(new Item(), 2, 2);

        assertNotNull(bag.getItem(1, 0));
    }


    /**
     * Tests the removeItem method
     */
    public void testRemoveItem()
    {
        bag.removeItem(10, 10);

        bag.addItem(new Item());
        bag.addItem(new Item(), 0, 1);
        bag.removeItem(0, 0);

        assertNull(bag.getItem(0, 0));

        bag.addItem(new Item());

        assertNotNull(bag.getItem(0, 0));

    }


    /**
     * Tests the moveItem method
     */
    public void testMoveItem()
    {
        bag.moveItem(10, 10, 0, 0);

        bag.moveItem(0, 0, 0, 1);

        assertNull(bag.getItem(0, 0));
        assertNull(bag.getItem(0, 1));

        bag.addItem(new Item());
        bag.addItem(new Item(), 1, 0);

        bag.moveItem(0, 0, 1, 0);

        assertNotNull(bag.getItem(0, 0));
        assertNotNull(bag.getItem(1, 0));

        bag.removeItem(1, 0);

        bag.moveItem(0, 0, 1, 0);

        assertNotNull(bag.getItem(1, 0));
        assertNull(bag.getItem(0, 0));

        bag.moveItem(1, 0, 0, 0);

        assertNotNull(bag.getItem(0, 0));
        assertNull(bag.getItem(1, 0));
    }


    /**
     * Tests the mergeBag method
     */
    public void testMergeBag()
    {
        for (int i = 0; i < (bag.getSize() * bag.getSize()); i++)
        {
            itemList.add(new Item());
        }

        Bag bag2 = new Bag();
        bag2.addItems(itemList);

        bag.addItem(new Item());
        bag.mergeBag(bag2);

        assertTrue(bag.isFull());
        assertFalse(bag2.isEmpty());
    }


    /**
     * Tests the clearBag method
     */
    public void testClearBag()
    {
        bag.addItem(new Item());
        bag.clearBag();

        assertTrue(bag.isEmpty());
        assertNull(bag.getItem(0, 0));
    }

}
