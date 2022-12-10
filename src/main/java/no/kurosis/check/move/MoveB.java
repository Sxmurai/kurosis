package no.kurosis.check.move;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.*;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig.EnumPlayerDigType;
import no.kurosis.check.Check;
import no.kurosis.check.Experimental;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

/**
 * A check for checking if the player is moving too fast when blocking a sword
 * This check does not yet factor important vanilla gameplay mechanics yet, hence the @Experimental
 *
 * @author aesthetical
 * @since 12/10/22
 */
@Experimental
public class MoveB extends Check {

    /**
     * A max sword block move speed. Does not take into account vanilla game mechanics
     */
    private static final double ITEM_USE_MOVE_SPEED = 0.2327017833211753;

    private boolean blocking = false;

    public MoveB(PlayerData data) {
        super("MoveB (Sword No Slow)", data, 5.0);
    }

    @EventListener
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof PacketPlayInBlockPlace) {
            PacketPlayInBlockPlace packet = event.getPacket();

            // if the stack isnt null and we're placing with a sword, we're blocking obviously
            blocking = packet.getItemStack() != null && packet.getItemStack().getItem() instanceof ItemSword;
        } else if (event.getPacket() instanceof PacketPlayInBlockDig) {
            PacketPlayInBlockDig packet = event.getPacket();

            // no longer blocking our sword
            if (packet.c().equals(EnumPlayerDigType.RELEASE_USE_ITEM)) {
                blocking = false;
            }
        } else if (event.getPacket() instanceof PacketPlayInFlying) {

            // TODO: hurtTime, slime blocks, speed pots/slowness pots
            // TODO: this falses when in a 2x1 space and headhitting and quickly unblocking/blocking in vanilla. vDist checks?
            if (blocking && getData().moveData.hDist > ITEM_USE_MOVE_SPEED) {
                violate(1.0);
            }
        }
    }
}
