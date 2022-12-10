package no.kurosis.check.killaura;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.*;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig.EnumPlayerDigType;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity.EnumEntityUseAction;
import no.kurosis.check.Check;
import no.kurosis.check.Experimental;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

/**
 * A check that checks for autoblock
 * The client cannot attack and block at the same time, so if the client has not yet unblocked
 * and is attacking, they are cheating.
 *
 * @author aesthetical
 * @since 12/10/22
 */
@Experimental
public class KillAuraA extends Check {

    private boolean blocking = false;

    public KillAuraA(PlayerData data) {
        super("KillAuraA (Constant AutoBlock)", data, 5.0);
    }

    @EventListener
    public void onPacket(PacketEvent event) {

        // TODO: this check falses a lot, but it does the job when the block is constant. add a delay?

        if (event.getPacket() instanceof PacketPlayInBlockPlace) {
            PacketPlayInBlockPlace packet = event.getPacket();
            if (packet.getItemStack() != null && packet.getItemStack().getItem() instanceof ItemSword) {
                blocking = true;
            }
        } else if (event.getPacket() instanceof PacketPlayInBlockDig) {
            PacketPlayInBlockDig packet = event.getPacket();
            if (packet.c().equals(EnumPlayerDigType.RELEASE_USE_ITEM)) {
                blocking = false;
            }
        } else if (event.getPacket() instanceof PacketPlayInUseEntity) {
            PacketPlayInUseEntity packet = event.getPacket();
            if (packet.a().equals(EnumEntityUseAction.ATTACK) && blocking) {
                violate(1.0);
            }
        }
    }

}
