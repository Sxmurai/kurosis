package no.kurosis.check.inventory;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.PacketPlayInWindowClick;
import no.kurosis.check.Check;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

/**
 * Checks if the player attempts to click in a window without actually opening it
 *
 * @author aesthetical
 * @since 12/09/22
 */
public class InventoryC extends Check {
    public InventoryC(PlayerData data) {
        super("InventoryC (Packet Cancel)", data, 2.0);
    }

    @EventListener
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof PacketPlayInWindowClick) {
            PacketPlayInWindowClick packet = event.getPacket();
            if (packet.a() != 0) {
                return;
            }

            if (!getData().inventoryData.opened) {
                violate(1.0);
            }
        }
    }
}
