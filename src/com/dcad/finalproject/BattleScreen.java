package com.dcad.finalproject;

import sofia.app.ShapeScreen;
import sofia.graphics.Color;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import sofia.graphics.RectangleShape;
import sofia.graphics.ShapeView;

// -------------------------------------------------------------------------
/**
 * Represents the main battle screen for this app. Here the user can fight
 * monsters using cards drawn from their deck.
 *
 * @author Cameron Rader (crader)
 * @version 2014.12.2
 */
public class BattleScreen
        extends ShapeScreen {

    // fields...................................................................

    private static Dungeon model;

    private TextView playerHealth;
    private TextView playerArmor;
    private TextView monsterHealth;
    private TextView monsterArmor;
    private TextView cardDescription;
    private TextView monstersCount;

    private Button play;

    @SuppressWarnings("unused")
    private RadioButton card1;
    @SuppressWarnings("unused")
    private RadioButton card2;
    @SuppressWarnings("unused")
    private RadioButton card3;

    private ShapeView shapeView;

    // methods..................................................................
    // ----------------------------------------------------------
    /**
     * <p>
     * Sets local model up using passed model (via presentScreen) from previous
     * screen.
     * </p>
     * <p>
     * Expected exception if no {@link Dungeon} model is passed through a
     * presentScreen call.
     * </p>
     *
     * @param mod model passed by presentScreen call to this class
     */
    public void initialize(Dungeon mod) {
        if (mod == null) {
            throw new IllegalStateException(
                    "Model was not passed to initialize method.");
        } else {
            model = mod;
        }

        model.addObserver(this);

        play.setEnabled(false);

        model.selectCard(0);

        shapeView.setBackgroundColor(Color.black);

        displayCards();
    }

    // ----------------------------------------------------------
    /**
     * Selects card in first position in the hand.
     */
    public void card1Clicked() {
        model.selectCard(0);
        play.setEnabled(true);
    }

    // ----------------------------------------------------------
    /**
     * Selects card in second position in the hand.
     */
    public void card2Clicked() {
        model.selectCard(1);
        play.setEnabled(true);
    }

    // ----------------------------------------------------------
    /**
     * Selects card in third position in the hand.
     */
    public void card3Clicked() {
        model.selectCard(2);
        play.setEnabled(true);
    }

    // ----------------------------------------------------------
    /**
     * Plays out the current turn with the selected card.
     */
    public void playClicked() {
        if (model.getSelectedCard() instanceof ItemCard /*&& !model.getInventory().isEmpty()*/) {
            presentScreen(InventoryScreen.class, model);
            model.selectItem(new Item());
        }

        model.playCard();

        model.selectCard(0);
        play.setEnabled(false);
        cardDescription.setText("");

        shapeView.clear();

        displayCards();
    }

    // ----------------------------------------------------------
    /**
     * Called when changes are made in the model. Updates GUI with updated
     * information.
     *
     * @param dun model class
     */
    public void changeWasObserved(Dungeon dun) {
        if (model.gameLost() || model.gameWon()) {
            presentScreen(MainScreen.class);
        }

        monstersCount.setText("Monsters Remaining: "
                + model.getMonstersRemaining());

        playerHealth.setText("Player Health: " + model.getPlayerHealth());
        playerArmor.setText("Player Armor: " + model.getPlayerArmor());
        monsterHealth.setText("Monster Health: " + model.getMonsterHealth());
        monsterArmor.setText("Monster Armor: " + model.getMonsterArmor());

        cardDescription.setText(model.getSelectedCard().toString());
    }

    /**
     * Draw the appropriate card images for the cards that were drawn.
     */
    private void displayCards() {
        ActionCard[] hand = model.getHand();

        for (int i = 0; i < hand.length; i++) {
            if (hand[i] instanceof ItemCard) {
                RectangleShape card
                        = new RectangleShape(
                                (i * (shapeView.getWidth() / 3)),
                                0,
                                ((i + 1) * (shapeView.getWidth() / 3)),
                                shapeView.getHeight());
                card.setImage("item_card");
                shapeView.add(card);
            } else {
                RectangleShape card
                        = new RectangleShape(
                                (i * (shapeView.getWidth() / 3)),
                                0,
                                ((i + 1) * (shapeView.getWidth() / 3)),
                                shapeView.getHeight());
                card.setImage("attack_card");
                shapeView.add(card);
            }
        }
    }
}
