package no.kurosis.check.crasher;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import no.kurosis.check.Check;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

/**
 * A check for an extremely old bukkit exploit.
 * This should not affect newer servers, but if the client sends these, they're definitely cheating
 *
 * @author aesthetical
 * @since 12/10/22
 */
public class CrasherA extends Check {
    public CrasherA(PlayerData data) {
        super("CrasherA (NaN/Infinity)", data, 1.0);
    }

    @EventListener
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof PacketPlayInFlying) {
            PacketPlayInFlying packet = event.getPacket();

            if (packet.g()) {
                if (Double.isNaN(packet.a()) || Double.isNaN(packet.b()) || Double.isNaN(packet.c())) {
                    violate(1.0);
                }

                if (Double.isInfinite(packet.a()) || Double.isInfinite(packet.b()) || Double.isInfinite(packet.c())) {
                    violate(1.0);
                }
            }

            if (packet.h()) {
                if (Float.isNaN(packet.e()) || Float.isNaN(packet.d())) {
                    violate(1.0);
                }

                if (Float.isInfinite(packet.e()) || Float.isInfinite(packet.d())) {
                    violate(1.0);
                }
            }
        }
    }
}
