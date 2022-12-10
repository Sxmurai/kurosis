package no.kurosis.check;

import no.kurosis.Kurosis;
import no.kurosis.data.PlayerData;

/**
 * Represents a base check
 *
 * @author aesthetical
 * @since 12/09/22
 */
public class Check {

    private final PlayerData data;
    private final String name;
    private double maxVL;

    private double localVL = 0.0;

    private final boolean experimental = getClass().getDeclaredAnnotation(Experimental.class) != null;

    /**
     * Creates a new check
     * @param data the player's player data
     * @param name the name of this check
     * @param maxVL the max violation count
     */
    public Check(String name, PlayerData data, double maxVL) {
        this.name = name;
        this.data = data;
        this.maxVL = maxVL;
    }

    /**
     * Adds a violation count
     * @param amount the amount to add to this violation count
     */
    protected void violate(double amount) {
        localVL += amount;
        localVL = Math.max(localVL, 0.0);

        if (amount > 0.0) {
            // notify all operators of this violation
            Kurosis.getInstance().getAlertManager().notify(this);
        }

        if (localVL > 0.0 && localVL >= maxVL) {
            // LOL

            return;
        }
    }

    /**
     * Gets the name for this check
     * @return the check name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the local violation count
     * @return the local VL
     */
    public double getViolationCount() {
        return localVL;
    }

    /**
     * Gets the max violation count this player can have
     * @return the maximum VL count
     */
    public double getMaxVL() {
        return maxVL;
    }

    /**
     * Sets the max violation count this player can have for this check
     * @param maxVL the new max violation count
     */
    public void setMaxVL(double maxVL) {
        this.maxVL = maxVL;
    }

    /**
     * Gets the player's player data for this check
     * @return the player data provided
     */
    public PlayerData getData() {
        return data;
    }

    /**
     * If the @Experimental annotation was added to this class
     * @return if this check is experimental
     */
    public boolean isExperimental() {
        return experimental;
    }
}
