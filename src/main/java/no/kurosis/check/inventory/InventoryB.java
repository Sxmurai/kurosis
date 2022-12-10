package no.kurosis.check.inventory;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import no.kurosis.check.Check;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

/**
 * Simple check that checks if the player moves while the inventory has been opened
 *
 * @author aesthetical
 * @since 12/09/22
 */
public class InventoryB extends Check {
    public InventoryB(PlayerData data) {
        super("InventoryB (Move)", data, 4.0);
    }

    @EventListener
    public void onPacket(PacketEvent event) {
//        if (event.getPacket() instanceof PacketPlayInFlying) {
//            PacketPlayInFlying packet = event.getPacket();
//            if (packet.g() && getData().inventoryData.opened) {
//                violate(1.0);
//            }
//        }
    }
}
