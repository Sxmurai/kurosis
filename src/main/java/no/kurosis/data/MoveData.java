package no.kurosis.data;

public class MoveData {

    public double hDist = 0.0;
    public double vDist = 0.0;

    public double x, y, z;
    public double lastX, lastY, lastZ;

    public float yaw, pitch;
    public float lastYaw, lastPitch;

    /**
     * The onGround state C03's report
     * This should NEVER be used as a sure way to tell if the player is on ground
     */
    public boolean packetGround = false;

}
