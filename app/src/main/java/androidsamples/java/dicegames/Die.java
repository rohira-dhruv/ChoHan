package androidsamples.java.dicegames;

public interface Die {
    /*
     * Rolls the die.
     */
    void roll();

    /*
     * Reports the number on the top face of the die.
     * Returns the number of dots on the top face of the die.
     */
    int value();
}
