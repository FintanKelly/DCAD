package com.dcad.finalproject;

import android.widget.RadioButton;
import sofia.app.Screen;

// -------------------------------------------------------------------------
/**
 * Represents the main menu screen for this app. Here the user can read the
 * app's title and description and select a game difficulty.
 *
 * @author Cameron Rader (crader)
 * @version 2014.12.2
 */
public class MainScreen
    extends Screen
{
    // ~ Fields.................................................................
    private static Dungeon model;
    private static RadioButton easy;


    // ~ Methods................................................................
    /**
     * Creates a new instance of the model that will be passed along to other
     * screens.
     */
    public void initialize()
    {
        model = new Dungeon();

        easy.setChecked(true);
    }


    // ----------------------------------------------------------
    /**
     * Sets difficulty to easy.
     */
    public void easyClicked()
    {
        model.setDifficulty(1);
    }


    // ----------------------------------------------------------
    /**
     * Sets difficulty to medium.
     */
    public void mediumClicked()
    {
        model.setDifficulty(5);
    }


    // ----------------------------------------------------------
    /**
     * Sets difficulty to hard.
     */
    public void hardClicked()
    {
        model.setDifficulty(10);
    }


    // ----------------------------------------------------------
    /**
     * Starts a new game with current difficulty setting.
     */
    public void newGameClicked()
    {
        presentScreen(BattleScreen.class, model);
    }
}
