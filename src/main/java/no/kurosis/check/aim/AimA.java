package no.kurosis.check.aim;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import no.kurosis.check.Check;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

/**
 * Simple check for invalid rotations
 *
 * @author aesthetical
 * @since 12/09/22
 */
public class AimA extends Check {
    public AimA(PlayerData data) {
        super("AimA (Invalid Rotations)", data, 1.0);
    }

    @EventListener
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof PacketPlayInFlying) {
            PacketPlayInFlying packet = event.getPacket();

            // if the packet pitch is greater than 90.0f either way, that's an instant ban
            // the vanilla game will NEVER do this
            if (Math.abs(packet.e()) > 90.0f) {
                violate(1.0);
            }
        }
    }
}
