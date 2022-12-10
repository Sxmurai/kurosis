package no.kurosis.check.killaura;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import no.kurosis.check.Check;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;
import no.kurosis.util.BukkitUtils;

/**
 * A check that checks if the player interacts with itself in any way
 *
 * @author aesthetical
 * @since 12/10/22
 */
public class KillAuraC extends Check {

    public KillAuraC(PlayerData data) {
        super("KillAuraC (Self Interact)", data, 1.0);
    }

    @EventListener
    public void onPacket(PacketEvent event) {

        // get the EntityPlayer by the UUID
        EntityPlayer player = BukkitUtils.getRealPlayer(getData().getUuid());
        if (player == null) {
            return;
        }

        // the packet where Attacks, Interact, and Interact At are sent by the client
        // the client will never interact with itself
        if (event.getPacket() instanceof PacketPlayInUseEntity) {
            PacketPlayInUseEntity packet = event.getPacket();

            // get the entity from the packet
            Entity entity = packet.a(player.getWorld());

            // check if the entity used is the local player, if so, instant ban
            if (entity != null && entity.getId() == player.getId()) {
                violate(1.0);
            }
        }
    }
}
