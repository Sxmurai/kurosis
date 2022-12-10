package no.kurosis.check.inventory;

import net.minecraft.server.v1_8_R3.PacketPlayInWindowClick;
import no.kurosis.check.Check;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;
import org.bukkit.event.EventHandler;

/**
 * Simple (yet kinda shitty) check for too fast inventory actions
 *
 * @author aesthetical
 * @since 12/09/22
 */
public class InventoryD extends Check {

    private long lastTime = -1L;

    public InventoryD(PlayerData data) {
        super("InventoryD (Fast Click)", data, 4.0);
    }

    @EventHandler
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof PacketPlayInWindowClick) {
            if (lastTime == -1L) {
                lastTime = System.currentTimeMillis();
                return;
            }

            if (System.currentTimeMillis() - lastTime < 51L) {
                violate(1.0);
            }

            lastTime = System.currentTimeMillis();
        }
    }
}
