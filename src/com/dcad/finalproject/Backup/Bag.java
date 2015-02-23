package com.dcad.finalproject;

import java.util.ArrayList;

/**
 * This class will act as the 'bag' of the character; it will hold all of the
 * items that the character obtains. The bag will consist of a 2D array of
 * items, representing the grid that one would normally find as an bag. The bag
 * will handle the addition of items into itself and other functions it might
 * need. This class allows for many features such as merging two inventories,
 * adding and removing items, and more.
 *
 * @author Fintan Kelly (fintank)
 * @version 2014.10.30
 */
public class Bag
{
    /**
     * This will be the 2D array that holds all of the character's items
     */
    private Item[][]     list;

    /**
     * Boolean to keep track of if the bag is empty
     */
    private boolean      empty;

    /**
     * Keeps track of the size of the bag
     */
    private int          size;

    /**
     * Keeps track of the first empty bag slot in the list
     */
    private EmptyBagSlot emptySlot;


    /**
     * The default constructor will simply initialize a bag of size 3 with blank
     * items, or nothing
     */
    public Bag()
    {
        list = new Item[3][3];

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                list[i][j] = null;
            }
        }

        size = 3;

        empty = true;

        emptySlot = new EmptyBagSlot();
    }


    /**
     * Creates a new bag that is of size newSize and initializes all of the
     * slots to blank items, or nothing
     *
     * @param newSize
     *            the size of the new bag
     */
    public Bag(int newSize)
    {
        list = new Item[newSize][newSize];

        for (int i = 0; i < newSize; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                list[i][j] = null;
            }
        }

        size = newSize;

        empty = true;

        emptySlot = new EmptyBagSlot();
    }


    /**
     * Creates a new bag from a pre-existing one
     *
     * @param newBag
     *            the pre-existing bag
     */
    public Bag(Bag newBag)
    {
        list = newBag.getBag();

        size = newBag.getSize();

        empty = newBag.isEmpty();

        emptySlot = newBag.getEmptySlot();
    }


    /**
     * Adds the item to the first empty slot in the list
     *
     * @param item
     *            item that is being added
     */
    public void addItem(Item item)
    {
        list[emptySlot.getRow()][emptySlot.getCol()] = item;
        emptySlot.increaseSlot();
    }


    /**
     * Adds the item to the slot at (row, col)
     *
     * @param item
     *            item that is being added to the bag
     * @param row
     *            row location of where the item is being added
     * @param col
     *            column location of where the item is being added
     */
    public void addItem(Item item, int row, int col)
    {
        if ((row < size && col < size) && (row >= 0 && col >= 0))
        {
            if (list[row][col] == null)
            {
                list[row][col] = item;

                if (empty)
                {
                    empty = false;
                }

                if (row == emptySlot.getRow() && col == emptySlot.getCol())
                {
                    emptySlot.increaseSlot();
                }
            }
            else
            {
                addItem(item);
            }
        }
        else
        {
            // Print this to the screen as well
            System.out
                .println("The slot you are trying to add an item to does not exist.");
        }
    }


    /**
     * Will add the list of items to the current bag
     *
     * @param items
     *            list of items to add
     */
    public void addItems(ArrayList<Item> items)
    {
        for (Item item : items)
        {
            addItem(item);
        }
    }


    /**
     * Removes the item that is in the slot (row, col)
     *
     * @param row
     *            row location of the item
     * @param col
     *            column location of the item
     */
    public void removeItem(int row, int col)
    {
        if ((row < size && col < size) && (row >= 0 && col >= 0))
        {
            if (list[row][col] != null)
            {
                list[row][col] = null;

                if (emptySlot.getRow() > row || emptySlot.getCol() > col)
                {
                    emptySlot.setRow(row);
                    emptySlot.setCol(col);
                }
            }
        }
        else
        {
            // Print this to the screen as well
            System.out.println("You are trying to remove an item from "
                + "a slot that doesn't exist.");
        }
    }


    /**
     * Will move the item at location row, col to the new location of newRow,
     * newCol
     *
     * @param row
     *            row of the item that is moving
     * @param col
     *            column of the item that is moving
     * @param newRow
     *            new row that the item is moving to
     * @param newCol
     *            new column that the item is moving to
     */
    public void moveItem(int row, int col, int newRow, int newCol)
    {
        if (((row < size && col < size) && (row >= 0 && col >= 0))
            && ((newRow < size && newCol < size) && (newRow >= 0) && newCol >= 0))
        {
            if (list[newRow][newCol] == null && list[row][col] == null)
            {
                // Just for testing purposes
                System.out.println("There are no items to move");
            }
            else if (list[newRow][newCol] == null)
            {
                list[newRow][newCol] = list[row][col];
                list[row][col] = null;
            }
            else if (list[row][col] == null)
            {
                list[row][col] = list[newRow][newCol];
                list[newRow][newCol] = null;
            }
            else
            {
                Item temp = list[newRow][newCol];
                list[newRow][newCol] = list[row][col];
                list[row][col] = temp;
            }
        }
        else
        {
            // Print this to the screen as well
            System.out
                .println("You are either trying to move an item in a slot that "
                    + "doesn't exist, or moving an item to a slot that doesn't exist");
        }
    }


    /**
     * Will merge the contents of one bag with another, as long as the bag that
     * is gaining the contents isn't full
     *
     * @param added
     *            bag that is being merged
     */
    public void mergeBag(Bag added)
    {
        for (int i = 0; i < added.getSize(); i++)
        {
            for (int j = 0; j < added.getSize(); j++)
            {
                if (!isFull())
                {
                    addItem(added.getItem(i, j));
                    added.removeItem(i, j);
                }
                else
                {
                    // Print this to the screen as well
                    System.out
                        .println("You are trying to add items to an already full bag.");
                }
            }
        }
    }


    /**
     * Clears the bag and fills it with empty items, or nothing
     */
    public void clearBag()
    {
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                list[i][j] = null;
            }
        }
    }


    /**
     * Will return the item that is in the indicated slot
     *
     * @param row
     *            row location of the item
     * @param col
     *            column location of the item
     * @return either the item that is in the indicated slot, or null if there
     *         is no such slot
     */
    public Item getItem(int row, int col)
    {
        return ((row < size && col < size) && (row >= 0 && col >= 0))
            ? list[row][col]
            : null;
    }


    /**
     * Returns the first empty slot in the list
     *
     * @return EmptyBagSlot of the first empty slot
     */
    public EmptyBagSlot getEmptySlot()
    {
        return emptySlot;
    }


    /**
     * Will return true or false depending on if the bag is full or not
     *
     * @return True/False if bag is full or not
     */
    public boolean isFull()
    {
        return checkFull();
    }


    /**
     * Checks the list to see if there are any empty slots
     *
     * @return True/False depending on if there are any empty slots
     */
    private boolean checkFull()
    {
        for (int i = 0; i < size; i++)
        {
            if (list[i][i] == null)
            {
                return false;
            }
        }

        return true;
    }


    /**
     * Will return true or false depending on if the bag is empty or not
     *
     * @return True/False if bag is empty or not
     */
    public boolean isEmpty()
    {
        return checkEmpty();
    }


    /**
     * Checks the list to see if there are any items in it
     *
     * @return True/False depending on if the list if void of items
     */
    private boolean checkEmpty()
    {
        for (int i = 0; i < size; i++)
        {
            if (list[i][i] != null)
            {
                empty = false;
                return false;
            }
        }

        empty = true;
        return true;
    }


    /**
     * Returns the 2D array of the current bag
     *
     * @return 2D array representation of the bag
     */
    public Item[][] getBag()
    {
        return list;
    }


    /**
     * Returns the size of the bag
     *
     * @return integer size of bag
     */
    public int getSize()
    {
        return size;
    }


    /**
     * Keeps track of the first empty slot in the Inventory. Simplifies the
     * operations of adding items to the Inventory without having to worry about
     * finding the first usable slot.
     *
     * @author Fintan Kelly (fintank)
     * @version 2014.10.30
     */
    private class EmptyBagSlot
    {
        /**
         * Will be used to keep track of the position within the 2D Inventory
         */
        private int row;
        private int col;


        /**
         * Sets the default empty location to (0, 0)
         */
        public EmptyBagSlot()
        {
            row = 0;
            col = 0;
        }


        /**
         * Sets the new row location of the empty slot
         *
         * @param num
         *            new row number
         */
        public void setRow(int num)
        {
            if (row < size && row >= 0)
            {
                row = num;
            }
        }


        /**
         * Sets the new column location of the empty slot
         *
         * @param num
         *            mew column number
         */
        public void setCol(int num)
        {
            if (col < size && col >= 0)
            {
                col = num;
            }
        }


        /**
         * Returns the row of the empty slot
         *
         * @return integer of the row
         */
        public int getRow()
        {
            return row;
        }


        /**
         * Returns the column of the empty slot
         *
         * @return integer of the column
         */
        public int getCol()
        {
            return col;
        }


        /**
         * Updates the EmptyBagSlot to point to the new first usable empty slot
         */
        public void increaseSlot()
        {
            if (col == (size - 1))
            {
                col = 0;
                row++;
            }
            else
            {
                col++;
            }
        }
    }
}
