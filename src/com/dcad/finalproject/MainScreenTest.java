package com.dcad.finalproject;

import android.widget.RadioButton;
import android.widget.Button;

// ----------------------------------------------------------
/**
 * Test cases for {@link MainScreen}.
 *
 * @author Cameron Rader (crader)
 * @version 2014.12.2
 */
public class MainScreenTest
    extends student.AndroidTestCase<MainScreen>
{
    // ~ Fields.................................................................
    private Button      newGame;
    private RadioButton easy;
    private RadioButton medium;
    private RadioButton hard;


    // ~ Constructors...........................................................
    // ----------------------------------------------------------
    /**
     * Create a new MainScreenTest object.
     */
    public MainScreenTest()
    {
        super(MainScreen.class);
    }


    // ~ Methods................................................................
    // ----------------------------------------------------------
    /**
     * Test method for {@link MainScreen#easyClicked()},
     * {@link MainScreen#mediumClicked()}, and {@link MainScreen#hardClicked()}.
     * Also tests to ensure only one radio button is checked at once.
     */
    public void testDiffilculties()
    {
        assertTrue(easy.isChecked());
        assertFalse(medium.isChecked());
        assertFalse(hard.isChecked());

        click(medium);
        assertFalse(easy.isChecked());
        assertTrue(medium.isChecked());
        assertFalse(hard.isChecked());

        click(hard);
        assertFalse(easy.isChecked());
        assertFalse(medium.isChecked());
        assertTrue(hard.isChecked());
    }


    /**
     * Tests the function of making a new game
     */
    public void testNewGame()
    {
        assertTrue(newGame.isActivated());
        click(newGame);
        assertNull(this.getScreen());
    }
}
