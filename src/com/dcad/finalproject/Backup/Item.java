package com.dcad.finalproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import sofia.util.Random;

/**
 * This class will be the representation for objects in the game that will be
 * placed within the Inventory. The easiest implementation of this class is to
 * simply use one of the constructors and then the class implementation will
 * handle most of the complexity itself.
 *
 * @author Fintan Kelly (fintank)
 * @version 2014.11.4
 */
public class Item
{
    /**
     * This HashSet will hold all of the special attributes that any Item could
     * hold
     */
    private HashMap<String, Integer> attributes;

    /**
     * Will hold all of the ranges for randomization of attributes
     */
    private ArrayList<RandomRange>   randoms;

    /**
     * Strings that will hold the names and descriptions of individual items
     */
    private String                   name;
    private String                   description;

    /**
     * Random number generator that will be used for assigning random values to
     * attributes
     */
    private Random                   gen;


    /**
     * Default constructor that will set the name and descriptions to nothing
     * and give the item no special attributes
     */
    public Item()
    {
        name = "";
        description = "";

        setupAttributes();
    }


    /**
     * Create a new Item object with an empty name, description, but random
     * attributes
     *
     * @param random
     *            Whether or not a random attribute and value is generated
     */
    public Item(boolean random)
    {
        name = "";
        description = "";

        setupAttributes();

        if (random)
        {
            randomizeAllAttributes();
        }
    }


    /**
     * Creates an Item with the given name and sets the default description to
     * nothing with no special attributes
     *
     * @param newName
     *            The specified name of the Item
     */
    public Item(String newName)
    {
        name = newName;
        description = "";

        setupAttributes();
    }


    /**
     * Creates an Item with the given name and description with no special
     * attributes
     *
     * @param newName
     *            The specified name of the Item
     * @param newDesc
     *            The specified description of the Item
     */
    public Item(String newName, String newDesc)
    {
        name = newName;
        description = newDesc;

        setupAttributes();
    }


    /**
     * Creates an Item with the given name, description, and attribute
     *
     * @param newName
     *            The specified name of the Item
     * @param newDesc
     *            The specified description of the Item
     * @param newAttribute
     *            The specified attribute of the item
     */
    public Item(String newName, String newDesc, String newAttribute)
    {
        this(newName, newDesc);

        setupAttributes();

        attributes.put(newAttribute, 0);
    }


    /**
     * Create a new Item object with the given name, description, attribute, and
     * attribute value
     *
     * @param newName
     *            The specified name of the Item
     * @param newDesc
     *            The specified description of the Item
     * @param newAttribute
     *            The specified attribute of the item
     * @param attributeValue
     *            The specified value of the attribute
     */
    public Item(
        String newName,
        String newDesc,
        String newAttribute,
        int attributeValue)
    {
        this(newName, newDesc);

        setupAttributes();

        attributes.put(newAttribute, attributeValue);
    }


    /**
     * Creates an Item with the given name, description, and attributes
     *
     * @param newName
     *            The specified name of the Item
     * @param newDesc
     *            The specified description of the Item
     * @param newAttributes
     *            The specified List of attributes of the Item
     */
    public Item(String newName, String newDesc, ArrayList<String> newAttributes)
    {
        this(newName, newDesc);

        setupAttributes();

        for (String str : newAttributes)
        {
            attributes.put(str, 0);
        }
    }


    /**
     * Create a new Item object with the given name, description, attributes,
     * and attribute values
     *
     * @param newName
     *            The specified name of the Item
     * @param newDesc
     *            The specified description of the Item
     * @param newAttributes
     *            The specified HashMap of attributes of the Item
     */
    public Item(
        String newName,
        String newDesc,
        HashMap<String, Integer> newAttributes)
    {
        this(newName, newDesc);

        setupAttributes();

        for (Map.Entry<String, Integer> attribute : newAttributes.entrySet())
        {
            attributes.put(attribute.getKey(), attribute.getValue());
        }
    }


    /**
     * Create a new Item object with the given name, description, and if wanted,
     * completely random attributes
     *
     * @param newName
     *            The specified name of the Item
     * @param newDesc
     *            The specified description of the Item
     * @param random
     *            Whether or not a random attribute and value is generated
     */
    public Item(String newName, String newDesc, boolean random)
    {
        this(newName, newDesc);

        setupAttributes();

        if (random)
        {
            randomizeAllAttributes();
        }
    }


    /**
     * Create a new Item object with the given name, description, and if wanted,
     * a random value to a random attribute
     *
     * @param newName
     *            The specified name of the Item
     * @param newDesc
     *            The specified description of the Item
     * @param newAttribute
     *            The specified attribute of the Item
     * @param random
     *            Whether or not a random attribute and value is generated
     */
    public Item(
        String newName,
        String newDesc,
        String newAttribute,
        boolean random)
    {
        this(newName, newDesc);

        setupAttributes();

        if (random)
        {
            randomizeAttributeAndValue(newAttribute);
        }
    }


    /**
     * Create a new Item object with the specified name, description, attribute,
     * and random attribute value
     *
     * @param newName
     *            The specified name of the Item
     * @param newDesc
     *            The specified description of the Item
     * @param newAttribute
     *            The specified attribute of the Item
     * @param lowVal
     *            The low end value for the range of random values
     * @param highVal
     *            The high end value for the range of random values
     * @param random
     *            Whether or not a random attribute and value is generated
     */
    public Item(
        String newName,
        String newDesc,
        String newAttribute,
        int lowVal,
        int highVal,
        boolean random)
    {
        this(newName, newDesc);

        setupAttributes();

        if (random)
        {
            randomizeAttributeAndValue(newAttribute, lowVal, highVal);
        }
    }


    /**
     * Initializes and sets the default values for all attributes
     */
    public void setupAttributes()
    {
        attributes = new HashMap<String, Integer>();
        randoms = new ArrayList<RandomRange>();

        attributes.put("Health", 0);
        randoms.add(new RandomRange("Health", 10, 50));

        attributes.put("Armor", 0);
        randoms.add(new RandomRange("Armor", 1, 3));

        attributes.put("HealthRestore", 0);
        randoms.add(new RandomRange("HealthRestore", 10, 50));
    }


    /**
     * Returns the name of the Item
     *
     * @return String representation of the name
     */
    public String getName()
    {
        return name;
    }


    /**
     * Sets the new name of the Item
     *
     * @param newName
     *            New name of the Item
     */
    public void setName(String newName)
    {
        name = newName;
    }


    /**
     * Returns the description of the Item
     *
     * @return String representation of the Item
     */
    public String getDescription()
    {
        return description;
    }


    /**
     * Sets the new description of the Item
     *
     * @param newDesc
     *            New description of the Item
     */
    public void setDescription(String newDesc)
    {
        description = newDesc;
    }


    /**
     * Returns a Set of Strings that contain the attributes of the Item
     *
     * @return Set<String> of attributes of the Item
     */
    public HashMap<String, Integer> getAttributes()
    {
        return attributes;
    }


    /**
     * Will return the ArrayList of RandomRanges that hold the random attribute
     * value ranges
     *
     * @return ArrayList<RandomRange> that contain the random value ranges
     */
    public ArrayList<RandomRange> getRandomValues()
    {
        return randoms;
    }


    /**
     * Adds a new attribute to the Set of attributes of the Item
     *
     * @param newAttribute
     *            New attribute to be added to the Item
     */
    public void addAttribute(String newAttribute)
    {
        attributes.put(newAttribute, 0);
    }


    /**
     * Adds the specified attribute to the map with the specified value
     *
     * @param newAttribute
     *            The specified attribute to add
     * @param attributeValue
     *            The specified value to add
     */
    public void addAttribute(String newAttribute, int attributeValue)
    {
        attributes.put(newAttribute, attributeValue);
    }


    /**
     * Randomizes a given attribute's value based on its range of random values
     *
     * @param newAttribute
     *            The specified attribute to randomize
     */
    public void randomizeAttributeAndValue(String newAttribute)
    {
        gen = new Random();
        int randomValue =
            gen.nextInt(
                randoms.get(randoms.indexOf(newAttribute)).getLowVal(),
                randoms.get(randoms.indexOf(newAttribute)).getHighVal());

        attributes.put(newAttribute, randomValue);
    }


    /**
     * Randomizes a given attribute's value
     *
     * @param newAttribute
     *            The specified attribute to randomize
     * @param lowVal
     *            Low end range of random values
     * @param highVal
     *            High end range of random values
     */
    public void randomizeAttributeAndValue(
        String newAttribute,
        int lowVal,
        int highVal)
    {
        gen = new Random();
        int randomValue = gen.nextInt(lowVal, highVal);

        attributes.put(newAttribute, randomValue);
    }


    /**
     * Runs through the list of attributes and randomizes the attribute's value
     * given its randomization range
     */
    public void randomizeAllAttributes()
    {
        gen = new Random();

        for (Map.Entry<String, Integer> attribute : attributes.entrySet())
        {
            attribute.setValue(gen.nextInt(
                randoms.get(randoms.indexOf(attribute.getKey())).getLowVal(),
                randoms.get(randoms.indexOf(attribute.getKey())).getHighVal()));
        }
    }


    /**
     * This internal class will keep track of the range of values that each
     * attribute can be randomized from. For example, Health can be randomized
     * from 10-50, so lowVal is 10 and highVal is 50.
     *
     * @author Fintan Kelly (fintank)
     * @version 2014.11.17
     */
    private class RandomRange
    {
        /**
         * String representation of the attribute's name
         */
        private String attribute;

        /**
         * Integer representation of the lowest value the attribute can be
         */
        private int    lowVal;

        /**
         * Integer representation of the highest value the attribute can be
         */
        private int    highVal;


        /**
         * Create a new RandomRange object with a given attribute name, low end
         * value, and high end value
         *
         * @param cAttribute
         *            The specified attribute's name
         * @param lowEnd
         *            The specified attribute's lowest value
         * @param highEnd
         *            The specified attribute's highest value
         */
        public RandomRange(String cAttribute, int lowEnd, int highEnd)
        {
            attribute = cAttribute;
            lowVal = lowEnd;
            highVal = highEnd;
        }


        /**
         * Returns the lowest value the attribute can be
         *
         * @return Integer representation of the lowest value the attribute can
         *         be
         */
        public int getLowVal()
        {
            return lowVal;
        }


        /**
         * Returns the highest value the attribute can be
         *
         * @return Integer representation of the highest value the attribute can
         *         be
         */
        public int getHighVal()
        {
            return highVal;
        }


        /**
         * Returns the name of the attribute
         *
         * @return String representation of the attribute's name
         */
        @SuppressWarnings("unused")
        public String getAttribute()
        {
            return attribute;
        }
    }
}
