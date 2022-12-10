package no.kurosis.check.timer;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayOutPosition;
import no.kurosis.check.Check;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

/**
 * Simple timer check that checks if the player is sending too many packets in the current tick
 *
 * @author aesthetical
 * @since 12/09/22
 */
public class TimerA extends Check {

    /**
     * The max amount of packets a player can send in one second
     * assuming there is no lag
     */
    private static final int MAX_PACKET_COUNT = 22;

    private long lastTime = -1L;
    private int packetsSent = 0;

    private int exemptTicks = 0;

    public TimerA(PlayerData playerData) {
        super("TimerA (More Packets)", playerData, 4.0);
    }

    @EventListener
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof PacketPlayInFlying) {

            if (--exemptTicks > 0) {
                packetsSent = 0;
                return;
            }

            // if the last time was not set, we'll set that now
            if (lastTime == -1L) {
                lastTime = System.currentTimeMillis();
                exemptTicks = 5;
                return;
            }

            // every move packet, we'll increment
            ++packetsSent;

            // if the time elapsed has been a second
            if (System.currentTimeMillis() - lastTime >= 1000L) {

                // if we have exceeded the threshold, add to our violation count
                if (packetsSent >= MAX_PACKET_COUNT) {
                    violate(1.5);
                } else {

                    // reward player
                    violate(-0.25);
                }

                // reset packet counter
                packetsSent = 0;
                lastTime = System.currentTimeMillis();
            }
        } else if (event.getPacket() instanceof PacketPlayOutPosition) {
            exemptTicks = 10;
        }
    }
}
