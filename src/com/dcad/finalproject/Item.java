package com.dcad.finalproject;

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
public class Item {

    /**
     * Variables that will hold the individual Item information
     */
    private ItemInfo itemInfo;
    private String name;
    private String description;
    private String attribute;
    private int value;
    private String target;

    /**
     * Random number generator that will be used for assigning random values to
     * attributes
     */
    private static Random rand = new Random();

    private static enum ItemInfo {

        POTION("Heals the player : ", "self", "Heal"),
        ARMOR("Player gains armor : ", "self", "Armor"),
        DAMAGE(
                "Damages the enemy and ignores armor and accuracy: ",
                "enemy",
                "Damage");

        final int power;
        final String description;
        final String target;
        final String attribute;

        ItemInfo(String des, String tar, String att) {
            description = des;
            target = tar;
            attribute = att;

            if (att.equals("Heal")) {
                power = rand.nextInt(200);
            } else if (att.equals("Armor")) {
                power = rand.nextInt(5);
            } else if (att.equals("Armor")) {
                power = rand.nextInt(10);
            } else if (att.equals("Damage")) {
                power = rand.nextInt(10);
            } else {
                power = 0;
            }

        }
    }

    /**
     * Create a new Item object that is either a potion, damage, or armor with
     * random power values.
     */
    public Item() {

        ItemInfo[] items = ItemInfo.values();
        itemInfo = items[rand.nextInt(items.length)];

        name = itemInfo.name();
        description = itemInfo.description;
        attribute = itemInfo.attribute;
        value = itemInfo.power;
        setTarget(itemInfo.target);

    }

    /**
     * Create a new Item object with the given name, description, attribute, and
     * attribute value
     *
     * @param newName The specified name of the Item
     * @param newDesc The specified description of the Item
     * @param newAttribute The specified attribute of the item
     * @param attributeValue The specified value of the attribute
     * @param targeted The entity targeted by the item
     */
    public Item(
            String newName,
            String newDesc,
            String newAttribute,
            int attributeValue,
            String targeted) {
        name = newName;
        description = newDesc;
        attribute = newAttribute;
        value = attributeValue;
        setTarget(targeted);
    }

    /**
     * Returns the name of the Item
     *
     * @return String representation of the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the new name of the Item
     *
     * @param newName New name of the Item
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Returns the description of the Item
     *
     * @return String representation of the Item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the new description of the Item
     *
     * @param newDesc New description of the Item
     */
    public void setDescription(String newDesc) {
        description = newDesc;
    }

    /**
     * Returns a String that contain the attributes of the Item
     *
     * @return String of attribute of the Item
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * Adds the specified attribute to the item
     *
     * @param newAttribute The specified attribute to add
     */
    public void setAttribute(String newAttribute) {
        attribute = newAttribute;
    }

    // ----------------------------------------------------------
    /**
     * @return the value
     */
    public int getItemPower() {
        return value;
    }

    // ----------------------------------------------------------
    /**
     * @param value the value to set
     */
    public void setItemPower(int value) {
        this.value = value;
    }

    // ----------------------------------------------------------
    /**
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    // ----------------------------------------------------------
    /**
     * @param target the target to set
     */
    public void setTarget(String target) {
        this.target = target;
    }
}
