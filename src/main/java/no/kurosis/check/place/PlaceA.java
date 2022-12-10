package no.kurosis.check.place;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.ItemBlock;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace;
import no.kurosis.check.Check;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

/**
 * Checks if blocks are being placed rapidly
 *
 * @author aesthetical
 * @since 12/09/22
 */
public class PlaceA extends Check {

    private long lastTime = -1L;

    public PlaceA(PlayerData data) {
        super("PlaceA (Fast Place)", data, 5.0);
    }

    @EventListener
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof PacketPlayInBlockPlace) {
            PacketPlayInBlockPlace packet = event.getPacket();
            if (packet.getItemStack() != null && packet.getItemStack().getItem() instanceof ItemBlock) {

                if (lastTime == -1L) {
                    lastTime = System.currentTimeMillis();
                    return;
                }

                if (System.currentTimeMillis() - lastTime < 55L) {
                    violate(1.0);
                }

                lastTime = System.currentTimeMillis();

            }
        }
    }
}
