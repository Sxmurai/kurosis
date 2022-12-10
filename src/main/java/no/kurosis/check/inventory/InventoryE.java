package no.kurosis.check.inventory;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.*;
import net.minecraft.server.v1_8_R3.PacketPlayInEntityAction.EnumPlayerAction;
import no.kurosis.check.Check;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

public class InventoryE extends Check {

    public InventoryE(PlayerData data) {
        super("InventoryE (Invalid Actions)", data, 3.0);
    }

    @EventListener
    public void onPacket(PacketEvent event) {
        if (getData().inventoryData.opened) {
            if (event.getPacket() instanceof PacketPlayInBlockPlace) {
                violate(1.0);
            } else if (event.getPacket() instanceof PacketPlayInEntityAction) {
                PacketPlayInEntityAction packet = event.getPacket();
                if (packet.b().equals(EnumPlayerAction.START_SPRINTING) || packet.b().equals(EnumPlayerAction.START_SNEAKING)) {
                    violate(1.0);
                }
            } else if (event.getPacket() instanceof PacketPlayInArmAnimation) {
                violate(1.0);
            } else if (event.getPacket() instanceof PacketPlayInHeldItemSlot) {
                violate(1.0);
            } else if (event.getPacket() instanceof PacketPlayInChat) {
                violate(1.0);
            }
        }
    }
}
