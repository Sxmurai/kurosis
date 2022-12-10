package no.kurosis.check.invalid;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.PacketPlayInHeldItemSlot;
import no.kurosis.check.Check;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

/**
 * A check that checks if the C09 slot is the same as the one on the server.
 *
 * @author aesthetical
 * @since 12/10/22
 */
public class InvalidC extends Check {

    private int slot = -1;

    public InvalidC(PlayerData data) {
        super("InvalidC (Same Slot)", data, 3.0);
    }

    @EventListener
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof PacketPlayInHeldItemSlot) {
            int slotIn = ((PacketPlayInHeldItemSlot) event.getPacket()).a();
            if (slot == -1) {
                slot = slotIn;
                return;
            }

            if (slot == slotIn) {
                violate(1.0);
            }

            slot = slotIn;
        }
    }
}
