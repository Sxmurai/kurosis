package no.kurosis.check.aim;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import no.kurosis.check.Check;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

public class AimB extends Check {

    private float lastYaw, lastPitch;
    private int exemptTicks = 0;

    public AimB(PlayerData data) {
        super("AimB (Snap Rotations)", data, 4.5);
    }

    @EventListener
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof PacketPlayInFlying) {

            // TODO

        } else if (event.getPacket() instanceof PacketPlayOutEntityTeleport) {

            // if the server lags the player back, allow them to be exempt for a bit
            // this can cause a bypass, but with other movement-related checks that shouldn't really matter
            exemptTicks = 10;
        }
    }
}
