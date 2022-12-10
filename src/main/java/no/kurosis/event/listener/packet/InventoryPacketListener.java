package no.kurosis.event.listener.packet;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInCloseWindow;
import no.kurosis.Kurosis;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

public class InventoryPacketListener {

    @EventListener
    public void onPacket(PacketEvent event) {
        PlayerData playerData = Kurosis.getInstance().getDataRegistry().get(event.getUuid());
        if (playerData == null) {
            return;
        }

        if (event.getPacket() instanceof PacketPlayInClientCommand) {
            PacketPlayInClientCommand packet = event.getPacket();
            if (packet.a().equals(EnumClientCommand.OPEN_INVENTORY_ACHIEVEMENT)) {
                playerData.inventoryData.opened = true;
                playerData.inventoryData.openedAt = System.currentTimeMillis();
            }
        } else if (event.getPacket() instanceof PacketPlayInCloseWindow) {
            playerData.inventoryData.opened = false;
            playerData.inventoryData.openedAt = -1L;
        }
    }

}
