package no.kurosis.check.move;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.PacketPlayInEntityAction;
import net.minecraft.server.v1_8_R3.PacketPlayInEntityAction.EnumPlayerAction;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import no.kurosis.Kurosis;
import no.kurosis.check.Check;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

/**
 * A check that makes sure the client stops sprinting when not moving
 * If the STOP_SPRINT entity action packet is not sent and move updates are still sent, rage sprint is enabled
 *
 * @author aesthetical
 * @since 12/09/22
 */
public class MoveA extends Check {

    private boolean sprintState = false;

    public MoveA(PlayerData data) {
        super("MoveA (Rage Sprint)", data, 5.0);
    }

    @EventListener
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof PacketPlayInEntityAction) {
            PacketPlayInEntityAction packet = event.getPacket();
            if (packet.b().equals(EnumPlayerAction.START_SPRINTING)) {
                sprintState = true;
            } else if (packet.b().equals(EnumPlayerAction.STOP_SPRINTING)) {
                sprintState = false;
            }
        } else if (event.getPacket() instanceof PacketPlayInFlying) {

            PacketPlayInFlying packet = event.getPacket();
            if (!packet.g() && sprintState) {
                violate(1.0);
            }

        }
    }
}
