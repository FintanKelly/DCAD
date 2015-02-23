package com.dcad.finalproject;

import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

// -------------------------------------------------------------------------
/**
 * Test cases for {@link BattleScreen}.
 *
 * @author Cameron Rader (crader)
 * @version 2014.12.2
 */
public class BattleScreenTest
    extends student.AndroidTestCase<BattleScreen>
{
    // ~ Fields.................................................................
    private TextView    playerHealth;
    private TextView    playerArmor;
    private TextView    monsterHealth;
    private TextView    monsterArmor;

    private Button      play;

    private RadioButton card1;
    private RadioButton card2;
    private RadioButton card3;


    // ~ Constructors...........................................................
    // ----------------------------------------------------------
    /**
     * Create a new BattleScreenTest object.
     */
    public BattleScreenTest()
    {
        super(BattleScreen.class);
    }


    // ~ Methods................................................................
    public void testCard1Clicked()
    {
        click(card1);

        assertTrue(card1.isChecked());
        assertFalse(card2.isChecked());
        assertFalse(card3.isChecked());
    }


    public void testCard2Clicked()
    {
        click(card2);

        assertFalse(card1.isChecked());
        assertTrue(card2.isChecked());
        assertFalse(card3.isChecked());
    }


    public void testCard3Clicked()
    {
        click(card3);

        assertFalse(card1.isChecked());
        assertFalse(card2.isChecked());
        assertTrue(card3.isChecked());
    }


    // ----------------------------------------------------------
    /**
     * Tests events following a play button click including GUI updates.
     */
    public void testPlayClicked()
    {
        click(play);


    }
}
