package no.kurosis.check.autoclicker;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation;
import no.kurosis.check.Check;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

/**
 * Simple check that checks if we swing too much in one second
 *
 * @author aesthetical
 * @since 12/09/22
 */
public class AutoClickerA extends Check {

    private long lastTime = -1L;
    private int swings = 0;

    public AutoClickerA(PlayerData data) {
        super("AutoClickerA (Excessive Swings)", data, 3.0);
    }

    @EventListener
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof PacketPlayInArmAnimation) {

            // if the last time was not set, we'll set that now
            if (lastTime == -1L) {
                lastTime = System.currentTimeMillis();
                return;
            }

            // every time the players increments
            ++swings;

            // if the time elapsed has been a second
            if (System.currentTimeMillis() - lastTime >= 1000L) {

                // if we have exceeded the threshold, add to our violation count
                // for some reason, the vanilla game sends 21 sometimes... odd, but we'll account for that
                if (swings > 21) {
                    violate(1.5);
                } else {

                    // reward player
                    violate(-0.25);
                }

                // reset swing counter
                swings = 0;
                lastTime = System.currentTimeMillis();
            }
        }
    }
}
