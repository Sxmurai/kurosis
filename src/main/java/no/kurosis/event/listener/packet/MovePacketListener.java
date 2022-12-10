package no.kurosis.event.listener.packet;

import me.bush.eventbus.annotation.EventListener;
import me.bush.eventbus.annotation.ListenerPriority;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import no.kurosis.Kurosis;
import no.kurosis.data.MoveData;
import no.kurosis.data.PlayerData;
import no.kurosis.event.events.net.PacketEvent;

public class MovePacketListener {

    @EventListener(priority = ListenerPriority.HIGHEST)
    public void onPacket(PacketEvent event) {
        PlayerData playerData = Kurosis.getInstance().getDataRegistry().get(event.getUuid());
        if (playerData == null) {
            return;
        }

        if (event.getPacket() instanceof PacketPlayInFlying) {
            PacketPlayInFlying packet = event.getPacket();

            MoveData moveData = playerData.moveData;

            // set the packet onGround location
            moveData.packetGround = packet.f(); // onGround

            moveData.lastX = moveData.x;
            moveData.lastY = moveData.y;
            moveData.lastZ = moveData.z;

            moveData.lastYaw = moveData.yaw;
            moveData.lastPitch = moveData.pitch;

            // hasPos
            if (packet.g()) {
                moveData.x = packet.a();
                moveData.y = packet.b();
                moveData.z = packet.c();
            }

            if (packet.h()) {
                moveData.yaw = packet.d();
                moveData.pitch = packet.e();
            }

            double xDiff = moveData.x - moveData.lastX;
            double zDiff = moveData.z - moveData.lastZ;

            moveData.hDist = Math.sqrt(xDiff * xDiff + zDiff * zDiff);
        }
    }

}
