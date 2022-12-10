package no.kurosis.check.invalid;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.PacketPlayInHeldItemSlot;
import no.kurosis.check.Check;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

/**
 * Checks if the hotbar slot in is less than 0 or greater than 8, the vanilla client never does this
 *
 * @author aesthetical
 * @since 12/09/22
 */
public class InvalidB extends Check {
    public InvalidB(PlayerData data) {
        super("InvalidB (Invalid Hotbar)", data, 1.0);
    }

    @EventListener
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof PacketPlayInHeldItemSlot) {
            PacketPlayInHeldItemSlot packet = event.getPacket();
            if (packet.a() > 8 || packet.a() < 0) {
                violate(1.0);
            }
        }
    }
}
