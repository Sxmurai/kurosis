package no.kurosis.check.inventory;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import no.kurosis.check.Check;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

/**
 * Simple check that checks if the player rotates while the inventory has been opened
 *
 * @author aesthetical
 * @since 12/09/22
 */
public class InventoryA extends Check {
    public InventoryA(PlayerData data) {
        super("InventoryA (Rotate)", data, 4.0);
    }

    @EventListener
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof PacketPlayInFlying) {
            PacketPlayInFlying packet = event.getPacket();
            if (packet.h() && getData().inventoryData.opened && System.currentTimeMillis() - getData().inventoryData.openedAt > 80L) {
                violate(1.0);
            }
        }
    }
}
