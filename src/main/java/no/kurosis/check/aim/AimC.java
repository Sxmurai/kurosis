package no.kurosis.check.aim;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayOutPosition;
import no.kurosis.Kurosis;
import no.kurosis.check.Check;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

/**
 * Simple check for silent rotations. If the client reports the same yaw and pitch in multiple packets
 * after eachother, it flags. The vanilla game checks and make sure
 *
 * @author aesthetical
 * @since 12/09/22
 */
public class AimC extends Check {

    private float lastYaw = 0.0f, lastPitch = 0.0f;
    private int exemptTicks = 10;

    public AimC(PlayerData data) {
        super("AimC (Constant Rotations)", data, 4.0);
    }

    @EventListener
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof PacketPlayInFlying) {
            PacketPlayInFlying packet = event.getPacket();

            if (--exemptTicks > 0) {
                return;
            }

            if (packet.h()) {

                float yaw = packet.d();
                float pitch = packet.e();

                if (lastYaw == 0.0f && lastPitch == 0.0f) {
                    lastYaw = yaw;
                    lastPitch = pitch;
                    return;
                }

                if (yaw == lastYaw && pitch == lastPitch) {
                    violate(2.0);
                }
            }
        } else if (event.getPacket() instanceof PacketPlayOutPosition) {
            exemptTicks = 10;
        }
    }
}
