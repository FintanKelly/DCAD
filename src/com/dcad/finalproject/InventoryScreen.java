package com.dcad.finalproject;

import sofia.graphics.Image;
import sofia.graphics.Color;
import sofia.graphics.RectangleShape;
import android.widget.TextView;
import android.widget.Button;
import sofia.graphics.ShapeView;
import sofia.app.Screen;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Zachary
 * @version Dec 3, 2014
 */

public class InventoryScreen
    extends Screen
{

    private Dungeon            gameModel;

    private ShapeView          inventoryDisplay;
    private Button             loadItem;
    private TextView           itemName;
    private TextView           itemDescription;
    private TextView           itemPower;
    private Item[][]           inventory;
    private Item               currentItem;
    private RectangleShape[][] imageArray;
    private RectangleShape     selected;

    private Image              healingPotion;
    private Image              armor;
    private Image              damage;

    private int                sideSize;


    // ----------------------------------------------------------
    /**
     * * <p>
     * Sets local model up using passed model (via battleScreen) from previous
     * screen.
     * </p>
     * <p>
     * Expected exception if no {@link Dungeon} model is passed through a
     * presentScreen call.
     * </p>
     *
     * @param model
     *            model passed by presentScreen call to this class
     */
    public void initialize(Dungeon model)
    {
        gameModel = model;

        healingPotion = new Image("potion.png");
        armor = new Image("armor.png");
        damage = new Image("damage.png");

        inventory = model.getInventory().getBag();
        int size = inventory.length;
        imageArray = new RectangleShape[size][size];

        sideSize =
            Math.min(inventoryDisplay.getWidth(), inventoryDisplay.getHeight());

        int cellSize = sideSize / size;

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                imageArray[i][j] =
                    new RectangleShape(
                        (float)cellSize * i,
                        (float)cellSize * j,
                        (float)cellSize * (i + 1),
                        (float)cellSize * (j + 1));

                imageArray[i][j].setColor(Color.black);
                allocateImage(i, j);
                inventoryDisplay.add(imageArray[i][j]);
            }
        }

        selected = imageArray[0][0];
        currentItem = inventory[0][0];

        showInfo();

    }


    // ----------------------------------------------------------
    /**
     * Processes the touch on the shapeView
     *
     * @param x
     *            The x position of the touch
     * @param y
     *            The y position of the touch
     */
    public void onTouchDown(float x, float y)
    {
        processTouch(x, y);
    }


    // ----------------------------------------------------------
    /**
     * Processes the touch on the shapeView
     *
     * @param x
     *            The x position of the touch
     * @param y
     *            The y position of the touch
     */
    public void onTouchMove(float x, float y)
    {
        processTouch(x, y);
    }


    // ----------------------------------------------------------
    /**
     * Sets the mazeCells according to what buttons are pressed.
     *
     * @param x
     *            The x position given
     * @param y
     *            The y position given
     */
    private void processTouch(float x, float y)
    {
        // Calculations for size and location
        int cellSize = sideSize / inventory.length;

        selected = imageArray[(int)(x / cellSize)][(int)(y / cellSize)];
        Item current = inventory[(int)(x / cellSize)][(int)(y / cellSize)];

        if(current != null)
        {
            currentItem = current;
            selected.setFillColor(Color.blue);

            showInfo();
        }
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void onLoadItemClicked()
    {
        gameModel.selectItem(currentItem);

        presentScreen(BattleScreen.class, gameModel);
    }

    private void allocateImage(int i, int j)
    {
        if (inventory[i][j] != null)
        {
            String current = inventory[i][j].getName();

            if (current.equalsIgnoreCase("DAMAGE"))
            {
                imageArray[i][j].setImage(damage);
            }

            else if (current.equalsIgnoreCase("ARMOR"))
            {
                imageArray[i][j].setImage(armor);
            }

            else
            {

                imageArray[i][j].setImage(healingPotion);
            }
        }

        else
        {
            imageArray[i][j].setFillColor(Color.gray);
        }
    }

    private void showInfo()
    {
        if(currentItem != null)
        {
            itemName.setText(currentItem.getAttribute());
            itemDescription.setText(currentItem.getDescription());
            itemPower.setText(currentItem.getItemPower());
        }
    }
}
