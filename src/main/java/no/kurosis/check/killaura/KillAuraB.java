package no.kurosis.check.killaura;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity.EnumEntityUseAction;
import no.kurosis.check.Check;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

/**
 * A check for if the player attempts to attack after the packets have been sent
 *
 * @author aesthetical
 * @since 12/10/22
 */
public class KillAuraB extends Check {

    private boolean moved = false;

    // TODO
    public KillAuraB(PlayerData data) {
        super("KillAuraB (Post Attack)", data, 4.0);
    }

    @EventListener
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof PacketPlayInFlying) {

        } else if (event.getPacket() instanceof PacketPlayInUseEntity) {
            PacketPlayInUseEntity packet = event.getPacket();
            if (packet.a().equals(EnumEntityUseAction.ATTACK)) {

            }
        }
    }
}
