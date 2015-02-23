package com.dcad.finalproject;

// -------------------------------------------------------------------------
/**
 * Represents a specific {@link ActionCard} that acts as an attack action. An
 * attack card is defined with preset variants as defined in the CardInfo
 * enumeration.
 *
 * @author Cameron Rader (crader)
 * @version 2014.11.5
 */
public class AttackCard
    extends ActionCard
{
    // ~ Private enums ---------------------------------------------------------
    /**
     * Includes all possible variants of AttackCard attributes in a single
     * enumeration. These attributes include the damage and accuracy of the
     * attack.
     */
    private static enum CardInfo
    {
        SLASH(10, 0.80),
        MAUL(15, 0.75),
        STAB(5, 0.90),
        LUNGE(20, 0.65);

        final int    damage;
        final double accuracy;


        CardInfo(int dam, double acc)
        {
            damage = dam;
            accuracy = acc;
        }
    }

    // ~ Fields ----------------------------------------------------------------
    /**
     * Information of the card variant held by a specific instance of this class
     */
    private CardInfo info;


    // ~ Constructors ----------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Create a new AttackCard object, picking a random variant preset from the
     * CardInfo enumeration.
     */
    public AttackCard()
    {
        this.assignRandomInfo();
    }


    // ~ Private Methods -------------------------------------------------------
    /**
     * Randomly assigns card info to a random CardInfo variant. Serves as a
     * helper method to the constructor among others.
     */
    private void assignRandomInfo()
    {
        CardInfo[] cards = CardInfo.values();
        info = cards[rand.nextInt(cards.length)];
    }


    // ~ Public Methods --------------------------------------------------------

    // ----------------------------------------------------------
    /**
     * Decides if the attack hits or misses once the card is activated.
     *
     * @return raw damage dealt
     */
    @Override
    public int activate()
    {
        return rand.nextDouble() <= info.accuracy ? info.damage : 0;
    }


    // ----------------------------------------------------------
    /**
     * Gets the name of the card's attack.
     *
     * @return attack name
     */
    @Override
    public String getName()
    {
        return info.name();
    }


    // ----------------------------------------------------------
    /**
     * Gets the damage of the card's attack.
     *
     * @return attack damage
     */
    public int getDamage()
    {
        return info.damage;
    }


    // ----------------------------------------------------------
    /**
     * Gets the accuracy of the card's attack.
     *
     * @return attack accuracy
     */
    public double getAccuracy()
    {
        return info.accuracy;
    }


    // ----------------------------------------------------------
    /**
     * Gets a general description of the card.
     *
     * @return card info
     */
    @Override
    public String toString()
    {
        return info.name() + " type attack\n" + "Damage: " + info.damage + "\n"
            + "Accuracy: " + (int)(info.accuracy * 100) + "%";
    }

}
